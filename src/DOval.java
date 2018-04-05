import java.awt.Graphics;

import models.DOvalModel;

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

	public void draw(Graphics g, boolean selected) {
		super.setSelected(selected);
		g.setClip(super.getRectangle());
		g.drawOval(ovalData.getX(), ovalData.getY(), ovalData.getWidth(), ovalData.getHeight());
		g.setColor(ovalData.getColor());
		g.fillOval(ovalData.getX(), ovalData.getY(), ovalData.getWidth(), ovalData.getHeight());
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
		// TODO Auto-generated method stub
		
	}
}
