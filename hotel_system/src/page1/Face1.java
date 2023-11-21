package page1;
import javax.swing.*;
import javax.swing.border.Border;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
public class Face1 implements ActionListener{
	JFrame f=new JFrame();  ;  
	 JButton start=new JButton("Start");
	 Color bp = new Color(239,223, 208);
	Border border= BorderFactory.createLineBorder(bp, 2);
	Face1(){  
	   prepareGUI();
	}  
	public void prepareGUI() {
		Color textColor = new Color(60,125,128);
		//Setting Main label
		JLabel l = new JLabel("Hotel Check-in System");
		Font font = new Font("",Font.PLAIN,20);
		
		l.setFont(font);
		l.setForeground(textColor);
		l.setBounds(200, 50, 300 , 200);
		f.add(l);
	    //setting color for background
	    Color bgColor= new Color (176, 224, 230);
		f.getContentPane().setBackground(bgColor);
		//add button
		buttonProperties();
		//create border and content
		 whitePanel(f);
		//setting frame
	    f.setSize(600,400);  
	    f.setLayout(null);
	    f.setVisible(true);  
	    f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    f.setLocationRelativeTo(null);
	}
	public void whitePanel( JFrame f1) {
		JPanel panel = new JPanel();
	      panel.setBounds(15,15,555,330);
	      panel.setLayout(null);
	      panel.setBackground(Color.white);
	      f1.getContentPane().add(panel);
	}
	public void buttonProperties() {
		Color c =new Color(239,223,209);
		start.setBounds(255, 200, 95, 30);
		start.setBackground(c);
		start.addActionListener(this) ;
		start.setBorder(border);
	    f.add(start);
	}
	public void actionPerformed(ActionEvent e) {
		f.setVisible(false);
		new Menu();
	}
	public static void main(String[] args) {  
	    new Face1();  
	}  
}
