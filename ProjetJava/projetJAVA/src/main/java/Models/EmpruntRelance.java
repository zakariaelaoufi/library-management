package Models;

import java.util.Date;

public class EmpruntRelance {
	
	private int id;
	private String id_emprunteur;
    private String nom;
    private String prenom;
    private String nomExemplaire;
    private Date date_prevue_retour;

    // Constructor to initialize fields
    public EmpruntRelance(int id, String id_emprunteur, String nom, String prenom, String nomExemplaire, Date date_prevue_retour) {
        this.id = id;
    	this.id_emprunteur = id_emprunteur;
        this.nom = nom;
        this.prenom = prenom;
        this.nomExemplaire = nomExemplaire;
        this.date_prevue_retour = date_prevue_retour;
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
