import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;


public class DRect implements DShape{
	DRectModel rectData = new DRectModel();
	
	public void draw() {
		this.draw(g);
	}
	private void draw(Graphics g){
		
	}
	@Override
	public void setXY(Point p) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void setRectangle(Rectangle r) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public Rectangle getRectangle() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public Point getPoint() {
		// TODO Auto-generated method stub
		return null;
	}
}
