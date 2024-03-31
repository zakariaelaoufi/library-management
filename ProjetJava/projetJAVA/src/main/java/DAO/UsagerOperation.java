package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import Models.Usager;

public class UsagerOperation {
	private static final String Add_Usager = "INSERT INTO Usager (CIN, Email, nom, Prenom, Telephone) VALUES (?, ?, ?, ?, ?)";
	private static final String Delete_Usager = "DELETE FROM Usager WHERE CIN = ?";
	private static final String Update_Usager = "UPDATE Usager SET Email = ?, nom = ?, Prenom = ?, Telephone = ? WHERE CIN = ?";

	public void Modify_Usager(Usager usager) throws SQLException {
		Connection connection = null;
		PreparedStatement statement = null;
		try {
			Connexion connexion = new Connexion();
			connection = connexion.getConnection();
			statement = connection.prepareStatement(Update_Usager);
			statement.setString(5, usager.getCIN());
			statement.setString(1, usager.getEmail());
			statement.setString(2, usager.getNom());
			statement.setString(3, usager.getPrenom());
			statement.setString(4, usager.getTelephone());
			statement.executeUpdate();
		} catch (SQLException e) {
			System.out.println(e);
		}
		connection.close();
	}

	public void Add_New_Usager(Usager usager) throws SQLException {
		Connection connection = null;
		PreparedStatement statement = null;
		try {
			Connexion connexion = new Connexion();
			connection = connexion.getConnection();
			statement = connection.prepareStatement(Add_Usager);
			statement.setString(1, usager.getCIN());
			statement.setString(2, usager.getEmail());
			statement.setString(3, usager.getNom());
			statement.setString(4, usager.getPrenom());
			statement.setString(5, usager.getTelephone());
			statement.executeUpdate();
		} catch (SQLException e) {
			System.out.println(e);
		}
		connection.close();
	}

	public void DeleteUsager(String CIN) throws Exception, SQLException {
		Connection connection = null;
		PreparedStatement statement = null;
		try {
			Connexion connexion = new Connexion();
			connection = connexion.getConnection();
			statement = connection.prepareStatement(Delete_Usager);
			statement.setString(1, CIN);
			statement.executeUpdate();
		} catch (SQLException e) {
			System.out.println(e);
		}
		connection.close();
	}

}
