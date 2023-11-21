package page1;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;


public class CheckOut implements ActionListener {
	JFrame f = new JFrame("Check out");
	JFrame addiChargePage,refundPage,confirmPage;
	JButton acp_Back,exit,confirm,search,next,amtEnter,rfdnext,reenterchg,conret;
	DefaultTableModel model = new DefaultTableModel();
	JTextField t1,chargesTf;
	JTable table = new JTable(model);
	String oname,ocon,oid,oindate, name,id,contact,tinput,inputChg,value,roomValue,cindate,i;//return string in check out
	int confRoomNo,roomNo,chargeAmtint,ostayed, roomrate;
	Color bgc= new Color (176, 224, 230);
	Color drgreen = new Color(81,169,173);
	Color headerC = new Color(60,125,127);
	Color buttonColor = new Color(81,169,173);
	Color brown = new Color(155,110,87);
	Font f1 = new Font("",Font.PLAIN,20);
	Font font = new Font("",Font.PLAIN,13);
	Border b = BorderFactory.createLineBorder(buttonColor,2);
	
	CheckOut(){
		prepareGUI();
		whitePanel(f);
	}
	public void prepareGUI() {
		
		buttonProperties();//button
		searchPanel();//setting enter label
		makeColumn();//create table
		checkoutHeader(f);//create white base 
		
		f.setSize(800, 600);
		f.setLayout(null);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setLocationRelativeTo(null);
		f.getContentPane().setBackground(bgc);//change bg color
		f.setVisible(true);
	}
	private static void checkoutHeader(JFrame frame){
	      //Create a border line
		Color textColor = new Color(60,125,128);
		JPanel panel = new JPanel();
		JLabel checkoutLabel = new JLabel(" Check Out ");
		Font f1 = new Font("",Font.PLAIN,30);
		LayoutManager layout = new BorderLayout();
		
		checkoutLabel.setForeground(textColor);
		checkoutLabel.setFont(f1);
		
		panel.setBackground(Color.white);
		panel.setBounds(15,15,555,100);
		panel.setLayout(layout);
		panel.add(checkoutLabel,BorderLayout.NORTH);
		
		frame.getContentPane().add(panel, BorderLayout.CENTER);    
	   }
	public void whitePanel( JFrame f1) {
		JPanel panel = new JPanel();
		panel.setBounds(15,15,755,530);
		panel.setLayout(null);
		panel.setBackground(Color.white);
		f1.getContentPane().add(panel);
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
			System.out.println(e.getMessage());
		}
		return con;
	}
	public void buttonProperties() {
		exit = new JButton("Menu");
		exit.setBounds(15, 500, 95, 30);
		exit.addActionListener(this);
		exit.setBackground(buttonColor);
		exit.setFont(font);
		exit.setForeground(Color.white);
		exit.setBorder(b);
		f.add(exit);
		
		
		search = new JButton("Search");
		search.setBounds(645, 75, 80, 30);
		search.addActionListener(this);
		search.setBackground(buttonColor);
		search.setFont(font);
		search.setForeground(Color.white);
		search.setBorder(b);
		f.add(search);
		
		next = new JButton("Proceed");
		next.setBounds(675, 500, 95, 30);
		next.addActionListener(this);
		next.setBackground(buttonColor);
		next.setForeground(Color.white);
		next.setFont(font);
		next.setBorder(b);
		f.add(next);
		
		conret = new JButton("Back");
		conret.setBounds(15, 500, 95, 30);
		conret.addActionListener(this);
		conret.setBackground(buttonColor);
		conret.setForeground(Color.white);
		conret.setFont(font);
		conret.setBorder(b);
		
		acp_Back = new JButton("Back");
		acp_Back.setBounds(15, 500, 95, 30);
		acp_Back.addActionListener(this);
		acp_Back.setBackground(buttonColor);
		acp_Back.setForeground(Color.white);
		acp_Back.setFont(font);
		acp_Back.setBorder(b);
		
		confirm = new JButton("Confirm");
		confirm.setBounds(675, 500, 95, 30);
		confirm.addActionListener(this);
		confirm.setBackground(buttonColor);
		confirm.setForeground(Color.white);
		confirm.setFont(font);
		confirm.setBorder(b);
		
		amtEnter = new JButton("Enter");
		amtEnter.setBounds(675, 500, 95, 30);
		amtEnter.addActionListener(this);
		amtEnter.setBackground(buttonColor);
		amtEnter.setForeground(Color.white);
		amtEnter.setFont(font);
		amtEnter.setBorder(b);
		
		rfdnext = new JButton("Done");
		rfdnext.setBounds(675, 500, 95, 30);
		rfdnext.addActionListener(this);
		rfdnext.setBackground(buttonColor);
		rfdnext.setForeground(Color.white);
		rfdnext.setFont(font);
		rfdnext.setBorder(b);
		
		reenterchg = new JButton("Reenter");
		reenterchg.setBounds(15, 500, 95, 30);
		reenterchg.addActionListener(this);
		reenterchg.setBackground(buttonColor);
		reenterchg.setForeground(Color.white);
		reenterchg.setFont(font);
		reenterchg.setBorder(b);
	}
	public void searchPanel() {//searching banner
		JPanel p = new JPanel();
		JPanel p1 = new JPanel();
		JLabel l = new JLabel("Keyword:");
		Color grey=new Color(220,231,236);
		Border bgrey = BorderFactory.createLineBorder(grey,2);
		Font f1 = new Font("",Font.PLAIN,16);

		l.setForeground(headerC);
		l.setFont(f1);
		
		t1 = new JTextField(35);
		t1.setFont(f1);
		t1.setBackground(grey);
		t1.setBorder(bgrey);
		
		p1.setBackground(Color.white);
		p.setBackground(Color.white);
		
		p.add(l);
		p1.add(t1);
		
		p.setBounds(60, 73, 80, 25);
		p1.setBounds(140, 73, 530, 30);
		
		f.add(p1);
		f.add(p);
	}
	public void makeColumn() {
		//table column
		model.addColumn("Guest name");
		model.addColumn("ID number");
		model.addColumn("Contact");
		model.addColumn("Room No.");
		model.addColumn("Checkin Date");
		findData();
	    }
	public void findData() {
	    String sql = "SELECT * FROM client;";
	    JTableHeader jh=table.getTableHeader();
	    Color textColor = new Color(152,128,108);
	    Color tableC = new Color(239,223, 208);
	    Font fh = new Font("",Font.PLAIN,16);
    		try(Connection con = this.register()) {
    		Statement s =con.createStatement();
    			ResultSet rs = s.executeQuery(sql);
    			while(rs.next()) {
	    				name=rs.getString("name");
	    				id = rs.getString("id");
	    				contact = rs.getString("contact");
	    				roomNo = rs.getInt("roomNo");
	    				String date = rs.getString("date");
	    				String rn=Integer.toString(roomNo);
	    				model.addRow(new Object[] {name,id,contact,rn,date});
				}
    			table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
    			table.setRowHeight(35);
	    		//center align in jtable
	    		DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
	    		centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);
	    		for(int i=0;i<table.getColumnCount();i++) {
	    			table.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
	    		}
	    		table.setBackground(tableC);
	    		table.setForeground(textColor);
	    		table.setFont(fh);
	    		jh.setFont(fh);
	    		jh.setBackground(headerC);
	    		jh.setForeground(Color.white);
	    		//setting scroll pane to table
    			JScrollPane sp = new JScrollPane(table, 
    					ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED, 
    					ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
    			sp.setBounds(50, 120, 680, 330);
				f.add(sp);
    			s.close();
    			rs.close();
    			con.close();
	    		} 
    			catch (SQLException e) {
	    			System.out.println(e.getMessage());
	    		}
	}
	public LocalDate findEstimatedCheckout() {
		String sql = "SELECT date,days FROM client WHERE name = ?;";
		String dayStayed = null;
		LocalDate estiCheckout = null;
		int estidays = 0;
		try(Connection con = this.register()) {
    		PreparedStatement s =con.prepareStatement(sql);
    		s.setString(1,value);
    			ResultSet rs = s.executeQuery();
    			while(rs.next()) {
	    				dayStayed=rs.getString("date");
	    				estidays = rs.getInt("days");
				}
    			LocalDate ldayStayed = LocalDate.parse(dayStayed);
    			estiCheckout = ldayStayed.plusDays(estidays); 
		}
		catch (SQLException e) {
			new ConnectionError();
			System.out.println(e.getMessage());
		}
		return estiCheckout;
	}
	
	public void confirmationPage() {
		confirmPage = new JFrame("Check details before check out");
		Font head= new Font("",Font.BOLD,26);
		JPanel p = new JPanel();
		JLabel l = new JLabel("Confirm Guest Details");
		
		//get value from selected row
		int row = table.getSelectedRow();
		value = table.getModel().getValueAt(row, 0).toString();//name
		roomValue = table.getModel().getValueAt(row, 3).toString();//room number
		cindate =  table.getModel().getValueAt(row, 4).toString(); //get date		
		
		//call confirmation page
		p.add(l);
		p.setBackground(bgc);
		l.setForeground(drgreen);
		l.setFont(head);
		p.setBounds(40, 40, 700, 40);
		
		confirmDetails(value,confirmPage);
		confirmPage.add(confirm);//add button
		confirmPage.add(conret);
		confirmPage.add(p);
		whitePanel(confirmPage);
		
		confirmPage.setSize(800, 600);
		confirmPage.setLayout(null);
		confirmPage.getContentPane().setBackground(bgc);
		confirmPage.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		confirmPage.setLocationRelativeTo(null);
		confirmPage.setVisible(true);
	}
	public int compareDays() {
		LocalDate estiCheckout = findEstimatedCheckout();
		LocalDate today = LocalDate.now();
		if(estiCheckout == today) {
			return 0;
		}
		else {
			//count over stayed days
			long noOfDaysBetween = ChronoUnit.DAYS.between(estiCheckout,today);
			return (int)noOfDaysBetween;
		}
	}
	public int getRoomRate(String roomno){
		String sql =  "SELECT rate FROM  room WHERE  roomNo = ? ;";
		int rrate = 0;
		try(Connection con = this.register()) {
			PreparedStatement s = con.prepareStatement(sql);
			s.setString(1, roomno);
			ResultSet rs = s.executeQuery();
			while(rs.next()) {
				rrate = rs.getInt("rate");
			}
			s.close();
			con.close();
		}
		catch(SQLException e){
			e.printStackTrace();
		}
		return rrate;
	} 
	public void confirmDetails(String name,JFrame fc) {
		String sql = "SELECT * FROM client WHERE name = ?;";
		
		try(Connection con = this.register()) {
			PreparedStatement s = con.prepareStatement(sql);
			s.setString(1, name);
			ResultSet rs = s.executeQuery();
			
			JPanel p = new JPanel();
			JLabel l1,l2,l3,l4,l5;
			JLabel a1,a2,a3,a4,a5;
			
			a1 = new JLabel();
			a2 = new JLabel();
			a3 = new JLabel();
			a4 = new JLabel();
			a5 = new JLabel();
			//get data from client table
			while(rs.next()) {
				oname = rs.getString("name");
				ocon = rs.getString("contact");
				oid = rs.getString("id");
				confRoomNo = rs.getInt("roomNo");
				oindate = rs.getString("date");
			}
			
			a1.setText(name);
			a2.setText(ocon);
			a3.setText(oid);
			a4.setText( Integer.toString(confRoomNo));
			a5.setText(oindate);
			
			l1 = new JLabel("Name:");
			l2 = new JLabel("ID Number:");
			l3 = new JLabel("Contact:");
			l4 = new JLabel("Room Number:");
			l5 = new JLabel("Check in Date:");
			
			a1.setFont(f1);
			a2.setFont(f1);
			a3.setFont(f1);
			a4.setFont(f1);
			a5.setFont(f1);
			
			a1.setForeground(brown);
			a2.setForeground(brown);
			a3.setForeground(brown);
			a4.setForeground(brown);
			a5.setForeground(brown);
			
			l1.setFont(f1);
			l2.setFont(f1);
			l3.setFont(f1);
			l4.setFont(f1);
			l5.setFont(f1);
			
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
			p.setBackground(Color.white);
			p.setBounds(200, 100,500, 400);
			p.setLayout(new GridLayout(6,2));
			fc.add(p);
			s.close();
			con.close();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}
	public void updateCheckoutDate() {
		String sql = "UPDATE history SET outDate = ? WHERE name = ? ;";
		try(Connection con = this.register()) {
			PreparedStatement s =con.prepareStatement(sql);
			LocalDate date = LocalDate.now();
			String sdate =date.toString();
			s.setString(1,sdate);
			s.setString(2, value);
			s.executeUpdate();
			s.close();
			con.close();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}		
	}
	public void removeClient() {
		String sql = "DELETE FROM client WHERE name = ? ;";
		try(Connection con = this.register()) {
			PreparedStatement s =con.prepareStatement(sql);
			s.setString(1, value);
			s.executeUpdate();
			s.close();
			con.close();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}		
	}
	public void chargesPage(int overstayDays) {
		addiChargePage=new JFrame("Additional Charges");
		JPanel chgLabelPanel = new JPanel();
		JPanel tfPanel = new JPanel();
		JPanel overstayPanel = new JPanel();
		JLabel chargesLbl = new JLabel("Additional Charges?");
		JLabel over, rate = new JLabel();
		JLabel overstayLabel = new JLabel();
		Font font = new Font("Arial",Font.PLAIN,50);
		Border border = BorderFactory.createLineBorder(drgreen,5);
		String ovsdays,roomrate_str;
		if(overstayDays<=0) {
			over = new JLabel("");
			ovsdays = "No over stay charges";
		}
		else {
			roomrate = getRoomRate(roomValue);//find room rate
			roomrate=roomrate*overstayDays;//total room rate
			roomrate_str = Integer.toString(roomrate);
			rate.setText(roomrate_str);
			rate.setForeground(buttonColor);
			over = new JLabel("Overstay Charges:");
			over.setForeground(drgreen);
			ovsdays = "for " + Integer.toString(overstayDays) + " days";
		}
		overstayLabel.setText(ovsdays);
		overstayLabel.setForeground(drgreen);
		
		chgLabelPanel.setBounds(120, 200, 545, 100);
		chgLabelPanel.setBackground(Color.white);
		Color tfColor = new Color(191,227,231);
		chargesTf = new JTextField(50);
		chargesTf.setSize(300,80);
		chargesTf.setFont(font);
		chargesTf.setBorder(border);
		chargesTf.setForeground(brown);
		chargesTf.setBackground(tfColor);
		chargesTf.setHorizontalAlignment(JTextField.CENTER);
		
		chargesLbl.setFont(font);
		chargesLbl.setForeground(drgreen);
		chgLabelPanel.add(chargesLbl);
		
		tfPanel.setLayout(null);
		tfPanel.setBounds(240, 300, 300, 80);
		tfPanel.add(chargesTf);
		
		overstayPanel.setLayout(new FlowLayout());
		overstayPanel.setBackground(Color.white);
		overstayPanel.setBounds(190, 260, 350, 30);
		overstayPanel.add(over);
		overstayPanel.add(rate);
		overstayPanel.add(overstayLabel);
		
		addiChargePage.add(overstayPanel);
		addiChargePage.add(tfPanel);
		addiChargePage.add(chgLabelPanel);
		addiChargePage.add(amtEnter);
		addiChargePage.add(acp_Back);
		whitePanel(addiChargePage);
		addiChargePage.setSize(800, 600);
		addiChargePage.setLayout(null);
		addiChargePage.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		addiChargePage.setLocationRelativeTo(null);
		addiChargePage.setVisible(true);
		addiChargePage.getContentPane().setBackground(bgc);
	}

	public void refundPage() {
		refundPage=new JFrame("Refund/ payment statement");//refund frame
		JLabel l = new JLabel("Refund / charges statement");
		String sql = "SELECT r.deposit,r.rate, c.days,c.name FROM room r INNER JOIN client c  WHERE r.roomNo = ? ;";
		Font titleFont = new Font("",Font.BOLD,26);
		int dep=0;
		try(Connection con = this.register()) {
			PreparedStatement s = con.prepareStatement(sql);
			s.setInt(1, confRoomNo);
			ResultSet rs = s.executeQuery();
			
			JPanel p = new JPanel();
			JPanel p1 = new JPanel();
			JPanel note = new JPanel();
			JLabel l1,l2,l3,l4,l5;
			JLabel a1,a2,a3,a4,a5;
			int erate = 0,dday = 0,ttl;
			
			a1 = new JLabel();
			a2 = new JLabel();
			a3 = new JLabel();
			a4 = new JLabel();
			a5 = new JLabel();
			
			while(rs.next()) {
				dep = rs.getInt("deposit");
				erate = rs.getInt("rate");
				dday = rs.getInt("days");
			}
			chargeAmtint = Integer.parseInt(inputChg) ;
			inputChg = Integer.toString(chargeAmtint);
			ttl = (erate*dday)+dep;
			int refundAmt = (roomrate+chargeAmtint) - dep;
			if(refundAmt>=0) {
				l5 = new JLabel("Amount receivable:");
			}
			else {
				l5 = new JLabel("Amount refundable:");
				refundAmt=refundAmt*-1;
			}
			l.setForeground(drgreen);
			l1 = new JLabel("Guest name:");
			l2 = new JLabel("Total paid:");
			l3 = new JLabel("Deposit:");
			l4 = new JLabel("Additional Charges:");
			
			l.setFont(titleFont);
			l1.setFont(f1);
			l2.setFont(f1);
			l3.setFont(f1);
			l4.setFont(f1);
			l5.setFont(f1);

			int chgEntered = Integer.parseInt(inputChg);				//charges entered by user
			int r1 = roomrate+chgEntered;										//charges entered+over stay charge
			a1.setText(oname);
			a2.setText(Integer.toString(ttl));
			a3.setText(Integer.toString(dep));
			a4.setText(Integer.toString(r1));
			a5.setText(Integer.toString(refundAmt));
			
			a1.setFont(f1);
			a2.setFont(f1);
			a3.setFont(f1);
			a4.setFont(f1);
			a5.setFont(f1);
			
			a1.setForeground(brown);
			a2.setForeground(brown);
			a3.setForeground(brown);
			a4.setForeground(brown);
			a5.setForeground(brown);
			
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
			
			refundPage.add(rfdnext);
			refundPage.add(reenterchg);
			
			p.setBackground(Color.white);
			p.setBounds(220, 100,500, 350);
			p.setLayout(new GridLayout(5,2));
			
			//Check in statement label
			p1.add(l);
			p1.setBounds(45, 45, 700, 40);
			p1.setBackground(bgc);
			//add panels into frame
			refundPage.add(note);
			refundPage.add(p);
			refundPage.add(p1);
			
			//add button
			s.close();
			con.close();
			whitePanel(refundPage);
			refundPage.getContentPane().setBackground(bgc);
			refundPage.setSize(800, 600);
			refundPage.setLayout(null);
			refundPage.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			refundPage.setLocationRelativeTo(null);
			refundPage.setVisible(true);
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}
	public void inputMessage() {
		JFrame messagePage = new JFrame("Message");
		JLabel l = new JLabel("Please enter number.");
		JPanel p = new JPanel();
		Font f3 = new Font("",Font.BOLD,14);
		l.setFont(f3);
		p.add(l);
		l.setForeground(drgreen);
		p.setBackground(bgc);
		p.setBounds(20, 10, 150, 30);
		messagePage.add(p);
		messagePage.getContentPane().setBackground(bgc);
		messagePage.setSize(200, 100);
		messagePage.setLayout(null);
		messagePage.setLocationRelativeTo(null);
		messagePage.setVisible(true);
	}
	public void updateOccupancyStatus(String roomNo) {
		int rnInt = Integer.parseInt(roomNo);
		String sql = "UPDATE room SET occupancy = 0 Where roomNo=?";
		try(Connection con = this.register()) {
			PreparedStatement s =con.prepareStatement(sql);
			s.setInt(1,rnInt);
			s.executeUpdate();
			s.close();
			con.close();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}		
	}
	public void actionPerformed(ActionEvent e) {
		final TableRowSorter<TableModel> sorter = new TableRowSorter<TableModel>(model);
		if(e.getSource()==exit) {
			f.setVisible(false);
			new Menu();
		}
		else if(e.getSource()==search) {
			table.setRowSorter(sorter);														//add filter function to jtable
			tinput = t1.getText();																	//get text from search panel
			if(tinput.length()==0) {
				sorter.setRowFilter(null);
			}
			else {
				sorter.setRowFilter(RowFilter.regexFilter(tinput));				//filter the table by keyword entered
			}
		}
		else if(e.getSource()==next) {
			f.setVisible(false);																		//main frame closed
			confirmationPage();																		//open confirmation page to check guest details
		}
		else if(e.getSource()==confirm) {
			confirmPage.setVisible(false);
			if(compareDays()==0) {																//no over stay
					chargesPage(0);
			}
			else {																							//if over stayed
				ostayed=compareDays();															//check how many days over stayed
				chargesPage(ostayed);															//calculate the over stay charges
			}
		}
		else if(e.getSource()==amtEnter) {
			inputChg = chargesTf.getText();													//get additional charges entered
			if(inputChg.length()==0) {																//no input
				inputMessage();																		//ask user to input number
			}
			else {
				addiChargePage.setVisible(false);
				refundPage();
			}
		}
		else if(e.getSource()==rfdnext) {
			removeClient();
			updateOccupancyStatus(roomValue);								//update room condition
			refundPage.setVisible(false);											//close refund page
			updateCheckoutDate();
			new CheckoutComplete();
		}
		else if(e.getSource()==reenterchg) {
			ostayed=compareDays();													//check over stay
			refundPage.setVisible(false);											//close refund page
			chargesPage(ostayed);
		}
		else if(e.getSource()==conret) {											//return button on confirmation page
			new CheckOut();
			confirmPage.setVisible(false);
		}
		else if(e.getSource()==acp_Back) {									//back button on additional charges
			new CheckOut();
			addiChargePage.setVisible(false);
			
		}
	}
	public static void main(String[] args) {
		new CheckOut();
	}

}
