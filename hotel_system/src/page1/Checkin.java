package page1;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

import javax.swing.*;
import javax.swing.border.Border;
public class Checkin implements ActionListener{
	JFrame f = new JFrame("Check in");
	JFrame cinconf,payf;
	JButton cinconReturn,payfReturn,payfContinue,back,next,cinconok;
	JTextField tname, tid, tcon, trn, tgn,tdays;
	String oname,oid,ocon,oindate,cname,cid,ccon,crn,cgn,cdays,receiveRno=null,rtype,rchosen=null;
	Color lbltxt = new Color(152,128,108), headerC = new Color(60,125,127);
	Font font = new Font("",Font.PLAIN,17) ;
	int daysint,crnint,orn;
	Checkin(){
		prepareGUI();
	}
	Checkin(String n){
		rchosen =n;
		prepareGUI();
	}
	public void prepareGUI() {
		Color c= new Color (176, 224, 230);
		//change background color
		f.getContentPane().setBackground(c);
		if(rchosen!=null) {
			inputArea1();
		}
		else {
			inputArea();//set input area
		}
		buttonProperties();	//put in button
		header();	//create guest details header 
		createUi(f);//heading
		whitePanel(f);//white area
		f.setSize(800, 600);
		f.setLayout(null);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setLocationRelativeTo(null);
		f.setVisible(true);
	}
	private static void createUi(JFrame frame){
	      //Create a border line
		Color textColor = new Color(60,125,128);
	      JPanel panel = new JPanel();
	      panel.setBounds(15,15,555,50);
	      LayoutManager layout = new BorderLayout();
	      panel.setLayout(layout);
	      JLabel l = new JLabel(" Check in ");
	      Font f1 = new Font("",Font.BOLD,32);
	      l.setFont(f1);
	      l.setBounds(120, 70, 300 , 200);
	      l.setForeground(textColor);
	      panel.add(l,BorderLayout.NORTH);
	      panel.setBackground(Color.white);
	      frame.getContentPane().add(panel);
	   }
	public void header() {
		JPanel p = new JPanel();
		JLabel header = new JLabel("Guest details");
		Font headerFont = new Font("",Font.PLAIN,25);
		p.add(header);
		p.setBounds(60, 85, 650, 50);
		p.setBackground(headerC);
		header.setForeground(Color.white);
		header.setFont(headerFont);
		f.add(p);
	}
	public void inputArea() {
		Color c = new Color(239,223,209);
		JPanel p1=new JPanel();
		JLabel name,id,cont,roomnum,gnum, days;
		Border border = BorderFactory.createEtchedBorder();

		
		name=new JLabel("    Name:");
		id=new JLabel("    ID:");
		cont=new JLabel("    Contact:");
		roomnum=new JLabel("    Room number:");
		gnum=new JLabel("    Guest Number:");
		days = new JLabel("    Estimated staying days");
		
		tname = new JTextField(50);
		tid = new JTextField(50);
		tcon = new JTextField(50);
		trn = new JTextField(50);
		tgn = new JTextField(50);
		tdays = new JTextField(50);
		
		name.setForeground(lbltxt);
		id.setForeground(lbltxt);
		cont.setForeground(lbltxt);
		roomnum.setForeground(lbltxt);
		gnum.setForeground(lbltxt);
		days.setForeground(lbltxt);
		
		name.setFont(font);
		id.setFont(font);
		cont.setFont(font);
		roomnum.setFont(font);
		gnum.setFont(font);
		days.setFont(font);
		
		tname.setBackground(c);
		tid.setBackground(c);
		tcon.setBackground(c);
		trn.setBackground(c);
		tgn.setBackground(c);
		tdays.setBackground(c);
		
		tname.setBorder(border);
		tid.setBorder(border);
		tcon.setBorder(border);
		trn.setBorder(border);
		tgn.setBorder(border);
		tdays.setBorder(border);
		
		tname.setFont(font);
		tid.setFont(font);
		tcon.setFont(font);
		trn.setFont(font);
		tgn.setFont(font);
		tdays.setFont(font);
		
		p1.setBackground(c);
		//add labels and text fields to the grid layout
		p1.add(name);
		p1.add(tname);
		p1.add(id);
		p1.add(tid);
		p1.add(cont);
		p1.add(tcon);
		p1.add(roomnum);
		p1.add(trn);
		p1.add(gnum);
		p1.add(tgn);
		p1.add(days);
		p1.add(tdays);
		p1.setFont(font);
		p1.setLayout(new GridLayout(6,2));
		p1.setBounds(60, 135, 650, 280);
		f.add(p1);
	}
	public void inputArea1() {
		Color c = new Color(239,223,209);
		JPanel p1=new JPanel();
		JLabel name,id,cont,roomnum,gnum, days;
		Border border = BorderFactory.createEtchedBorder();
		
		name=new JLabel("    Name:");
		id=new JLabel("    ID:");
		cont=new JLabel("    Contact:");
		roomnum=new JLabel("    Room number:");
		gnum=new JLabel("    Guest Number:");
		days = new JLabel("    Estimated staying days");
		
		tname = new JTextField(50);
		tid = new JTextField(50);
		tcon = new JTextField(50);
		JLabel rn1= new JLabel(rchosen);
		tgn = new JTextField(50);
		tdays = new JTextField(50);
		
		name.setForeground(lbltxt);
		id.setForeground(lbltxt);
		cont.setForeground(lbltxt);
		roomnum.setForeground(lbltxt);
		gnum.setForeground(lbltxt);
		days.setForeground(lbltxt);
		
		name.setFont(font);
		id.setFont(font);
		cont.setFont(font);
		roomnum.setFont(font);
		gnum.setFont(font);
		days.setFont(font);
		
		tname.setBackground(c);
		tid.setBackground(c);
		tcon.setBackground(c);
		rn1.setBackground(c);
		tgn.setBackground(c);
		tdays.setBackground(c);
		
		tname.setBorder(border);
		tid.setBorder(border);
		tcon.setBorder(border);

		tgn.setBorder(border);
		tdays.setBorder(border);
		
		tname.setFont(font);
		tid.setFont(font);
		tcon.setFont(font);
		rn1.setFont(font);
		tgn.setFont(font);
		tdays.setFont(font);
		
		p1.setBackground(c);
		//add labels and text fields to the grid layout
		p1.add(name);
		p1.add(tname);
		p1.add(id);
		p1.add(tid);
		p1.add(cont);
		p1.add(tcon);
		p1.add(roomnum);
		p1.add(rn1);
		p1.add(gnum);
		p1.add(tgn);
		p1.add(days);
		p1.add(tdays);
		p1.setFont(font);
		p1.setLayout(new GridLayout(6,2));
		p1.setBounds(60, 135, 650, 280);
		f.add(p1);
	}
	public void buttonProperties() {
		Color exitColor = new Color(81,169,173);
		Border b = BorderFactory.createLineBorder(exitColor);
		Font font = new Font("",Font.PLAIN,13);
		//back button
		back = new JButton("Menu");
		back.setBounds(15, 500, 95, 30);
		back.addActionListener(this);
		back.setBackground(exitColor);
		back.setForeground(Color.white);
		back.setBorder(b);
		back.setFont(font);
		f.add(back);
		//confirm button
		next = new JButton("Proceed");
		next.setBounds(675, 500, 95, 30);
		next.addActionListener(this);
		next.setBackground(exitColor);
		next.setForeground(Color.white);
		next.setFont(font);
		next.setBorder(b);
		f.add(next);
		//confirm page ok button
		cinconok = new JButton("Ok");
		cinconok.setBounds(675, 500, 95, 30);
		cinconok.addActionListener(this);
		cinconok.setBackground(exitColor);
		cinconok.setForeground(Color.white);
		cinconok.setFont(font);
		cinconok.setBorder(b);
		//confirm page return button
		cinconReturn = new JButton("Return");
		cinconReturn.setBounds(15, 500, 95, 30);
		cinconReturn.addActionListener(this);
		cinconReturn.setBackground(exitColor);
		cinconReturn.setForeground(Color.white);
		cinconReturn.setFont(font);
		cinconReturn.setBorder(b);
		//payment page cancel button
		payfReturn=new JButton("Cancel");
		payfReturn.setBounds(15, 500, 95, 30);
		payfReturn.addActionListener(this);
		payfReturn.setBackground(exitColor);
		payfReturn.setForeground(Color.white);
		payfReturn.setFont(font);
		payfReturn.setBorder(b);
		//continue payment button
		payfContinue = new JButton("Done");
		payfContinue.setBounds(675, 500, 95, 30);
		payfContinue.addActionListener(this);
		payfContinue.setBackground(exitColor);
		payfContinue.setForeground(Color.white);
		payfContinue.setFont(font);
		payfContinue.setBorder(b);
		
	}
	public void removeCheckin() {
		String sql = "DELETE FROM client WHERE name = ? ;";
		try(Connection con = this.register()) {
			PreparedStatement s =con.prepareStatement(sql);
			s.setString(1, cname);
			s.executeUpdate();
			s.close();
			con.close();
		} catch (SQLException e) {
			new ConnectionError();
			System.out.println(e.getMessage());
		}		
	}
	private Connection register() {
		Connection con = null;
		try {
			DriverManager.registerDriver(new org.sqlite.JDBC());//register driver
			String url="jdbc:sqlite:test2.db";
			con = DriverManager.getConnection(url);
			if(con != null) {
				return con;
			}
			
		} catch (SQLException e) {
			new ConnectionError();
			System.out.println(e.getMessage());
		}
		return con;
	}
	public void confirmDetails(String name) {
		cinconf = new JFrame("Confirmation"); 
		Color bgc= new Color (176, 224, 230);
		JLabel l = new JLabel("Check in statement");
		Font f = new Font("",Font.PLAIN,18);
		String sql = "SELECT client.*,room.roomType FROM client "
				+ "INNER JOIN room "
				+ "ON client.roomNo=room.roomNo "
				+ "WHERE client.name=?;";
		try(Connection con = this.register()) {
			PreparedStatement s = con.prepareStatement(sql);
			s.setString(1, name);
			ResultSet rs = s.executeQuery();
			
			l.setForeground(headerC);
			l.setFont(f);
			
			JPanel p = new JPanel();
			JPanel p1 = new JPanel();
			JLabel l1,l2,l3,l4,l5,l6;
			JLabel a1,a2,a3,a4,a5,a6;
			
			a1 = new JLabel();
			a2 = new JLabel();
			a3 = new JLabel();
			a4 = new JLabel();
			a5 = new JLabel();
			a6 = new JLabel();
			while(rs.next()) {
				oname = rs.getString("name");
				ocon = rs.getString("contact");
				oid = rs.getString("id");
				orn = rs.getInt("roomNo");
				oindate = rs.getString("days");
				rtype = rs.getString("roomType");
			}
			oindate = oindate + "  day(s) ";
			a1.setText(oname);
			a2.setText(oid);
			a3.setText("+"+ocon);
			a4.setText( Integer.toString(orn));
			a5.setText(oindate);
			a6.setText(rtype);
			
			l1 = new JLabel("  Name:");
			l2 = new JLabel("  ID Number:");
			l3 = new JLabel("  Contact:");
			l4 = new JLabel("  Room Number");
			l5 = new JLabel("  Estimated staying days:");
			l6 = new JLabel("  Room type:");
			
			l1.setFont(f);
			l2.setFont(f);
			l3.setFont(f);
			l4.setFont(f);
			l5.setFont(f);
			l6.setFont(f);
			
			a1.setFont(f);
			a2.setFont(f);
			a3.setFont(f);
			a4.setFont(f);
			a5.setFont(f);
			a6.setFont(f);
			
			a1.setForeground(lbltxt);
			a2.setForeground(lbltxt);
			a3.setForeground(lbltxt);
			a4.setForeground(lbltxt);
			a5.setForeground(lbltxt);
			a6.setForeground(lbltxt);
			
			p.add(l1);
			p.add(a1);
			p.add(l2);
			p.add(a2);
			p.add(l3);
			p.add(a3);
			p.add(l4);
			p.add(a4);
			p.add(l5);
			p.add(a5);
			p.add(l6);
			p.add(a6);
			
			p.setBackground(Color.white);
			p.setBounds(170, 80,600, 400);
			p.setLayout(new GridLayout(6,2));
			
			//Check in statement label
			p1.add(l);
			p1.setBounds(35, 40, 720, 40);
			p1.setBackground(bgc);
			//add panels into frame
			cinconf.add(p);
			cinconf.add(p1);
			//add button
			cinconf.add(cinconok);
			cinconf.add(cinconReturn);
			s.close();
			con.close();
			whitePanel(cinconf);
			cinconf.getContentPane().setBackground(bgc);
			cinconf.setSize(800, 600);
			cinconf.setLayout(null);
			cinconf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			cinconf.setLocationRelativeTo(null);
			cinconf.setVisible(true);
		} catch (SQLException e) {
			new ConnectionError();
			System.out.println(e.getMessage());
		}
	}
	public void whitePanel( JFrame f1) {
		JPanel panel = new JPanel();
	      panel.setBounds(15,15,755,530);
	      panel.setLayout(null);
	      panel.setBackground(Color.white);
	      f1.getContentPane().add(panel);
	}
	public void payable(String rnumber) {
		payf = new JFrame("Payable statement"); 
		Color bgc= new Color (176, 224, 230);
		JLabel l = new JLabel("Payable amount");
		String sql = "SELECT * FROM room WHERE roomNo = ? ;";
		int dep=0,rate=0, total=0;
		try(Connection con = this.register()) {
			PreparedStatement s = con.prepareStatement(sql);
			s.setString(1, rnumber);
			ResultSet rs = s.executeQuery();
			
			JPanel p = new JPanel();
			JPanel p1 = new JPanel();
			JLabel l1,l2,l3;
			JLabel a1,a2,a3;
			
			a1 = new JLabel();
			a2 = new JLabel();
			a3 = new JLabel();
			
			while(rs.next()) {
				dep = rs.getInt("deposit");
				rate = rs.getInt("rate");
			}
			
			rate=rate*daysint;
			total =dep+rate;
			
			//payment label
			a1.setText( Integer.toString(dep));
			a2.setText( Integer.toString(rate));
			a3.setText(Integer.toString(total));
			l1 = new JLabel("Deposit:");
			l2 = new JLabel("Room Rate:");
			l3 = new JLabel("Total:");
			//setting font style
			a1.setFont(font);
			a2.setFont(font);
			a3.setFont(font);
			l1.setFont(font);
			l2.setFont(font);
			l3.setFont(font);
			l.setFont(font);
			//add element into panel
			p.add(l1);
			p.add(a1);
			p.add(l2);
			p.add(a2);
			p.add(l3);
			p.add(a3);
			p.setFont(font);
			p.setBackground(Color.white);
			p.setBounds(285, 180,355, 180);
			p.setLayout(new GridLayout(3,2));
			
			//Check in statement label
			p1.add(l);
			p1.setBounds(35, 40, 720, 40);
			p1.setBackground(bgc);
			//add panels into frame
			payf.add(p);
			payf.add(p1);
			//add button
			payf.add(payfReturn);
			payf.add(payfContinue);
			s.close();
			con.close();
			
			whitePanel(payf);
			
			payf.getContentPane().setBackground(bgc);
			payf.setSize(800, 600);
			payf.setLayout(null);
			payf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			payf.setLocationRelativeTo(null);
			payf.setVisible(true);
		} catch (SQLException e) {
			new ConnectionError();
			System.out.println(e.getMessage());
		}
	}
	public int checkOccupancy(int roomNo) {
	    String sql = "SELECT occupancy FROM room WHERE roomNo=?;";
	    int vac=0;
    		try(Connection con = this.register()) {
    		PreparedStatement s =con.prepareStatement(sql);
    		s.setInt(1, roomNo);
    			ResultSet rs = s.executeQuery();
    			while(rs.next()) {
	    				vac=rs.getInt("occupancy");
				}
    			s.close();
    			rs.close();
    			con.close();
	    		} 
    			catch (SQLException e) {
    				new ConnectionError();
	    			System.out.println(e.getMessage());
	    		}
    		return vac;
	}
	public void updateOccupancy(int roomNo) {
		String sql = "UPDATE room SET occupancy = 1 Where roomNo=?;";
		try(Connection con = this.register()) {
			PreparedStatement s =con.prepareStatement(sql);
			s.setInt(1,roomNo);
			s.executeUpdate();
			s.close();
			con.close();
		} catch (SQLException e) {
			new ConnectionError();
			System.out.println(e.getMessage());
		}		
	}
	public void actionPerformed(ActionEvent e) {
		Client c = new Client();
		if(e.getSource()==back) {
			//back to the main page if back button clicked
			f.setVisible(false);
			if(rchosen!=null) {
				new roomVacancy();
			}
			else {
				new Menu();
			}
		}
		else if(e.getSource()==next) {
			//if confirm button click
			//get text entered to text field
			if(rchosen==null) {
				cname = tname.getText();
				cid = tid.getText();
				ccon = tcon.getText();
				crn = trn.getText();
				cgn = tgn.getText();
			}
			else {
				cname = tname.getText();
				cid = tid.getText();
				ccon = tcon.getText();
				crn = rchosen;
				cgn = tgn.getText();
			}
			cdays=tdays.getText();
			//String convert to integer
			crnint=Integer.parseInt(crn);
			daysint=Integer.parseInt(cdays);
			//make sure all text fields are filled in
			if(cname.length()!=0 && cid.length()!=0 && ccon.length()!=0 &&crn.length()!=0 && cgn.length()!=0) {
				int occr = checkOccupancy(crnint);
				if(occr==0) {//if the room is unoccupied
					c.clientDetails(cname, cid, "6"+ccon, crn, cgn,daysint);//add into client table
					f.setVisible(false);
					confirmDetails(cname);//show confirm statement
				}
				else {//if room is occupied
					new roomUnavailable();
				}
			}//if any text field is empty
			else {
				new CheckinFailed();
			}
		}
		else if(e.getSource()==cinconok) { //confirm statement ok
			cinconf.setVisible(false);
			payable(crn);//payable statement display
		}
		else if(e.getSource()==cinconReturn){//confirm statement not ok
			cinconf.setVisible(false);
			new Checkin();//start a new check in	
			removeCheckin();//remove the check in entered to client details
		}
		else if(e.getSource()==payfReturn) {
			//cancel payment then remove from current in house client table
			//and go back to check in page
			removeCheckin();
			payf.setVisible(false);
			new Checkin();
		}
		else if(e.getSource()==payfContinue) {
			//payment made and complete check in
			updateOccupancy(crnint);
			//insert the record into history table
			c.copyAsHistory(cname, cid, "6"+ccon, crn, cgn,daysint);
			payf.setVisible(false);
			new CheckinComplete();
		}
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new Checkin();
	}
}
