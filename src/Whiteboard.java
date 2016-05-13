import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JColorChooser;

public class Whiteboard extends JFrame{
	private static JFrame whiteBoard;
	private static JPanel westControls;
	private static Random r = new Random();
	private static JFrame colorChooser = new JFrame("Select a Color");
	/**
	 * @param an array of components to add to the panel
	 * @return JPanel that represents this whiteboard's controls
	 */
	private static JPanel boxControls(JComponent[] controls){
		JPanel west = new JPanel();
		west.setLayout(new BoxLayout(west, BoxLayout.X_AXIS));
		for(JComponent b: controls){
			west.add(b);
		}
		return west;
	}
	private static void CreateAndShowGUI(){
		whiteBoard = new JFrame("Whiteboard");
		whiteBoard.setLayout(new BorderLayout());
		whiteBoard.setDefaultCloseOperation(EXIT_ON_CLOSE);
		//whiteBoard.setResizable(false);
		colorChooser.setLayout(new BorderLayout());
		colorChooser.setDefaultCloseOperation(HIDE_ON_CLOSE);
		westControls = new JPanel();
		westControls.setLayout(new BoxLayout(westControls, BoxLayout.Y_AXIS));
		Canvas c = new Canvas();
		whiteBoard.add(c, BorderLayout.CENTER);
		JButton rectCreate = new JButton("Draw Rectangle");
		rectCreate.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				int x = r.nextInt(100);
				int y = r.nextInt(100);
				int width = r.nextInt(100);
				int height = r.nextInt(100);
				DRectModel newRect= new DRectModel();
				newRect.setBounds(new Rectangle(x,y,width,height));
				c.addDShape(newRect);
			}
		});
		JComponent[] ctrls = new JComponent[4];
		ctrls[0] = rectCreate;
		JButton ovalCreate = new JButton("Draw Oval");
		ovalCreate.addActionListener(new ActionListener(){

			public void actionPerformed(ActionEvent arg0) {
				int x = r.nextInt(100);
				int y = r.nextInt(100);
				int width = r.nextInt(100);
				int height = r.nextInt(100);
				DOvalModel newOval= new DOvalModel();
				/*newOval.addModelListener(new ModelListener(){

					public void modelChanged(DShapeModel model) {
						
					}
					
				});*/
				newOval.setBounds(new Rectangle(x,y,width,height));
				c.addDShape(newOval);				
			}
			
		});
		ctrls[1] = ovalCreate;
		JButton drawAll = new JButton("Draw all");
		drawAll.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				c.paintComponents(whiteBoard.getGraphics());
			}
			
		});
		ctrls[2] = drawAll;
		JButton setColor = new JButton("Set Color");
		JColorChooser clrChoose = new JColorChooser();
		colorChooser.add(clrChoose);
		JButton okColor = new JButton("Ok");
		okColor.setSize(20,20);
		okColor.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0) {
				if(c.isSelected()){
					Color chosenClr= clrChoose.getColor();
					c.setSelectedColor(chosenClr);
				}
			}
		});
		colorChooser.add(okColor,BorderLayout.EAST);
		colorChooser.setSize(450, 300);
		colorChooser.setResizable(false);
		setColor.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				colorChooser.setVisible(true);
			}
		});
		ctrls[3]=setColor;
		JPanel wBox = boxControls(ctrls);
		for(Component comp : wBox.getComponents()){
			((JComponent)comp).setAlignmentX(Box.LEFT_ALIGNMENT);
		}
		westControls.add(wBox);
		whiteBoard.add(westControls, BorderLayout.WEST);
		whiteBoard.pack();
		whiteBoard.setVisible(true);
	}
	public static void main(String[] args){
		CreateAndShowGUI();
	}
}
