package Controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

import DAO.LoginOperation;


@WebServlet("/Login")
public class LoginController extends HttpServlet { 

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	
	String uname = req.getParameter("uname");
	String pwd = req.getParameter("pwd");
	String loginType = req.getParameter("loginType");
	
	
	LoginOperation dbc = new LoginOperation();
	
	 // Si le loginType est Biblio:
    if ("Biblio".equals(loginType)) {
        if (dbc.verifierLoginBiblio(uname, pwd)) {
            HttpSession session = req.getSession();
            session.setAttribute("username", uname);
            resp.sendRedirect("./dashboardBibliothecaire.jsp");  // a modifier selon le path!
        } else {
            resp.sendRedirect("./Login/loginBiblio.jsp?error=true");
        }
    }
    
    // Sinon, si le loginType est Assist:
    else if ("Assist".equals(loginType)) {
        if (dbc.verifierLoginAssist(uname, pwd)) {
            HttpSession session = req.getSession();
            session.setAttribute("username", uname);
            resp.sendRedirect("./dashboardAssistant.jsp");
        } else {
            resp.sendRedirect("./Login/loginAssistant.jsp?error=true");
        }
    }
  
}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		  doPost(req, resp);
	
	}
	

     
 


}