import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;


public class DOval implements DShape{
	DOvalModel ovalData = new DOvalModel();
	
	public void draw() {
		this.draw(g);
	}
	private void draw(Graphics g){
		
	}
	@Override
	public void setXY(Point p) {
		ovalData.setXY(p);
		
	}
	@Override
	public void setRectangle(Rectangle r) {
		ovalData.setBounds(r);
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
