//¹ùÅV°a
//105403517
//¸êºÞ3A
import javax.swing.JFrame;
import javax.swing.JOptionPane;


public class PainterTest {
	public static void main(String[]args) {
		PainterFrame painterFrame = new PainterFrame();
		
		painterFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		painterFrame.setSize(1000, 500);
		painterFrame.setVisible(true);
		JOptionPane.showMessageDialog(painterFrame, "wellcome",	"°T®§", JOptionPane.PLAIN_MESSAGE);
	}//end main
}//end class
