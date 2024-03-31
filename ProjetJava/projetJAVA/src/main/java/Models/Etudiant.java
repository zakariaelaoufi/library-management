package Models;

public class Etudiant extends Usager {
	private String adresse;
	private String CNE; // Identifiant unique pour l'étudiant

	public Etudiant(String CIN, String email, String nom, String prenom, String telephone, String adresse, String CNE) {
		super(CIN, email, nom, prenom, telephone);
		this.adresse = adresse;
		this.CNE = CNE;
	}

	public String getAdresse() {
		return adresse;
	}

	public void setAdresse(String adresse) {
		this.adresse = adresse;
	}

	public String getCNE() {
		return CNE;
	}

	public void setCNE(String cNE) {
		CNE = cNE;
	}

	@Override
	public String toString() {
		return "Etudiant [" + super.toString() + " adresse=" + adresse + ", CNE=" + CNE + "]";
	}

}
