package DAO;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import Models.Livre;

public class LivreOperation {
	private static final String SELECT_ALL_Livre = "SELECT * FROM Livre;";
	private static final String SELECT_SINGLE_Livre = "SELECT * FROM Livre WHERE ISBN = ?;";
	private static final String ADD_NEW_Livre = "INSERT INTO Livre(ISBN,Titre,Resume,DateEdition) VALUES (?, ?, ?, ?);";
	private static final String UPDTAE_UN_Livre = "UPDATE Livre set Titre = ?, Resume = ?,DateEdition = ? Where ISBN = ?;";
	private static final String DELETE_UN_Livre = "DELETE FROM Livre WHERE ISBN = ?;";

	// Get all books from database
	public ArrayList<Livre> getAllLivre() throws SQLException {
		ArrayList<Livre> listLivre = new ArrayList<Livre>();
		Connection connection = null;
		PreparedStatement statement = null;
		try {
			Connexion connexion = new Connexion();
			connection = connexion.getConnection();
			statement = connection.prepareStatement(SELECT_ALL_Livre);
			ResultSet rs = statement.executeQuery();
			while (rs.next()) {
				String isbn = rs.getString("ISBN");
				String titre = rs.getString("Titre");
				String resume = rs.getString("Resume");
				Date dateEdition = rs.getDate("DateEdition");

				// get all authors
				ArrayList<String> auteurs = Actions.getAuteurLivre(connection, isbn);

				// add book to the list of books
				listLivre.add(new Livre(isbn, titre, resume, dateEdition, auteurs));
			}
		} catch (SQLException e) {
			System.out.println("a problem has occured " + e);
		}
		connection.close();
		return listLivre;
	}

	// Get a single book
	public Livre getLivre(String ISBN) throws SQLException {
		Livre l = null;
		try {
			Connexion connexion = new Connexion();
			Connection connection = connexion.getConnection();
			PreparedStatement statment = connection.prepareStatement(SELECT_SINGLE_Livre);
			statment.setString(1, ISBN);
			ResultSet rs = statment.executeQuery();
			while (rs.next()) {
				String isbn = rs.getString("ISBN");
				String titre = rs.getString("Titre");
				String resume = rs.getString("Resume");
				Date dateEdition = rs.getDate("DateEdition");
				l = new Livre(isbn, titre, resume, dateEdition);
			}
		} catch (SQLException e) {
			System.out.println(e);
		}
		return l;
	}

	// Add new book
	public void addLivre(Livre livre) throws SQLException {
		Connection connection = null;
		PreparedStatement statement = null;
		try {
			Connexion connexion = new Connexion();
			connection = connexion.getConnection();
			statement = connection.prepareStatement(ADD_NEW_Livre);
			statement.setString(1, livre.getISBN());
			statement.setString(2, livre.getTitre());
			statement.setString(3, livre.getResume());
			statement.setDate(4, livre.getDateEdition());
			statement.executeUpdate();
		} catch (SQLException e) {
			System.out.println(e);
		}
		connection.close();
	}

	// Update a Book
	public void updateLivre(Livre livre) throws SQLException {
		Connection connection = null;
		PreparedStatement statement = null;
		try {
			Connexion connexion = new Connexion();
			connection = connexion.getConnection();
			statement = connection.prepareStatement(UPDTAE_UN_Livre);
			statement.setString(1, livre.getTitre());
			statement.setString(2, livre.getResume());
			statement.setDate(3, livre.getDateEdition());
			statement.setString(4, livre.getISBN());
			statement.executeUpdate();
		} catch (SQLException e) {
			System.out.println(e);
		}
		connection.close();
	}

	// delete a book
	public void deleteLivre(String ISBN) throws SQLException {
		Connection connection = null;
		PreparedStatement statement = null;
		try {
			Connexion connexion = new Connexion();
			connection = connexion.getConnection();
			connection = connexion.getConnection();
			statement = connection.prepareStatement(DELETE_UN_Livre);
			statement.setString(1, ISBN);
			statement.executeUpdate();
		} catch (SQLException e) {
			System.out.println(e);
		}
		connection.close();
	}
}
