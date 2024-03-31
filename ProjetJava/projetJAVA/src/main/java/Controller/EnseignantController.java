package Controller;

import java.io.IOException;
import java.sql.SQLException;

import DAO.EnseignantOperation;
import DAO.UsagerOperation;
import Models.Enseignant;
import Models.Usager;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(name = "enseignant", urlPatterns = "/enseignant/*")
public class EnseignantController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private EnseignantOperation EnseignantDAO;
	private UsagerOperation UsagerDAO;

	@Override
	public void init() {
		UsagerDAO = new UsagerOperation();
		EnseignantDAO = new EnseignantOperation();
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
		try {
			switch (action) {
			case "/new":
				newAddEnseignant(request, response);
				break;
			case "/delete":
				deleteEnseignant(request, response);
				break;
			case "/update":
				updateEnseignant(request, response);
				break;
			default:
				response.sendRedirect("../ConsulterEnseignant.jsp");
				break;
			}
		} catch (SQLException ex) {
			throw new ServletException(ex);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void updateEnseignant(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, SQLException {

		String cin = request.getParameter("CIN");
		String email = request.getParameter("Email");
		String nom = request.getParameter("Nom");
		String prenom = request.getParameter("Prenom");
		String telephone = request.getParameter("phone");
		String grade = request.getParameter("Grade");
		if (cin != null && grade != null && nom != null && prenom != null && telephone != null && email != null) {
			Usager usager = new Usager(cin, email, nom, prenom, telephone);
			Enseignant enseignant = new Enseignant(cin, email, nom, prenom, telephone, grade);
			UsagerDAO.Modify_Usager(usager);
			EnseignantDAO.Modify_Enseignant(enseignant);
			response.sendRedirect("../ConsulterEnseignant.jsp");
		} else {
			response.sendRedirect("../ModifierEnseignant.jsp?msg=invalid");
		}
	}

	private void newAddEnseignant(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, SQLException {

		String cin = request.getParameter("CIN");
		String email = request.getParameter("Email");
		String nom = request.getParameter("Nom");
		String prenom = request.getParameter("Prenom");
		String telephone = request.getParameter("phone");
		String grade = request.getParameter("Grade");
		if (cin != null && grade != null && nom != null && prenom != null && telephone != null && email != null) {
			Usager usager = new Usager(cin, email, nom, prenom, telephone);
			Enseignant enseignant = new Enseignant(cin, email, nom, prenom, telephone, grade);
			UsagerDAO.Add_New_Usager(usager);
			EnseignantDAO.Add_New_Enseignant(enseignant);
			response.sendRedirect("../AjouterEnseignant.jsp?msg=valid");
		} else {
			response.sendRedirect("../AjouterEnseignant.jsp?msg=invalid");
		}
	}

	private void deleteEnseignant(HttpServletRequest request, HttpServletResponse response) throws Exception {
		try {
			String CIN = request.getParameter("CIN");
			EnseignantDAO.DeleteEnseignant(CIN);
			UsagerDAO.DeleteUsager(CIN);
		} catch (SQLException e) {
			throw new ServletException(e);
		}
		response.sendRedirect("../ConsulterEnseignant.jsp");
	}
}
