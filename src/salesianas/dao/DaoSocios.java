package salesianas.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import salesianas.conections.DatabaseConection;
import salesianas.socio.Socio;

/**
 * Data Access Object, maneja la recuperación e inserción de información a
 * traves de la base datos conectada.
 * 
 * @author manuf
 *
 */
public class DaoSocios {

	private Connection con = null;
	private static DaoSocios instance = null;

	/**
	 * Constructor privado si aun no se ha instancia el singleton
	 * 
	 * @throws SQLException
	 */
	private DaoSocios() throws SQLException {
		System.out.println("ASDSDS");
		con = DatabaseConection.getConnection();
		System.out.println("con: " + con);
	}

	/**
	 * instncia el singleton o lo devuelve si ya esta construido.
	 * 
	 * @return
	 * @throws SQLException
	 */
	public static DaoSocios getInstance() throws SQLException {
		if (instance == null)
			instance = new DaoSocios();

		return instance;
	}

	/**
	 * Inserta un socio en la base de datos.
	 * 
	 * @param s
	 * @throws SQLException
	 */
	public void insert(Socio s) throws SQLException {

		PreparedStatement ps = con.prepareStatement("INSERT INTO socios VALUES(?, ?, ?, ?, ?)");
		ps.setInt(1, s.getnSocio());
		ps.setString(2, s.getNombre());
		ps.setInt(3, s.getEstatura());
		ps.setInt(4, s.getEdad());
		ps.setString(5, s.getLocalidad());

		ps.executeUpdate();

		ps.close();

	}

	/**
	 * Recupera una lista con todos los socios de la base de datos.
	 * 
	 * @return
	 * @throws SQLException
	 */
	public List<Socio> listAll() throws SQLException {

		PreparedStatement ps = con.prepareStatement("SELECT * FROM socios");
		ResultSet rs = ps.executeQuery();

		List<Socio> result = null;

		while (rs.next()) {
			if (result == null)
				result = new ArrayList<>();

			result.add(new Socio(rs.getInt("nSocio"), rs.getString("nombre"), rs.getInt("edad"), rs.getInt("estatura"),
					rs.getString("localidad")));
		}

		rs.close();
		ps.close();

		return result;

	}

	/**
	 * Elimina un socio de la base de datos.
	 * 
	 * @param s
	 * @throws SQLException
	 */
	public void delete(Socio s) throws SQLException {
		int id = s.getnSocio();
		delete(id);
	}

	/**
	 * Elimina un socio a traves de la base de datos a traves de su clave
	 * 
	 * @param id
	 * @throws SQLException
	 */
	public void delete(int id) throws SQLException {

		if (id <= 0)
			return;

		PreparedStatement ps = con.prepareStatement("DELETE FROM socios WHERE nSocio = ?");
		ps.setInt(1, id);

		ps.executeUpdate();

		ps.close();
	}

	/**
	 * Actualiza los campos de un socio de la base de datos.
	 * 
	 * @param s
	 * @throws SQLException
	 */
	public void update(Socio s) throws SQLException {
		int id = s.getnSocio();
		if (id == 0)
			return;

		PreparedStatement ps = con.prepareStatement(
				"UPDATE socios SET nombre = ?, estatura = ?, edad = ?, localidad = ? WHERE nSocio = ?");

		ps.setString(1, s.getNombre());
		ps.setInt(2, s.getEstatura());
		ps.setInt(3, s.getEdad());
		ps.setString(4, s.getLocalidad());
		ps.setInt(5, id);

		ps.executeUpdate();

		ps.close();

	}

}
