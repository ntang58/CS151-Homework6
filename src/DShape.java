import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;

import models.DShapeModel;
public abstract class DShape{
	private DShapeModel theModel = null;
	protected void attachModel(DShapeModel ds){
		theModel = ds;
	}
	/**
	 * draws the specific DShape
	 */
	abstract public void draw(Graphics g, boolean selected);
	/**
	 * sets the XY coordinate of the DShape
	 * @param p
	 */
	public void setXY(Point p) {
		theModel.setXY(p);
	}
	public void setX(int x){
		theModel.setX(x);
	}
	public void setY(int y){
		theModel.setY(y);
	}
	public void setWidth(int width){
		theModel.setWidth(width);
	}
	public void setHeight(int height){
		theModel.setHeight(height);
	}
	public void setColor(Color c){
		theModel.setColor(c);
	}
	public Color getColor(){
		return theModel.getColor();
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
	public int getX(){
		return theModel.getX();
	}
	public int getY(){
		return theModel.getY();
	}
	public int getWidth(){
		return theModel.getWidth();
	}
	public int getHeight(){
		return theModel.getHeight();
	}
	public Point[] getKnobs(){
		Rectangle r = this.getRectangle();
		Point bLeft = new Point((int)r.getMinX()-4, (int)r.getMaxY()-4);
		Point bRight = new Point((int)r.getMaxX()-4, (int)r.getMaxY()-4);
		Point tLeft = new Point((int)r.getMinX()-4,(int)r.getMinY()-4);
		Point tRight = new Point((int)r.getMaxX()-4,(int)r.getMinY()-4);
		Point [] knobs = {bRight, tRight, bLeft, tLeft};
		return knobs;
	}
	public String toString(){
		return getRectangle().toString() + theModel.getColor() + "selected "+theModel.getSelected();
	}
	public void setSelected(boolean flag){
		theModel.setSelected(flag);
	}
	public boolean getSelected(){
		return theModel.getSelected();
	}
	public DShapeModel getModel(){
		return this.theModel;
	}
	public abstract void setText(String t);
	public abstract String getText();
	public abstract void setFont(String t);
}
