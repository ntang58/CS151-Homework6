import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.Shape;

public class DText extends DShape{
	private DTextModel textData =new DTextModel();
	public DText(){
		super.attachModel(textData);
	}
	public DText(DTextModel tData){
		textData=tData;
		textData.setText((tData).getText());
		super.attachModel(tData);
	}
	public void setText(String t){
		textData.setText(t);
	}
	@Override
	public void draw(Graphics g, boolean selected){
		super.setSelected(selected);
		Rectangle superRe = super.getRectangle();
		g.setColor(textData.getColor());
		double sizeFont = 1.0;
		Font currentF = new Font(textData.getFont(), (int)sizeFont, (int) sizeFont);
		g.setFont(currentF);
		FontMetrics fMetrics = g.getFontMetrics();
		double currentSize=fMetrics.getHeight();
		Double compareSize = superRe.getHeight();
		//System.out.println(compareSize);
		while(currentSize<compareSize){
			currentSize = currentSize + (currentSize * .1);
		}
		//System.out.println(currentSize);
		currentF = new Font(textData.getFont(),(int)currentSize,(int)currentSize);
		g.setFont(currentF);
		g.setClip(superRe);
		Shape clip = g.getClip();
		g.setClip(clip.getBounds().createIntersection(superRe.getBounds()));
		g.drawString(textData.getText(), (int)superRe.getMinX(),(int)superRe.getMaxY());
		g.setClip(clip);
	}
	public String getText() {
		return textData.getText();
	}
	@Override
	public void setFont(String t) {
		textData.setFont(t);
	}
}
