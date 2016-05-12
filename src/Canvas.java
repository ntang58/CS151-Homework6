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
		addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e){
				//get x y part of mouse 
				Point p = new Point(e.getX(), e.getY());
				//System.out.println("Pointer at"+ p);
				DShape check = isSelectedShape(p);
				if(check!=null){
					selected= check;
					drawSelected();
				}
			}
		});
	}
	private DShape isSelectedShape(Point p){
		int numberAt = 0;
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
	private void drawSelected(){
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
	private void drawPrev(){
		Graphics2D g2 = (Graphics2D)this.getGraphics();
		prevSelected.setColor(prevColor);
		prevSelected.draw(g2, false);
	}
	/**
	 * draws all the components in the canvas
	 */
	public void paintComponents(){
		for(DShape ds : shapes){
			Graphics g = this.getGraphics();
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
		System.out.println(shapes);
	}
	public void removeDShape(DShapeModel d ){
		
	}
	
}
