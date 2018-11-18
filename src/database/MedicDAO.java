
package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import model.user.Medic;

public class MedicDAO {
	
	/**
	 * method to add medic into a database
	 */
	public void addMedic(Medic medic) {
		try {
			Connection conn = DbConnections.getConnection(DbConnections.ConnectionType.POSTGRESQL);
			PreparedStatement ps = conn.prepareStatement(
					"insert into public.medic(id_specializare, id_user, nume, prenume, data_nasterii) values(?,?,?,?,?)",
					Statement.RETURN_GENERATED_KEYS);
			ps.setInt(1, medic.getIdSpecializare());
			ps.setInt(2, medic.getIdUser());
			ps.setString(3, medic.getNume());
			ps.setString(4, medic.getPrenume());
			ps.setDate(5, new java.sql.Date(DateHelper.asDate(medic.getDataNasterii()).getTime()));
			

			int affectedRows = ps.executeUpdate();
			ResultSet rs = ps.getGeneratedKeys();
			if (rs.next()) {
				medic.setId(rs.getInt(1));
			}
			DbConnections.closeConnection(conn);
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	/**
	 * method to update medic into a database by id
	 */

	public boolean update(Medic medic) {
		try {
			Connection conn = DbConnections.getConnection(DbConnections.ConnectionType.POSTGRESQL);
			PreparedStatement ps = conn.prepareStatement(
					"update public.medic set id_specializare = ?, id_user = ?, nume = ?, prenume = ?, data_nasterii = ? where id = ?");

			ps.setInt(1, medic.getIdSpecializare());
			ps.setInt(2, medic.getIdUser());
			ps.setString(3, medic.getNume());
			ps.setString(4, medic.getPrenume());
			ps.setDate(5, new java.sql.Date(DateHelper.asDate(medic.getDataNasterii()).getTime()));
			ps.setInt(6, medic.getId());
			int affectedRows = ps.executeUpdate();
			DbConnections.closeConnection(conn);
			return affectedRows == 1;
		} catch (SQLException e) {
			return false;
		}

	}

	/**
	 * method to delete medic into a database by id
	 */

	public boolean delete(int id) {
		try {
			Connection conn = DbConnections.getConnection(DbConnections.ConnectionType.POSTGRESQL);
			PreparedStatement ps = conn.prepareStatement("delete from public.medic where id = ?");
			ps.setInt(1, id);
			int affectedRows = ps.executeUpdate();
			DbConnections.closeConnection(conn);
			return affectedRows == 1;
		} catch (SQLException e) {
			return false;
		}

	}

	/**
	 * method to select a from medic database by id
	 */

	public Medic findById(int id) {
		Medic medic = null;
		try {
			Connection conn = DbConnections.getConnection(DbConnections.ConnectionType.POSTGRESQL);
			PreparedStatement ps = conn.prepareStatement("select * from public.medic where id = ?");
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				int id2 = rs.getInt("id");
				int idSpecializare = rs.getInt("id_specializare");
				int idUser = rs.getInt("id_user");
				String nume = rs.getString("nume");
				String prenume = rs.getString("prenume");
				Date dataNasterii = rs.getDate("data_nasterii");

				medic = new Medic(id2, idSpecializare, idUser, nume, prenume,  DateHelper.asLocalDate(dataNasterii));
			}
			DbConnections.closeConnection(conn);
			return medic;
		} catch (SQLException e) {
			return null;
		}

	}
	

	public Medic findByUserId(int id_user) {
		Medic medic = null;
		try {
			Connection conn = DbConnections.getConnection(DbConnections.ConnectionType.POSTGRESQL);
			PreparedStatement ps = conn.prepareStatement("select * from public.medic where id_user = ?");
			ps.setInt(1, id_user);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				int id2 = rs.getInt("id");
				int idSpecializare = rs.getInt("id_specializare");
				int idUser = rs.getInt("id_user");
				String nume = rs.getString("nume");
				String prenume = rs.getString("prenume");
				Date dataNasterii = rs.getDate("data_nasterii");

				medic = new Medic(id2, idSpecializare, idUser, nume, prenume,  DateHelper.asLocalDate(dataNasterii));
			}
			DbConnections.closeConnection(conn);
			return medic;
		} catch (SQLException e) {
			return null;
		}

	}

	/**
	 * method to show all medici from a database
	 */

	public Medic[] getAllMedici() {
		try {
			Connection conn = DbConnections.getConnection(DbConnections.ConnectionType.POSTGRESQL);
			PreparedStatement ps = conn.prepareStatement("select * from public.medic");
			ResultSet rs = ps.executeQuery();
			List<Medic> mediciList = new ArrayList<Medic>();
			while (rs.next()) {
				int id = rs.getInt("id");
				int idSpecializare = rs.getInt("id_specializare");
				int idUser = rs.getInt("id_user");
				String nume = rs.getString("nume");
				String prenume = rs.getString("prenume");
				Date dataNasterii = rs.getDate("data_nasterii");

				mediciList.add(new Medic(id, idSpecializare, idUser, nume, prenume,  DateHelper.asLocalDate(dataNasterii)));
			}
			DbConnections.closeConnection(conn);
			return mediciList.toArray(new Medic[mediciList.size()]);
		} catch (SQLException e) {
			return null;
		}

	}

}
