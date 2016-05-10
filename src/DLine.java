import java.awt.Graphics;

public class DLine extends DShape{
	private DLineModel lineData =new DLineModel();;
	public DLine(){
		super.attachModel(lineData);
	}
	@Override
	public void draw(Graphics g){
		
	}
}
