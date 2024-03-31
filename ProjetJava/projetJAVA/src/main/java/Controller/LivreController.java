package Controller;

import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.util.List;

import DAO.Actions;
import DAO.AuteurOperation;
import DAO.LivreOperation;
import Models.Auteur;
import Models.Livre;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(name = "livre", urlPatterns = "/livre/*")
public class LivreController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private LivreOperation livreDAO;
	private AuteurOperation AuteurDAO;

	@Override
	public void init() {
		livreDAO = new LivreOperation();
		AuteurDAO = new AuteurOperation();
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String action = request.getPathInfo();
		;
		try {
			switch (action) {
			case "/new":
				addLivre(request, response);
				break;
			case "/delete":
				deleteLivre(request, response);
				break;
			case "/update":
				updateLivre(request, response);
				break;
			case "/list":
				listLivre(request, response);
				break;
			default:
				response.sendRedirect("../dashboard.jsp");
				break;
			}
		} catch (SQLException ex) {
			throw new ServletException(ex);
		}
	}

	private void deleteLivre(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException, SQLException {
		try {
			String ISBN = request.getParameter("isbn");
			// Delete existing authors
			Actions.deleteAuteurLivre(ISBN);
			// Delete existing keywords
			Actions.deleteKeyLivre(ISBN);
			// Delete existing authors not linked to any book
			Actions.deleteNotLinked();
			// delete the book
			livreDAO.deleteLivre(ISBN);

		} catch (SQLException e) {
			throw new ServletException(e);
		}
		response.sendRedirect("../listLivre.jsp");
	}

	private void updateLivre(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException, SQLException {
		String ISBN = request.getParameter("ISBN");
		String titre = request.getParameter("titre");
		String resume = request.getParameter("resume");
		String dateEditionParam = request.getParameter("dateEdition");
		Date dateEdition = Date.valueOf(dateEditionParam);
		String[] Auteurs = request.getParameterValues("auteur[]");
		String[] Keywords = request.getParameterValues("keyword[]");

		if (dateEditionParam != null && ISBN != null && titre != null && resume != null && Auteurs != null
				&& Keywords != null) {

			Livre updatedLivre = new Livre(ISBN, titre, resume, dateEdition);
			livreDAO.updateLivre(updatedLivre);

			// Delete existing authors
			Actions.deleteAuteurLivre(ISBN);
			// Delete existing keywords
			Actions.deleteKeyLivre(ISBN);
			// Delete existing authors not linked to any book
			Actions.deleteNotLinked();

			// add new authors
			for (String auteur : Auteurs) {
				Auteur author = new Auteur(auteur);
				AuteurDAO.addAuteur(author);
				// associate author with book
				Actions.insertAuteurLivre(ISBN);
			}

			// add new keywords
			for (String Keyword : Keywords) {
				int idKeyword = Integer.parseInt(Keyword);
				Actions.insertKeyLivre(ISBN, idKeyword);
			}

		}

		// Redirect to a confirmation page or listLivre.jsp after the update
		response.sendRedirect("../listLivre.jsp");
	}

	// lister all books
	private void listLivre(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			List<Livre> livres = livreDAO.getAllLivre();
			request.setAttribute("livres", livres);
			RequestDispatcher dispatcher = request.getRequestDispatcher("../listLivre.jsp");
			dispatcher.forward(request, response);
		} catch (SQLException e) {
			throw new ServletException(e);
		}
	}

	// inserer nouveaux livre
	private void addLivre(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
		String isbn = request.getParameter("ISBN");
		String titre = request.getParameter("Titre");
		String resume = request.getParameter("Resume");
		String dateEditionParam = request.getParameter("dateEdition");
		Date dateEdition = Date.valueOf(dateEditionParam);
		String[] Auteurs = request.getParameterValues("auteur[]");
		String[] Keywords = request.getParameterValues("keyword[]");
		if (dateEditionParam != null && isbn != null && titre != null && resume != null && Auteurs != null
				&& Keywords != null) {
			boolean flag = false;
			boolean flag2 = false;
			boolean flag3 = false;

			// add book
			Livre newLivre = new Livre(isbn, titre, resume, dateEdition);
			livreDAO.addLivre(newLivre);
			flag = true;

			// add author
			for (String auteur : Auteurs) {
				Auteur author = new Auteur(auteur);
				AuteurDAO.addAuteur(author);
				// associate author with book
				Actions.insertAuteurLivre(isbn);
				flag2 = true;
			}

			// associate keyword with book
			for (String Keyword : Keywords) {
				int idKeyword = Integer.parseInt(Keyword);
				Actions.insertKeyLivre(isbn, idKeyword);
				flag3 = true;
			}

			// check if the data of all table is properly inserted to the database
			if (flag == true && flag2 == true && flag3 == true) {
				response.sendRedirect("../AjouterLivre.jsp?msg=valid");
			} else {
				response.sendRedirect("../AjouterLivre.jsp?msg=invalid");
			}
		} else {
			response.sendRedirect("../AjouterLivre.jsp?msg=invalid");
		}

	}

}
