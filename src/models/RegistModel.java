package models;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import Dao.DBConnect;

public class RegistModel extends DBConnect {
 
	private Boolean admin;
	private int id;
 
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Boolean isAdmin() {
		return admin;
	}
	public void setAdmin(Boolean admin) {
		this.admin = admin;
	}
		
	public Boolean getCredentials(String username, String password){
           
		String query = "SELECT * FROM kbao2_users WHERE username = ? and password = ?;";
            try(PreparedStatement stmt = connection.prepareStatement(query)) {
               stmt.setString(1, username);
               stmt.setString(2, password);
               ResultSet rs = stmt.executeQuery();
                if(!rs.next()) { 
                	return true;
               	}
             }catch (SQLException e) {
            	e.printStackTrace(); 
             }
			return false;
    }

    public Boolean registUser(String username, String password, Boolean isAdmin) {
      String sql = " ";
		sql += "INSERT INTO kbao2_users(username, password, admin) ";
		sql += " VALUES ('" + username + "','" + password + "', " + (isAdmin ? 1: 0) + ");";
      try(PreparedStatement stmt = connection.prepareStatement(sql)) {
    	 stmt.executeUpdate();
    	 return true;
      }catch (SQLException e) {
       e.printStackTrace(); 
      }
      
      return false;
    } 
    
    public void initialTable () {
    	try {
			// Open a connection
			String sql = "CREATE TABLE kbao2_users " + "(pid INTEGER not NULL AUTO_INCREMENT, "
					+ " username VARCHAR(10), " + " password VARCHAR(10), " + " admin INT(1), PRIMARY KEY ( pid ))";
			PreparedStatement stmt = connection.prepareStatement(sql);
			stmt.executeUpdate(sql);
			System.out.println("Created table kbao2_users");
//			conn.connect().close(); // close db connection
		} catch (SQLException se) { // Handle errors for JDBC
			se.printStackTrace();
		}
    }
}//end class