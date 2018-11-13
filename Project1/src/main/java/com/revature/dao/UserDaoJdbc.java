package com.revature.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.apache.log4j.Logger;

import com.revature.models.Role;
import com.revature.models.User;
import com.revature.utils.ConnectionUtil;

public class UserDaoJdbc implements UserDao {

	private Logger log = Logger.getRootLogger();

	private User extractFromResultSet(ResultSet rs) throws SQLException {
		return new User(rs.getInt("usersid"), rs.getString("username"), 
						null,rs.getString("firstname"),
						rs.getString("lastname"),rs.getString("email"),
						new Role(rs.getInt("userroleid"), rs.getString("userrole"))
						);
	}

	@Override
	public User findById(int id) {
		try (Connection conn = ConnectionUtil.getConnection()) {
			PreparedStatement ps = conn.prepareStatement(
					"SELECT * FROM users INNER JOIN user_roles USING (userroleid) WHERE usersid = ? ");
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				return extractFromResultSet(rs);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	

	@Override
	public boolean saveUser(User newUser) {
		try (Connection conn = ConnectionUtil.getConnection()) {
			
			Statement s = conn.createStatement();
			s.execute("Select MAX(usersid) FROM users");    
			ResultSet rsId = s.getResultSet(); // 
			
			
			if ( rsId.next() ){
				newUser.setId(rsId.getInt(1) + 1);
			}
			
			PreparedStatement ps = conn
					.prepareStatement("INSERT INTO users(usersid,username,password,firstname,lastname,email,userroleid) VAlUES (?,?,?,?,?,?,?);");
			ps.setInt   (1, newUser.getId());
			ps.setString(2, newUser.getUsername());
			ps.setString(3, newUser.getPassword());
			ps.setString(4, newUser.getFirstName());
			ps.setString(5, newUser.getLastName());
			ps.setString(6, newUser.getEmail());
			ps.setInt   (7, newUser.getRoleId().getRoleId());
			ps.executeUpdate();
			return true;

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();			
		}
		return false;
	}

	@Override
	public User findByUsernameAndPassword(String username, String password) {
		try (Connection conn = ConnectionUtil.getConnection()) {
			PreparedStatement ps = conn.prepareStatement(
					"SELECT * FROM users INNER JOIN user_roles USING (userroleid) WHERE username = ?  AND password = ?");
			ps.setString(1, username);
			ps.setString(2, password);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				return extractFromResultSet(rs);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public User findByUsername(String username) {
		try (Connection conn = ConnectionUtil.getConnection()) {
			PreparedStatement ps = conn.prepareStatement(
					"SELECT * FROM users INNER JOIN user_roles USING (userroleid) WHERE username = ? ");
			ps.setString(1, username);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				return extractFromResultSet(rs);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
}