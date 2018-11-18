package database;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import model.cabinetMedical.Consultatie;
import model.cabinetMedical.Consultatie.Status;


public class ConsultatieDAO {

	/**
	 * method to add consultatie into a database
	 */
	public void addConsultatie(Consultatie consultatie) {
		try {
			Connection conn = DbConnections.getConnection(DbConnections.ConnectionType.MYSQL);
			PreparedStatement ps = conn.prepareStatement(
					"insert into consultatie( id_pacient, id_medic, id_reteta, id_factura, status, data, detalii) values(?,?,?,?,?,?,?)",
					Statement.RETURN_GENERATED_KEYS);
			ps.setInt(1, consultatie.getIdPacient());
			ps.setInt(2, consultatie.getIdMedic());
			if(consultatie.getIdReteta() == null) {
				ps.setNull(3,  Types.INTEGER);
			}else {
				ps.setInt(3, consultatie.getIdReteta());
			}
			
			if(consultatie.getIdFactura() == null) {
				ps.setNull(4,  Types.INTEGER);
			}else {
				ps.setInt(4, consultatie.getIdFactura());
			}
			ps.setString(5, consultatie.getStatus().toString());
			ps.setDate(6, new java.sql.Date(DateHelper.asDate(consultatie.getData()).getTime()));
			ps.setString(7, consultatie.getDetalii());
			
			int affectedRows = ps.executeUpdate();
			ResultSet rs = ps.getGeneratedKeys();
			if (rs.next()) {
				consultatie.setId(rs.getInt(1));
			}
			DbConnections.closeConnection(conn);
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}
	
	/**
	 * method to update consultatie into a database by id
	 */

	public boolean update(Consultatie consultatie) {
		try {
			Connection conn = DbConnections.getConnection(DbConnections.ConnectionType.MYSQL);
			PreparedStatement ps = conn
					.prepareStatement("update consultatie set id_pacient = ?, id_medic = ?, id_reteta = ?, id_factura = ?, status = ?, data = ?, detalii = ? where id = ?");
			ps.setInt(1, consultatie.getIdPacient());
			ps.setInt(2, consultatie.getIdMedic());
			if(consultatie.getIdReteta() == null) {
				ps.setNull(3,  Types.INTEGER);
			}else {
				ps.setInt(3, consultatie.getIdFactura());
			}
			if(consultatie.getIdReteta() == null) {
				ps.setNull(4,  Types.INTEGER);
			}else {
				ps.setInt(4, consultatie.getIdFactura());
			}
			ps.setString(5, consultatie.getStatus().toString());
			ps.setDate(6, new java.sql.Date(DateHelper.asDate(consultatie.getData()).getTime()));
			ps.setString(7, consultatie.getDetalii());
			ps.setInt(8, consultatie.getId());
			int affectedRows = ps.executeUpdate();
			DbConnections.closeConnection(conn);
			return affectedRows == 1;
		} catch (SQLException e) {
			return false;
		}

	}

	/**
	 * method to delete consultatie into a database by id
	 */

	public boolean delete(int id) {
		try {
			Connection conn = DbConnections.getConnection(DbConnections.ConnectionType.MYSQL);
			PreparedStatement ps = conn
					.prepareStatement("delete from consultatie where id = ?");
			ps.setInt(1, id);
			int affectedRows = ps.executeUpdate();
			DbConnections.closeConnection(conn);
			return affectedRows == 1;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	/**
	 * method to select consultatie from a database by id
	 */

	public Consultatie findById(int id) {
		Consultatie consultatie = null;
		try {
			Connection conn = DbConnections.getConnection(DbConnections.ConnectionType.MYSQL);
			PreparedStatement ps = conn
					.prepareStatement("select * from consultatie where id = ?");
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				int id2 = rs.getInt("id");
				int idPacient = rs.getInt("id_pacient");
				int idMedic = rs.getInt("id_medic");
				Integer idReteta = rs.getInt("id_reteta");
				if( idReteta == 0) {
					idReteta = null;
				}
				Integer idFactura = rs.getInt("id_factura");
				if(idFactura == 0) {
					idFactura = null;
				}
				Status status = Status.valueOf(rs.getString("status"));
				Date data = rs.getDate("data");
				String detalii = rs.getString("detalii");
				
				consultatie = new Consultatie(id2, idPacient, idMedic, idReteta, idFactura, status, DateHelper.asLocalDate(data), detalii);
			}
			DbConnections.closeConnection(conn);
			return consultatie;
		} catch (SQLException e) {
			return null;
		}

	}

	public List<Consultatie> findByIdPacient(int id) {
		Consultatie consultatie = null;
		List<Consultatie> consultatiiList = new ArrayList<>();
		try {
			Connection conn = DbConnections.getConnection(DbConnections.ConnectionType.MYSQL);
			PreparedStatement ps = conn
					.prepareStatement("select * from consultatie where id_pacient = ?");
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				int id2 = rs.getInt("id");
				int idPacient = rs.getInt("id_pacient");
				int idMedic = rs.getInt("id_medic");
				Integer idReteta = rs.getInt("id_reteta");
				if( idReteta == 0) {
					idReteta = null;
				}
				Integer idFactura = rs.getInt("id_factura");
				if(idFactura == 0) {
					idFactura = null;
				}
				Status status = Status.valueOf(rs.getString("status"));
				Date data = rs.getDate("data");
				String detalii = rs.getString("detalii");
				
				consultatie = new Consultatie(id2, idPacient, idMedic, idReteta, idFactura, status, DateHelper.asLocalDate(data), detalii);
				consultatiiList.add(consultatie);
			}
			DbConnections.closeConnection(conn);
			return consultatiiList;
		} catch (SQLException e) {
			return null;
		}

	}
	
	public List<Consultatie> findByDate(LocalDate date) {
		Consultatie consultatie = null;
		List<Consultatie> consultatiiList = new ArrayList<>();
		try {
			Connection conn = DbConnections.getConnection(DbConnections.ConnectionType.MYSQL);
			PreparedStatement ps = conn
					.prepareStatement("select * from consultatie where data = ?");
			ps.setDate(1, new java.sql.Date(DateHelper.asDate(date).getTime()));
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				int id2 = rs.getInt("id");
				int idPacient = rs.getInt("id_pacient");
				int idMedic = rs.getInt("id_medic");
				Integer idReteta = rs.getInt("id_reteta");
				if( idReteta == 0) {
					idReteta = null;
				}
				Integer idFactura = rs.getInt("id_factura");
				if(idFactura == 0) {
					idFactura = null;
				}
				Status status = Status.valueOf(rs.getString("status"));
				Date data = rs.getDate("data");
				String detalii = rs.getString("detalii");
				
				consultatie = new Consultatie(id2, idPacient, idMedic, idReteta, idFactura, status, DateHelper.asLocalDate(data), detalii);
				consultatiiList.add(consultatie);
			}
			DbConnections.closeConnection(conn);
			return consultatiiList;
		} catch (SQLException e) {
			return null;
		}

	}
	
