package page1;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.time.LocalDate;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

public class CurrentGuest implements ActionListener{
	JFrame f = new JFrame("Current in house guests");
	JButton exit = new JButton("Exit");
	JButton search = new JButton("Search");
	
	LocalDate estimatedDeparture;
	DefaultTableModel model = new DefaultTableModel();
	JTable j = new JTable(model);
	JTextField t1 = new JTextField(30);
	Color headerC = new Color(60,125,127);//dark green	
	Color tableC = new Color(239,223, 208);//light orange pink
	Color lbltxt = new Color(152,128,108);
	Font f1 = new Font("",Font.PLAIN,14);
	final TableRowSorter<TableModel> sorter = new TableRowSorter<TableModel>(model);
	int guestNo,roomNo;
	CurrentGuest(){
		prepareGUI();
	}
	public void whitePanel( JFrame frameIn) {						//creating white background
		JPanel whiteBg = new JPanel();
		whiteBg.setBounds(15,15,755,530);
		whiteBg.setLayout(null);
		whiteBg.setBackground(Color.white);
		frameIn.getContentPane().add(whiteBg);
	}
	public void prepareGUI() {
		Color bgc= new Color (176, 224, 230);
		//change bg color
		f.getContentPane().setBackground(bgc);
		buttonProperties();
		//create border 
		searchPanel();//keyword and search bar
		headerColumn(); //make table column
		headerWhitePanel(f);//white back panel
		
		f.setSize(800, 600);
		f.setLayout(null);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setLocationRelativeTo(null);
		f.setVisible(true);
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
	public void headerColumn() {										//create header of jtable
		model.addColumn("Guest Name");
		model.addColumn("ID No.");
		model.addColumn("Contact No.");
		model.addColumn("Room No.");
		model.addColumn("Check-in D.");
		model.addColumn("E.D.D.");
		model.addColumn("Overstay");
		findData();
	    }
	public void findData() {
	    String sql = "SELECT * FROM client ORDER BY date;";			//search data from database for table
	    JTableHeader jh=j.getTableHeader();
	    LocalDate date;
	    String name,id,contact,indate;
	    int days;
    		try(Connection con = this.register()) {
    		Statement s =con.createStatement();
    		ResultSet rs = s.executeQuery(sql);
    			while(rs.next()) {
	    				name=rs.getString("name");
	    				id = rs.getString("id");
	    				contact = rs.getString("contact");
	    				roomNo = rs.getInt("roomNo");
	    				indate = rs.getString("date");
	    				days = rs.getInt("days");
	    				date = LocalDate.parse(indate);
	    				long dday = days;
	    				estimatedDeparture = date.plusDays(dday);
	    				String rn=Integer.toString(roomNo);
	    				String tickSymbol= " \u2713";																					//unicode for tick
	    				if(LocalDate.now().isAfter(estimatedDeparture)) {
	    					model.addRow(new Object[] {name,id,contact,rn,indate,estimatedDeparture,tickSymbol});				
	    				}
	    				else {
	    					model.addRow(new Object[] {name,id,contact,rn,indate,estimatedDeparture});
	    				}
				}
    			//setting JTable properties
    			j.getColumnModel().getColumn(3).setPreferredWidth(40);
    			j.getColumnModel().getColumn(4).setPreferredWidth(48);
    			j.getColumnModel().getColumn(5).setPreferredWidth(60);
    			j.getColumnModel().getColumn(6).setPreferredWidth(30);
    			j.setRowHeight(35);
	    		jh.setPreferredSize(new Dimension(100,40));//change header row height
	    		//jtable center text alignment
	    		DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
	    		centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);
	    		for(int i=0;i<j.getColumnCount();i++) {
	    			j.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
	    		}
	    		//setting color 
	    		j.setBackground(tableC);
	    		j.setForeground(lbltxt);
	    		jh.setBackground(headerC);
	    		jh.setForeground(Color.white);
	    		
	    		Font fh = new Font("",Font.PLAIN,16);
	    		jh.setFont(fh);
	    		j.setFont(fh);
	    		//scroll bar
    			JScrollPane sp = new JScrollPane(j, 
    					ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED, 
    					ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
    			sp.setBounds(50, 120, 680, 330);																				//setting table size
				f.add(sp);
    			s.close();
    			rs.close();
    			con.close();
	    		} 
    			catch (SQLException e) {
	    			System.out.println(e.getMessage());
	    		}
	}
	private static void headerWhitePanel(JFrame frame){
	      //Create a border line
		Color textColor = new Color(60,125,128);
		JPanel panel = new JPanel();
		JLabel titleLabel = new JLabel(" Current staying guest ");
		Font font = new Font("",Font.BOLD,32);
		LayoutManager layout = new BorderLayout();
		
		titleLabel.setBounds(120, 70, 300 , 80);
		titleLabel.setForeground(textColor);
		titleLabel.setFont(font);
		
		panel.setBounds(15,15,755,530);
		panel.setLayout(layout);
		panel.add(titleLabel,BorderLayout.NORTH);
		panel.setBackground(Color.white);
		frame.getContentPane().add(panel, BorderLayout.CENTER);    
	   }
	public void buttonProperties() {
		Color buttonColor = new Color(81,169,173);
		Border greenborder = BorderFactory.createLineBorder(buttonColor,2);
		exit = new JButton("Menu");
		exit.setBounds(15, 500, 95, 30);
		exit.addActionListener(this);
		exit.setBackground(buttonColor);
		exit.setFont(f1);
		exit.setForeground(Color.white);
		exit.setBorder(greenborder);
		f.add(exit);
		
		search.setBounds(645, 75, 80, 30);
		search.addActionListener(this);
		search.setBackground(buttonColor);
		search.setForeground(Color.white);
		search.setBorder(greenborder);
		search.setFont(f1);
		f.add(search);
	}
	public void searchPanel() {//enter label setting
		JPanel headerPanel = new JPanel();
		JPanel textArea = new JPanel();
		JLabel labelKeyword = new JLabel("Keyword:");
		Color grey=new Color(220,231,236);
		Border greyBorder = BorderFactory.createLineBorder(grey,2);
		Font f1 = new Font("",Font.PLAIN,16);
		
		labelKeyword.setForeground(headerC);
		labelKeyword.setFont(f1);
		
		t1 = new JTextField(35);
		t1.setFont(f1);
		t1.setBackground(grey);
		t1.setBorder(greyBorder);
		
		textArea.setBackground(Color.white);
		headerPanel.setBackground(Color.white);
		
		headerPanel.add(labelKeyword);
		textArea.add(t1);
		
		headerPanel.setBounds(60, 73, 80, 25);
		textArea.setBounds(140, 73, 530, 30);
		
		f.add(textArea);
		f.add(headerPanel);
	}
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==exit) {
			f.setVisible(false);
			new Menu();
		}
		else if(e.getSource()==search) {
			String tinput;
			j.setRowSorter(sorter);
			tinput = t1.getText();
			if(tinput.length()==0) {
				sorter.setRowFilter(null);
			}
			else {
				sorter.setRowFilter(RowFilter.regexFilter(tinput));
			}
		}
	}
	public static void main(String[] args) {
		new CurrentGuest()	;
	}
}
