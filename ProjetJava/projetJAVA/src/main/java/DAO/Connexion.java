package DAO;

import java.sql.*;

public class Connexion {
	
	static Connection con;
	private String url, driver;
	
	public Connexion() {
		try {
			driver= new String("com.mysql.cj.jdbc.Driver");
			Class.forName(driver);
			url = new String("jdbc:mysql://localhost:3306/bibliotheque");
		}
		catch(ClassNotFoundException e) {
			System.err.println("Erreur lors du chargement du pilote : " + e); 
		}
	};

	
	public  Connection getConnection() throws SQLException {
		try {
			con = DriverManager.getConnection(url,"root","");
		}
		catch(SQLException e) {
			System.out.println("erreur has occured" + e);
		}
		return con;
	}
	
}
