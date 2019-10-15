//���V�a
//105403517
//���3A

//�зN�\��    �e����έI�����ܪ��ɭ�  �|�b�����s�W��ܿ�ܪ��C��
//�w�]�e����O�¦�
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.color.*;
import javax.swing.*;



//���V�a
//105403517
//���3A

public class PainterFrame extends JFrame{
	private final JComboBox <String> paintingToolsBox;
	private final static String toolsArray[] = {"����","���u","����","�x��","�ꨤ�x��"}; 
	private final JPanel northPnl;
	private final JPanel radioButtonPnl;
	private final JPanel northRightPnl;
	private final JPanel northLeftPnl;
	private  PaintPanel paintPanel;
	private final JRadioButton smallRadioButton;
	private final JRadioButton mediumRadioButton;
	private final JRadioButton largeRadioButton;
	private final ButtonGroup radioGroup;
	private final JLabel label1;
	private final JLabel label2;
	private final JLabel label3;
	public  static JLabel statusBarLabel;
	private final JCheckBox fillCheckBox;
	private final JButton button1;
	private final JButton button2;
	private final JButton button3;
	private final JButton button4;
	
	public static int paintsize = 4;
	public static String toolselect =toolsArray[0];
	public static boolean fillcheck =false;
	public static Color forecolor = Color.BLACK;
	public static Color backcolor = Color.black;
	public static boolean clear =false;
	public PainterFrame() {
		super("�p�e�a");
		BorderLayout layOut = new BorderLayout();
		setLayout(layOut);
		//welcome message
		
		//JCombobox
		paintingToolsBox = new JComboBox<String>(toolsArray);
		paintingToolsBox.addItemListener(
				new ItemListener()
				{
					@Override
					public void itemStateChanged(ItemEvent e)
					{
						if(e.getStateChange() == ItemEvent.SELECTED)
							System.out.printf("���  %s\r\n",toolsArray[paintingToolsBox.getSelectedIndex()]);
						switch(toolsArray[paintingToolsBox.getSelectedIndex()]) {
						case "����":
							toolselect = toolsArray[0];
							fillCheckBox.setEnabled(false);
							break;
						case"���u":
							toolselect = toolsArray[1];
							fillCheckBox.setEnabled(true);
							break;
						case"����":
							toolselect = toolsArray[2];
							fillCheckBox.setEnabled(true);
							break;
						case"�x��":
							toolselect = toolsArray[3];
							fillCheckBox.setEnabled(true);
							break;
						case"�ꨤ�x��":
							toolselect = toolsArray[4];
							fillCheckBox.setEnabled(true);
							break;
						}//end switch
					}//end itemStateChanged
					
				});
		
		//�W���jPanel
		northPnl = new JPanel();
		GridLayout northLayout = new GridLayout(1,2);
		northPnl.setLayout(northLayout);
		//�k�Wpanel
		northRightPnl = new JPanel();
		GridLayout northRightLayout =  new GridLayout(2,3);
		northRightPnl.setLayout(northRightLayout);
		//���Wpanel
		northLeftPnl = new JPanel();
		GridLayout northLeftLayout = new GridLayout(1,4,10,10);
		northLeftPnl.setLayout(northLeftLayout);
		//radioButton panel
		radioButtonPnl = new JPanel();
		radioButtonPnl.setLayout(new GridLayout());
		//drawing Panwl
		paintPanel = new PaintPanel();
		//labels
		label1 = new JLabel("ø�Ϥu��");
		label2 = new JLabel("����j�p");
		label3 = new JLabel("��");
		
		//radioButtons
		smallRadioButton = new JRadioButton("�p",true);
		mediumRadioButton = new JRadioButton("��",false);
		largeRadioButton = new JRadioButton("�j",false);
		radioButtonPnl.add(smallRadioButton);
		radioButtonPnl.add(mediumRadioButton);
		radioButtonPnl.add(largeRadioButton);
		smallRadioButton.addItemListener(new RadioButtonHandler(smallRadioButton.getText()));
		mediumRadioButton.addItemListener(new RadioButtonHandler(mediumRadioButton.getText()));
		largeRadioButton.addItemListener(new RadioButtonHandler(largeRadioButton.getText()));
		//checkBox
		fillCheckBox = new JCheckBox();
		fillCheckBox.setEnabled(false);
		CheckBoxHandler checkBoxHandler= new CheckBoxHandler();
		fillCheckBox.addItemListener(checkBoxHandler);
		//buttons
		button1 = new JButton("�e����");
		button2 = new JButton("�I����");
		button3 = new JButton("�M���e��");
		button4 = new JButton("�����");
		button1.setBackground(Color.BLACK);
		
		button1.addActionListener(new ButtonHandler(button1.getText()));
		button2.addActionListener(new ButtonHandler(button2.getText()));
		button3.addActionListener(new ButtonHandler(button3.getText()));
		button4.addActionListener(new ButtonHandler(button4.getText()));
		
		//create logical relationship between JRadioButtons
		radioGroup = new ButtonGroup();
		radioGroup.add(smallRadioButton);
		radioGroup.add(mediumRadioButton);
		radioGroup.add(largeRadioButton);
		
		//status bar,mouse handler
		statusBarLabel = new JLabel();
		//MouseHandler mouseHandler = new MouseHandler();
		//paintPanel.addMouseMotionListener(mouseHandler);
		
		northRightPnl.add(label1);//1
		northRightPnl.add(label2);//2
		northRightPnl.add(label3);//3
		northRightPnl.add(paintingToolsBox);//4
		northRightPnl.add(radioButtonPnl);//5
		northRightPnl.add(fillCheckBox);//6
		northLeftPnl.add(button1);
		northLeftPnl.add(button2);
		northLeftPnl.add(button3);
		northLeftPnl.add(button4);
		northPnl.add(northRightPnl);
		northPnl.add(northLeftPnl);
		add(northPnl,layOut.NORTH);
		add(paintPanel,BorderLayout.CENTER);
		add(statusBarLabel,BorderLayout.SOUTH);
	}//end constructor
		
