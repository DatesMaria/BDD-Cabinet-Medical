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

import model.cabinetMedical.Factura;

public class FacturaDAO {
	/**
	 * method to add factura into a database
	 */
	public void addFactura(Factura factura) {
		try {
			Connection conn = DbConnections.getConnection(DbConnections.ConnectionType.MYSQL);
			PreparedStatement ps = conn.prepareStatement(
					"insert into factura( id_consultatie, valoare, data, TVA) values(?,?,?,?)",
					Statement.RETURN_GENERATED_KEYS);
			ps.setInt(1, factura.getIdConsultatie());
			ps.setDouble(2, factura.getValoare());
			ps.setDate(3, new java.sql.Date(DateHelper.asDate(factura.getData()).getTime()));
			ps.setDouble(4, factura.getTva());
			
			int affectedRows = ps.executeUpdate();
			ResultSet rs = ps.getGeneratedKeys();
			if (rs.next()) {
				factura.setId(rs.getInt(1));
	
			}
			DbConnections.closeConnection(conn);
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}
	
	/**
	 * method to update factura into a database by id
	 */

	public boolean update(Factura factura) {
		try {
			Connection conn = DbConnections.getConnection(DbConnections.ConnectionType.MYSQL);
			PreparedStatement ps = conn
					.prepareStatement("update factura set id_consultatie = ?, valoare = ?, data = ?, tva = ? where id = ?");
			
			ps.setInt(1, factura.getIdConsultatie());
			ps.setDouble(2, factura.getValoare());
			ps.setDate(3, new java.sql.Date(DateHelper.asDate(factura.getData()).getTime()));
			ps.setDouble(4, factura.getTva());
			ps.setInt(5, factura.getId());
			int affectedRows = ps.executeUpdate();
			DbConnections.closeConnection(conn);
			return affectedRows == 1;
		} catch (SQLException e) {
			return false;
		}

	}

	/**
	 * method to delete factura into a database by id
	 */

	public boolean delete(int id) {
		try {
			Connection conn = DbConnections.getConnection(DbConnections.ConnectionType.MYSQL);
			PreparedStatement ps = conn
					.prepareStatement("delete from factura where id = ?");
			ps.setInt(1, id);
			int affectedRows = ps.executeUpdate();
			DbConnections.closeConnection(conn);
			return affectedRows == 1;
		} catch (SQLException e) {
			return false;
		}

	}

	/**
	 * method to select factura from a database by id
	 */

	public Factura findById(int id) {
		Factura factura = null;
		try {
			Connection conn = DbConnections.getConnection(DbConnections.ConnectionType.MYSQL);
			PreparedStatement ps = conn
					.prepareStatement("select * from factura where id = ?");
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				int id2 = rs.getInt("id");
				int idConsultatie = rs.getInt("id_consultatie");
		        Double valoare = rs.getDouble("valoare");
			    Date data = rs.getDate("data");
			   Double tva = rs.getDouble("TVA");
				
				factura = new Factura(id2, idConsultatie, valoare, DateHelper.asLocalDate(data), tva);
			}
			DbConnections.closeConnection(conn);
			return factura;
		} catch (SQLException e) {
			return null;
		}

	}
	public List<Factura> findByIdConsultatie(int id) {
		Factura factura = null;
		ArrayList<Factura> facturaList = new ArrayList<>();
		try {
			Connection conn = DbConnections.getConnection(DbConnections.ConnectionType.MYSQL);
			PreparedStatement ps = conn
					.prepareStatement("select * from factura where id_consultatie = ?");
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				int id2 = rs.getInt("id");
				int idConsultatie = rs.getInt("id_consultatie");
		        Double valoare = rs.getDouble("valoare");
			    Date data = rs.getDate("data");
			    Double tva = rs.getDouble("TVA");
				
				factura = new Factura(id2, idConsultatie, valoare, DateHelper.asLocalDate(data), tva);
				facturaList.add(factura);
			}
			DbConnections.closeConnection(conn);
			return facturaList;
		} catch (SQLException e) {
			return null;
		}

	}

	/**
	 * method to show all facturi from a database
	 */
	
	public Factura[] getAllFacturi() {
		try {
			Connection conn = DbConnections.getConnection(DbConnections.ConnectionType.MYSQL);
			PreparedStatement ps = conn
					.prepareStatement("select * from factura");
			ResultSet rs = ps.executeQuery();
			List<Factura> facturiList = new ArrayList<Factura>();
			while (rs.next()) {
				int id = rs.getInt("id");
				int idConsultatie = rs.getInt("id_consultatie");
		        Double valoare = rs.getDouble("valoare");
			    Date data = rs.getDate("data");
			   Double tva = rs.getDouble("TVA");
				
				facturiList.add( new Factura(id, idConsultatie, valoare, DateHelper.asLocalDate(data), tva));
			}
			DbConnections.closeConnection(conn);
			return facturiList.toArray(new Factura[facturiList.size()]);
		} catch (SQLException e) {
			return null;
		}

	}

}
