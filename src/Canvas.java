import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import javax.swing.JPanel;

import models.DLineModel;
import models.DOvalModel;
import models.DRectModel;
import models.DShapeModel;
import models.DTextModel;
import models.ModelListener;

public class Canvas extends JPanel implements MouseListener, MouseMotionListener, ModelListener{
	private ArrayList <DShape> shapes = new ArrayList<DShape>();
	private ArrayList<DShapeModel> shapeModels = new ArrayList<DShapeModel>();
	private ArrayList<Rectangle> sknobs = new ArrayList<Rectangle>();
	private ArrayList<Integer> codes = new ArrayList<Integer>();
	private DShape selected=null;
	private DShape prevSelected = null;
	private Color prevColor = null;
	private static final int PIXEL9 = 9;
	private Point previousM=null;
	private boolean move=false;
	private boolean resize=false;
	private Rectangle anchor, drag;
	private int movpt =0;
	private boolean clientMode=false;
	private Point anchorPoint, dragPoint;

	/**
	 * Extends JPanel. The layout of the Panel is following a FlowLayout
	 */
	public Canvas(){
		super(new FlowLayout());
		this.setPreferredSize(new Dimension(400, 400));
		this.setBackground(Color.WHITE);
		this.clear();
		addMouseListener(this);
		addMouseMotionListener(this);
	}
	
