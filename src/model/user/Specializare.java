package model.user;

public class Specializare {
private int id;
private String denumire;
private String descriere;


public Specializare(int id, String denumire, String descriere) {
	super();
	this.id = id;
	this.denumire = denumire;
	this.descriere = descriere;
}

public int getId() {
	return id;
}
public void setId(int id) {
	this.id = id;
}

public String getDenumire() {
	return denumire;
}
public void setDenumire(String denumire) {
	this.denumire = denumire;
}

public String getDescriere() {
	return descriere;
}
public void setDescriere(String descriere) {
	this.descriere = descriere;
}

@Override
public String toString() {
	return "Specializare [id=" + id + ", denumire=" + denumire + ", descriere=" + descriere + "]";
}



}
