package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import Models.EmpruntPerdu;
import Models.EmpruntRelance;
import Models.Exemplaire;

public class EmprunteOperation {

	private static final String Get_ALL_EMPRUNT_2Days_Lateness = "SELECT e.id, e.id_emprunteur, u.nom, u.Prenom, ex.NumeroInventaire AS Nomexemplaire, e.date_prevue_retour FROM emprunte e JOIN Usager u ON e.id_emprunteur = u.CIN JOIN Exemplaire ex ON e.NumeroInventaire = ex.NumeroInventaire WHERE DATE_ADD(e.date_prevue_retour, INTERVAL 2 DAY) < CURDATE() AND e.dateRelance is NULL AND e.dateRetoure is NULL;";
	private static final String Relancer_User = "UPDATE emprunte SET dateRelance = CURDATE() WHERE id = ?;";
	private static final String Get_Number_Of_Latness = "SELECT COUNT(id) AS Total FROM emprunte WHERE DATE_ADD(date_prevue_retour, INTERVAL 2 DAY) < CURDATE() AND dateRelance is NULL AND dateRetoure is NULL;";
	private static final String Get_ALL_EMPRUNT_30Days_Lateness = "SELECT e.id, e.id_emprunteur, u.nom, u.Prenom, ex.NumeroInventaire AS Nomexemplaire, e.date_prevue_retour, e.dateRelance FROM emprunte e JOIN Usager u ON e.id_emprunteur = u.CIN JOIN Exemplaire ex ON e.NumeroInventaire = ex.NumeroInventaire WHERE DATE_ADD(e.date_prevue_retour, INTERVAL 30 DAY) <	 CURDATE() AND e.dateRelance is NOT NULL AND e.dateRetoure is NULL AND ex.idEtat <> 6;";
	private static final String Lost_Exemplaire = "UPDATE Exemplaire e JOIN emprunte emp ON e.NumeroInventaire = emp.NumeroInventaire SET e.idEtat = 6 WHERE emp.id = ?;";
	private static final String Get_All_Not_Available_Exemplaire = "SELECT NumeroInventaire FROM exemplaire WHERE disponibility = 0;";
	private static final String Update_Availability = "UPDATE Exemplaire SET idEtat = ?, disponibility = ? WHERE NumeroInventaire = ?;";
	private static final String Add_Date_Retoure = "UPDATE emprunte SET dateRetoure = ? WHERE id_emprunteur = ? AND NumeroInventaire = ? AND dateRetoure IS NULL;";
	private static final String Get_All__Retoure_Null = "SELECT id_emprunteur FROM emprunte WHERE dateRetoure IS NULL";
	private static final String Number_Of_Current_Copy_Of_Someone = "SELECT COUNT(*) AS 'nbCopy' FROM emprunte WHERE id_emprunteur = ? and dateRetoure is NULL";
	private static final String Number_Of_Copy_For_a_Book = "SELECT COUNT(*) AS 'nbCopy' FROM exemplaire WHERE disponibility = true AND ISBN = ?";
	private static final String Get_Exemplaire_Available = "SELECT * FROM Exemplaire WHERE disponibility = true AND ISBN = ?";
	private static final String Check_CIN_Existing = "SELECT CIN FROM Usager WHERE CIN = ?";
	private static final String Check_Book_Exising = "SELECT ISBN FROM Livre WHERE ISBN = ?";
	private static final String Check_CIN_Nature_Etu = "SELECT CIN FROM Etudiant WHERE CIN = ?";
	private static final String Check_CIN_Nature_Ens = "SELECT CIN FROM Enseignant WHERE CIN = ?";
	private static final String Update_Disponability_after_Emprunte = "UPDATE exemplaire SET disponibility = false WHERE NumeroInventaire = ?";
	private static final String Insert_Into_Emprunte = "INSERT INTO emprunte (id_emprunteur, ISBN, NumeroInventaire, date_emprunt, date_prevue_retour, dateRelance, dateRetoure) VALUES (?, ?, ?, ?, ?, NULL, NULL)";

