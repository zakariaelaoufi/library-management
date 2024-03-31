package Models;

public class Exemplaire {
	private String numeroInventaire;
	private String isbn;
	private int idEtat;
	private String etat;

	public Exemplaire() {
	}

	public Exemplaire(String numeroInventaire, String isbn, int idEtat, String etat) {
		super();
		this.numeroInventaire = numeroInventaire;
		this.isbn = isbn;
		this.idEtat = idEtat;
		this.etat = etat;
	}

	public Exemplaire(String numeroInventaire, int idEtat, String isbn) {
		super();
		this.numeroInventaire = numeroInventaire;
		this.isbn = isbn;
		this.idEtat = idEtat;
	}

	@Override
	public String toString() {
		return "Exemplaire [numeroInventaire=" + numeroInventaire + ", isbn=" + isbn + ", idEtat=" + idEtat + "]";
	}

	public String getNumeroInventaire() {
		return numeroInventaire;
	}

	public void setNumeroInventaire(String numeroInventaire) {
		this.numeroInventaire = numeroInventaire;
	}

	public String getIsbn() {
		return isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	public String getEtat() {
		return etat;
	}

	public void setEtat(String etat) {
		this.etat = etat;
	}

	public int getIdEtat() {
		return idEtat;
	}

	public void setIdEtat(int idEtat) {
		this.idEtat = idEtat;
	}

}