	public List<Consultatie> findByIdMedic(int idMed) {
		Consultatie consultatie = null;
		List<Consultatie> consultatiiList = new ArrayList<>();
		try {
			Connection conn = DbConnections.getConnection(DbConnections.ConnectionType.MYSQL);
			PreparedStatement ps = conn
					.prepareStatement("select * from consultatie where id_medic = ?");
			ps.setInt(1, idMed);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				int id2 = rs.getInt("id");
				int idPacient = rs.getInt("id_pacient");
				int idMedic = rs.getInt("id_medic");
				Integer idReteta = rs.getInt("id_reteta");
				if( idReteta == 0) {
					idReteta = null;
				}
				Integer idFactura = rs.getInt("id_factura");
				if(idFactura == 0) {
					idFactura = null;
				}
				Consultatie.Status status = Consultatie.Status.valueOf(rs.getString("status"));
				Date data = rs.getDate("data");
				String detalii = rs.getString("detalii");
				
				consultatie = new Consultatie(id2, idPacient, idMedic, idReteta, idFactura, status, DateHelper.asLocalDate(data), detalii);
				consultatiiList.add(consultatie);
			}
			DbConnections.closeConnection(conn);
			return consultatiiList;
		} catch (SQLException e) {
			return null;
		}

	}
	/**
	 * method to show all consultatii from a database
	 */
	
	public Consultatie[] getAllConsultatii() {
		try {
			Connection conn = DbConnections.getConnection(DbConnections.ConnectionType.MYSQL);
			PreparedStatement ps = conn
					.prepareStatement("select * from consultatie");
			ResultSet rs = ps.executeQuery();
			List<Consultatie> consultatieList = new ArrayList<Consultatie>();
			while (rs.next()) {
				int id = rs.getInt("id");
				int idPacient = rs.getInt("id_pacient");
				int idMedic = rs.getInt("id_medic");
				int idReteta = rs.getInt("id_reteta");
				int idFactura = rs.getInt("id_factura");
				Status status = Status.valueOf(rs.getString("status"));
				Date data = rs.getDate("data");
				String detalii = rs.getString("detalii");
				
				consultatieList.add( new Consultatie(id, idPacient, idMedic, idReteta, idFactura, status,  DateHelper.asLocalDate(data), detalii));
			}
			
			DbConnections.closeConnection(conn);
			return consultatieList.toArray(new Consultatie[consultatieList.size()]);
		} catch (SQLException e) {
			return null;
		}

	}
}
