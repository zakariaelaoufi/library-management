package Controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import DAO.ExemplaireOperation;
import Models.Exemplaire;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(name = "exemplaire", urlPatterns = "/exemplaire/*")
public class ExemplaireController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private ExemplaireOperation ExemplaireDAO;

	@Override
	public void init() {
		ExemplaireDAO = new ExemplaireOperation();
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		String action = request.getPathInfo();
		;
		switch (action) {
		case "/new":
			newAddForm(request, response);
			break;
		case "/insert":
			insererExemplaire(request, response);
			break;
		case "/destroy":
			delstroyExemplaire(request, response);
			break;
		case "/Reparation":
			ReparationExemplaire(request, response);
			break;
		default:
			response.sendRedirect("../ConsulterExemplaire.jsp");
			break;
		}
	}

	private void newAddForm(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			List<String> ISBN = ExemplaireDAO.getAllISBNLivre();
			request.setAttribute("ISBN", ISBN);
			RequestDispatcher dispatcher = request.getRequestDispatcher("../AjouterExemplaire.jsp");
			dispatcher.forward(request, response);
		} catch (SQLException e) {
			throw new ServletException(e);
		}
	}

	private void insererExemplaire(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			String ISBN = request.getParameter("livreISBN");
			String Etat = request.getParameter("etats");
			int idEtat = Integer.parseInt(Etat);
			String nIventaire = request.getParameter("numInventaire");
			boolean flag = false;
			if (ISBN != null && Etat != null && nIventaire != null) {
				ExemplaireDAO.addExemplaire(new Exemplaire(nIventaire, idEtat, ISBN));
				flag = true;
			}
			if (flag == true) {
				response.sendRedirect("../exemplaire/new?msg=valid");
			} else {
				response.sendRedirect("../exemplaire/new?msg=invalid");
			}
		} catch (SQLException e) {
			response.sendRedirect("../exemplaire/new?msg=invalid");
		}
	}

	private void ReparationExemplaire(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		try {
			String numeroInventaire = request.getParameter("numeroInventaire");
			ExemplaireDAO.ReparationExemplaire(numeroInventaire);

		} catch (SQLException e) {
			throw new ServletException(e);
		}
		response.sendRedirect("../ConsulterExemplaire.jsp");

	}

	private void delstroyExemplaire(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		try {
			String numeroInventaire = request.getParameter("numeroInventaire");
			ExemplaireDAO.deleteExemplaire(numeroInventaire);

		} catch (SQLException e) {
			throw new ServletException(e);
		}
		response.sendRedirect("../exemplaire/list");

	}

}
