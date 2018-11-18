
package database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import model.user.Pacient;

public class PacientDAO {
	
	
	/**
	 * method to add pacient into a database
	 */
	
	public void addPacient(Pacient pacient) {
		try {
			Connection conn = DbConnections.getConnection(DbConnections.ConnectionType.POSTGRESQL);
			PreparedStatement ps = conn.prepareStatement(
					"insert into public.pacient(id_user, nume, prenume, cnp, sex, data_nasterii, telefon, adresa, greutate, inaltime) values(?,?,?,?,?,?,?,?,?,?)",
					Statement.RETURN_GENERATED_KEYS);
			ps.setInt(1, pacient.getIdUser());
			ps.setString(2, pacient.getNume());
			ps.setString(3, pacient.getPrenume());
			ps.setLong(4, pacient.getCnp());
			ps.setString(5, pacient.getSex());
			ps.setDate(6, new java.sql.Date(DateHelper.asDate(pacient.getDataNasterii()).getTime()));
			ps.setLong(7, pacient.getTelefon());
			ps.setString(8, pacient.getAdresa());
			ps.setDouble(9, pacient.getGreutate());
			ps.setFloat(10, pacient.getInaltime());

			int affectedRows = ps.executeUpdate();
			ResultSet rs = ps.getGeneratedKeys();
			if (rs.next()) {
				pacient.setId(rs.getInt(1));
				
			}
			DbConnections.closeConnection(conn);
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	/**
	 * method to update pacient into a database by id
	 */

	public boolean update(Pacient pacient) {
		try {
			Connection conn = DbConnections.getConnection(DbConnections.ConnectionType.POSTGRESQL);
			PreparedStatement ps = conn.prepareStatement(
					"update public.pacient set id_user = ?, nume = ?, prenume = ?, cnp = ?, sex = ?, data_nasterii = ?, "
							+ "telefon = ?, adresa = ?, greutate = ?, inaltime= ? where id = ?");

			ps.setInt(1, pacient.getIdUser());
			ps.setString(2, pacient.getNume());
			ps.setString(3, pacient.getPrenume());
			ps.setLong(4, pacient.getCnp());
			ps.setString(5, pacient.getSex());
			ps.setDate(6, new java.sql.Date(DateHelper.asDate(pacient.getDataNasterii()).getTime()));
			ps.setLong(7, pacient.getTelefon());
			ps.setString(8, pacient.getAdresa());
			ps.setFloat(9, pacient.getGreutate());
			ps.setFloat(10, pacient.getInaltime());
			ps.setInt(11, pacient.getId());
			int affectedRows = ps.executeUpdate();
			DbConnections.closeConnection(conn);
			return affectedRows == 1;
		} catch (SQLException e) {
			return false;
		}

	}

	/**
	 * method to delete pacient into a database by id
	 */

	public boolean delete(int id) {
		try {
			Connection conn = DbConnections.getConnection(DbConnections.ConnectionType.POSTGRESQL);
			PreparedStatement ps = conn.prepareStatement("delete from public.pacient where id = ?");
			ps.setInt(1, id);
			int affectedRows = ps.executeUpdate();
			DbConnections.closeConnection(conn);
			return affectedRows == 1;
		} catch (SQLException e) {
			return false;
		}

	}

	/**
	 * method to select a from pacient database by id
	 */

	public Pacient findById(int id) {
		Pacient pacient = null;
		try {
			Connection conn = DbConnections.getConnection(DbConnections.ConnectionType.POSTGRESQL);
			PreparedStatement ps = conn.prepareStatement("select * from public.pacient where id = ?");
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				int id2 = rs.getInt("id");
				int idUser = rs.getInt("id_user");
				String nume = rs.getString("nume");
				String prenume = rs.getString("prenume");
				long cnp = rs.getLong("cnp");
				String sex = rs.getString("sex");
				Date dataNasterii = rs.getDate("data_nasterii");
				long telefon = rs.getLong("telefon");
				String adresa = rs.getString("adresa");
				float greutate = rs.getFloat("greutate");
				float inaltime = rs.getFloat("inaltime");

				pacient = new Pacient(id2, idUser, nume, prenume, cnp, sex, DateHelper.asLocalDate(dataNasterii), telefon, adresa, greutate,
						inaltime);
			}
			DbConnections.closeConnection(conn);
			return pacient;
		} catch (SQLException e) {
			return null;
		}

	}
	

	public Pacient findByCnp(long l) {
		Pacient pacient = null;
		try {
			Connection conn = DbConnections.getConnection(DbConnections.ConnectionType.POSTGRESQL);
			PreparedStatement ps = conn.prepareStatement("select * from public.pacient where cnp = ?");
			ps.setLong(1, l);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				int id = rs.getInt("id");
				int idUser = rs.getInt("id_user");
				String nume = rs.getString("nume");
				String prenume = rs.getString("prenume");
				long cnp1 = rs.getLong("cnp");
				String sex = rs.getString("sex");
				Date dataNasterii = rs.getDate("data_nasterii");
				long telefon = rs.getLong("telefon");
				String adresa = rs.getString("adresa");
				float greutate = rs.getFloat("greutate");
				float inaltime = rs.getFloat("inaltime");

				pacient = new Pacient(id, idUser, nume, prenume, cnp1, sex, DateHelper.asLocalDate(dataNasterii), telefon, adresa, greutate,
						inaltime);
			}
			DbConnections.closeConnection(conn);
			return pacient;
		} catch (SQLException e) {
			return null;
		}

	}
	
