package Models;

import java.sql.Date;
import java.util.ArrayList;

public class Livre {
	int id;
	String ISBN;
	String Titre;
	String Resume;
	Date DateEdition;
	ArrayList<String> listAuteurs = new ArrayList<String>();
	
	public Livre(String iSBN, String titre, String resume, Date dateEdition) {
		this.Titre = titre;
		this.ISBN = iSBN;
		this.Resume = resume;
		this.DateEdition = dateEdition;
	}
	
	public Livre(int id, String iSBN, String titre, String resume, Date dateEdition) {
		this.id = id;
		this.Titre = titre;
		this.ISBN = iSBN;
		this.Resume = resume;
		this.DateEdition = dateEdition;
	}
	
	public Livre(String iSBN, String titre, String resume, Date dateEdition, ArrayList<String> listAuteurs) {
		this.ISBN = iSBN;
		this.Titre = titre;
		this.Resume = resume;
		this.DateEdition = dateEdition;
		this.listAuteurs = listAuteurs;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	public String getISBN() {
		return ISBN;
	}

	public void setISBN(String iSBN) {
		this.ISBN = iSBN;
	}

	public String getTitre() {
		return Titre;
	}

	public void setTitre(String titre) {
		this.Titre = titre;
	}

	public String getResume() {
		return Resume;
	}

	public void setResume(String resume) {
		this.Resume = resume;
	}

	public Date getDateEdition() {
		return DateEdition;
	}

	public void setDateEdition(Date dateEdition) {
		this.DateEdition = dateEdition;
	}

	public ArrayList<String> getListAuteurs() {
		return listAuteurs;
	}

	public void setListAuteurs(ArrayList<String> listAuteurs) {
		this.listAuteurs = listAuteurs;
	}
	
	
	
}
