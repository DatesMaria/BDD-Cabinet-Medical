package model.user;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class User {
	private int id;
	private String nume_user;
	private String parola;
	private String email;
	private Rol rol;

	public static enum Rol {
		PACIENT,MEDIC,ASISTENT,ADMIN;
	}
	
	public static final ObservableList<Rol> ROLURI = FXCollections.observableArrayList(Rol.values());

	public User(int id, String nume_user, String parola, String email, Rol rol) {
		super();
		this.id = id;
		this.nume_user = nume_user;
		this.parola = parola;
		this.email = email;
		this.rol = rol;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNume_user() {
		return nume_user;
	}

	public void setNume_user(String nume_user) {
		this.nume_user = nume_user;
	}

	public String getParola() {
		return parola;
	}

	public void setParola(String parola) {
		this.parola = parola;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Rol getRol() {
		return rol;
	}

	public void setRol(Rol rol) {
		this.rol = rol;
	}

	@Override
	public String toString() {
		return "User [id=" + id + " , nume_user=" + nume_user + ", parola=" + parola
				+ ", email=" + email + ", rol=" + rol + "]";
	}
}
