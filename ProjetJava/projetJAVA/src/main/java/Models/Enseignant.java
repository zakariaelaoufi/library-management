package Models;

public class Enseignant extends Usager {

	private String grade;

	public Enseignant(String CIN, String email, String nom, String prenom, String telephone, String grade) {
		super(CIN, email, nom, prenom, telephone);
		this.grade = grade;
	}

	public String getGrade() {
		return grade;
	}

	public void setGrade(String grade) {
		this.grade = grade;
	}

	@Override
	public String toString() {
		return "Enseignant [" + super.toString() + " grade=" + grade + "]";
	}

}