	public void clear() {
		shapes.clear();
		sknobs.clear();
		shapeModels.clear();
		selected = null;
		prevSelected=null;
		prevColor = null;
		anchor = null;
		drag = null;
		movpt=0;
		previousM = null;
	}
	public void addShapeModelArray(DShapeModel [] modelArr){
		for(DShapeModel ds : modelArr){
			addDShape(ds,modelArr.hashCode());
		}
		repaintComps();
	}
	/**
	 * for checking to see if a shape is selected or not on this canvas
	 * @return true if a shape is selected, false if not
	 */
	public boolean isSelected(){
		if(selected==null){
			return false;
		}
		else{
			return true;
		}
	}
	/**
	 * if the mouse pointer hits a shape, draw the shape
	 * @param e the mouse event to check
	 * @return true if the mouse hits a point
	 */
	private boolean selectShape(MouseEvent e){
		Point p = new Point(e.getX(), e.getY());
		DShape check = isSelectedShape(p);
		if(check!=null){
			selected= check;
			drawSelected();
			previousM= new Point(selected.getX()-e.getX(),selected.getY()-e.getY());
			return true;
		}
		return false;
	}
	/**
	 * changes the selected shape to this color
	 * @param c color to change shape to 
	 */
	public void setSelectedColor(Color c){
		prevColor = c;
		selected.setColor(c);
		selected.draw((Graphics2D)this.getGraphics(), false);
	}
	private DShape isSelectedShape(Point p){
		DShape lastselectedOne = null;
		for(DShape ds: shapes){
			Rectangle check = ds.getRectangle();
			if(check.contains(p)){//get the bounds as a rectangle object and use contains(x, y)
				if(check.contains(p) ){
					ds.setSelected(true);//sets it as true 
					lastselectedOne = ds;
				}
			}
		}
			
		return lastselectedOne;
	}
	/**
	 * draws knobs around a selected shape
	 */
	private void drawSelected(){
		if(selected!=null){
			if(prevSelected!=null){
				setSelectedFalse();
				drawPrev();
			}
			if(selected!=prevSelected){
				prevColor = selected.getColor();
			}
			prevSelected = selected;
			Graphics2D g2= (Graphics2D)this.getGraphics();
			Point [] knobs = selected.getKnobs();
			addKnobs(selected, knobs, g2);
			selected.draw(g2, true);
		}
	}
	private void addKnobs(DShape d, Point[] knobs, Graphics2D shapeGraphic){
		ArrayList<Rectangle> selectedK = new ArrayList<Rectangle>();
		int i=0;
		for(Point kP : knobs){
			shapeGraphic.setColor(Color.BLACK);
			shapeGraphic.fillRect((int)kP.getX(), (int)kP.getY(), PIXEL9, PIXEL9);
			selectedK.add(i, new Rectangle((int)kP.getX(),(int)kP.getY(), PIXEL9, PIXEL9));
			i++;
		}
		this.sknobs = selectedK;
		/*for(Rectangle r:sknobs)
			System.out.print(r.getBounds()+" ");
		System.out.println("");*/
	}
	/**
	 * checks to see if mouse point is contained in bounds of the 4 knobs
	 * @param e mouse point
	 * @return the position in knobs list, -1 if not a knob
	 */
	private int onSelectKnob(MouseEvent e){
		int i=0;
		if(sknobs.size()==4){
			for(Rectangle r : sknobs){
				if(r.contains(e.getPoint())){
					//System.out.println("SLECTED "+r.getBounds());
					return i;
				}
				i++;
			}
		}
		return -1;
	}
	/**
	 * Used when previously selected component needs to redraw itself
	 */
	private void drawPrev(){
		Graphics2D g2 = (Graphics2D)this.getGraphics();
		prevSelected.setColor(prevColor);
		prevSelected.draw(g2, false);
	}
	private void setSelectedFalse(){
		for(DShape ds: shapes)
			if(ds.getSelected()==true)
				ds.setSelected(false);
	}
	/**
	 * draws all the components based on graphics
	 */
	public void paintComponents(Graphics g){
		//super.paintComponent(g);
		for(DShape ds : shapes){
			//g = this.getGraphics();
			Graphics2D g2 = (Graphics2D) g;
			ds.draw(g2, false);
		}
	}
	public void repaintComps(){
		Graphics2D g2 = (Graphics2D)this.getGraphics();
		g2.clearRect(this.getX(), this.getY(), this.getWidth(), this.getHeight());
		g2.setColor(Color.WHITE);
		g2.fillRect(0, 0, this.getWidth(), this.getHeight());
		for(int i = shapes.size()-1; i>=0;i--){
			DShape ds = shapes.get(i);
			if(ds.getSelected()==true){
				//repaint(selected.getX(),selected.getY(),selected.getWidth(), selected.getHeight());
				drawSelected();
			}
			else{
				ds.draw(g2, false);
			}
		}
	}
	/**
	 * creates and add a shape based on the DShapeModel passed in
	 * @param d the DShapeModel to add
	 */
	public void addDShape(DShapeModel d,int code){
		DShape thisD = null;
		if(d instanceof DOvalModel){
			thisD = new DOval((DOvalModel)d);//already checked if it is an instance, can type cast
		}
		if(d instanceof DRectModel){
			thisD=new DRect((DRectModel)d);
		}
		if(d instanceof DLineModel){
			thisD=new DLine((DLineModel)d);
		}
		if(d instanceof DTextModel){
			thisD = new DText((DTextModel)d);
		}
		if(clientMode==true){
			codes.add(code);
			//System.out.println(codes);
		}
		d.addModelListener(this);
		thisD.setXY(new Point(d.getX(), d.getY()));
		thisD.setRectangle(new Rectangle(d.getX(), d.getY(), d.getWidth(), d.getHeight()));
		shapeModels.add(d);
		shapes.add(thisD);
		//System.out.println(shapes);
		//System.out.println(shapes);
	}
	//used if client, returns hashcode of selected shape
	public int getSelectedCode(){
		if(selected!=null){
			return selected.hashCode();
		}
		return -1;
	}
	public DShapeModel getSelectedModel(){
		DShapeModel retModel = null;
		for(DShapeModel ds : shapeModels){
			if(ds.getSelected()){
				retModel = ds;
				break;
			}
		}
		return retModel;
	}
	//used if client
	public void changeModel(DShapeModel cM, Integer i){
		int changeIndex = 0;
		for(Integer code:codes){
			if(code.equals(i)){
				System.out.println("change");
				System.out.println(shapeModels.get(changeIndex));
				if(shapeModels.get(changeIndex) instanceof DTextModel){
					DTextModel text = (DTextModel) shapeModels.get(changeIndex);
					text.mimic((DTextModel)cM);
					shapeModels.set(changeIndex, text);
				}
				else{
					shapeModels.get(changeIndex).mimic(cM);//mimic the passed in model, a mimic will notify its listeners of a change
				}
				break;
			}
			changeIndex++;
		}
	}
	public void removeSelected(){
		if(selected!=null){
			//System.out.println("before: "+shapes);
			shapes.remove(selected);
			selected.getModel().deleted();
			shapeModels.remove(selected.getModel());
			selected=null;
			prevSelected = null;
			repaintComps();
			//System.out.println("after "+shapes);
		}
	}
	//used if client
	public void remove(DShapeModel ds, Integer i){
		int deleteIndex = 0;
		//System.out.println(shapeModels);
		//System.out.println(codes);
		for(Integer code : codes){
			if(code.equals(i)){
				//System.out.println("True "+co);
				/*
				 *if the code of the passed in shape matches the list of codes in this canvas,
				 *get the shape to remove + the view to remove and remove the model, view and 
				 *code from list.
				 */
				DShapeModel toRemove = shapeModels.get(deleteIndex);
				DShape toRemoveView = shapes.get(deleteIndex);
				if(shapeModels.remove(toRemove)&&shapes.remove(toRemoveView)&&codes.remove(i)){
					//System.out.println("removed");
					break;
				}
				else{
					System.out.println("not removed");
				}
			}
			deleteIndex++;
		}
		repaintComps();
		/*if(shapeModels.remove(i)==false&&shapes.remove(i)==false){
			System.out.println("cannot remove");
		}*/
	}
	//used if client
	public void moveFront(DShapeModel ds, Integer i){
		//"moves object to back of the list" (graphically, the shape will appear in front of shapes)
		DShape toShapeBack = null;
		DShapeModel toModelBack = null;
		int deleteIndex=0;
		for(Integer code :codes){
			if(code.equals(i)){
				toShapeBack = shapes.get(deleteIndex);
				toModelBack =shapeModels.get(deleteIndex); 
				//System.out.println("OUT");
				break;
			}
			deleteIndex++;
		}
		if(shapeModels.remove(toModelBack)&&shapes.remove(toShapeBack)&&codes.remove(i)){
			shapes.add(toShapeBack);
			shapeModels.add(toModelBack);
			codes.add(i);
			//this.repaintComps(this.getGraphics());
			//System.out.println("Move to Back");
		}
		else{
			//System.out.println(shapeModels +" "+ codes+" "+ shapes);
			System.out.println("Could not move to Front: " +ds);
		}
	}
	public void moveSelectedFront(){
		if(selected!=null){
			DShapeModel tempSelectedModel=this.getSelectedModel();
			shapeModels.remove(tempSelectedModel);
			shapeModels.add(0,tempSelectedModel);
			shapes.remove(selected);
			shapes.add(0,selected);
			repaintComps();
		}
	}
	//used if client
	public void moveBack(DShapeModel ds, Integer i){
		//"moves object to the front of the list" (graphically, the shape will appear behind shapes)
		DShape toShapeFront = null;
		DShapeModel toModelFront = null;
		int deleteIndex=0;
		for(Integer code :codes){
			if(code.equals(i)){
				toShapeFront = shapes.get(deleteIndex);
				toModelFront =shapeModels.get(deleteIndex); 
				//System.out.println("OUT");
				break;
			}
			deleteIndex++;
		}
		if(shapeModels.remove(toModelFront)&&shapes.remove(toShapeFront)&&codes.remove(i)){
			shapes.add(0, toShapeFront);
			shapeModels.add(0,toModelFront);
			codes.add(0,i);
			//this.repaintComps(this.getGraphics());
			//System.out.println("Move to front");
		}
		else{
			//System.out.println(shapeModels +" "+ codes+" "+ shapes);
			System.out.println("Could not move to back: " +ds);
		}
	}
	public void moveSelectedBack(){
		if(selected!=null){
			shapes.add(selected);
			shapes.remove(selected);
			repaintComps();
		}
	}
	public void setText(String t){
		if(selected!=null&&selected instanceof DText){
			selected = (DText)selected;
			selected.setText(t);
			repaintComps();
		}
	}
	public String getText(){
		if(selected!=null&&selected instanceof DText){
			selected = (DText)selected;
			return selected.getText();
		}
		return"";
	}
	public void setFont(String t){
		if(selected!=null&&selected instanceof DText){
			selected = (DText)selected;
			selected.setFont(t);
			repaintComps();
		}
	}
	public boolean selectedIsTextShape(){
		if(selected!=null&& selected instanceof DText)
			return true;
		return false;
	}
	public ArrayList<DShapeModel> getShapeModels(){
		if(shapeModels.size()!=0){
			return shapeModels;
		}
		else{
			return null;
		}
	}
	public BufferedImage getBuffImage(){
	      BufferedImage image = (BufferedImage) createImage(getWidth(), getHeight());
	      Graphics g = image.getGraphics();
	      paintComponents(g);
	      return image;
	}
	public void setClientModeOn(){
		clientMode=true;
	}
	/*
	 * Used online resource : "http://www.java2s.com/Code/Java/Event/MoveShapewithmouse.htm" for moving objects with mouse
	 */
	//MouseMotionListner
	@Override
	public void mouseDragged(MouseEvent arg0) {
		if(selected!=null &&super.isEnabled()){
			if(move==true&&resize==false){
				//code changd here by elias in order to get DLine working
				if(selected instanceof DLine){
					((DLine) selected).moveP1((int)arg0.getX()+(int)previousM.getX(),(int)arg0.getY()+(int)previousM.getY());
					((DLine) selected).moveP2((int)arg0.getX()+(int)previousM.getX(),(int)arg0.getY()+(int)previousM.getY() + 20);
					selected.setXY(new Point((int)arg0.getX()+(int)previousM.getX(),(int)arg0.getY()+(int)previousM.getY()));
				} 
				else {
					selected.setXY(new Point((int)arg0.getX()+(int)previousM.getX(),(int)arg0.getY()+(int)previousM.getY()));
				}
				repaintComps();
			}
			if(resize==true&&move==false){
				boolean line = false;
				if(selected instanceof DLine){
					line = true;
				}
				resizeSelected(arg0,line);
				repaintComps();
			}
		}
	}
	private void resizeSelected(MouseEvent arg0, boolean line){
		if(line){
			dragPoint.setLocation(arg0.getX(), arg0.getY());
			Rectangle ancRect = new Rectangle(anchorPoint);
			ancRect.add(dragPoint);
			selected.setRectangle(ancRect);
			((DLine)selected).moveP1((int)dragPoint.getX(),(int)dragPoint.getY());
			((DLine)selected).moveP2((int)anchorPoint.getX(),(int)dragPoint.getY());
		}
		else{
			drag.setLocation(arg0.getX(), arg0.getY());
			Rectangle ancRect = new Rectangle(anchor);
			ancRect.add(drag);
			selected.setRectangle(ancRect);
		}
	}
	@Override
	public void mouseMoved(MouseEvent arg0) {
	}
	//MouseListener
	@Override
	public void mouseClicked(MouseEvent e) {
	}
	@Override
	public void mouseReleased(MouseEvent e) {
	
	}
	@Override
	public void mouseEntered(MouseEvent e) {
	}

