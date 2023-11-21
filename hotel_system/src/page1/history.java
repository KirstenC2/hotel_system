package page1;

import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.table.*;

public class history implements ActionListener {
	JFrame f = new JFrame("History record"),conf;
	JButton exit ,search,delete,confirm,cancel;
	JTextField t1;
	Color headerC = new Color(60,125,127);//dark green	
	Color tableC = new Color(239,223, 208);//light orange pink
	DefaultTableModel model = new DefaultTableModel();
	JTable table = new JTable(model);
	String value;
	int row;
	final TableRowSorter<TableModel> sorter = new TableRowSorter<TableModel>(model);
	history(){
		prepareGUI();
	}
	public void prepareGUI() {
		Color c= new Color (176, 224, 230);
		//change bg color
		f.getContentPane().setBackground(c);
		
		buttonProperties();
		searchPanel();
		constructTable();
		headerWhitePanel(f);
		
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
	public void constructTable() {
		model.addColumn("Guest name");
		model.addColumn("ID number");
		model.addColumn("Contact");
		model.addColumn("Room No.");
		model.addColumn("In date");
		model.addColumn("Out date");
		findData();
	    }
	public void findData() {
		String name,id,contact,indate,outdate;
		int roomNo;
		Color lbltxt = new Color(152,128,108);
	    String sql = "SELECT * FROM history ORDER BY outDate;";
	    JTableHeader tableHeader=table.getTableHeader();
	    Font fh = new Font("",Font.PLAIN,16);
    		try(Connection con = this.register()) {
    		Statement s =con.createStatement();
    			ResultSet rs = s.executeQuery(sql);
    			while(rs.next()) {
	    				name=rs.getString("name");
	    				id = rs.getString("id");
	    				contact = rs.getString("contact");
	    				roomNo = rs.getInt("roomNo");
	    				indate = rs.getString("inDate");
	    				outdate = rs.getString("outDate");
	    				model.addRow(new Object[] {name,id,contact,Integer.toString(roomNo),indate,outdate});
				}
    			table.setRowHeight(35);
    			//center align contents  in jtable
	    		DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
	    		centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);
	    		for(int i=0 ; i<table.getColumnCount() ; i++) {
	    			table.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
	    		}
	    		table.setBackground(tableC);
	    		table.setForeground(lbltxt);
	    		table.setFont(fh);	
	    		table.getColumnModel().getColumn(3).setPreferredWidth(30);
	    		tableHeader.setBackground(headerC);
	    		tableHeader.setForeground(Color.white);
	    		tableHeader.setFont(fh);
	    		tableHeader.setPreferredSize(new Dimension(100,40));
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
	private static void headerWhitePanel(JFrame frame){
		   //Create a border line
			Color textColor = new Color(60,125,128);
			JPanel panel = new JPanel();
			JLabel l = new JLabel(" History records ");
			Font font = new Font("",Font.BOLD,32);
			LayoutManager layout = new BorderLayout();
			
			l.setBounds(120, 70, 300 , 80);
			l.setForeground(textColor);
			l.setFont(font);
			
			panel.setBounds(15,15,755,530);
			panel.setLayout(layout);
			panel.add(l,BorderLayout.NORTH);
			panel.setBackground(Color.white);
			frame.getContentPane().add(panel, BorderLayout.CENTER);    
	  }
	public void buttonProperties() {
		Color exitColor = new Color(81,169,173);
		Border green = BorderFactory.createLineBorder(exitColor,2);
		Font f1 = new Font("",Font.PLAIN,14);
		
		exit = new JButton("Exit");
		exit.setBounds(15, 500, 95, 30);
		exit.addActionListener(this);
		exit.setBackground(exitColor);
		exit.setFont(f1);
		exit.setForeground(Color.white);
		exit.setBorder(green);
		f.add(exit);
		
		delete = new JButton("Delete");
		delete.setBounds(675, 500, 95, 30);
		delete.addActionListener(this);
		delete.setBackground(exitColor);
		delete.setForeground(Color.white);
		delete.setFont(f1);
		delete.setBorder(green);
		f.add(delete);
		
		search = new JButton("Search");
		search.setBounds(645, 75, 80, 30);
		search.addActionListener(this);
		search.setBackground(exitColor);
		search.setFont(f1);
		search.setForeground(Color.white);
		search.setBorder(green);
		f.add(search);
		
		confirm = new JButton("Confirm");
		confirm.setBounds(150, 70, 60,30 );
		confirm.addActionListener(this);
		confirm.setBackground(exitColor);
		confirm.setFont(f1);
		confirm.setForeground(Color.white);
		confirm.setBorder(green);
		
		cancel = new JButton("Cancel");
		cancel.setBounds(60, 70, 60,30 );
		cancel.addActionListener(this);
		cancel.setBackground(exitColor);
		cancel.setFont(f1);
		cancel.setForeground(Color.white);
		cancel.setBorder(green);
	}
	public void searchPanel() {//enter label setting
		JPanel labelPanel = new JPanel();
		JPanel tfPanel = new JPanel();
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
		
		tfPanel.setBackground(Color.white);
		labelPanel.setBackground(Color.white);
		
		labelPanel.setBounds(60, 73, 80, 25);
		tfPanel.setBounds(140, 73, 530, 30);
		
		labelPanel.add(l);
		tfPanel.add(t1);

		f.add(tfPanel);
		f.add(labelPanel);
	}
	public String getRow() {
		row = table.getSelectedRow();
		value = table.getModel().getValueAt(row, 1).toString();
		return value;
	}
	public void deleteRow() {
		String idSelected = getRow();
		String sql =  "Delete FROM  history WHERE  id = ? ;";
		try(Connection con = this.register()) {
			PreparedStatement s =con.prepareStatement(sql);
			s.setString(1, idSelected);
			s.executeUpdate();
			s.close();
			con.close();
		}
		catch(SQLException e){
			e.printStackTrace();
		}
		deleteMessage();
		model.removeRow(row);
	}
	public void confirmMessage() {
		conf = new JFrame("Confirmation");
		JLabel l = new JLabel("Confirm to delete ?");
		JPanel p = new JPanel();
		conf.add(confirm);
		conf.add(cancel);
		p.add(l);
		conf.add(p);
		
		p.setBounds(40, 30, 200, 60);
		conf.setSize(300, 150);
		conf.setLayout(null);
		conf.setLocationRelativeTo(null);
		conf.setVisible(true);
	}
	public void deleteMessage() {
		JFrame deletePage = new JFrame("Message");
		JLabel l = new JLabel("Record deleted.");
		JPanel p = new JPanel();
		p.add(l);
		deletePage.add(p);
		p.setBounds(20, 10, 150, 30);
		deletePage.setSize(200, 100);
		deletePage.setLayout(null);
		deletePage.setLocationRelativeTo(null);
		deletePage.setVisible(true);
	}
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==exit) {
			f.setVisible(false);
			new Menu();
		}
		else if(e.getSource()==search) {
			String tinput;
			table.setRowSorter(sorter);
			tinput = t1.getText();//get keyword from search bar
			if(tinput.length()==0) {//if nothing entered
				sorter.setRowFilter(null);
			}
			else {
				sorter.setRowFilter(RowFilter.regexFilter(tinput));
			}
		}
		else if(e.getSource()==delete) {
			confirmMessage();
		}
		else if(e.getSource()==confirm) {
			conf.setVisible(false);
			deleteRow();
		}
		else if(e.getSource()==cancel) {
			conf.setVisible(false);
		}
	}
	public static void main(String[] args) {
		new history()	;
	}
}
