package page1;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.LayoutManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.Border;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

public class roomVacancy implements ActionListener {
	JFrame f = new JFrame();
	JButton exit = new JButton("Menu");
	JButton search = new JButton("Search");
	JButton cin = new JButton("check in");
	String tinput,indate,day;
    int roomNo,capacity,rate,occupancy,col,row;
	String roomType,value;
	DefaultTableModel model = new DefaultTableModel();
	JTable j = new JTable(model);
	JTextField t1 = new JTextField(30);
	Color headerC = new Color(60,125,127);//dark green	
	Color tableC = new Color(239,223, 208);//light orange pink
	Color lbltxt = new Color(152,128,108);
	final TableRowSorter<TableModel> sorter = new TableRowSorter<TableModel>(model);

	roomVacancy(){
		prepareGUI();
	}
	public void prepareGUI() {
		Color c= new Color (176, 224, 230);
		//change bg color
		f.getContentPane().setBackground(c);
		buttonProperties();
		
		//make up whole page
		searchPanel();
		constructTable();
		titleLabel(f);
		whitePanel(f);
		
		f.setSize(800, 600);
		f.setLayout(null);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setLocationRelativeTo(null);
		f.setVisible(true);
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
			new ConnectionError();
			System.out.println(e.getMessage());
		}
		return con;
	}
	public void constructTable() {
		model.addColumn("Room No.");
		model.addColumn("Capacity");
		model.addColumn("Rate");
		model.addColumn("Type");
		model.addColumn("Occupancy");
		findData();
	}
	public void findData() {
	    String sql = "SELECT * FROM room ORDER BY occupancy;";
	    String o;
	    JTableHeader jh=j.getTableHeader();
	    Font fh = new Font("",Font.PLAIN,16);
    		try(Connection con = this.register()) {
    		Statement s =con.createStatement();
    			ResultSet rs = s.executeQuery(sql);
    			while(rs.next()) {
	    				roomNo=rs.getInt("roomNo");
	    				capacity = rs.getInt("capacity");
	    				rate = rs.getInt("rate");
	    				roomType = rs.getString("roomType");
	    				occupancy = rs.getInt("occupancy");
	    				if(occupancy==0) {
	    					o="Unoccupied";
	    				}
	    				else {
	    					o="Occupied";
	    				}
	    				model.addRow(new Object[] {roomNo,capacity,rate,roomType,o});
				}
    			//table header properties
	    		jh.setBackground(headerC);
	    		jh.setForeground(Color.white);
	    		jh.setFont(fh);
	    		//table content properties
	    		j.setFont(fh);
	    		j.setBackground(tableC);
	    		j.setForeground(lbltxt);
	    		j.setRowHeight(35);
	    		
    			JScrollPane sp = new JScrollPane(j, 
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
	private static void titleLabel(JFrame frame){
	      //Create a border line
		  Color textColor = new Color(60,125,128);
	      JPanel panel = new JPanel();	
	      JLabel l = new JLabel(" Room vacancy ");
	      Font font = new Font("",Font.BOLD,32);
	      LayoutManager layout = new BorderLayout();
	      panel.setBounds(15,15,555,330);
	      panel.setLayout(layout);
		  l.setBounds(120, 70, 300 , 80);
		  l.setForeground(textColor);
		  l.setFont(font);
	      panel.add(l,BorderLayout.NORTH);
	      panel.setBackground(Color.white);
	      frame.getContentPane().add(panel, BorderLayout.CENTER);    
	   }

	public void buttonProperties() {
		Color buttonColor = new Color(81,169,173);
		Font font = new Font("",Font.PLAIN,13);
		Border b = BorderFactory.createLineBorder(buttonColor,2);
		
		exit.setBounds(15, 500, 95, 30);
		exit.addActionListener(this);
		exit.setBackground(buttonColor);
		exit.setForeground(Color.white);
		exit.setBorder(b);
		exit.setFont(font);
		f.add(exit);
		
		search.setBounds(645, 75, 80, 30);
		search.addActionListener(this);
		search.setBackground(buttonColor);
		search.setForeground(Color.white);
		search.setBorder(b);
		search.setFont(font);
		f.add(search);
		
		cin.setBounds(675, 500, 95, 30);
		cin.addActionListener(this);
		cin.setBackground(buttonColor);
		cin.setForeground(Color.white);
		cin.setBorder(b);
		cin.setFont(font);
		f.add(cin);
	}
	public void searchPanel() {//enter label setting
		JPanel p = new JPanel();																	//for 'keyword'label
		JPanel p1 = new JPanel();																//for text field
		JLabel l = new JLabel("Keyword:");
		Color grey=new Color(220,231,236);
		Border bgrey = BorderFactory.createLineBorder(grey,2);
		Font f1 = new Font("",Font.PLAIN,16);
		
		//keyword label setting
		l.setForeground(headerC);
		l.setFont(f1);
		
		//search panel's text field setting
		t1 = new JTextField(35);
		t1.setFont(f1);
		t1.setBackground(grey);
		t1.setBorder(bgrey);
		//panel setting
		p1.setBackground(Color.white);
		p.setBackground(Color.white);
		p.setBounds(60, 73, 80, 25);
		p1.setBounds(140, 73, 530, 30);
		p.add(l);
		p1.add(t1);
		f.add(p1);
		f.add(p);
	}
	public void calling() {																//enter label setting
		row = j.getSelectedRow();													//get row selected
		value = j.getModel().getValueAt(row, 0).toString();			//get room number of selected row
	}
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==exit) {
			f.setVisible(false);
			new Menu();
		}
		else if(e.getSource()==search) {											//search button 
			j.setRowSorter(sorter);													//set filtering function
			tinput = t1.getText();														//get input from search panel text field
			if(tinput.length()==0) {														//if nothing entered
				sorter.setRowFilter(null);
			}
			else {
				sorter.setRowFilter(RowFilter.regexFilter(tinput));
			}
		}
		else if(e.getSource()==cin) {
			f.setVisible(false);
			if(j.getSelectedRow()==-1) {											//if no row selected
				new Checkin();
			}
			else {
				calling();																		//get value from selected row
				new Checkin(value);														//check in with selected room from vacancy page
			}
		}
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new roomVacancy()	;
	}
}
