package model.user;

import java.time.LocalDate;
import java.util.Date;

public class Medic {
	private int id;
	private int idSpecializare;
	private int idUser;
	private String nume;
	private String prenume;
	private LocalDate dataNasterii;

	public Medic(int id, int idSpecializare, int idUser, String nume, String prenume, LocalDate dataNasterii) {
		super();
		this.id = id;
		this.idSpecializare = idSpecializare;
		this.idUser = idUser;
		this.nume = nume;
		this.prenume = prenume;
		this.dataNasterii = dataNasterii;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getIdSpecializare() {
		return idSpecializare;
	}

	public void setIdSpecializare(int idSpecializare) {
		this.idSpecializare = idSpecializare;
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

	public LocalDate getDataNasterii() {
		return dataNasterii;
	}

	public void setDataNasterii(LocalDate dataNasterii) {
		this.dataNasterii = dataNasterii;
	}

	@Override
	public String toString() {
		return "Medic [id=" + id + ", idSpecializare=" + idSpecializare + ", idUser=" + idUser + ", nume=" + nume
				+ ", prenume=" + prenume + ", dataNasterii=" + dataNasterii + "]";
	}

}