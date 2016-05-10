import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class DLine extends DShape{
	private DLineModel lineData =new DLineModel();;
	public DLine(){
		super.attachModel(lineData);
	}
	@Override
	public void draw() {
		System.out.println("Draw line implementation");
	}
	private void draw(Graphics g){
		
	}
}
