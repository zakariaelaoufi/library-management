package Models;

public class Usager {
	private String CIN;
	private String email;
	private String nom;
	private String prenom;
	private String telephone;

	// Constructeur
	public Usager(String CIN, String email, String nom, String prenom, String telephone) {
		this.CIN = CIN;
		this.email = email;
		this.nom = nom;
		this.prenom = prenom;
		this.telephone = telephone;
	}

	// Getters
	public String getCIN() {
		return CIN;
	}

	public String getEmail() {
		return email;
	}

	public String getNom() {
		return nom;
	}

	public String getPrenom() {
		return prenom;
	}

	public String getTelephone() {
		return telephone;
	}

	@Override
	public String toString() {
		return "CIN=" + CIN + ", email=" + email + ", nom=" + nom + ", prenom=" + prenom + ", telephone=" + telephone;
	}

}
