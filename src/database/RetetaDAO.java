package database;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import model.cabinetMedical.Reteta;

public class RetetaDAO {


	/**
	 * method to add reteta into a database
	 */
	public void addReteta(Reteta reteta) {
		try {
			Connection conn = DbConnections.getConnection(DbConnections.ConnectionType.MYSQL);
			PreparedStatement ps = conn.prepareStatement(
					"insert into reteta( id_consultatie, compensat, data, medicamente) values(?,?,?,?)",
					Statement.RETURN_GENERATED_KEYS);
			ps.setInt(1, reteta.getIdConsultatie());
			ps.setBoolean(2, reteta.isCompensat());
			ps.setDate(3,new java.sql.Date(DateHelper.asDate(reteta.getData()).getTime()));
			ps.setString(4, reteta.getMedicamente());
	

			int affectedRows = ps.executeUpdate();
			ResultSet rs = ps.getGeneratedKeys();
			if (rs.next()) {
				reteta.setId(rs.getInt(1));
				
			
			}
			DbConnections.closeConnection(conn);
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	/**
	 * method to update reteta into a database by id
	 */

	public boolean update(Reteta reteta) {
		try {
			Connection conn = DbConnections.getConnection(DbConnections.ConnectionType.MYSQL);
			PreparedStatement ps = conn.prepareStatement(
					"update reteta set id_consultatie = ?, compensat = ?, data = ?, medicamente=? where id = ?");

	
			ps.setInt(1, reteta.getIdConsultatie());
			ps.setBoolean(2, reteta.isCompensat());
			ps.setDate(3,new java.sql.Date(DateHelper.asDate(reteta.getData()).getTime()));
		    ps.setInt(5, reteta.getId());
		    ps.setString(4, reteta.getMedicamente());
			int affectedRows = ps.executeUpdate();
			DbConnections.closeConnection(conn);
			return affectedRows == 1;
		} catch (SQLException e) {
			return false;
		}

	}

	/**
	 * method to delete reteta into a database by id
	 */

	public boolean delete(int id) {
		try {
			Connection conn = DbConnections.getConnection(DbConnections.ConnectionType.MYSQL);
			PreparedStatement ps = conn.prepareStatement("delete from reteta where id = ?");
			ps.setInt(1, id);
			int affectedRows = ps.executeUpdate();
			DbConnections.closeConnection(conn);
			return affectedRows == 1;
		} catch (SQLException e) {
			return false;
		}

	}

	/**
	 * method to select reteta from a database by id
	 */

	public Reteta findById(int id) {
		Reteta reteta = null;
		try {
			Connection conn = DbConnections.getConnection(DbConnections.ConnectionType.MYSQL);
			PreparedStatement ps = conn.prepareStatement("select * from reteta where id = ?");
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				int id2 = rs.getInt("id");
				int idConsultatie = rs.getInt("id_consultatie");
				Boolean compensat = rs.getBoolean("compensat");
				Date data = rs.getDate("data");
				String medicament=rs.getString("medicamente");

				reteta = new Reteta(id2, idConsultatie, compensat, DateHelper.asLocalDate(data), medicament);
			}
			DbConnections.closeConnection(conn);
			return reteta;
		} catch (SQLException e) {
			return null;
		}

	}
	
	public List<Reteta> findByIdConsultatie(int id) {
		Reteta reteta = null;
		List<Reteta> retetaList = new ArrayList<>();
		try { 
			Connection conn = DbConnections.getConnection(DbConnections.ConnectionType.MYSQL);
			PreparedStatement ps = conn.prepareStatement("select * from reteta where id_consultatie = ?");
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				int id2 = rs.getInt("id");
				int idConsultatie = rs.getInt("id_consultatie");
				Boolean compensat = rs.getBoolean("compensat");
				Date data = rs.getDate("data");
				String medicament=rs.getString("medicamente");

				reteta = new Reteta(id2, idConsultatie, compensat, DateHelper.asLocalDate(data), medicament);
				retetaList.add(reteta);
			}
			DbConnections.closeConnection(conn);
			return retetaList;
		} catch (SQLException e) {
			return null;
		}

	}


	/**
	 * method to show all retete from a database
	 */

	public Reteta[] getAllRetete() {
		try {
			Connection conn = DbConnections.getConnection(DbConnections.ConnectionType.MYSQL);
			PreparedStatement ps = conn.prepareStatement("select * from reteta");
			ResultSet rs = ps.executeQuery();
			List<Reteta> reteteList = new ArrayList<Reteta>();
			while (rs.next()) {
				int id = rs.getInt("id");
				int idConsultatie = rs.getInt("id_consultatie");
				Boolean compensat = rs.getBoolean("compensat");
				Date data = rs.getDate("data");
				String medicament=rs.getString("medicamente");

				reteteList.add(new Reteta(id, idConsultatie, compensat,  DateHelper.asLocalDate(data), medicament));
			}
			DbConnections.closeConnection(conn);
			return reteteList.toArray(new Reteta[reteteList.size()]);
		} catch (SQLException e) {
			return null;
		}

	}

}
