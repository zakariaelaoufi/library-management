package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import Models.Auteur;

public class AuteurOperation {
	private static final String SELECT_ALL_Auteur = "SELECT * FROM Auteur;";
	private static final String SELECT_SINGLE_Auteur = "SELECT * FROM Auteur WHERE idAuteur = ?;";
	private static final String ADD_NEW_Auteur = "INSERT INTO Auteur(nomAuteur) VALUES (?);";
	private static final String UPDTAE_UN_Auteur = "UPDATE Livre SET nomAuteur = ? Where idAuteur = ?;";
	private static final String DELETE_UN_Auteur = "DELETE FROM Auteur WHERE idAuteur = ?;";

	public List<Auteur> getAllAutheur() throws SQLException {
		List<Auteur> listAuteur = new ArrayList<Auteur>();
		try {
			Connection connection = null;
			PreparedStatement statement = null;
			Connexion connexion = new Connexion();
			connection = connexion.getConnection();
			statement = connection.prepareStatement(SELECT_ALL_Auteur);
			ResultSet rs = statement.executeQuery();
			while (rs.next()) {
				String nomAuteur = rs.getString("nomAuteur");
				listAuteur.add(new Auteur(nomAuteur));
				connection.close();
			}
		} catch (SQLException e) {
			System.out.println("a problem has occured " + e);
		}
		return listAuteur;
	}

	public Auteur getAuteur(Auteur auteur) throws SQLException {
		Auteur author = null;
		try {
			Connection connection = null;
			PreparedStatement statement = null;
			Connexion connexion = new Connexion();
			connection = connexion.getConnection();
			statement = connection.prepareStatement(SELECT_SINGLE_Auteur);
			statement.setInt(1, auteur.getIdAuteur());
			;
			ResultSet rs = statement.executeQuery();
			while (rs.next()) {
				String nomAuteur = rs.getString("nomAuteur");
				author = new Auteur(nomAuteur);
			}
			connection.close();
		} catch (SQLException e) {
			System.out.println(e);
		}
		return author;
	}

	public void addAuteur(Auteur auteur) throws SQLException {
		Connection connection = null;
		PreparedStatement statement = null;
		try {
			Connexion connexion = new Connexion();
			connection = connexion.getConnection();
			statement = connection.prepareStatement(ADD_NEW_Auteur);
			statement.setString(1, auteur.getNomAuteur());
			statement.executeUpdate();
		} catch (SQLException e) {
			System.out.println(e);
		}
		connection.close();
	}

	public void updateAuteur(Auteur auteur) throws SQLException {
		Connection connection = null;
		PreparedStatement statement = null;
		try {
			Connexion connexion = new Connexion();
			connection = connexion.getConnection();
			statement = connection.prepareStatement(UPDTAE_UN_Auteur);
			statement.setString(1, auteur.getNomAuteur());
			statement.setInt(2, auteur.getIdAuteur());
			statement.executeUpdate();
		} catch (SQLException e) {
			System.out.println(e);
		}
		connection.close();
	}

	public void deleteAuteur(Auteur auteur) throws SQLException {
		Connection connection = null;
		PreparedStatement statement = null;
		try {
			Connexion connexion = new Connexion();
			connection = connexion.getConnection();
			statement = connection.prepareStatement(DELETE_UN_Auteur);
			statement.setInt(1, auteur.getIdAuteur());
			statement.executeUpdate();
		} catch (SQLException e) {
			System.out.println(e);
		}
	}
}
