import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class DRect extends DShape{
	private DRectModel rectData = new DRectModel();
	public DRect(){
		super.attachModel(rectData);
	}
	public DRect(DRectModel dr){
		rectData = dr;
		super.attachModel(rectData);
	}
	public void draw(){
		Graphics g = new BufferedImage(rectData.getWidth(), rectData.getHeight(),BufferedImage.TYPE_INT_ARGB).getGraphics();
		draw(g);
	}
	private void draw(Graphics g){
		g.drawRect(rectData.getX(), rectData.getY(), rectData.getWidth(), rectData.getHeight());
		g.setColor(rectData.getColor());
		g.fillRect(rectData.getX(), rectData.getY(), rectData.getWidth(), rectData.getHeight());
	}
}
