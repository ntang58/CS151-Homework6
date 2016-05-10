import java.awt.Color;
import java.awt.Point;
import java.awt.Rectangle;
/**
 * stores a conceptual rectangular bound and color data of a DShape
 */
public class DShapeModel {
	private int x;
	private int y;
	private int height;
	private int width;
	private Color color;
	/**
	 * default constructor. Sets x,y coordinates to 0 and height and width to 0. Color is set to Gray
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
		this.x = (int)r.getX();
		this.y = (int)r.getY();
		this.height = (int)r.getHeight();
		this.width = (int)r.getWidth();
	}
	public void setXY(int x, int y){
		this.x = x;
		this.y = y;
	}
	public void setX(int x){
		this.x = x;
	}
	public void setY(int y){
		this.y = y;
	}
	public void setHeight(int height){
		this.height = height;
	}
	public void setWidth(int width){
		this.width = width;
	}
	
	public void setColor(Color color){
		this.color = color;
	}
	//getters----------------------
	public int getHeight(){
		return height;
	}
	public int getWidth(){
		return width;
	}
	public int getX(){
		return x;
	}
	public int getY(){
		return y;
	}
	public Color getColor(){
		return color;
	}
}
