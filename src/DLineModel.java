import java.awt.Point;
public class DLineModel extends DShapeModel {
	private Point p1;
	private Point p2;
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
	public DLineModel(int x, int y, int length){
		p1 = new Point(x,y);
		p2 = new Point(x,y+length);
		super.setXY(p1);
	}
	
}
