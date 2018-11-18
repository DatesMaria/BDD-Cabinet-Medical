package database;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import model.user.User;
import model.user.User.Rol;

public class UserDAO {
	
	/**
	 * method to add user into a database
	 */
	public boolean addUser(User user) {
		try {
			Connection conn =  DbConnections.getConnection(DbConnections.ConnectionType.POSTGRESQL);
			PreparedStatement ps = conn.prepareStatement(
					"insert into public.user(nume_user, parola, \"e-mail\", rol) values(?,?,?,?)",
					Statement.RETURN_GENERATED_KEYS);
			ps.setString(1,user.getNume_user());
			ps.setString(2, user.getParola());
			ps.setString(3, user.getEmail());
			ps.setString(4, user.getRol().toString());
	
			
			int affectedRows = ps.executeUpdate();
			ResultSet rs = ps.getGeneratedKeys();
			if (rs.next()) {
				user.setId(rs.getInt(1));
			
				}
			DbConnections.closeConnection(conn);
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}

	}
	
	/**
	 * method to update  user into a database by id
	 */

	public boolean update(User user) {
		try {
			Connection conn = DbConnections.getConnection(DbConnections.ConnectionType.POSTGRESQL);
			PreparedStatement ps = conn
					.prepareStatement("update public.user set nume_user = ?, parola = ?, \"e-mail\" = ?, rol = ?  where id = ?");
			
	
			ps.setString(1, user.getNume_user());
			ps.setString(2, user.getParola());
			ps.setString(3, user.getEmail());
			ps.setString(4, user.getRol().toString());
			ps.setInt(5, user.getId());
			int affectedRows = ps.executeUpdate();
			DbConnections.closeConnection(conn);
			return affectedRows == 1;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}

	}

	/**
	 * method to delete user into a database by id
	 */

	public boolean delete(int id) {
		try {
			Connection conn = DbConnections.getConnection(DbConnections.ConnectionType.POSTGRESQL);
			PreparedStatement ps = conn
					.prepareStatement("delete from public.user where id = ?");
			ps.setInt(1, id);
			int affectedRows = ps.executeUpdate();
			DbConnections.closeConnection(conn);
			return affectedRows == 1;
		} catch (SQLException e) {
			return false;
		}

	}

	/**
	 * method to select user from a database by id
	 */

	public User findById(int id) {
		User user = null;
		try {
			Connection conn = DbConnections.getConnection(DbConnections.ConnectionType.POSTGRESQL);
			PreparedStatement ps = conn
					.prepareStatement("select * from public.user where id = ?");
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				int id2 = rs.getInt("id");
				String numeUser = rs.getString("nume_user");
		        String parola = rs.getString("parola");
		        String email = rs.getString("e-mail");
		        Rol rol = Rol.valueOf(rs.getString("rol")) ;
			 
				user = new User(id2, numeUser, parola, email, rol);
			}
			DbConnections.closeConnection(conn);
			return user;
		} catch (SQLException e) {
			return null;
		}

	}
	public User findByUsername(String username) {
		User user = null;
		try {
			Connection conn = DbConnections.getConnection(DbConnections.ConnectionType.POSTGRESQL);
			PreparedStatement ps = conn
					.prepareStatement("select * from public.user where nume_user=?");
			ps.setString(1, username);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				int id2 = rs.getInt("id");
				String numeUser = rs.getString("nume_user");
		        String parola = rs.getString("parola");
		        String email = rs.getString("e-mail");
		        Rol rol = Rol.valueOf(rs.getString("rol")) ;
			 
				user = new User(id2, numeUser, parola, email, rol);
			}
			DbConnections.closeConnection(conn);
			return user;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}

	}

	/**
	 * method to show all user from a database
	 */
	
	public User[] getAllUsers() {
		try {
			Connection conn = DbConnections.getConnection(DbConnections.ConnectionType.POSTGRESQL);
			PreparedStatement ps = conn
					.prepareStatement("select * from public.user");
			ResultSet rs = ps.executeQuery();
			List<User> usersList = new ArrayList<User>();
			while (rs.next()) {
				int id = rs.getInt("id");
		        String nume_user= rs.getString("nume_user");
		        String parola = rs.getString("parola");
		        String email = rs.getString("e-mail");
		        Rol rol = Rol.valueOf(rs.getString("rol")) ;
			  
				
		        usersList.add( new User(id, nume_user, parola, email, rol));
			}
			DbConnections.closeConnection(conn);
			return usersList.toArray(new User[usersList.size()]);
		} catch (SQLException e) {
			return null;
		}

	}


	
}
