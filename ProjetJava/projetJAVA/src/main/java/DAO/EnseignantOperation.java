package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import Models.Enseignant;

public class EnseignantOperation {
	private static final String Add_New_Enseignant = "INSERT INTO Enseignant (CIN, Grade) VALUES (?, ?)";
	private static final String Get_All_Enseignant = "select * from VueEnseignants";
	private static final String Delete_Enseignant = "DELETE FROM Enseignant WHERE CIN = ?";
	private static final String Get_Enseignant = "select * from VueEnseignants WHERE CIN = ?";
	private static final String Update_Enseignant = "UPDATE Enseignant SET Grade = ? WHERE CIN = ?";

	public void Modify_Enseignant(Enseignant enseignant) throws SQLException {
		Connection connection = null;
		PreparedStatement statement = null;
		try {
			Connexion connexion = new Connexion();
			connection = connexion.getConnection();
			statement = connection.prepareStatement(Update_Enseignant);
			statement.setString(2, enseignant.getCIN());
			statement.setString(1, enseignant.getGrade());
			statement.executeUpdate();
		} catch (SQLException e) {
			System.out.println(e);
		}
		connection.close();
	}

	public void DeleteEnseignant(String CIN) throws Exception, SQLException {
		Connection connection = null;
		PreparedStatement statement = null;
		try {
			Connexion connexion = new Connexion();
			connection = connexion.getConnection();
			statement = connection.prepareStatement(Delete_Enseignant);
			statement.setString(1, CIN);
			statement.executeUpdate();
		} catch (SQLException e) {
			System.out.println(e);
		}
		connection.close();
	}

	public void Add_New_Enseignant(Enseignant enseignant) throws SQLException {
		Connection connection = null;
		PreparedStatement statement = null;
		try {
			Connexion connexion = new Connexion();
			connection = connexion.getConnection();
			statement = connection.prepareStatement(Add_New_Enseignant);
			statement.setString(1, enseignant.getCIN());
			statement.setString(2, enseignant.getGrade());
			statement.executeUpdate();
		} catch (SQLException e) {
			System.out.println(e);
		}
		connection.close();
	}

	public ArrayList<Enseignant> GetAllEnseignants() throws Exception, SQLException {
		ArrayList<Enseignant> le = new ArrayList<>();
		Connection connection = null;
		PreparedStatement statement = null;
		try {
			Connexion connexion = new Connexion();
			connection = connexion.getConnection();
			statement = connection.prepareStatement(Get_All_Enseignant);
			ResultSet rs = statement.executeQuery();
			while (rs.next()) {
				le.add(new Enseignant(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4),
						rs.getString(5), rs.getString(6)));
			}
		} catch (SQLException e) {
			e.getMessage();
		}
		return le;
	}

	public Enseignant GetEnseignants(String CIN) throws Exception, SQLException {
		Enseignant le = null;
		Connection connection = null;
		PreparedStatement statement = null;
		try {
			Connexion connexion = new Connexion();
			connection = connexion.getConnection();
			statement = connection.prepareStatement(Get_Enseignant);
			statement.setString(1, CIN);
			ResultSet rs = statement.executeQuery();
			while (rs.next()) {
				le = new Enseignant(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5),
						rs.getString(6));
			}
		} catch (SQLException e) {
			e.getMessage();
		}
		return le;
	}
}
