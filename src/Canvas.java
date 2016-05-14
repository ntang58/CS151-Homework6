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
	private DShape selected;
	private DShape prevSelected = null;
	private Color prevColor = null;
	private static final int PIXEL9 = 9;
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
	private void selectShape(MouseEvent e){
		Point p = new Point(e.getX(), e.getY());
		//System.out.println("Pointer at"+ p);
		DShape check = isSelectedShape(p);
		if(check!=null){
			selected= check;
			drawSelected(this.getGraphics());
		}
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
	 * draws the current selected shape as black
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
			//selected.setColor(Color.BLACK);//add knobs to the shape instead
			Point [] knobs = selected.getKnobs();
			addKnobs(selected, knobs, g2);
			selected.draw(g2, true);
		}
	}
	private void addKnobs(DShape d, Point[] knobs, Graphics2D shapeGraphic){
		for(Point kP : knobs){
			shapeGraphic.setColor(Color.BLACK);
			shapeGraphic.fillRect((int)kP.getX(), (int)kP.getY(), PIXEL9, PIXEL9);
		}
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
	public void removeDShape(DShapeModel d ){
		
	}
	/**
	 * Used online resource : "http://www.java2s.com/Code/Java/Event/MoveShapewithmouse.htm" for moving objects with mouse
	 */
	//MouseMotionListner
	@Override
	public void mouseDragged(MouseEvent arg0) {
		Point mPoint=null;
		mPoint = arg0.getPoint();
		if(selected!=null){
			selected.setXY(mPoint);
		}
		repaintComps(this.getGraphics());
	}
	@Override
	public void mouseMoved(MouseEvent arg0) {
	}
	//MouseListener
	@Override
	public void mouseClicked(MouseEvent e) {
		selectShape(e);
		repaintComps(this.getGraphics());
	}
	@Override
	public void mouseReleased(MouseEvent e) {
		if(selected!=null)
			drawSelected(this.getGraphics());
	}
	@Override
	public void mouseEntered(MouseEvent e) {
	}

	@Override
	public void mouseExited(MouseEvent e) {
	}

	@Override
	public void mousePressed(MouseEvent e) {
		selectShape(e);
		repaintComps(this.getGraphics());
	}
}
	

