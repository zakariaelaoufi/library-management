package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import Models.Exemplaire;

public class ExemplaireOperation {
	private static final String SELECT_ALL_Livre_ISBN = "SELECT ISBN FROM Livre;";
	private static final String Add_New_Exemplaire = "INSERT INTO Exemplaire VALUES (?, ?, ?,?);";
	private static final String SELECT_ALL_Exemplaire = "SELECT * FROM Exemplaire;";
	private static final String DELETE_UN_Exemplaire = "DELETE FROM exemplaire WHERE numeroInventaire = ?;";
	private static final String Reparation_Exemplaire = "UPDATE exemplaire set idEtat = 5 Where numeroInventaire = ?;";

	public List<String> getAllISBNLivre() throws SQLException {
		List<String> listISBN = new ArrayList<String>();
		Connection connection = null;
		PreparedStatement statement = null;
		try {
			Connexion connexion = new Connexion();
			connection = connexion.getConnection();
			statement = connection.prepareStatement(SELECT_ALL_Livre_ISBN);
			ResultSet rs = statement.executeQuery();
			while (rs.next()) {
				String isbn = rs.getString("ISBN");
				listISBN.add(isbn);
			}
		} catch (SQLException e) {
			System.out.println("a problem has occured " + e);
		}
		connection.close();
		return listISBN;

	}

	public void addExemplaire(Exemplaire exemplaire) throws SQLException {
		Connection connection = null;
		PreparedStatement statement = null;
		try {
			Connexion connexion = new Connexion();
			connection = connexion.getConnection();
			statement = connection.prepareStatement(Add_New_Exemplaire);
			statement.setString(1, exemplaire.getNumeroInventaire());
			statement.setInt(2, exemplaire.getIdEtat());
			statement.setString(3, exemplaire.getIsbn());
			statement.setBoolean(4, true);
			statement.executeUpdate();
		} catch (SQLException e) {
			System.out.println(e);
		}
		connection.close();
	}

	public ArrayList<Exemplaire> getAllExemplaire() throws SQLException {
		ArrayList<Exemplaire> lex = new ArrayList<Exemplaire>();
		Connection connection = null;
		PreparedStatement ps = null;
		try {
			Connexion connexion = new Connexion();
			connection = connexion.getConnection();

			ps = connection.prepareStatement(SELECT_ALL_Exemplaire);

			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				String numInventaire = rs.getString("NumeroInventaire");
				int idEtat = rs.getInt("idEtat");
				String isbn = rs.getString("ISBN");
				String EtatExemplaire = getEtatExemplaire(idEtat);

				lex.add(new Exemplaire(numInventaire, isbn, idEtat, EtatExemplaire));
			}

		} catch (SQLException e) {
			System.out.println("a problem has occured " + e);
		}

		connection.close();
		return lex;
	}

	private String getEtatExemplaire(int idEtat) throws SQLException {
		Connection connection = null;
		PreparedStatement ps = null;
		try {
			Connexion connexion = new Connexion();
			connection = connexion.getConnection();

			ps = connection.prepareStatement("Select Etat from etatexemplaire where idEtat = ?;");
			ps.setInt(1, idEtat);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				String EtatExemplaire = rs.getString("Etat");
				return EtatExemplaire;
			}

		} catch (SQLException e) {
			System.out.println("a problem has occured " + e);
		}

		connection.close();
		return null;

	}

	public void deleteExemplaire(String numeroInventaire) throws SQLException {
		Connection connection = null;
		PreparedStatement statement = null;
		try {
			Connexion connexion = new Connexion();
			connection = connexion.getConnection();
			statement = connection.prepareStatement(DELETE_UN_Exemplaire);
			statement.setString(1, numeroInventaire);
			statement.executeUpdate();
		} catch (SQLException e) {
			System.out.println(e);
		}
		connection.close();

	}

	public void ReparationExemplaire(String numeroInventaire) throws SQLException {
		Connection connection = null;
		PreparedStatement statement = null;
		try {
			Connexion connexion = new Connexion();
			connection = connexion.getConnection();
			statement = connection.prepareStatement(Reparation_Exemplaire);
			statement.setString(1, numeroInventaire);
			statement.executeUpdate();
		} catch (SQLException e) {
			System.out.println(e);
		}
		connection.close();

	}
}
