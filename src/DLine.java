import java.awt.Graphics;
import java.awt.Point;

public class DLine extends DShape{
	private DLineModel lineData =new DLineModel();
	public DLine(DLineModel pointD){
		lineData=pointD;
		super.attachModel(pointD);
	}
	public Point[] getKnobs(){
		Point t1 = new Point((int)lineData.getP1().getX()-4, (int)lineData.getP2().getY()-4);
		Point t2 = new Point((int)lineData.getP2().getX()-4, (int)lineData.getP2().getY()-4);
		Point [] knobs = {t1, t2};
		return knobs;
	}
	@Override
	public void draw(Graphics g, boolean selected) {
		super.setSelected(selected);		
		g.drawLine((int)lineData.getP1().getX(), (int)lineData.getP1().getY(), (int)lineData.getP2().getX(), (int)lineData.getP2().getY());
	}
}
