
	package database;

	import java.sql.Connection;
	import java.sql.DriverManager;
	import java.sql.PreparedStatement;
	import java.sql.ResultSet;
	import java.sql.SQLException;
	import java.sql.Statement;
	import java.util.ArrayList;
	import java.util.List;

	import model.user.Asistent;

	public class AsistentDAO {
		
		/**
		 * method to add asistent into a database
		 */
		public void addAsistent(Asistent asistent) {
			try {
				Connection conn =DbConnections.getConnection(DbConnections.ConnectionType.POSTGRESQL);
				PreparedStatement ps = conn.prepareStatement(
						"insert into public.asistent(id_user, nume, prenume) values(?,?,?)", Statement.RETURN_GENERATED_KEYS);
				ps.setInt(1, asistent.getIdUser());
				ps.setString(2, asistent.getNume());
				ps.setString(3, asistent.getPrenume());

				int affectedRows = ps.executeUpdate();
				ResultSet rs = ps.getGeneratedKeys();
				if (rs.next()) {
					asistent.setId(rs.getInt(1));
				}
				DbConnections.closeConnection(conn);
			} catch (SQLException e) {
				e.printStackTrace();
			}

		}

		/**
		 * method to update asistent into a database by id
		 */

		public boolean update(Asistent asistent) {
			try {
				Connection conn = DbConnections.getConnection(DbConnections.ConnectionType.POSTGRESQL);
				PreparedStatement ps = conn
						.prepareStatement("update public.asistent set id_user = ?, nume = ?, prenume = ? where id = ?");

				ps.setInt(1, asistent.getIdUser());
				ps.setString(2, asistent.getNume());
				ps.setString(3, asistent.getPrenume());
				int affectedRows = ps.executeUpdate();
				 DbConnections.closeConnection(conn);
				return affectedRows == 1;
			} catch (SQLException e) {
				return false;
			}

		}

		/**
		 * method to delete asistent into a database by id
		 */

		public boolean delete(int id) {
			try {
				Connection conn = DbConnections.getConnection(DbConnections.ConnectionType.POSTGRESQL);
				PreparedStatement ps = conn.prepareStatement("delete from public.asistent where id = ?");
				ps.setInt(1, id);
				int affectedRows = ps.executeUpdate();
				 DbConnections.closeConnection(conn);
				return affectedRows == 1;
			} catch (SQLException e) {
				return false;
			}

		}

		/**
		 * method to select a from asistent database by id
		 */

		public Asistent findById(int id) {
			Asistent asistent = null;
			try {
				Connection conn = DbConnections.getConnection(DbConnections.ConnectionType.POSTGRESQL);
				PreparedStatement ps = conn.prepareStatement("select * from public.asistent where id = ?");
				ps.setInt(1, id);
				ResultSet rs = ps.executeQuery();
				if (rs.next()) {
					int id2 = rs.getInt("id");
					int idUser = rs.getInt("id_user");
					String nume = rs.getString("nume");
					String prenume = rs.getString("prenume");

					asistent = new Asistent(id2, idUser, nume, prenume);
				}
				 DbConnections.closeConnection(conn);
				return asistent;
			} catch (SQLException e) {
				return null;
			}

		}

		/**
		 * method to show all asistenti from a database
		 */

		public Asistent[] getAllAsistenti() {
			try {
				Connection conn = DbConnections.getConnection(DbConnections.ConnectionType.POSTGRESQL);
				PreparedStatement ps = conn.prepareStatement("select * from public.asistent");
				ResultSet rs = ps.executeQuery();
				List<Asistent> asistentiList = new ArrayList<Asistent>();
				while (rs.next()) {
					int id = rs.getInt("id");
					int idUser = rs.getInt("id_user");
					String nume = rs.getString("nume");
					String prenume = rs.getString("prenume");
					
					asistentiList.add(new Asistent(id, idUser, nume, prenume));
				}
			    DbConnections.closeConnection(conn);
				return asistentiList.toArray(new Asistent[asistentiList.size()]);
			} catch (SQLException e) {
				return null;
			}

		}

	}



