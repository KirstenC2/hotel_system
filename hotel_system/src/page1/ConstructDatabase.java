package page1;
import java.sql.*;
import java.util.Scanner;


public class ConstructDatabase {
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
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
		}
		return con;
	}
	public void createRoomTable() {
		String sql = "CREATE TABLE IF NOT EXISTS room("
				+"roomNo int NOT NULL, "
				+ "capacity int NOT NULL,"
				+ "rate int not null,"
				+ "deposit int NOT NULL,"
				+"roomType VARCHAR NOT NULL,"
				+ "occupancy int not null);";
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
	public void createClientTable() {
		String sql = "CREATE TABLE IF NOT EXISTS client("
				+"name VARCHAR NOT NULL, "
				+"id VARCHAR NOT NULL,"
				+ "contact VARCAHR NOT NULL,"
				+ "guestNo int not null,"
				+ "roomNo int not null,"
				+ "date VARCHAR not null,"
				+ "days int not null);";
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
	public void createHistoryTable() {
		String sql = "CREATE TABLE IF NOT EXISTS history("
				+"name VARCHAR NOT NULL, "
				+"id VARCHAR NOT NULL,"
				+ "contact VARCAHR NOT NULL,"
				+ "guestNo int not null,"
				+ "roomNo int not null,"
				+ "inDate Varchar not null,"
				+ "outDate Varchar,"
				+ "days int not null);";
		Statement s;
		try(Connection con = this.register()) {
			s = con.createStatement();
			s.execute(sql);
			System.out.println("done");
			s.close();
			con.close();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}		
	}
	public void RoomBase(String type,int roomNo,int capacity,int deposit,int rate,int vacancy){
		String sql = "INSERT INTO room (roomType,roomNo,capacity,deposit,rate,occupancy) VALUES(?,?,?,?,?,?)";
		try(Connection con = this.register();PreparedStatement s = con.prepareStatement(sql)) {
			s.setString(1, type);
			s.setInt(2, roomNo);
			s.setInt(3, capacity);
			s.setInt(4, deposit);
			s.setInt(5, rate);
			s.setInt(6,vacancy);
			s.executeUpdate();
			s.close();
			con.close();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}		
	}
	public void roomBaseInfo(){
		ConstructDatabase c = new ConstructDatabase();
		c.createRoomTable();
		int v=0;
		int dep=88;
		for(int i=0; i<=9;i++) {//double bed
			c.RoomBase("Double K-Bed", 200+i , 4, dep, 208, v);
		}
		for(int i=0; i<=9;i++) {//double standard
			c.RoomBase("Double Standard",210+i , 2, dep, 118, v);
		}
		for(int i=0; i<=5;i++) {//Double theme
			c.RoomBase("Double Theme",220+i , 4, dep, 168, v);
		}
		for(int i=0; i<=4;i++) {//Triple standard
			c.RoomBase("Triple Standard",300+i , 3, dep, 158, v);
		}
		for(int i=5; i<=10;i++) {//Triple bed
			c.RoomBase("Triple K-Bed",300+i , 6, dep, 268, v);
		}
		for(int i=1; i<=5;i++) {//Triple Theme
			c.RoomBase("Triple Theme",310+i , 3, dep, 208, v);
		}
	
		//premium
		for(int i=1; i<=3;i++) {
			c.RoomBase("Premium",100+i , 2, dep, 388, v);
		}
		//special room type
		c.RoomBase("Theme",520 , 2, dep, 168, v);
		c.RoomBase("Theme",1314 , 2, dep, 168, v);
		c.RoomBase("Theme",609 , 2, dep, 168, v);
		c.RoomBase("Double Bed",8087 , 2, dep, 138, v);
		c.RoomBase("Theme",5170 , 2, dep, 168, v);
		c.RoomBase("President",1 , 2, dep, 588, v);
	}

	public static void main(String[] args) {
		//construct room
		Scanner s = new Scanner(System.in);
		ConstructDatabase c = new ConstructDatabase();
		//c.roomBaseInfo();
		//c.createClientTable();
		System.out.println("services:");
		int service;
		System.out.println("1 create history table\n2 create client table \n 3 construct room database");
		service = s.nextInt();
		if(service ==1) {
			c.createHistoryTable();
		}
		else if(service==2) {
			c.createClientTable();
		}
		else if(service==3) {//construct whole room database
			c.roomBaseInfo();
		}
		System.out.println("done");
		s.close();
	}
}
