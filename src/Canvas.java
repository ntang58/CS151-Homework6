import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JPanel;


public class Canvas extends JPanel{
	private DShape[] shapes;
	/**
	 * Extends JPanel. The layout of the Panel is following a FlowLayout
	 */
	public Canvas(){
		super(new FlowLayout());
		this.setPreferredSize(new Dimension(400, 400));
		this.setBackground(Color.WHITE);
	}
	/**
	 * draws all the components in the canvas
	 */
	public void paintComponenet(){
		for(DShape ds : shapes){
			ds.draw();
		}
	}
	public void addDShape(DShape d){
		
	}
	public void removeDShape(DShape d ){
		
	}
	
}
