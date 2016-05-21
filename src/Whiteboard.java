import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GraphicsEnvironment;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Random;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JColorChooser;
import javax.swing.JTextField;

public class Whiteboard extends JFrame{
	private JFrame whiteBoard;
	private JPanel westControls;
	//private Random r = new Random();
	private JFrame colorChooser = new JFrame("Select a Color");
	/**
	 * @param an array of components to add to the panel
	 * @return JPanel that represents this whiteboard's controls
	 */
	public Whiteboard(){
	}
	private static JPanel boxControls(JComponent[] controls){
		JPanel west = new JPanel();
		west.setLayout(new BoxLayout(west, BoxLayout.Y_AXIS));
		for(JComponent b: controls){
			west.add(b);
		}
		return west;
	}
	public void CreateAndShowGUI(){
		whiteBoard = new JFrame("Whiteboard");
		whiteBoard.setLayout(new BorderLayout());
		whiteBoard.setDefaultCloseOperation(EXIT_ON_CLOSE);
		//whiteBoard.setResizable(false);
		colorChooser.setLayout(new BorderLayout());
		colorChooser.setDefaultCloseOperation(HIDE_ON_CLOSE);
		westControls = new JPanel();
		westControls.setLayout(new BoxLayout(westControls, BoxLayout.Y_AXIS));
		Canvas c = new Canvas();
		whiteBoard.add(c, BorderLayout.EAST);
		JPanel southBox = new JPanel();
		southBox.setLayout(new BoxLayout(southBox, BoxLayout.Y_AXIS));
		JTextField thisField = new JTextField("",20);
		String fonts[] = GraphicsEnvironment.getLocalGraphicsEnvironment().getAvailableFontFamilyNames();
		JComboBox <String> fontBox = new JComboBox<String>(fonts);
		fontBox.setEnabled(false);
		fontBox.addMouseListener(new MouseAdapter(){
			 public void mouseClicked(MouseEvent e){
	                if(c.selectedIsTextShape()==true){
	                	thisField.setEnabled(true);
	                	fontBox.setEnabled(true);
	                	thisField.setText(c.getText());
	                }
	                else{
	                	thisField.setEnabled(false);
	                	fontBox.setEnabled(false);
	                	thisField.setText("");
	                }
	            }
		});
		fontBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String fType = (String)fontBox.getSelectedItem();
				c.setFont(fType);
			}
		});
		thisField.setEnabled(false);
		thisField.addMouseListener(new MouseAdapter(){
			@Override
            public void mouseClicked(MouseEvent e){
                if(c.selectedIsTextShape()==true){
                	thisField.setEnabled(true);
                	fontBox.setEnabled(true);
                	thisField.setText(c.getText());
                }
                else{
                	thisField.setEnabled(false);
                	fontBox.setEnabled(false);
                	thisField.setText("");
                }
			}
		});
		thisField.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0) {
				c.setText(thisField.getText());
				thisField.setText("");
			}
		});
		southBox.add(thisField);
		southBox.add(fontBox);
		JButton rectCreate = new JButton("Draw Rectangle");
		rectCreate.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				/*int x = r.nextInt(10);
				int y = r.nextInt(10);
				int width = r.nextInt(20);
				int height = r.nextInt(20);*/
				DRectModel newRect= new DRectModel();
				newRect.setBounds(new Rectangle(10,10,20,20));
				c.addDShape(newRect);
			}
		});
		JComponent[] ctrls = new JComponent[9];
		ctrls[0] = rectCreate;
		JButton ovalCreate = new JButton("Draw Oval");
		ovalCreate.addActionListener(new ActionListener(){

			public void actionPerformed(ActionEvent arg0) {
				/*int x = r.nextInt(10);
				int y = r.nextInt(10);
				int width = r.nextInt(20);
				int height = r.nextInt(20);*/
				DOvalModel newOval= new DOvalModel();
				newOval.setBounds(new Rectangle(10,10,20,20));
				c.addDShape(newOval);				
			}
			
		});
		ctrls[1] = ovalCreate;
		JButton dLine = new JButton("Draw Line");
		dLine.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				DLineModel lM = new DLineModel(100,100,100,150);
				c.addDShape(lM);
			}
		});
		ctrls[2]= dLine;
		JButton dText = new JButton("Draw Text");
		dText.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				DTextModel tM = new DTextModel("Hello", "Dialog");
				tM.setX(20);
				tM.setY(20);
				tM.setHeight(100);
				tM.setWidth(100);
				c.addDShape(tM);
			}
		});
		ctrls[3] = dText;
		JButton drawAll = new JButton("Draw Shapes");
		drawAll.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				c.paintComponents(c.getGraphics());
			}
			
		});
		ctrls[4] = drawAll;
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
		ctrls[5]=setColor;
		JButton deleteShape = new JButton("Delete Selected");
		deleteShape.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0) {
				c.removeSelected();
			}
			
		});
		ctrls[6] = deleteShape;
		JButton moveF = new JButton("Move Front");
		moveF.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0) {
				c.moveSelectedFront();
			}
		});
		ctrls[7] = moveF;
		JButton moveB = new JButton("Move Back");
		moveB.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				c.moveSelectedBack();
			}
		});
		
		ctrls[8] = moveB;
		
		JPanel wBox = boxControls(ctrls);
		wBox.setAlignmentX(LEFT_ALIGNMENT);
		whiteBoard.add(westControls, BorderLayout.WEST);
		
		
		//whiteBoard.add(southBox, BorderLayout.WEST);
		//panle for table, cetner the table in that panel
		//add table whiteBoard.add(table, BorderLayout.SOUTH); 
		wBox.add(southBox);
		westControls.add(wBox);
		whiteBoard.pack();
		whiteBoard.setVisible(true);
	}
	
	public static void main(String[] args){
		Whiteboard whiteboard = new Whiteboard();
		whiteboard.CreateAndShowGUI();
	}
	
	
	
}
