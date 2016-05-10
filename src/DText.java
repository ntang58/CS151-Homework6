import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class DText extends DShape{
	private DTextModel textData =new DTextModel();
	public DText(){
		super.attachModel(textData);
	}
	@Override
	public void draw() {
		System.out.println("DText override");
	}
	private void draw(Graphics g){
		
	}
	
}
