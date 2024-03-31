package Controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebServlet("/logout")
public class Logout extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// Invalider la session pour déconnecter l'utilisateur
        HttpSession session = request.getSession(false);
        if (session != null) {
            session.invalidate();
        }
        
     // Add these headers to prevent caching of authenticated pages
//        response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate"); // HTTP 1.1.
//        response.setHeader("Pragma", "no-cache"); // HTTP 1.0.
//        response.setDateHeader("Expires", 0); // Proxies.
//        
        
        // Rediriger vers la page de connexion
        response.sendRedirect("Accueil_page.jsp"); // Remplacer "Login.jsp" par le chemin vers votre page de connexion si différent
		
	}

	

}