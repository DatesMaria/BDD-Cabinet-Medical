package model.user;
import java.time.LocalDate;

public class Pacient {
	private int id;
	private int idUser;
	private String nume;
	private String prenume;
	private long cnp;
	private String sex;
	private LocalDate dataNasterii;
	private long telefon;
	private String adresa;
	private float greutate;
	private float inaltime;
	
	
	public Pacient(int id, int idUser, String nume, String prenume, long cnp, String sex, LocalDate dataNasterii, long telefon,
			String adresa, float greutate, float inaltime) {
		super();
		this.id = id;
		this.idUser = idUser;
		this.nume = nume;
		this.prenume = prenume;
		this.cnp = cnp;
		this.sex = sex;
		this.dataNasterii = dataNasterii;
		this.telefon = telefon;
		this.adresa = adresa;
		this.greutate = greutate;
		this.inaltime = inaltime;
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


	public long getCnp() {
		return cnp;
	}


	public void setCnp(long cnp) {
		this.cnp = cnp;
	}


	public String getSex() {
		return sex;
	}


	public void setSex(String sex) {
		this.sex = sex;
	}


	public LocalDate getDataNasterii() {
		return dataNasterii;
	}


	public void setDataNasterii(LocalDate dataNasterii) {
		this.dataNasterii = dataNasterii;
	}


	public long getTelefon() {
		return telefon;
	}


	public void setTelefon(long telefon) {
		this.telefon = telefon;
	}


	public String getAdresa() {
		return adresa;
	}


	public void setAdresa(String adresa) {
		this.adresa = adresa;
	}


	public float getGreutate() {
		return greutate;
	}


	public void setGreutate(float greutate) {
		this.greutate = greutate;
	}


	public float getInaltime() {
		return inaltime;
	}


	public void setInaltime(float inaltime) {
		this.inaltime = inaltime;
	}


	@Override
	public String toString() {
		return "Pacient [id=" + id + ", idUser=" + idUser + ", nume=" + nume + ", prenume=" + prenume + ", cnp=" + cnp
				+ ", sex=" + sex + ", dataNasterii=" + dataNasterii + ", telefon=" + telefon + ", adresa=" + adresa
				+ ", greutate=" + greutate + ", inaltime=" + inaltime + "]";
	}
	
	
		
}
