package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import Models.Etudiant;

public class EtudiantOperation {
	private static final String Add_New_Etudiant = "INSERT INTO Etudiant (CNE, Address, CIN) VALUES (?, ?, ?)";
	private static final String List_All_Etudiant = "select * from VueEtudiants";
	private static final String Delete_Etudiant = "DELETE FROM Etudiant WHERE CIN = ?";
	private static final String Update_Etudiant = "UPDATE Etudiant SET Address = ?, CNE = ? WHERE CIN = ?";
	private static final String Get_Etudiant = "select * from VueEtudiants WHERE CIN = ?";

	public void ModifyEtudiant(Etudiant etudiant) throws SQLException {
		Connection connection = null;
		PreparedStatement statement = null;
		try {
			Connexion connexion = new Connexion();
			connection = connexion.getConnection();
			statement = connection.prepareStatement(Update_Etudiant);
			statement.setString(2, etudiant.getCNE());
			statement.setString(3, etudiant.getCIN());
			statement.setString(1, etudiant.getAdresse());
			statement.executeUpdate();
		} catch (SQLException e) {
			System.out.println(e);
		}
		connection.close();
	}

	public void DeleteEtudiant(String CIN) throws Exception, SQLException {
		Connection connection = null;
		PreparedStatement statement = null;
		try {
			Connexion connexion = new Connexion();
			connection = connexion.getConnection();
			statement = connection.prepareStatement(Delete_Etudiant);
			statement.setString(1, CIN);
			statement.executeUpdate();
		} catch (SQLException e) {
			System.out.println(e);
		}
		connection.close();
	}

	public void Add_New_Etudiant(Etudiant etudiant) throws SQLException {
		Connection connection = null;
		PreparedStatement statement = null;
		try {
			Connexion connexion = new Connexion();
			connection = connexion.getConnection();
			statement = connection.prepareStatement(Add_New_Etudiant);
			statement.setString(1, etudiant.getCNE());
			statement.setString(2, etudiant.getAdresse());
			statement.setString(3, etudiant.getCIN());
			statement.executeUpdate();
		} catch (SQLException e) {
			System.out.println(e);
		}
		connection.close();
	}

	public ArrayList<Etudiant> GetAllEtudiants() throws Exception, SQLException {

		ArrayList<Etudiant> listEtudiant = new ArrayList<>();
		Connection connection = null;
		PreparedStatement statement = null;
		try {
			Connexion connexion = new Connexion();
			connection = connexion.getConnection();
			statement = connection.prepareStatement(List_All_Etudiant);
			ResultSet rs = statement.executeQuery();
			while (rs.next()) {
				listEtudiant.add(new Etudiant(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4),
						rs.getString(5), rs.getString(6), rs.getString(7)));
			}
		} catch (SQLException e) {
			e.getMessage();
		}
		return listEtudiant;
	}

	public Etudiant GetEtudiant(String CIN) throws Exception, SQLException {

		Etudiant etudiant = null;
		Connection connection = null;
		PreparedStatement statement = null;
		try {
			Connexion connexion = new Connexion();
			connection = connexion.getConnection();
			statement = connection.prepareStatement(Get_Etudiant);
			statement.setString(1, CIN);
			ResultSet rs = statement.executeQuery();
			while (rs.next()) {
				etudiant = new Etudiant(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4),
						rs.getString(5), rs.getString(6), rs.getString(7));
			}
		} catch (SQLException e) {
			e.getMessage();
		}
		return etudiant;
	}
}
