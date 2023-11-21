package page1;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.border.Border;

public class CheckoutComplete implements ActionListener{
	JFrame f = new JFrame();
	JButton exit = new JButton("Ok");
	Color exitColor = new Color(81,169,173);
	
	CheckoutComplete(){
		prepareGUI();
	}
	public void prepareGUI() {
		Color c= new Color (176, 224, 230);
		//change bg color
		f.getContentPane().setBackground(c);
		buttonProperties();
		//create border 
		header();
		whitePanel(f);
		
		f.setSize(600, 400);
		f.setLayout(null);
		
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
	public void header() {
		JPanel p = new JPanel();
		JLabel header = new JLabel("Check out successfully!");
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
		Font font = new Font("",Font.PLAIN,13);
		Border b = BorderFactory.createLineBorder(exitColor,2);
		exit.setBounds(475, 300, 95, 30);
		exit.addActionListener(this);
		exit.setBackground(exitColor);
		exit.setBorder(b);
		exit.setForeground(Color.white);
		exit.setForeground(Color.white);
		exit.setFont(font);
		exit.setBorder(b);
		f.add(exit);
	}
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==exit) {
			f.setVisible(false);
			new Menu();
		}
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new CheckoutComplete();
	}

}
