package Models;
import java.sql.Date;

public class Emprunte {
    private int id;
    private String id_emprunteur;
    private String ISBN;
    private String NumeroInventaire;
    private Date date_emprunt;
    private Date date_prevue_retour;
    private Date dateRelance;
    private Date dateRetoure;

    // Constructors
    public Emprunte() {
    }

    public Emprunte(String id_emprunteur, String ISBN, String NumeroInventaire, Date date_emprunt, Date date_prevue_retour, Date dateRelance, Date dateRetoure) {
        this.id_emprunteur = id_emprunteur;
        this.ISBN = ISBN;
        this.NumeroInventaire = NumeroInventaire;
        this.date_emprunt = date_emprunt;
        this.date_prevue_retour = date_prevue_retour;
        this.dateRelance = dateRelance;
        this.dateRetoure = dateRetoure;
    }

    // Getters and Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getId_emprunteur() {
        return id_emprunteur;
    }

    public void setId_emprunteur(String id_emprunteur) {
        this.id_emprunteur = id_emprunteur;
    }

    public String getISBN() {
        return ISBN;
    }

    public void setISBN(String ISBN) {
        this.ISBN = ISBN;
    }

    public String getNumeroInventaire() {
        return NumeroInventaire;
    }

    public void setNumeroInventaire(String NumeroInventaire) {
        this.NumeroInventaire = NumeroInventaire;
    }

    public Date getDate_emprunt() {
        return date_emprunt;
    }

    public void setDate_emprunt(Date date_emprunt) {
        this.date_emprunt = date_emprunt;
    }

    public Date getDate_prevue_retour() {
        return date_prevue_retour;
    }

    public void setDate_prevue_retour(Date date_prevue_retour) {
        this.date_prevue_retour = date_prevue_retour;
    }

    public Date getDateRelance() {
        return dateRelance;
    }

    public void setDateRelance(Date dateRelance) {
        this.dateRelance = dateRelance;
    }

    public Date getDateRetoure() {
        return dateRetoure;
    }

    public void setDateRetoure(Date dateRetoure) {
        this.dateRetoure = dateRetoure;
    }

    // toString() method
    @Override
    public String toString() {
        return "Emprunte{" +
                "id=" + id +
                ", id_emprunteur='" + id_emprunteur + '\'' +
                ", ISBN='" + ISBN + '\'' +
                ", NumeroInventaire='" + NumeroInventaire + '\'' +
                ", date_emprunt=" + date_emprunt +
                ", date_prevue_retour=" + date_prevue_retour +
                ", dateRelance=" + dateRelance +
                ", dateRetoure=" + dateRetoure +
                '}';
    }
}