	@Override
	public void mouseExited(MouseEvent e) {
	}

	@Override
	public void mousePressed(MouseEvent e) {
		if(super.isEnabled()&&selectShape(e)){
			move=true;
			resize=false;
			//repaintComps(this.getGraphics());
		}
		else if(selected!=null &&super.isEnabled()){
			move=false; resize=false;
			selected.setSelected(false);
			repaintComps();
			//System.out.println("not selected");
		}
		if(selected!=null && sknobs.size()==4&&super.isEnabled()){
			movpt = onSelectKnob(e);
			int opPt = 0;
			if(movpt!=-1){//messy, I know
				resize=true;
				move = false;
				if(movpt ==0)
				{	opPt = 3;}
				if(movpt ==1)
				{	opPt = 2;}
				if(movpt==2)
				{	opPt = 1;}
				if(movpt==3)
				{	opPt = 0;}
				anchor = sknobs.get(opPt);
				drag = sknobs.get(movpt);
			}
		}
		if(selected!=null && sknobs.size()==2&&super.isEnabled()){
			movpt = onSelectKnob(e);
			int opPt = 0;
			resize=true;
			move=false;
			if(movpt!=-1){
				if(movpt == 0){
					opPt = 1;
				}
				if(movpt==1){
					opPt=0;
				}
				anchor = sknobs.get(opPt);
				drag = sknobs.get(movpt);
			}
		}
	}
	@Override
	public void modelChanged(DShapeModel model) {
		if(selected!=null){
			selected.draw(this.getGraphics(), true);
		}
		if(clientMode){
			
		}
	}
}
