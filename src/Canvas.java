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
import java.util.ArrayList;

import javax.swing.JPanel;


public class Canvas extends JPanel{
	private ArrayList <DShape> shapes = new ArrayList<DShape>();
	private DShape selected;
	private DShape prevSelected = null;
	private Color prevColor = null;
	/**
	 * Extends JPanel. The layout of the Panel is following a FlowLayout
	 */
	public Canvas(){
		super(new FlowLayout());
		this.setPreferredSize(new Dimension(400, 400));
		this.setBackground(Color.WHITE);
		addMouseListener(new MouseAdapter(){
			Point p = null;
			public void mouseClicked(MouseEvent e){
				p = new Point(e.getX(), e.getY());
				//System.out.println("Pointer at"+ p);
				DShape check = isSelectedShape(p);
				if(check!=null){
					selected= check;
					drawSelected();
				}
			}
			public void mouseReleased(MouseEvent e){
				if(selected!=null)
					drawSelected();
			}
		});
		/*
		 * moves the selected shape
		 */
		addMouseMotionListener(new MouseAdapter() {
			Point end=null;
			
			public void mouseDragged(MouseEvent e){//drag object
				end = e.getPoint();
				if(selected!=null){
					selected.setXY(end);
					drawSelected();
				}
			}
		});

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
					lastselectedOne = ds;
				}
			}
		}
			
		return lastselectedOne;
	}
	/**
	 * draws the current selected shape as black
	 */
	private void drawSelected(){
		if(selected!=null){
			if(prevSelected!=null){
				drawPrev();
			}
			if(selected!=prevSelected){
				prevColor = selected.getColor();
			}
			prevSelected = selected;
			Graphics2D g = (Graphics2D)this.getGraphics();
			selected.setColor(Color.BLACK);
			selected.draw(g, true);
		}
	}
	/**
	 * clears the selected component from the canvas
	 */
	private void clearSelected(){
		selected.setColor(Color.WHITE);
		selected.draw((Graphics2D)this.getGraphics(), false);
	}
	/**
	 * Used when previously selected component needs to redraw itself
	 */
	private void drawPrev(){
		Graphics2D g2 = (Graphics2D)this.getGraphics();
		prevSelected.setColor(prevColor);
		prevSelected.draw(g2, false);
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
}
	