	public void AddNewEmprunte(String ISBN, String CIN, String numInventaire) throws SQLException {
		Connection connection = null;
		PreparedStatement statement1 = null;
		PreparedStatement statement2 = null;
		PreparedStatement statement3 = null;
		PreparedStatement statement4 = null;
		try {
			// time
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(new Date());
			Date dateRetourPrevue = null;
			// Connection to the database
			Connexion connexion = new Connexion();
			connection = connexion.getConnection();
			//
			statement1 = connection.prepareStatement(Check_CIN_Nature_Ens);
			statement2 = connection.prepareStatement(Check_CIN_Nature_Etu);
			statement2.setString(1, CIN);
			statement1.setString(1, CIN);
			ResultSet rs1 = statement1.executeQuery();
			ResultSet rs2 = statement2.executeQuery();
			if (rs1.next()) {
				calendar.add(Calendar.DAY_OF_MONTH, 15);
				dateRetourPrevue = calendar.getTime();
			} else if (rs2.next()) {
				calendar.add(Calendar.DAY_OF_MONTH, 7);
				dateRetourPrevue = calendar.getTime();
			}
			// update disponability of exemplaire
			statement3 = connection.prepareStatement(Update_Disponability_after_Emprunte);
			statement3.setString(1, numInventaire);
			statement3.executeUpdate();
			// insert new emprunt into emprunte table
			statement4 = connection.prepareStatement(Insert_Into_Emprunte);
			statement4.setString(1, CIN);
			statement4.setString(2, ISBN);
			statement4.setString(3, numInventaire);
			statement4.setDate(4, new java.sql.Date(new Date().getTime()));
			statement4.setDate(5, new java.sql.Date(dateRetourPrevue.getTime()));
			statement4.executeUpdate();
		} catch (SQLException e) {
			System.out.println(e);
		}
		connection.close();
	}

	public String CheckExisting(String ISBN, String CIN) throws SQLException {
		Connection connection = null;
		PreparedStatement statement1 = null;
		PreparedStatement statement2 = null;
		String msg = "";
		try {
			Connexion connexion = new Connexion();
			connection = connexion.getConnection();
			statement1 = connection.prepareStatement(Check_CIN_Existing);
			statement1.setString(1, CIN);
			ResultSet rs = statement1.executeQuery();
			if (!rs.next()) {
				msg = "Aucun usager avec ce CIN. ";
			}
			statement2 = connection.prepareStatement(Check_Book_Exising);
			statement2.setString(1, ISBN);
			rs = statement2.executeQuery();
			if (!rs.next()) {
				msg = msg + "Aucun Livre avec ce ISBN.";
			}
		} catch (SQLException e) {
			System.out.println(e);
		}
		connection.close();
		return msg;

	}

	public ArrayList<Exemplaire> getAllAvailableExemplaire(String ISBN) throws SQLException {
		ArrayList<Exemplaire> listExemplaire = new ArrayList<Exemplaire>();
		Connection connection = null;
		PreparedStatement statement = null;
		try {
			Connexion connexion = new Connexion();
			connection = connexion.getConnection();
			statement = connection.prepareStatement(Get_Exemplaire_Available);
			statement.setString(1, ISBN);
			ResultSet rs = statement.executeQuery();
			while (rs.next()) {
				listExemplaire.add(
						new Exemplaire(rs.getString("numeroInventaire"), rs.getInt("idEtat"), rs.getString("isbn")));
			}
		} catch (SQLException e) {
			System.out.println(e);
		}
		connection.close();
		return listExemplaire;
	}

	public ArrayList<EmpruntRelance> getAllLatnessEmprunt() throws SQLException {
		ArrayList<EmpruntRelance> listEmpruntRelance = new ArrayList<EmpruntRelance>();
		Connection connection = null;
		PreparedStatement statement = null;
		try {
			Connexion connexion = new Connexion();
			connection = connexion.getConnection();
			statement = connection.prepareStatement(Get_ALL_EMPRUNT_2Days_Lateness);
			ResultSet rs = statement.executeQuery();
			while (rs.next()) {
				int id = rs.getInt("id");
				String id_emprunteur = rs.getString("id_emprunteur");
				String nom = rs.getNString("nom");
				String prenom = rs.getString("prenom");
				String nomExemplaire = rs.getString("nomExemplaire");
				Date date_prevue_retour = rs.getDate("date_prevue_retour");
				listEmpruntRelance
						.add(new EmpruntRelance(id, id_emprunteur, nom, prenom, nomExemplaire, date_prevue_retour));
			}
		} catch (SQLException e) {
			System.out.println(e);
		}
		connection.close();
		return listEmpruntRelance;
	}

	public boolean RelancerUser(int id) throws SQLException {
		Connection connection = null;
		PreparedStatement statement = null;
		try {
			Connexion connexion = new Connexion();
			connection = connexion.getConnection();
			statement = connection.prepareStatement(Relancer_User);
			statement.setInt(1, id);
			statement.executeUpdate();
		} catch (SQLException e) {
			System.out.println(e);
		}
		connection.close();
		return true;
	}

	public int getNumberOfLatness() throws SQLException {
		Connection connection = null;
		PreparedStatement statement = null;
		int total = 0;
		try {
			Connexion connexion = new Connexion();
			connection = connexion.getConnection();
			statement = connection.prepareStatement(Get_Number_Of_Latness);
			ResultSet rs = statement.executeQuery();
			while (rs.next()) {
				total = rs.getInt("Total");
			}
		} catch (SQLException e) {
			System.out.println(e);
		}
		connection.close();
		return total;
	}

