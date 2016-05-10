import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.util.ArrayList;

import javax.swing.JPanel;


public class Canvas extends JPanel{
	private ArrayList <DShape> shapes = new ArrayList<DShape>();
	/**
	 * Extends JPanel. The layout of the Panel is following a FlowLayout
	 */
	public Canvas(){
		super(new FlowLayout());
		this.setPreferredSize(new Dimension(400, 400));
		this.setBackground(Color.WHITE);
		System.out.println(this.getBounds());
	}
	/**
	 * draws all the components in the canvas
	 */
	public void paintComponents(){
		for(DShape ds : shapes){
			//System.out.println(ds.getClass().getName()+" "+ds);
			Graphics g = this.getGraphics();
			Graphics2D g2 = (Graphics2D) g;
			ds.draw(g2);
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
	}
	public void removeDShape(DShapeModel d ){
		
	}
	
}
