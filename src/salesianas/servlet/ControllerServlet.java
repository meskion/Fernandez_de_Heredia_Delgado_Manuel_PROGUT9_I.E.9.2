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

public class ControllerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private DaoSocios dao;

	public void init() {
		try {
			dao = DaoSocios.getInstance();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String action = request.getServletPath();

		System.out.println(action);

		try {
			switch (action) {
			case "/new":
				showNewForm(request, response);
				break;
			case "/insert":
				insertSocio(request, response);
				break;
			case "/delete":
				deleteSocio(request, response);
				break;
			case "/edit":
				showEditForm(request, response);
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

	private void listSocios(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException, ServletException {
		List<Socio> listSocio = dao.listAll();
		request.setAttribute("listSocio", listSocio);
		RequestDispatcher dispatcher = request.getRequestDispatcher("crudList.jsp");
		request.setAttribute("error", request.getParameter("error"));
		
		dispatcher.forward(request, response);
		
//		request.getRequestDispatcher("crudList.jsp").forward(request, response);
	}

	private void showNewForm(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		RequestDispatcher dispatcher = request.getRequestDispatcher("BookForm.jsp");
		dispatcher.forward(request, response);
	}

	/*
	 * Este metodo lo tendre que reutilizar quizas cuando edite filas de la bbdd
	 */
	private void showEditForm(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, ServletException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		Socio existingBook = dao.getSocio(id);
		RequestDispatcher dispatcher = request.getRequestDispatcher("BookForm.jsp");
		request.setAttribute("book", existingBook);
		dispatcher.forward(request, response);

	}

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

	private void deleteSocio(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));

		dao.delete(id);
		response.sendRedirect("list");

	}
}
