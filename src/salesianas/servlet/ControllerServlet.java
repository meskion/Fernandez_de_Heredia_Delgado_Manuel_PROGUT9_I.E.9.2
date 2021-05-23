package salesianas.servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import salesianas.dao.DaoSocios;
import salesianas.socio.Socio;

/**
 * Servlet que maneja las peticiones al servidor web
 * 
 * @author manuf
 *
 */
public class ControllerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	/** Instancia del data access object */
	private DaoSocios dao;

	/**
	 * Iniciailiza una instancia del servlet
	 */
	public void init() {
		try {
			dao = DaoSocios.getInstance();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	/**
	 * Recibe peticiones al servidor
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

	/**
	 * Maneja las peticiones
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String action = request.getServletPath();

		System.out.println(action);

		try {
			switch (action) {
			case "/insert":
				insertSocio(request, response);
				break;
			case "/delete":
				deleteSocio(request, response);
				break;
			case "/update":
				updateSocio(request, response);
				break;
			default:
				listSocios(request, response);
				break;
			}
		} catch (SQLException ex) {
			throw new ServletException(ex);
		}
	}

	/**
	 * Metodo que redirige a la unica pagina con el CRUD. Accede a la lista de
	 * socios a traves del DAO para mostrarla en el la pagina.
	 * 
	 * @param request
	 * @param response
	 * @throws SQLException
	 * @throws IOException
	 * @throws ServletException
	 */
	private void listSocios(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException, ServletException {
		List<Socio> listSocio = dao.listAll();
		request.setAttribute("listSocio", listSocio);
		RequestDispatcher dispatcher = request.getRequestDispatcher("crudList.jsp");
		request.setAttribute("error", request.getParameter("error"));

		dispatcher.forward(request, response);
	}

	/**
	 * recibe una peticion con los datos de un nuevo usuario a insertar y lo delega
	 * al DAO
	 * 
	 * @param request
	 * @param response
	 * @throws SQLException
	 * @throws IOException
	 * @throws ServletException
	 */
	private void insertSocio(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException, ServletException {
		String error = "";
		try {
			Integer nSocio = Integer.parseInt(request.getParameter("nSocio"));
			String nombre = request.getParameter("nombre");
			Integer edad = Integer.parseInt(request.getParameter("edad"));
			Integer estatura = Integer.parseInt(request.getParameter("estatura"));
			String localidad = request.getParameter("localidad");

			Socio s = new Socio(nSocio, nombre, edad, estatura, localidad);
			dao.insert(s);
		} catch (NumberFormatException | SQLException e) {
			error = "ERROR: Datos introducidos no validos";
		}
		response.sendRedirect("list?error=" + error);
	}

	/**
	 * Recibe una peticion con datos de un socio a actualizar y lo delega al DAO
	 * 
	 * @param request
	 * @param response
	 * @throws SQLException
	 * @throws IOException
	 */
	private void updateSocio(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException {
		Integer nSocio = Integer.parseInt(request.getParameter("nSocio"));
		String nombre = request.getParameter("nombre");
		Integer edad = Integer.parseInt(request.getParameter("edad"));
		Integer estatura = Integer.parseInt(request.getParameter("estatura"));
		String localidad = request.getParameter("localidad");

		Socio s = new Socio(nSocio, nombre, edad, estatura, localidad);
		dao.update(s);
		response.sendRedirect("list");
	}

	/**
	 * Recibe el codigo de un socio y llama al DAO para que lo elimine.
	 * 
	 * @param request
	 * @param response
	 * @throws SQLException
	 * @throws IOException
	 */
	private void deleteSocio(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));

		dao.delete(id);
		response.sendRedirect("list");

	}
}
