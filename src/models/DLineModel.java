package models;
import java.awt.Point;
import java.awt.Rectangle;
public class DLineModel extends DShapeModel {
	private Point p1;
	private Point p2;
	private Rectangle lRectangle;
	public DLineModel(){
		p1 = new Point(5,5);
		p2 = new Point(5,10);
	}
	public Point getP1(){
		return p1;
	}
	public Point getP2(){
		return p2;
	}
	public DLineModel(int x1, int y1, int x2, int y2){
		p1 = new Point(x1,y1);
		p2 = new Point(x2,y2);
		lRectangle = new Rectangle(p1);
		lRectangle.height=Math.abs((int)(p2.getY()-p1.getY()));
		lRectangle.width = 10;
		super.setXY(p1);
		super.setBounds(lRectangle);
	}
	public void setP1(Point p1){
		this.p1 = p1;
		lRectangle = new Rectangle(p1);
		super.setBounds(lRectangle);
	}
	public void setP2(Point p2){
		this.p2 = p2;
		lRectangle.add(p2);
	}
	//used these when resizing the line, elias
	public void moveP1(int x,int y){
		p1.setLocation(x,y);
	}
	
	public void moveP2(int x, int y){
		p2.setLocation(x,y);
	}

	public Rectangle getRectangle(){
		return lRectangle;
	}
}