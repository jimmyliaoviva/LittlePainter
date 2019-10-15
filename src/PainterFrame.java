//廖顥軒
//105403517
//資管3A

//創意功能    前景色及背景色選擇的時候  會在按按鈕上顯示選擇的顏色
//預設前景色是黑色
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



//廖顥軒
//105403517
//資管3A

public class PainterFrame extends JFrame{
	private final JComboBox <String> paintingToolsBox;
	private final static String toolsArray[] = {"筆刷","直線","橢圓形","矩形","圓角矩形"}; 
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
		super("小畫家");
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
							System.out.printf("選擇  %s\r\n",toolsArray[paintingToolsBox.getSelectedIndex()]);
						switch(toolsArray[paintingToolsBox.getSelectedIndex()]) {
						case "筆刷":
							toolselect = toolsArray[0];
							fillCheckBox.setEnabled(false);
							break;
						case"直線":
							toolselect = toolsArray[1];
							fillCheckBox.setEnabled(true);
							break;
						case"橢圓形":
							toolselect = toolsArray[2];
							fillCheckBox.setEnabled(true);
							break;
						case"矩形":
							toolselect = toolsArray[3];
							fillCheckBox.setEnabled(true);
							break;
						case"圓角矩形":
							toolselect = toolsArray[4];
							fillCheckBox.setEnabled(true);
							break;
						}//end switch
					}//end itemStateChanged
					
				});
		
		//上面大Panel
		northPnl = new JPanel();
		GridLayout northLayout = new GridLayout(1,2);
		northPnl.setLayout(northLayout);
		//右上panel
		northRightPnl = new JPanel();
		GridLayout northRightLayout =  new GridLayout(2,3);
		northRightPnl.setLayout(northRightLayout);
		//左上panel
		northLeftPnl = new JPanel();
		GridLayout northLeftLayout = new GridLayout(1,4,10,10);
		northLeftPnl.setLayout(northLeftLayout);
		//radioButton panel
		radioButtonPnl = new JPanel();
		radioButtonPnl.setLayout(new GridLayout());
		//drawing Panwl
		paintPanel = new PaintPanel();
		//labels
		label1 = new JLabel("繪圖工具");
		label2 = new JLabel("筆刷大小");
		label3 = new JLabel("填滿");
		
		//radioButtons
		smallRadioButton = new JRadioButton("小",true);
		mediumRadioButton = new JRadioButton("中",false);
		largeRadioButton = new JRadioButton("大",false);
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
		button1 = new JButton("前景色");
		button2 = new JButton("背景色");
		button3 = new JButton("清除畫面");
		button4 = new JButton("橡皮擦");
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
				System.out.printf("選擇  %s 筆刷\r\n", size);
			switch(size) {
			case "小":
				paintsize = 4;
				break;
			case "中":
				paintsize = 25;
				break;
			case "大":
				paintsize = 50;
				break;
			}//end switch
		}//end itemStatechanged
	}//end radioButtonHandler
	
	private class CheckBoxHandler implements ItemListener{
		@Override
		public void itemStateChanged(ItemEvent e) {
			if(e.getStateChange() == ItemEvent.SELECTED) {
				System.out.println("選擇填滿");
				fillcheck = true;
				
			}//end if
			else {
				System.out.println("取消填滿");
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
			System.out.printf("點選  %s\r\n",text);
			switch(text) {
				case"前景色":
				forecolor = JColorChooser.showDialog(PainterFrame.this, "Choose a color", forecolor);
				button1.setBackground(forecolor);
				break;
			case"背景色":
				backcolor = JColorChooser.showDialog(PainterFrame.this, "Choose a color", backcolor);
				button2.setBackground(backcolor);
				break;
				
			case"清除畫面":
				clear = true;
				paintPanel.clearPanel();
				
				//paintPanel = new PaintPanel();
				//add(paintPanel,BorderLayout.CENTER);
				break;
			case"橡皮擦":
				forecolor = Color.WHITE;
				backcolor = Color.WHITE;
				
				break;
				
			}//end switch
		}
	}//end ButtonHandler
	
	/*private class MouseHandler extends MouseAdapter{
		@Override
		public void mouseMoved(MouseEvent e) {
			statusBarLabel.setText((String.format("游標位置 [%d,%d]", e.getX(),e.getY())));
		}//end mouseMoved
	}//end mouseHandler
	*/
}//end class
