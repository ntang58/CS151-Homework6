import java.awt.BorderLayout;
import java.awt.Component;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;


public class Whiteboard extends JFrame{
	private static JFrame whiteBoard;
	private static JPanel westControls;
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
		JComponent[] ctrls = new JComponent[5];
		ctrls[0] = new JButton("1");
		ctrls[1] = new JButton("2");
		ctrls[2] = new JButton("3");
		ctrls[3] = new JButton("4");
		ctrls[4] = new JButton("5");
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
