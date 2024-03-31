package Controller;

import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;

import DAO.EmprunteOperation;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(name = "emprunte", urlPatterns = "/emprunte/*")
public class RelanceController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private EmprunteOperation EmpruntDAO;

	@Override
	public void init() {
		EmpruntDAO = new EmprunteOperation();
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
		try {
			switch (action) {
			case "/envoyerrelance":
				EnvoyerRelance(request, response);
				break;
			case "/considererPerdu":
				ConsidererPerdu(request, response);
				break;
			case "/retoureExemplaire":
				retoureExemplaire(request, response);
				break;
			case "/checkAvailability":
				checkAvailability(request, response);
				break;
			case "/ajouteEmprunte":
				ajouteEmprunte(request, response);
				break;
			default:
				response.sendRedirect("../dashboard.jsp");
				break;
			}
		} catch (SQLException ex) {
			throw new ServletException(ex);
		}
	}

	private void retoureExemplaire(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException, SQLException {
		boolean flag = false;
		try {
			Date dateRetoure = Date.valueOf(request.getParameter("dateRetoure"));
			int idEtat = Integer.parseInt(request.getParameter("etats"));
			flag = EmpruntDAO.EngisteRetoure(request.getParameter("NumeroInventaire"), idEtat,
					request.getParameter("idEmprunteur"), dateRetoure);
		} catch (SQLException ex) {
			throw new ServletException(ex);
		}
		if (flag == true) {
			response.sendRedirect("../gestionRetoure.jsp?msg=valid");
		} else {
			response.sendRedirect("../gestionRetoure.jsp?msg=invalid");
		}
	}

	private void EnvoyerRelance(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException, SQLException {
		String[] idstring = request.getParameterValues("idEmprunte");
		boolean flag = false;
		int i = 0;
		for (String ids : idstring) {
			int id = Integer.parseInt(ids);
			flag = EmpruntDAO.RelancerUser(id);
			i++;
		}

		if (flag == true && i == 1) {
			response.sendRedirect("../EnvoyerRelance.jsp?msg=valid");
		} else if (flag == true && i > 1) {
			response.sendRedirect("../EnvoyerRelance.jsp?msg=allvalid");
		} else {
			response.sendRedirect("../EnvoyerRelance.jsp?msg=invalid");
		}
	}

	private void ConsidererPerdu(HttpServletRequest request, HttpServletResponse response)
			throws IOException, SQLException {
		String[] idstring = request.getParameterValues("idEmprunte");
		boolean flag = false;
		int i = 0;
		for (String ids : idstring) {
			int id = Integer.parseInt(ids);
			flag = EmpruntDAO.ConsidirerPerdu(id);
			i++;
		}
		if (flag == true && i == 1) {
			response.sendRedirect("../RetardApres30jours.jsp?msg=valid");
		} else if (flag == true && i > 1) {
			response.sendRedirect("../RetardApres30jours.jsp?msg=allvalid");
		} else {
			response.sendRedirect("../RetardApres30jours.jsp?msg=invalid");
		}
	}

	private void checkAvailability(HttpServletRequest request, HttpServletResponse response)
			throws IOException, SQLException, ServletException {
		String idEmprunteur = request.getParameter("CINSearch");
		String isbn = request.getParameter("isbnSearch");
		if (isbn.isEmpty() || idEmprunteur.isEmpty()) {
			response.sendRedirect("../emprunteLivre.jsp?msg=invalid");
			return;
		} else {
			String msg = EmpruntDAO.CheckExisting(isbn, idEmprunteur);
			if (!msg.isEmpty()) {
				response.sendRedirect("../emprunteLivre.jsp?isbn=" + isbn + "&id=" + idEmprunteur + "&msge=" + msg);
				return;
			}
			int nbCurrentCopy = EmpruntDAO.NumberOfCurrentCopyOfSomone(idEmprunteur);
			int nbAvailableCopy = EmpruntDAO.NumberOfAvailableCopy(isbn);
			if (nbAvailableCopy == 0) {
				response.sendRedirect(
						"../emprunteLivre.jsp?isbn=" + isbn + "&id=" + idEmprunteur + "&err=notavailable");
				return;
			} else if (nbCurrentCopy >= 2) {
				response.sendRedirect("../emprunteLivre.jsp?isbn=" + isbn + "&id=" + idEmprunteur + "&err=toomuch");
				return;
			} else if (nbAvailableCopy > 0 && nbCurrentCopy < 2) {
				response.sendRedirect("../emprunteLivre.jsp?msg=valid&isbn=" + isbn + "&id=" + idEmprunteur);
				return;
			}
		}
	}

	private void ajouteEmprunte(HttpServletRequest request, HttpServletResponse response)
			throws IOException, SQLException, ServletException {
		String idEmprunteur = request.getParameter("CIN");
		String isbn = request.getParameter("ISBN");
		String numIventaire = request.getParameter("numIventaire");
		EmpruntDAO.AddNewEmprunte(isbn, idEmprunteur, numIventaire);
		response.sendRedirect("../emprunteLivre.jsp?msg=allvalid");
	}
}
