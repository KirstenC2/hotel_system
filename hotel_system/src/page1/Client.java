package page1;
import java.sql.*;
import java.time.LocalDate;

//for check in purpose
public class Client {
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
	//create client table if not exists
	public void createClientTable() {
		String sql = "CREATE TABLE IF NOT EXISTS client("
				+"name VARCHAR NOT NULL, "
				+"id String NOT NULL,"
				+ "contact String,"
				+ "roomType String NOT NULL"
				+ "date date not null);";
		Statement s;
		try(Connection con = this.register()) {
			s = con.createStatement();
			s.execute(sql);
			s.close();
			con.close();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}		
	}
	public void clientDetails(String name,String idNo,String contact,String roomNo,String guestNo,int days){
		String sql = "INSERT INTO client (name,id,contact,roomNo,guestNo,date,days) VALUES(?,?,?,?,?,?,?)";
		try(Connection con = this.register();PreparedStatement s = con.prepareStatement(sql)) {
			s.setString(1, name);
			s.setString(2, idNo);
			s.setString(3, contact);
			s.setString(4, roomNo);
			s.setString(5, guestNo);
			//get today date as check-in date
			LocalDate date = LocalDate.now();
			String sdate =date.toString();
			s.setString(6, sdate);
			
			s.setInt(7, days);
			s.executeUpdate();
			s.close();
			con.close();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}		
	}
	public void copyAsHistory(String name,String idNo,String contact,String roomNo,String guestNo,int days) {
		String copy = "INSERT INTO history (name,id,contact,roomNo,guestNo,indate,days) VALUES(?,?,?,?,?,?,?)";
		try(Connection con = this.register();PreparedStatement s = con.prepareStatement(copy)) {
			s.setString(1, name);
			s.setString(2, idNo);
			s.setString(3, contact);
			s.setString(4, roomNo);
			s.setString(5, guestNo);
			LocalDate date = LocalDate.now();
			String sdate =date.toString();
			s.setString(6, sdate);
			s.setInt(7, days);
			s.executeUpdate();
			s.close();
			con.close();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}		
	}
	public void dropTable() {
		String sql = "Drop table client";
		try(Connection con = this.register();Statement s = con.createStatement()) {
			s.execute(sql);
			s.close();
			con.close();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}		
	}
	public static void main(String[] args) {
	}
}
