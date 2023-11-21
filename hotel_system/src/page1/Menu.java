package page1;
import javax.swing.*;
import javax.swing.border.Border;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
public class Menu implements ActionListener{
	JFrame f = new JFrame("Menu");
	JButton checkin,checkout,history,exit,current,inhouse,cguest;
	Color borderColor = new Color(239,223, 208);
	Color exitColor = new Color(81,169,173);
	Menu(){
		prepareGUI();
	}
	public void prepareGUI() {
		Color textColor = new Color(60,125,128);
		Color c= new Color (176, 224, 230);
		Font font = new Font("",Font.PLAIN,45);
		JLabel label = new JLabel("Welcome to the system");
		//setting label
		label.setBounds(165, 110, 500 , 200);
		label.setForeground(textColor);
		label.setFont(font);
		f.add(label);
		f.getContentPane().setBackground(c);
		buttonProperties();
		whitePanel(f);
		f.setSize(800,600);  
	    f.setLayout(null);
	    f.setVisible(true);  
	    f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    f.setLocationRelativeTo(null);
	}
	private static void whitePanel(JFrame frame){
	      //Create a border
	      JPanel panel = new JPanel();
	      LayoutManager layout = new FlowLayout();
	      panel.setBounds(15,15,755,530);
	      panel.setLayout(layout);
	      panel.setBackground(Color.white);
	      frame.getContentPane().add(panel, BorderLayout.CENTER);    
	   }
	public void buttonProperties() {
		Color brown = new Color(149,100,47);
		Border border= BorderFactory.createLineBorder(borderColor, 2);
		Font fb = new Font("",Font.PLAIN,23);
		//check in button
		checkin = new JButton("Check-in");
		checkin.setBounds(300, 290, 180, 45);
		checkin.setBackground(borderColor);
		checkin.setForeground(brown);
		checkin.setBorder(border);
		checkin.setFont(fb);
		f.add(checkin);
		checkin.addActionListener(this) ;
		//check out button
	    checkout = new JButton("Check-out");
	    checkout.setBounds(500, 290, 180, 45);
	    checkout.setBackground(borderColor);
	    checkout.setForeground(brown);
	    checkout.setBorder(border);
	    checkout.setFont(fb);
	    f.add(checkout);
	    checkout.addActionListener(this);
	    //button for checking room vacancy
	    current = new JButton("	Room Vacancy");
	    current.setBounds(100,290,180,45);
	    current.setBackground(borderColor);
	    current.setForeground(brown);
	    current.setBorder(border);
	    current.setFont(fb);
	    f.add(current);
	    current.addActionListener(this);
	    //history button
	    history = new JButton("History");
	    history.setBounds(420,350,200,45);
	    history.setBackground(borderColor);
	    history.setBorder(border);
	    history.setForeground(brown);
	    history.setFont(fb);
	    f.add(history);
	    history.addActionListener(this);
	    
	    cguest = new JButton("Current staying");
	    cguest.setBounds(200,350,200,45);
	    cguest.setBackground(borderColor);
	    cguest.setForeground(brown);
	    cguest.setBorder(border);
	    cguest.setFont(fb);
	    f.add(cguest);
	    cguest.addActionListener(this);
	    
	    //exit button
	    Border green = BorderFactory.createLineBorder(exitColor,2);
	    Font font = new Font("",Font.PLAIN,13);
	    exit = new JButton("Exit");
	    exit.setBounds(15, 500, 95, 30);
		exit.setBackground(exitColor);
		exit.setBorder(green);
		exit.setForeground(Color.white);
		exit.setFont(font);
		f.add(exit);
	    exit.addActionListener(this);
	}
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==checkin) {
			f.setVisible(false);
			new Checkin();
		}
		else if(e.getSource()==checkout){
			f.setVisible(false);
			new CheckOut();
		}
		else if(e.getSource()==history) {
			f.setVisible(false);
			new history();
		}
		else if(e.getSource()==exit) {
			f.setVisible(false);
			new Face1();
		}
		else if(e.getSource()==current) {
			f.setVisible(false);
			new roomVacancy();
		}
		else if(e.getSource()==cguest) {
			f.setVisible(false);
			new CurrentGuest();
		}
	}
	public static void main(String[] args) {
		new Menu();
	}

}
