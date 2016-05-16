import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;

import javax.swing.JPanel;


public class Canvas extends JPanel implements MouseListener, MouseMotionListener {
	private ArrayList <DShape> shapes = new ArrayList<DShape>();
	private Rectangle [] sknobs = new Rectangle[4];
	private DShape selected;
	private DShape prevSelected = null;
	private Color prevColor = null;
	private static final int PIXEL9 = 9;
	private Point previousM=null;
	private boolean move;
	private boolean resize;
	private Point anchor, drag;
	/**
	 * Extends JPanel. The layout of the Panel is following a FlowLayout
	 */
	public Canvas(){
		super(new FlowLayout());
		this.setPreferredSize(new Dimension(400, 400));
		this.setBackground(Color.WHITE);
		addMouseListener(this);
		addMouseMotionListener(this);
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
			drawSelected(this.getGraphics());
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
	private void drawSelected(Graphics g){
		if(selected!=null){
			if(prevSelected!=null){
				setSelectedFalse();
				drawPrev(g);
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
		Rectangle [] selectedK = new Rectangle[4];
		int i=0;
		for(Point kP : knobs){
			shapeGraphic.setColor(Color.BLACK);
			shapeGraphic.fillRect((int)kP.getX(), (int)kP.getY(), PIXEL9, PIXEL9);
			selectedK[i]= new Rectangle((int)kP.getX(),(int)kP.getY(), PIXEL9, PIXEL9);
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
		for(Rectangle r : sknobs){
			if(r.contains(e.getPoint())){
				System.out.println("SLECTED "+r.getBounds());
				return i;
			}
			i++;
		}
		return -1;
	}
	/**
	 * Used when previously selected component needs to redraw itself
	 */
	private void drawPrev(Graphics g){
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
	 * draws all the components in the canvas
	 */
	public void paintComponents(Graphics g){
		for(DShape ds : shapes){
			g = this.getGraphics();
			Graphics2D g2 = (Graphics2D) g;
			ds.draw(g2, false);
		}
	}
	private void repaintComps(Graphics g){
		Graphics2D g2 = (Graphics2D)g;
		g2.setPaint(Color.WHITE);
		g2.fillRect(0, 0, this.getWidth(), this.getHeight());
		for(DShape ds: shapes){
			if(ds.getSelected()==true){
				drawSelected(g2);
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
	public void addDShape(DShapeModel d){
		DShape thisD = null;
		if(d instanceof DOvalModel){
			thisD = new DOval((DOvalModel)d);//already checked if it is an instance, can type cast
		}
		if(d instanceof DRectModel){
			thisD=new DRect((DRectModel)d);
		}
		if(d instanceof DLineModel){
			thisD=new DLine();
		}
		if(d instanceof DTextModel){
			thisD = new DText();
		}
		thisD.setXY(new Point(d.getX(), d.getY()));
		thisD.setRectangle(new Rectangle(d.getX(), d.getY(), d.getWidth(), d.getHeight()));
		shapes.add(thisD);
		//System.out.println(shapes);
	}
	public void removeSelected(){
		if(selected!=null){
			
		}
	}
	/*
	 * Used online resource : "http://www.java2s.com/Code/Java/Event/MoveShapewithmouse.htm" for moving objects with mouse
	 */
	//MouseMotionListner
	@Override
	public void mouseDragged(MouseEvent arg0) {
		if(selected!=null){
			if(move==true&&resize==false){
				selected.setXY(new Point((int)arg0.getX()+(int)previousM.getX(),(int)arg0.getY()+(int)previousM.getY()));
			}
			if(resize==true&&move==false){
				drag.setLocation(arg0.getX(), arg0.getY());
				int width = Math.abs((int)(anchor.getX() - drag.getX()));
				int height = Math.abs((int)(anchor.getY()-drag.getY()));
				selected.setWidth(width);
				selected.setHeight(height);
				if(selected.getPoint()!=anchor){
				}
			}
		}
		drawSelected(this.getGraphics());
		repaintComps(this.getGraphics());
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
		if(selectShape(e)){
			move=true;
			resize=false;
		}
		if(selected!=null){
			int movPt = onSelectKnob(e);
			int opPt = 0;
			if(movPt!=-1){//messy, I know
				resize=true;
				move = false;
				if(movPt ==0)
				{	opPt = 3;}
				if(movPt ==1)
				{	opPt = 2;}
				if(movPt==2)
				{	opPt = 1;}
				if(movPt==3)
				{	opPt = 0;}
				anchor = sknobs[opPt].getLocation();
				drag = sknobs[movPt].getLocation();
				//selected.setWidth((int)drag.getX()-e.getX()+selected.getWidth());
			}
		}
		repaintComps(this.getGraphics());
	}
}
	

