import java.awt.Point;
import java.awt.Rectangle;
public interface DShape {
	/**
	 * draws the specific DShape
	 */
	public void draw();
	/**
	 * sets the XY coordinate of the DShape
	 * @param p
	 */
	public void setXY(Point p);
	/**
	 * sets the rectangular bound of the DShape
	 * @param r
	 */
	public void setRectangle(Rectangle r);
	/**
	 * @return a rectangle object that represents the bounds of this DShape
	 */
	public Rectangle getRectangle();
	/**
	 * @return a Point object that represents the Point of this DShape
	 */
	public Point getPoint():
		
}
