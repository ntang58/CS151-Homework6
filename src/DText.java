import java.awt.Font;
import java.awt.Graphics;
import java.awt.Rectangle;

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
		g.setFont(new Font(textData.getFont(), (int)superRe.getWidth(), (int) superRe.getHeight()));
		g.drawString(textData.getText(), (int)superRe.getMinX(),(int)superRe.getMaxY());
	}
	public String getText() {
		return textData.getText();
	}
	@Override
	public void setFont(String t) {
		textData.setFont(t);
	}
}
