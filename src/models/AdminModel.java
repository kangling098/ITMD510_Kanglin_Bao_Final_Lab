package models;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
 
import Dao.DBConnect;

public class AdminModel extends DBConnect {

	private int cid;
	private int tid;
	private double balance;

	// Declare DB objects
	Statement stmt = null;

	public AdminModel() {

	}

	/* getters & setters */
	
	public int getCid() {
		return cid;
	}

	public void setCid(int cid) {
		this.cid = cid;
	}

	public void setTid(int tid) {
		this.tid = tid;
	}

	public int getTid() {
		return tid;
	}

	public Double getBalance() {
		return balance;
	}

	public void setBalance(Double balance) {
		this.balance = balance;
	}

	// INSERT INTO METHOD
	public void insertRecord(int cid, double bal) {

		try {
			setCid(cid);
			// Execute a query
			System.out.println("Inserting record into the table...");
			stmt = connect().createStatement();
			String sql = null;

			// Include data to the database table

			sql = " insert into jpapa_accounts(cid, balance) values('" + cid + "', '" + bal + "')";

			stmt.executeUpdate(sql);
			System.out.println("Balance inserted $" + bal + " for ClientID " + cid);

		} catch (SQLException se) {
			se.printStackTrace();
		}
	}

	public List<AdminModel> getAccounts(int cid) {
		List<AdminModel> accounts = new ArrayList<>();
		String query = "SELECT tid,balance FROM jpapa_accounts WHERE cid = ?;";
		try (PreparedStatement statement = connect().prepareStatement(query)) {
			statement.setInt(1, cid);
			ResultSet resultSet = statement.executeQuery();
			while (resultSet.next()) {
				AdminModel account = new AdminModel();
				// grab record data by table field name into ClientModel account object
				account.setTid(resultSet.getInt("tid"));
				account.setBalance(resultSet.getDouble("balance"));
				accounts.add(account); // add account data to arraylist
			}
		} catch (SQLException e) {
			System.out.println("Error fetching Accounts: " + e);
		}
		return accounts; // return arraylist
	}
	
	// INSERT INTO METHOD (add new food)
	public void addFood(String name, double pri) {

		try {
			System.out.println("add new food");
			stmt = connect().createStatement();
			// Insert data
			String sql = " insert into kbao2_foods_menu(name, price)";
			sql +=  " VALUES ('" + name + "', " + pri + ")";

			stmt.executeUpdate(sql);
			System.out.println("Added" + name + "into food menu");
		} catch (SQLException se) {
			se.printStackTrace();
		}
	}

	public void initialFoodMenu () {
		try {
			Connection con = connect();
			Statement stmt = con.createStatement();
			// Open a connection
			// stmt.executeUpdate("DROP TABLE kbao2_foods_menu;");
			String sql = "CREATE TABLE kbao2_foods_menu " + "(pid INTEGER not NULL AUTO_INCREMENT, "
					+ " name VARCHAR(24), " + " price NUMERIC(10,4), " + " PRIMARY KEY ( pid ))";
			stmt.executeUpdate(sql);
			System.out.println("Created table kbao2_foods_menu");
			con.close(); // close db connection
		} catch (SQLException se) { // Handle errors for JDBC
			se.printStackTrace();
		}
	}
	
	public ResultSet getView() {
		ResultSet rs = null;

		try {
			String sql = "SELECT * from kbao2_foods_menu ORDER BY pid DESC";
			PreparedStatement stmt = connect().prepareStatement(sql);
			rs = stmt.executeQuery(sql);
			System.out.println("Get table kbao2_foods_menu View");
			return rs;
		} catch (SQLException e) {
			e.printStackTrace();
			return rs;
		}
		
	}
	
	public void deleteFood(String cuid) {

		try {
			Connection con = connect();
			Statement stmt = con.createStatement();
			// Open a connection
			stmt.executeUpdate("Delete FROM kbao2_foods_menu WHERE pid=" + cuid + ";");
			
			System.out.println("delete " + cuid + " sucess");
			con.close(); // close db connection
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	public void updateFood(String cuid, Double cuPri) {
		try {
			Connection con = connect();
			Statement stmt = con.createStatement();
			String sql = "UPDATE kbao2_foods_menu SET price=" +cuPri+ " WHERE pid=" + cuid + ";";
			// Open a connection
			stmt.executeUpdate(sql);
			System.out.println(sql);
			System.out.println("update " + cuid + " price sucess");
			con.close(); // close db connection
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
}