	public Pacient findByUserId(int id_user) {
		Pacient pacient = null;
		try {
			Connection conn = DbConnections.getConnection(DbConnections.ConnectionType.POSTGRESQL);
			PreparedStatement ps = conn.prepareStatement("select * from public.pacient where id_user = ?");
			ps.setInt(1, id_user);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				int id2 = rs.getInt("id");
				int idUser = rs.getInt("id_user");
				String nume = rs.getString("nume");
				String prenume = rs.getString("prenume");
				long cnp = rs.getLong("cnp");
				String sex = rs.getString("sex");
				Date dataNasterii = rs.getDate("data_nasterii");
				long telefon = rs.getLong("telefon");
				String adresa = rs.getString("adresa");
				float greutate = rs.getFloat("greutate");
				float inaltime = rs.getFloat("inaltime");

				pacient = new Pacient(id2, idUser, nume, prenume, cnp, sex, DateHelper.asLocalDate(dataNasterii), telefon, adresa, greutate,
						inaltime);
			}
			DbConnections.closeConnection(conn);
			return pacient;
		} catch (SQLException e) {
			return null;
		}

	}

	/**
	 * method to show all pacienti from a database
	 */

	public Pacient[] getAllPacienti() {
		try {
			Connection conn = DbConnections.getConnection(DbConnections.ConnectionType.POSTGRESQL);
			PreparedStatement ps = conn.prepareStatement("select * from public.pacient");
			ResultSet rs = ps.executeQuery();
			List<Pacient> pacientiList = new ArrayList<Pacient>();
			while (rs.next()) {
				int id = rs.getInt("id");
				int idUser = rs.getInt("id_user");
				String nume = rs.getString("nume");
				String prenume = rs.getString("prenume");
				long cnp = rs.getLong("cnp");
				String sex = rs.getString("sex");
				Date dataNasterii = rs.getDate("data_nasterii");
				long telefon = rs.getLong("telefon");
				String adresa = rs.getString("adresa");
				float greutate = rs.getFloat("greutate");
				float inaltime = rs.getFloat("inaltime");

				pacientiList.add(new Pacient(id, idUser, nume, prenume, cnp, sex, DateHelper.asLocalDate(dataNasterii), telefon, adresa, greutate,
						inaltime));
			}
			DbConnections.closeConnection(conn);
			return pacientiList.toArray(new Pacient[pacientiList.size()]);
		} catch (SQLException e) {
			return null;
		}

	}}
