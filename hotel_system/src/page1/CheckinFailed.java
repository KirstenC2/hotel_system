package page1;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class CheckinFailed implements ActionListener{
	JFrame f = new JFrame();
	JButton exit = new JButton("Ok");
	CheckinFailed(){
		prepareGUI();
	}
	public void prepareGUI() {
		Color c= new Color (176, 224, 230);
		//change bg color
		f.getContentPane().setBackground(c);
		
		buttonProperties();
		contentLabel();
		whitePanel(f);
		f.setSize(600, 400);
		f.setLayout(null);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setLocationRelativeTo(null);
		f.setVisible(true);
	}
	private static void whitePanel(JFrame frame){
	      //Create a border line
		JPanel panel = new JPanel();
		panel.setBounds(15,15,555,330);
		panel.setBackground(Color.white);
		frame.getContentPane().add(panel);    
	   }
	public void contentLabel() {
		JPanel p = new JPanel();
		JLabel header = new JLabel("Check-in Failed TT");
		Color c = new Color(81,169,173);
		Font font = new Font("Arial",Font.PLAIN,28);
		header.setFont(font);
		p.add(header);
		p.setBounds(50, 150, 480, 100);
		header.setForeground(c);
		p.setBackground(Color.white);
		f.add(p);
	}
	public void buttonProperties() {
		Color exitColor = new Color(81,169,173);
		exit.setBounds(475, 300, 95, 30);
		exit.addActionListener(this);
		exit.setBackground(exitColor);
		f.add(exit);
	}
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==exit) {
			new Checkin();
		}
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new CheckinComplete();
	}


}
