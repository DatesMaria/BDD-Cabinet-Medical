package model.user;

public class Administrator {
	
	private int id;
	private int idUser;
	private String nume;
	private String prenume;
	
	
	public Administrator(int id, int idUser, String nume, String prenume) {
		super();
		this.id = id;
		this.idUser = idUser;
		this.nume = nume;
		this.prenume = prenume;
	}


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public int getIdUser() {
		return idUser;
	}


	public void setIdUser(int idUser) {
		this.idUser = idUser;
	}


	public String getNume() {
		return nume;
	}


	public void setNume(String nume) {
		this.nume = nume;
	}


	public String getPrenume() {
		return prenume;
	}


	public void setPrenume(String prenume) {
		this.prenume = prenume;
	}


	@Override
	public String toString() {
		return "Administrator [id=" + id + ", idUser=" + idUser + ", nume=" + nume + ", prenume=" + prenume + "]";
	}
	
	
	
}