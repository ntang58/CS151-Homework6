import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;
public abstract class DShape{
	DShapeModel theModel = null;
	public void attachModel(DShapeModel ds){
		theModel = ds;
	}
	/**
	 * draws the specific DShape
	 */
	abstract public void draw(Graphics g);
	/**
	 * sets the XY coordinate of the DShape
	 * @param p
	 */
	public void setXY(Point p) {
		theModel.setXY(p);
	}
	/**
	 * sets the rectangular bound of the DShape
	 * @param r
	 */
	public void setRectangle(Rectangle r) {
		theModel.setBounds(r);
	}
	/**
	 * @return a rectangle object that represents the bounds of this DShape
	 */
	public Rectangle getRectangle() {
		 return new Rectangle(theModel.getX(),theModel.getY(),theModel.getWidth(), theModel.getHeight());
	}
	/**
	 * @return a Point object that represents the Point of this DShape
	 */
	public Point getPoint() {
		return new Point(theModel.getX(), theModel.getY());
	}
	public String toString(){
		return getRectangle().toString() + theModel.getColor();
	}
}
