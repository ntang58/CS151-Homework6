import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class DOval extends DShape{
	private DOvalModel ovalData = new DOvalModel();
	public DOval(){
		super.attachModel(ovalData);
	}
	/**
	 * attaches a model instead of a zero-value default constructor
	 * @param d
	 */
	public DOval(DOvalModel d){
		ovalData=d;
		super.attachModel(ovalData);
	}
	public void draw(){
		Graphics g = new BufferedImage(ovalData.getWidth(), ovalData.getHeight(), BufferedImage.TYPE_INT_ARGB).getGraphics();
		draw(g);
	}
	private void draw(Graphics g) {
		g.drawOval(ovalData.getX(), ovalData.getY(), ovalData.getWidth(), ovalData.getHeight());
		g.setColor(ovalData.getColor());
		g.fillOval(ovalData.getX(), ovalData.getY(), ovalData.getWidth(), ovalData.getHeight());
	}
}
