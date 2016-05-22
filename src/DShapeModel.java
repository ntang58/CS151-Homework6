import java.awt.Color;
import java.awt.Point;
import java.awt.Rectangle;
import java.util.ArrayList;
/**
 * stores a conceptual rectangular bound and color data of a DShape
 */
public class DShapeModel {
	private ArrayList<ModelListener>mListeners = new ArrayList<ModelListener>();
	private int x;
	private int y;
	private int height;
	private int width;
	private Color color;
	private boolean selected;
	/**
	 * default constructor. Sets x,y coordinates to 0 and height and width to 0. Color is set to Gray
	 */
	public DShapeModel(){
		x =0;
		y=0;
		height = 0;
		width = 0;
		color = Color.GRAY;
	}
	/**
	 * adds a model listener to this model 
	 * @param observer 
	 */
	public void addModelListener(ModelListener observer){
		mListeners.add(observer);
	}
	public void removeListener(ModelListener tbRem){
		mListeners.remove(tbRem);
	}
	/**
	 * loops through notifier list and notifies each model with modelChanged(this model) whenever this model changes
	 */
	private void notifyListeners(){
		if(mListeners!=null){
			for(ModelListener mL:mListeners){
				 mL.modelChanged(this);
			}
		}
	}
	/**
	 * sets coordinate position of this shape using a Point
	 * @param p the point to use
	 */
	public void setXY(Point p){
		this.x=(int) p.getX();
		this.y=(int)p.getY();
		notifyListeners();
	}
	/**
	 * sets the bounds of DShape based on xy coordinates and height and width of a rectangle
	 * @param r the rectangle
	 */
	public void setBounds(Rectangle r){
		this.x = (int)r.getX();
		this.y = (int)r.getY();
		this.height = (int)r.getHeight();
		this.width = (int)r.getWidth();
		notifyListeners();
	}
	public void setXY(int x, int y){
		this.x = x;
		this.y = y;
		notifyListeners();
	}
	public void setX(int x){
		this.x = x;
		notifyListeners();
	}
	public void setY(int y){
		this.y = y;
		notifyListeners();
	}
	public void setHeight(int height){
		this.height = height;
		notifyListeners();
	}
	public void setWidth(int width){
		this.width = width;
		notifyListeners();
	}
	
	public void setColor(Color color){
		this.color = color;
		notifyListeners();
	}
	public void setSelected(boolean selected){
		this.selected = selected;
	}
	public void mimic(DShapeModel d){
		this.setXY(d.getX(),d.getY());
		this.setHeight(d.getHeight());
		this.setWidth(d.getWidth());
		this.setColor(d.getColor());
	}
	//getters----------------------
	public int getHeight(){
		return height;
	}
	public int getWidth(){
		return width;
	}
	public int getX(){
		return x;
	}
	public int getY(){
		return y;
	}
	public Color getColor(){
		return color;
	}
	public boolean getSelected(){
		return selected;
	}
	public String toString(){
		return"X= "+this.x+" Y= "+this.y+" Height= "+this.height+" Width= "+this.width +" "+"Color= "+this.color+" "+ selected;
	}
}
