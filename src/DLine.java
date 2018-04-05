import java.awt.Graphics;
import java.awt.Point;

import models.DLineModel;

public class DLine extends DShape{
	private DLineModel lineData =new DLineModel();
	public DLine(DLineModel pointD){
		lineData=pointD;
		super.attachModel(pointD);
	}
	public Point[] getKnobs(){	
		Point t1 = new Point((int)lineData.getP1().getX(), (int)lineData.getP1().getY());
		Point t2 = new Point((int)lineData.getP2().getX(), (int)lineData.getP2().getY());
		Point [] knobs = {t1, t2};
		return knobs;
	}
	@Override
	public void draw(Graphics g, boolean selected) {
		super.setSelected(selected);
		g.setColor(lineData.getColor());
		g.drawLine((int)lineData.getP1().getX(), (int)lineData.getP1().getY(), (int)lineData.getP2().getX(), (int)lineData.getP2().getY());
	}
	@Override
	public void setText(String t) {
	}
	@Override
	public String getText() {
		return "";
	}
	public void moveP1(int x, int y){
		lineData.moveP1(x,y);
	}
	public void moveP2(int x, int y){
		lineData.moveP2(x,y);
	}
	@Override
	public void setFont(String t) {
		// TODO Auto-generated method stub
	}
}
