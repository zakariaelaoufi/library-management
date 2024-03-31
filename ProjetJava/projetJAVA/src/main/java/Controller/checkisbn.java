package Controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import DAO.Connexion;

@WebServlet("/checkisbn")
public class checkisbn extends HttpServlet {
	private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String isbnToCheck = request.getParameter("isbn");

        // Query your database to check if the ISBN exists
        boolean isbnExists = checkIsbnExistsInDatabase(isbnToCheck);

        // Set the response based on whether the ISBN exists
        if (isbnExists) {
            response.getWriter().write("exists");
        } else {
            response.getWriter().write("notexists");
        }
    }
    
    private boolean checkIsbnExistsInDatabase(String isbn) {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        boolean isbnExists = false;

        try {
            // Establish a connection to your database (replace databaseUrl, username, and password with your actual values)
        	Connexion connexion = new Connexion();
            connection = connexion.getConnection();

            // Prepare the SQL query to check if the ISBN exists
            String query = "SELECT COUNT(*) FROM livre WHERE ISBN = ?";
            statement = connection.prepareStatement(query);
            statement.setString(1, isbn);

            // Execute the query
            resultSet = statement.executeQuery();

            // Check if the ISBN exists by checking the count
            if (resultSet.next() && resultSet.getInt(1) > 0) {
                isbnExists = true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // Close resources
            try {
                if (resultSet != null) {
                    resultSet.close();
                }
                if (statement != null) {
                    statement.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return isbnExists;
    }

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
