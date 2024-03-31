package Controller;

import java.io.IOException;
import java.sql.SQLException;

import DAO.EtudiantOperation;
import DAO.UsagerOperation;
import Models.Etudiant;
import Models.Usager;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(name = "etudiant", urlPatterns = "/etudiant/*")
public class EtudiantController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private EtudiantOperation EtudiantDAO;
	private UsagerOperation UsagerDAO;

	@Override
	public void init() {
		UsagerDAO = new UsagerOperation();
		EtudiantDAO = new EtudiantOperation();
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
				System.out.println("ggg");
				newAddEtudiant(request, response);
				break;
			case "/delete":
				deleteEtudiant(request, response);
				break;
			case "/update":
				updateEtudiant(request, response);
				break;
			default:
				response.sendRedirect("../ConsulterExemplaire.jsp");
				break;
			}
		} catch (Exception ex) {
			throw new ServletException(ex);
		}
	}

	private void updateEtudiant(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, SQLException {

		String cin = request.getParameter("CIN");
		String email = request.getParameter("Email");
		String nom = request.getParameter("Nom");
		String prenom = request.getParameter("Prenom");
		String telephone = request.getParameter("phone");
		String adresse = request.getParameter("Adresse");
		String cne = request.getParameter("CIN");
		if (cin != null && cne != null && adresse != null && nom != null && prenom != null && telephone != null
				&& email != null) {
			Usager usager = new Usager(cin, email, nom, prenom, telephone);
			Etudiant etudiant = new Etudiant(cin, email, nom, prenom, telephone, adresse, cne);
			EtudiantDAO.ModifyEtudiant(etudiant);
			UsagerDAO.Modify_Usager(usager);
			response.sendRedirect("../ConsulterEtudiant.jsp");
		} else {
			response.sendRedirect("../ModifierEtudiant.jsp?msg=invalid");
		}
	}

	private void newAddEtudiant(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, SQLException {
		String cin = request.getParameter("CIN");
		String email = request.getParameter("Email");
		String nom = request.getParameter("Nom");
		String prenom = request.getParameter("Prenom");
		String telephone = request.getParameter("phone");
		String adresse = request.getParameter("Adresse");
		String cne = request.getParameter("CIN");
		if (cin != null && cne != null && adresse != null && nom != null && prenom != null && telephone != null
				&& email != null) {
			Usager usager = new Usager(cin, email, nom, prenom, telephone);
			Etudiant etudiant = new Etudiant(cin, email, nom, prenom, telephone, adresse, cne);
			UsagerDAO.Add_New_Usager(usager);
			EtudiantDAO.Add_New_Etudiant(etudiant);
			response.sendRedirect("../AjouterEtudiant.jsp?msg=valid");
		} else {
			response.sendRedirect("../AjouterEtudiant.jsp?msg=invalid");
		}
	}

	private void deleteEtudiant(HttpServletRequest request, HttpServletResponse response) throws Exception {
		try {
			String CIN = request.getParameter("CIN");
			EtudiantDAO.DeleteEtudiant(CIN);
			UsagerDAO.DeleteUsager(CIN);
		} catch (SQLException e) {
			throw new ServletException(e);
		}
		response.sendRedirect("../ConsulterEtudiant.jsp");
	}
}
