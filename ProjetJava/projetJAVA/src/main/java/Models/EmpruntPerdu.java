package Models;

import java.util.Date;

public class EmpruntPerdu {
	
	private int id;
	private String id_emprunteur;
    private String nom;
    private String prenom;
    private String nomExemplaire;
    private Date date_prevue_retour;
    private Date date_de_relance;
    

    // Constructor to initialize fields
    public EmpruntPerdu(int id, String id_emprunteur, String nom, String prenom, String nomExemplaire, Date date_prevue_retour, Date date_de_relance) {
        this.id = id;
    	this.id_emprunteur = id_emprunteur;
        this.nom = nom;
        this.prenom = prenom;
        this.nomExemplaire = nomExemplaire;
        this.date_prevue_retour = date_prevue_retour;
        this.date_de_relance = date_de_relance;
    }


	public Date getDate_de_relance() {
		return date_de_relance;
	}


	public void setDate_de_relance(Date date_de_relance) {
		this.date_de_relance = date_de_relance;
	}


	// Getters to access the fields
    public String getId_emprunteur() {
        return id_emprunteur;
    }

    public String getNom() {
        return nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public String getNomExemplaire() {
        return nomExemplaire;
    }

    public Date getDate_prevue_retour() {
        return date_prevue_retour;
    }

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

}
