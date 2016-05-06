import java.awt.Color;
import java.awt.Point;
import java.awt.Rectangle;
/**
 * stores a conceptual rectangular bound and color data of a DShape
 */
public class DShapeModel {
	private double x;
	private double y;
	private double height;
	private double width;
	private Color color;
	/**
	 * default contructor. Sets x,y coordinates to 0 and height and width to 0. Color is set to Gray
	 */
	public DShapeModel(){
		x =0;
		y=0;
		height = 0;
		width = 0;
		color = Color.GRAY;
	}
	/**
	 * sets coordinate position of this shape using a Point
	 * @param p the point to use
	 */
	public void setXY(Point p){
		this.x=(int) p.getX();
		this.y=(int)p.getY();
	}
	/**
	 * sets the bounds of DShape based on xy coordinates and height and width of a rectangle
	 * @param r the rectangle
	 */
	public void setBounds(Rectangle r){
		this.x = r.getX();
		this.y = r.getY();
		this.height = r.getHeight();
		this.width = r.getWidth();
	}
	public void setXY(int x, int y){
		this.x = x;
		this.y = y;
	}
	public void setX(double x){
		this.x = x;
	}
	public void setY(double y){
		this.y = y;
	}
	public void setHeight(double height){
		this.height = height;
	}
	public void setWidth(double width){
		this.width = width;
	}
	
	public void setColor(Color color){
		this.color = color;
	}
	//getters----------------------
	public double getHeight(){
		return height;
	}
	public double getWidth(){
		return width;
	}
	public double getX(){
		return x;
	}
	public double getY(){
		return y;
	}
	public Color getColor(){
		return color;
	}
}
