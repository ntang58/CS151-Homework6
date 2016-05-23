import java.awt.Graphics;
public class DRect extends DShape{
	private DRectModel rectData = new DRectModel();
	public DRect(){
		super.attachModel(rectData);
	}
	public DRect(DRectModel dr){
		rectData = dr;
		super.attachModel(rectData);
	}
	
	public void draw(Graphics g, boolean selected){
		super.setSelected(selected);
		g.setClip(super.getRectangle());
		g.drawRect(rectData.getX(), rectData.getY(), rectData.getWidth(), rectData.getHeight());
		g.setColor(rectData.getColor());
		g.fillRect(rectData.getX(), rectData.getY(), rectData.getWidth(), rectData.getHeight());
	}
	@Override
	public void setText(String t) {
	}
	@Override
	public String getText() {
		return "";
	}
	@Override
	public void setFont(String t) {
		
	}
}
