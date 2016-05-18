import java.awt.Color;
public class DTextModel extends DShapeModel {
	String text;
	String font;
	public DTextModel(){
		super();
	}
	public DTextModel(int x, int y, int width, int height){
		super.setX(x);
		super.setY(y);
		super.setWidth(width);
		super.setHeight(height);
		super.setColor(Color.GRAY);
	}
	public DTextModel(String text, String font){
		this.text = text;
		this.font = font;
	}
	public void setText(String text){
		this.text = text;
	}
	public void setFont(String font){
		this.font = font;
	}
	public String getFont(){
		return this.font;
	}
	public String toString(){
		return super.toString() +" "+text+" "+font;
	}
}