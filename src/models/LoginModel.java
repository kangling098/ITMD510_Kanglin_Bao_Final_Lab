package models;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import Dao.DBConnect;

public class LoginModel extends DBConnect {
 
	private Boolean admin;
	private int pid;
 
	public int getPid() {
		return pid;
	}
	public void setPid(int pid) {
		this.pid = pid;
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
                if(rs.next()) { 
                 
                	setPid(rs.getInt("pid"));
                	int adminInt = rs.getInt("admin");
                	System.out.println("adminInt: " + adminInt);
                	setAdmin(rs.getInt("admin") == 1 ? true: false);
                	return true;
               	}
             }catch (SQLException e) {
            	e.printStackTrace(); 
             }
			return false;
    }

}//end class