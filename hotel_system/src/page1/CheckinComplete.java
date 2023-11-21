package page1;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.border.Border;

public class CheckinComplete implements ActionListener{
	JFrame f = new JFrame("Check in Completed");
	JButton exit = new JButton("Home page");
	CheckinComplete(){
		prepareGUI();
	}
	public void prepareGUI() {
		Color c= new Color (176, 224, 230);

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
	public void whitePanel( JFrame f1) {
		JPanel panel = new JPanel();
	      panel.setBounds(15,15,555,330);
	      panel.setLayout(null);
	      panel.setBackground(Color.white);
	      f1.getContentPane().add(panel);
	}
	public void contentLabel() {
		JPanel p = new JPanel();
		JLabel header = new JLabel("Check-in Details entered successfully!");
		Color c = new Color(81,169,173);
		Font font = new Font("Arial",Font.PLAIN,28);
		
		header.setFont(font);
		header.setForeground(c);
		
		p.add(header);
		p.setBounds(50, 150, 480, 100);
		p.setBackground(Color.white);
		
		f.add(p);
	}
	public void buttonProperties() {
		Color exitColor = new Color(81,169,173);
		Border green = BorderFactory.createLineBorder(exitColor,2);
		Font f1 = new Font("",Font.PLAIN,14);
		exit.setBounds(475, 300, 95, 30);
		exit.addActionListener(this);
		exit.setBackground(exitColor);
		exit.setFont(f1);
		exit.setForeground(Color.white);
		exit.setBorder(green);
		f.add(exit);
	}
	public void actionPerformed(ActionEvent e) {
		f.setVisible(false);
			new Menu();
	}
}
