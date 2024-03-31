package Models;

public class Keyword {

	private int idKeyword;
	private String motCle;
	
	public Keyword(int idKeyword, String motCle) {
	super();
	this.idKeyword = idKeyword;
	this.motCle = motCle;
	}

	public Keyword(String motCle) {
		super();
		this.motCle = motCle;
	}
	
	public Keyword() {}

	public int getIdKeyword() {
		return idKeyword;
	}

	public void setIdKeyword(int idKeyword) {
		this.idKeyword = idKeyword;
	}

	public String getMotCle() {
		return motCle;
	}

	public void setMotCle(String motCle) {
		this.motCle = motCle;
	}

}
