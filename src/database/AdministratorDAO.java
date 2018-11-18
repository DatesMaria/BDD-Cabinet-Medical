package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import model.user.Administrator;

public class AdministratorDAO {
	
	/**
	 * method to add administrator into a database
	 */
	public void addAdministrator(Administrator admin) {
		try {
			Connection conn = DbConnections.getConnection(DbConnections.ConnectionType.POSTGRESQL);
			PreparedStatement ps = conn.prepareStatement(
					"insert into public.administrator(id_user, nume, prenume) values(?,?,?)", Statement.RETURN_GENERATED_KEYS);
			ps.setInt(1, admin.getIdUser());
			ps.setString(2, admin.getNume());
			ps.setString(3, admin.getPrenume());

			int affectedRows = ps.executeUpdate();
			ResultSet rs = ps.getGeneratedKeys();
			if (rs.next()) {
				admin.setId(rs.getInt(1));
			}
			DbConnections.closeConnection(conn);
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	/**
	 * method to update administrator into a database by id
	 */

	public boolean update(Administrator admin) {
		try {
			Connection conn =  DbConnections.getConnection(DbConnections.ConnectionType.POSTGRESQL);
			PreparedStatement ps = conn
					.prepareStatement("update public.administrator set id_user = ?, nume = ?, prenume = ? where id = ?");

			ps.setInt(1, admin.getIdUser());
			ps.setString(2, admin.getNume());
			ps.setString(3, admin.getPrenume());
			ps.setInt(4, admin.getId());
			int affectedRows = ps.executeUpdate();
			DbConnections.closeConnection(conn);
			return affectedRows == 1;
		} catch (SQLException e) {
			return false;
		}

	}

	/**
	 * method to delete administrator into a database by id
	 */

	public boolean delete(int id) {
		try {
			Connection conn =  DbConnections.getConnection(DbConnections.ConnectionType.POSTGRESQL);
			PreparedStatement ps = conn.prepareStatement("delete from public.administrator where id = ?");
			ps.setInt(1, id);
			int affectedRows = ps.executeUpdate();
			DbConnections.closeConnection(conn);
			return affectedRows == 1;
		} catch (SQLException e) {
			return false;
		}

	}

	/**
	 * method to select a from administrator database by id
	 */

	public Administrator findById(int id) {
		Administrator admin = null;
		try {
			Connection conn =  DbConnections.getConnection(DbConnections.ConnectionType.POSTGRESQL);
			PreparedStatement ps = conn.prepareStatement("select * from public.administrator where id = ?");
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				int id2 = rs.getInt("id");
				int idUser = rs.getInt("id_user");
				String nume = rs.getString("nume");
				String prenume = rs.getString("prenume");

				admin = new Administrator(id2, idUser, nume, prenume);
			}
			DbConnections.closeConnection(conn);
			return admin;
		} catch (SQLException e) {
			return null;
		}

	}

	/**
	 * method to show all admin from a database
	 */

	public Administrator[] getAllAdmin() {
		try {
			Connection conn =  DbConnections.getConnection(DbConnections.ConnectionType.POSTGRESQL);
			PreparedStatement ps = conn.prepareStatement("select * from public.administrator");
			ResultSet rs = ps.executeQuery();
			List<Administrator> adminList = new ArrayList<Administrator>();
			while (rs.next()) {
				int id = rs.getInt("id");
				int idUser = rs.getInt("id_user");
				String nume = rs.getString("nume");
				String prenume = rs.getString("prenume");
				
				adminList.add(new Administrator(id, idUser, nume, prenume));
			}
			DbConnections.closeConnection(conn);
			return adminList.toArray(new Administrator[adminList.size()]);
		} catch (SQLException e) {
			return null;
		}

	}

}