	public ArrayList<EmpruntPerdu> getAllLatnessEmpruntAfter30Days() throws SQLException {
		ArrayList<EmpruntPerdu> listEmpruntPerdu = new ArrayList<EmpruntPerdu>();
		Connection connection = null;
		PreparedStatement statement = null;
		try {
			Connexion connexion = new Connexion();
			connection = connexion.getConnection();
			statement = connection.prepareStatement(Get_ALL_EMPRUNT_30Days_Lateness);
			ResultSet rs = statement.executeQuery();
			while (rs.next()) {
				int id = rs.getInt("id");
				String id_emprunteur = rs.getString("id_emprunteur");
				String nom = rs.getNString("nom");
				String prenom = rs.getString("prenom");
				String nomExemplaire = rs.getString("nomExemplaire");
				Date date_prevue_retour = rs.getDate("date_prevue_retour");
				Date date_de_relance = rs.getDate("dateRelance");
				listEmpruntPerdu.add(new EmpruntPerdu(id, id_emprunteur, nom, prenom, nomExemplaire, date_prevue_retour,
						date_de_relance));
			}
		} catch (SQLException e) {
			System.out.println(e);
		}
		connection.close();
		return listEmpruntPerdu;
	}

	public boolean ConsidirerPerdu(int id) throws SQLException {
		Connection connection = null;
		PreparedStatement statement = null;
		try {
			Connexion connexion = new Connexion();
			connection = connexion.getConnection();
			statement = connection.prepareStatement(Lost_Exemplaire);
			statement.setInt(1, id);
			statement.executeUpdate();
		} catch (SQLException e) {
			System.out.println(e);
		}
		connection.close();
		return true;
	}

	public ArrayList<String> getNotAvailableExemplaire() throws SQLException {
		Connection connection = null;
		PreparedStatement statement = null;
		ArrayList<String> listExmNotAvailable = new ArrayList<String>();
		try {
			Connexion connexion = new Connexion();
			connection = connexion.getConnection();
			statement = connection.prepareStatement(Get_All_Not_Available_Exemplaire);
			ResultSet rs = statement.executeQuery();
			while (rs.next()) {
				listExmNotAvailable.add(rs.getString("NumeroInventaire"));
			}
		} catch (SQLException e) {
			System.out.println(e);
		}
		connection.close();
		return listExmNotAvailable;
	}

	public boolean EngisteRetoure(String numInventaire, int idEtat, String idEmprunteur, Date dateRetoure)
			throws SQLException {
		Connection connection = null;
		PreparedStatement statement1 = null;
		PreparedStatement statement2 = null;
		try {
			Connexion connexion = new Connexion();
			connection = connexion.getConnection();
			// update the disponibility of exemplaire
			statement1 = connection.prepareStatement(Update_Availability);
			statement1.setInt(1, idEtat);
			statement1.setInt(2, 1);
			statement1.setString(3, numInventaire);
			statement1.executeUpdate();
			// add return date to emprunte table
			statement2 = connection.prepareStatement(Add_Date_Retoure);
			statement2.setDate(1, (java.sql.Date) dateRetoure);
			statement2.setString(2, idEmprunteur);
			statement2.setString(3, numInventaire);
			statement2.executeUpdate();
			connection.close();
			return true;
		} catch (SQLException e) {
			System.out.println(e);
		}
		connection.close();
		return false;
	}

	public ArrayList<String> RetoureIsNull() throws SQLException {
		ArrayList<String> listRetoureNull = new ArrayList<String>();
		Connection connection = null;
		PreparedStatement statement = null;
		try {
			Connexion connexion = new Connexion();
			connection = connexion.getConnection();
			statement = connection.prepareStatement(Get_All__Retoure_Null);
			ResultSet rs = statement.executeQuery();
			while (rs.next()) {
				listRetoureNull.add(rs.getString("id_emprunteur"));
			}
		} catch (SQLException e) {
			System.out.println(e);
		}
		connection.close();
		return listRetoureNull;
	}

	public int NumberOfCurrentCopyOfSomone(String id) throws SQLException {
		Connection connection = null;
		PreparedStatement statement = null;
		int nb = 0;
		try {
			Connexion connexion = new Connexion();
			connection = connexion.getConnection();
			statement = connection.prepareStatement(Number_Of_Current_Copy_Of_Someone);
			statement.setString(1, id);
			ResultSet rs = statement.executeQuery();
			while (rs.next()) {
				nb = rs.getInt("nbCopy");
			}
		} catch (SQLException e) {
			System.out.println(e);
		}
		connection.close();
		return nb;
	}

	public int NumberOfAvailableCopy(String isbn) throws SQLException {
		Connection connection = null;
		PreparedStatement statement = null;
		int nb = -1;
		try {
			Connexion connexion = new Connexion();
			connection = connexion.getConnection();
			statement = connection.prepareStatement(Number_Of_Copy_For_a_Book);
			statement.setString(1, isbn);
			ResultSet rs = statement.executeQuery();
			while (rs.next()) {
				nb = rs.getInt("nbCopy");
				System.out.println(nb);
			}
		} catch (SQLException e) {
			System.out.println(e);
		}
		connection.close();
		return nb;
	}
}
