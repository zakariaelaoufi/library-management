package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginOperation {

	private static final String cnx_biblio = "select * from Bibliothecaire where Username=? and Pwd=?";

	private static final String cnx_assist = "select * from Assistant where Username=? and Pwd=?";

	private static final String url = "jdbc:mysql://localhost:3306/bibliotheque";

	private static final String uname = "root";

	// test connexion:
	public static Connection getConnection() throws SQLException {

		Connection cnx = DriverManager.getConnection(url, uname, "");

		return cnx;

	}

	public static boolean verifierLoginBiblio(String user, String mdp) {

		try {

			Class.forName("com.mysql.cj.jdbc.Driver");

			Connection cnx = DriverManager.getConnection(url, uname, "");

			PreparedStatement ps = cnx.prepareStatement(cnx_biblio);

			ps.setString(1, user);
			ps.setString(2, mdp);

			ResultSet rs = ps.executeQuery();

			if (rs.next()) {
				return true;
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return false;

	}

	public static boolean verifierLoginAssist(String user, String mdp) {

		try {

			Class.forName("com.mysql.cj.jdbc.Driver");

			Connection cnx = DriverManager.getConnection(url, uname, "");

			PreparedStatement ps = cnx.prepareStatement(cnx_assist);

			ps.setString(1, user);
			ps.setString(2, mdp);

			ResultSet rs = ps.executeQuery();

			if (rs.next()) {
				return true;
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return false;

	}

}

/*
 * better and simple way:
 * 
 * import javax.sql.DataSource; import javax.naming.InitialContext; import
 * javax.naming.NamingException;
 * 
 * public class LoginDao {
 * 
 * private static final String cnx_biblio =
 * "SELECT * FROM Bibliothecaire WHERE Username=? AND Pwd=?"; private static
 * final String cnx_assist =
 * "SELECT * FROM Assistant WHERE Username=? AND Pwd=?";
 * 
 * private DataSource dataSource;
 * 
 * public LoginDao() { try { InitialContext ctx = new InitialContext();
 * dataSource = (DataSource) ctx.lookup("java:comp/env/jdbc/biblioDataSource");
 * } catch (NamingException e) { e.printStackTrace(); } }
 * 
 * private boolean verifierLogin(String query, String user, String mdp) { try
 * (Connection cnx = dataSource.getConnection(); PreparedStatement ps =
 * cnx.prepareStatement(query)) { ps.setString(1, user); ps.setString(2, mdp);
 * try (ResultSet rs = ps.executeQuery()) { return rs.next(); } } catch
 * (SQLException e) { e.printStackTrace(); return false; } }
 * 
 * public boolean verifierLoginBiblio(String user, String mdp) { return
 * verifierLogin(cnx_biblio, user, mdp); }
 * 
 * public boolean verifierLoginAssist(String user, String mdp) { return
 * verifierLogin(cnx_assist, user, mdp); } }
 */