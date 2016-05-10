import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;


public class Whiteboard extends JFrame{
	private static JFrame whiteBoard;
	private static JPanel westControls;
	private static Random r = new Random();
	/**
	 * @param controls- an array of components to add to the panel
	 * @return constructs and returns a panel of controls for whiteboard
	 */
	private static JPanel boxControls(JComponent[] controls){
		JPanel west = new JPanel();
		west.setLayout(new BoxLayout(west, BoxLayout.X_AXIS));
		for(JComponent b: controls){
			west.add(b);
		}
		return west;
	}
	public static void main(String[] args){
		whiteBoard = new JFrame("Whiteboard");
		whiteBoard.setLayout(new BorderLayout());
		westControls = new JPanel();
		westControls.setLayout(new BoxLayout(westControls, BoxLayout.Y_AXIS));
		Canvas c = new Canvas();
		whiteBoard.add(c, BorderLayout.CENTER);
		JComponent[] ctrls = new JComponent[3];
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
		ctrls[0] = rectCreate;
		JButton ovalCreate = new JButton("Draw Oval");
		ovalCreate.addActionListener(new ActionListener(){

			public void actionPerformed(ActionEvent arg0) {
				int x = r.nextInt(400);
				int y = r.nextInt(400);
				int width = r.nextInt(400);
				int height = r.nextInt(400);
				DOvalModel newOval= new DOvalModel();
				newOval.setBounds(new Rectangle(x,y,width,height));
				c.addDShape(newOval);				
			}
			
		});
		ctrls[1] = ovalCreate;
		JButton drawAll = new JButton("Draw all");
		drawAll.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				c.paintComponents();
			}
			
		});
		ctrls[2] = drawAll;
		JPanel wBox = boxControls(ctrls);
		westControls.add(wBox);
		for(Component comp : wBox.getComponents()){
			
		}
		whiteBoard.add(westControls, BorderLayout.WEST);
		whiteBoard.pack();
		whiteBoard.setVisible(true);
		whiteBoard.setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
}
