package Models;

public class Auteur {
	private int idAuteur;
	private String nomAuteur;
	
	public Auteur(String nomAuteur) {
		super();
		this.nomAuteur = nomAuteur;
	}
	public Auteur(int idAuteur, String nomAuteur) {
		super();
		this.idAuteur = idAuteur;
		this.nomAuteur = nomAuteur;
	}
	public int getIdAuteur() {
		return idAuteur;
	}
	public void setIdAuteur(int idAuteur) {
		this.idAuteur = idAuteur;
	}
	public String getNomAuteur() {
		return nomAuteur;
	}
	public void setNomAuteur(String nomAuteur) {
		this.nomAuteur = nomAuteur;
	}
}
