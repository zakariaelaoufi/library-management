package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import Models.Auteur;
import Models.Keyword;

public class Actions {
	private static final String INSERT_INTO_KeyLivre = "INSERT INTO keyLivre(ISBN,idKeyword) VALUES (?, ?);";
	private static final String INSERT_INTO_AuteurLivre = "INSERT INTO AuteurLivre(ISBN,idAuteur) VALUES (?, ?);";
	private static final String Get_AuteurLivre = "SELECT nomAuteur FROM Auteur INNER JOIN AuteurLivre ON Auteur.idAuteur = AuteurLivre.idAuteur WHERE AuteurLivre.ISBN = ?";
	private static final String Get_idAuteurLivre = "SELECT au.idAuteur, au.nomAuteur  FROM Auteur au , AuteurLivre al where au.idAuteur = al.idAuteur And al.ISBN = ?;";	
	private static final String Get_KeyLivre = "SELECT Keyword.idKeyword, Keyword.motCle FROM Keyword INNER JOIN keyLivre ON Keyword.idKeyword = keyLivre.idKeyword WHERE keyLivre.ISBN = ?";
	private static final String Get_All_Keyword = "SELECT idKeyword, motCle FROM Keyword";	
	private static final String Delete_AuteurLivre = "DELETE FROM AuteurLivre WHERE ISBN = ?";	
	private static final String Delete_KeyLivre = "DELETE FROM keyLivre WHERE ISBN = ?";	
	private static final String Delete_Auteur_Not_Linked = "DELETE FROM Auteur WHERE IdAuteur NOT IN (SELECT DISTINCT IdAuteur FROM AuteurLivre)";	

	
	public static void deleteNotLinked() throws SQLException {
		Connection connection = null;
	    PreparedStatement statement = null;
	    try {
	    	Connexion connexion = new Connexion();
            connection = connexion.getConnection();
            statement = connection.prepareStatement(Delete_Auteur_Not_Linked);
            statement.executeUpdate();
	    } catch(SQLException e) {
			System.out.println(e);
		}
		connection.close();
		
	}
	
	public static void deleteKeyLivre(String isbn) throws SQLException {
		Connection connection = null;
	    PreparedStatement statement = null;
	    try {
			Connexion connexion = new Connexion();
            connection = connexion.getConnection();
            statement = connection.prepareStatement(Delete_KeyLivre);
            statement.setString(1, isbn);
            statement.executeUpdate();
	    } catch(SQLException e) {
			System.out.println(e);
		}
		connection.close();
	}
	
	public static void deleteAuteurLivre(String isbn) throws SQLException {
		Connection connection = null;
	    PreparedStatement statement = null;
	    try {
			Connexion connexion = new Connexion();
            connection = connexion.getConnection();
            statement = connection.prepareStatement(Delete_AuteurLivre);
            statement.setString(1, isbn);
            statement.executeUpdate();
	    } catch(SQLException e) {
			System.out.println(e);
		}
		connection.close();
	}
	
	public static void insertAuteurLivre(String isbn) throws SQLException {
		Connection connection = null;
	    PreparedStatement statement = null;
	    int z = 0;
		try {
			Connexion connexion = new Connexion();
            connection = connexion.getConnection();
            //get the last id in author table
            Statement stm = connection.createStatement();
            ResultSet rs = stm.executeQuery("SELECT MAX(idAuteur) AS 'lastID' FROM Auteur");
            while(rs.next()) {
            	z = rs.getInt("lastID");
            }
            if(z==0) {
            	z = 1;
            }
            //insert into AuteurLivre table
            statement = connection.prepareStatement(INSERT_INTO_AuteurLivre);
            statement.setString(1, isbn);
            statement.setInt(2, z);
            statement.executeUpdate();
		}catch(SQLException e) {
			System.out.println(e);
		}
		connection.close();
	}
	
	public static void insertKeyLivre(String isbn, int idKey) throws SQLException {
		Connection connection = null;
	    PreparedStatement statement = null;
		try {
			Connexion connexion = new Connexion();
            connection = connexion.getConnection();
            statement = connection.prepareStatement(INSERT_INTO_KeyLivre);
            statement.setString(1, isbn);
            statement.setInt(2, idKey);
            statement.executeUpdate();
		}catch(SQLException e) {
			System.out.println(e);
		}
		connection.close();
	}
	
	public static ArrayList<String> getAuteurLivre(Connection connection, String isbn) throws SQLException {
		ArrayList<String> auteurs = new ArrayList<String>();
	    PreparedStatement statement = connection.prepareStatement(Get_AuteurLivre);
	    statement.setString(1, isbn);
	    ResultSet rs = statement.executeQuery();
	    while (rs.next()) {
	        String auteur = rs.getString("nomAuteur");
	        auteurs.add(auteur);
	    }
	    return auteurs;
	}
	
	public static ArrayList<Auteur> getidAuteurLivre(Connection connection, String isbn) throws SQLException {
		ArrayList<Auteur> auteurs = new ArrayList<Auteur>();
	    PreparedStatement statement = connection.prepareStatement(Get_idAuteurLivre);
	    statement.setString(1, isbn);
	    ResultSet rs = statement.executeQuery();
	    while (rs.next()) {
	    	int id = rs.getInt("idAuteur");
	    	String nom = rs.getString("nomAuteur");
	        auteurs.add(new Auteur(id,nom));
	    }
	    return auteurs;
	}
	
	public static ArrayList<Keyword> getidKeyLivre(Connection connection, String isbn) throws SQLException {
		ArrayList<Keyword> keywords = new ArrayList<Keyword>();
	    PreparedStatement statement = connection.prepareStatement(Get_KeyLivre);
	    statement.setString(1, isbn);
	    ResultSet rs = statement.executeQuery();
	    while (rs.next()) {
	    	int id = rs.getInt("idKeyword");
	    	String cle = rs.getString("motCle");
	    	keywords.add(new Keyword(id,cle));
	    }
	    return keywords;
	}
	
	public static ArrayList<Keyword> getAllKeywords(Connection connection) throws SQLException {
		ArrayList<Keyword> liskeywords = new ArrayList<Keyword>();
	    PreparedStatement statement = connection.prepareStatement(Get_All_Keyword);
	    ResultSet rs = statement.executeQuery();
	    while (rs.next()) {
	    	int id = rs.getInt("idKeyword");
	    	String cle = rs.getString("motCle");
	    	liskeywords.add(new Keyword(id,cle));
	    }
	    return liskeywords;
	}

}