	private class RadioButtonHandler implements ItemListener {
		public String size;
		public RadioButtonHandler(String size) {
			this.size = size;
			
			
		}//end constructor
		@Override 
		public void itemStateChanged(ItemEvent e) {
			if(e.getStateChange() == ItemEvent.SELECTED)
				System.out.printf("���  %s ����\r\n", size);
			switch(size) {
			case "�p":
				paintsize = 4;
				break;
			case "��":
				paintsize = 25;
				break;
			case "�j":
				paintsize = 50;
				break;
			}//end switch
		}//end itemStatechanged
	}//end radioButtonHandler
	
	private class CheckBoxHandler implements ItemListener{
		@Override
		public void itemStateChanged(ItemEvent e) {
			if(e.getStateChange() == ItemEvent.SELECTED) {
				System.out.println("��ܶ�");
				fillcheck = true;
				
			}//end if
			else {
				System.out.println("������");
				fillcheck = false;
			}
		}//end itemStateChanged
		
	}//end CheckBoxHandler
	
	private class ButtonHandler implements ActionListener{
		public String text;
		public ButtonHandler(String text) {
			this.text = text;
		}//end constructor
		@Override 
		public void actionPerformed(ActionEvent e) {
			System.out.printf("�I��  %s\r\n",text);
			switch(text) {
				case"�e����":
				forecolor = JColorChooser.showDialog(PainterFrame.this, "Choose a color", forecolor);
				button1.setBackground(forecolor);
				break;
			case"�I����":
				backcolor = JColorChooser.showDialog(PainterFrame.this, "Choose a color", backcolor);
				button2.setBackground(backcolor);
				break;
				
			case"�M���e��":
				clear = true;
				paintPanel.clearPanel();
				
				//paintPanel = new PaintPanel();
				//add(paintPanel,BorderLayout.CENTER);
				break;
			case"�����":
				forecolor = Color.WHITE;
				backcolor = Color.WHITE;
				
				break;
				
			}//end switch
		}
	}//end ButtonHandler
	
	/*private class MouseHandler extends MouseAdapter{
		@Override
		public void mouseMoved(MouseEvent e) {
			statusBarLabel.setText((String.format("��Ц�m [%d,%d]", e.getX(),e.getY())));
		}//end mouseMoved
	}//end mouseHandler
	*/
}//end class
