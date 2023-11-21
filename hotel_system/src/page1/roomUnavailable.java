package page1;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class roomUnavailable implements ActionListener{

	JFrame f = new JFrame();
	JButton exit = new JButton("Ok");
	roomUnavailable(){
		prepareGUI();
	}
	public void prepareGUI() {
		Color c= new Color (176, 224, 230);
		header();
		whitePanel(f);
		f.getContentPane().setBackground(c);
		f.setSize(450, 300);
		f.setLayout(null);
		f.setLocationRelativeTo(null);
		f.setVisible(true);
	}
	private static void whitePanel(JFrame frame){
		JPanel panel = new JPanel();
		panel.setBounds(15,15,405,235);
		panel.setBackground(Color.white);
		frame.getContentPane().add(panel);    
	   }
	public void header() {
		JPanel p = new JPanel();
		JLabel header = new JLabel("Room chosen is currently unavailable.");
		Color c = new Color(81,169,173);
		Font font = new Font("",Font.PLAIN,20);
		header.setFont(font);
		p.add(header);
		p.setBounds(15, 110, 400, 100);
		header.setForeground(c);
		p.setBackground(Color.white);
		f.add(p);
	}
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==exit) {
			f.setVisible(false);
			new Checkin();
		}
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new roomUnavailable();
	}


}
