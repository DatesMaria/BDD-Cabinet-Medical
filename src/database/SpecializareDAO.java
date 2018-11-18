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

import model.user.Specializare;

public class SpecializareDAO {

	/**
	 * method to add specializare into a database
	 */
	public void addSpecializare(Specializare specializare) {
		try {
			Connection conn = DbConnections.getConnection(DbConnections.ConnectionType.POSTGRESQL);
			
			PreparedStatement ps = conn.prepareStatement(
					"insert into public.specializare(denumire, descriere) values(?,?)",
					Statement.RETURN_GENERATED_KEYS);
			ps.setString(1, specializare.getDenumire());
			ps.setString(2, specializare.getDescriere());
	
			
			int affectedRows = ps.executeUpdate();
			ResultSet rs = ps.getGeneratedKeys();
			if (rs.next()) {
				specializare.setId(rs.getInt(1));
			}
			 DbConnections.closeConnection(conn);
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}
	
	/**
	 * method to update  specializare into a database by id
	 */

	public boolean update(Specializare specializare) {
		try {
			Connection conn = DbConnections.getConnection(DbConnections.ConnectionType.POSTGRESQL);
			PreparedStatement ps = conn
					.prepareStatement("update public.specializare set denumire = ?, descriere = ? where id = ?");
			
			ps.setString(1, specializare.getDenumire());
			ps.setString(2, specializare.getDescriere());
			ps.setInt(3, specializare.getId());
			int affectedRows = ps.executeUpdate();
			 DbConnections.closeConnection(conn);
			return affectedRows == 1;
		} catch (SQLException e) {
			return false;
		}

	}

	/**
	 * method to delete specializare into a database by id
	 */

	public boolean delete(int id) {
		try {
			Connection conn = DbConnections.getConnection(DbConnections.ConnectionType.POSTGRESQL);
			PreparedStatement ps = conn
					.prepareStatement("delete from public.specializare where id = ?");
			ps.setInt(1, id);
			int affectedRows = ps.executeUpdate();
			 DbConnections.closeConnection(conn);
			return affectedRows == 1;
		} catch (SQLException e) {
			return false;
		}

	}

	/**
	 * method to select specializare from a database by id
	 */

	public Specializare findById(int id) {
		Specializare  specializare = null;
		try {
			Connection conn = DbConnections.getConnection(DbConnections.ConnectionType.POSTGRESQL);
			PreparedStatement ps = conn
					.prepareStatement("select * from public.specializare where id = ?");
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				int id2 = rs.getInt("id");
				String denumire = rs.getString("denumire");
		        String descriere= rs.getString("descriere");
			 
				specializare = new Specializare(id2,denumire, descriere);
			}
			 DbConnections.closeConnection(conn);
			return specializare;
		} catch (SQLException e) {
			return null;
		}

	}

	/**
	 * method to show all specializari from a database
	 */
	
	public Specializare[] getAllSpecializari() {
		try {
			Connection conn = DbConnections.getConnection(DbConnections.ConnectionType.POSTGRESQL);
			PreparedStatement ps = conn
					.prepareStatement("select * from public.specializare");
			ResultSet rs = ps.executeQuery();
			List<Specializare> specializariList = new ArrayList<Specializare>();
			while (rs.next()) {
				int id = rs.getInt("id");
		        String denumire= rs.getString("denumire");
		        String descriere = rs.getString("denumire");
			  
				
		        specializariList.add( new Specializare(id, denumire, descriere));
			}
			 DbConnections.closeConnection(conn);
			return specializariList.toArray(new Specializare[specializariList.size()]);
		} catch (SQLException e) {
			return null;
		}

	}


}
