package model.cabinetMedical;
import java.time.LocalDate;
import java.util.Date;

public class Factura {

	private int id;
	private int idConsultatie;
	private double valoare;
	private LocalDate data;
	private double tva;
	
	
	public Factura(int id, int idConsultatie, double valoare, LocalDate data, double tva) {
		super();
		this.id = id;
		this.idConsultatie = idConsultatie;
		this.valoare = valoare;
		this.data = data;
		this.tva = tva;
	}


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public int getIdConsultatie() {
		return idConsultatie;
	}


	public void setIdConsultatie(int idConsultatie) {
		this.idConsultatie = idConsultatie;
	}


	public double getValoare() {
		return valoare;
	}


	public void setValoare(double valoare) {
		this.valoare = valoare;
	}


	public LocalDate getData() {
		return data;
	}


	public void setData(LocalDate data) {
		this.data = data;
	}


	public double getTva() {
		return tva;
	}


	public void setTva(double tva) {
		this.tva = tva;
	}


	@Override
	public String toString() {
		return "Factura [id=" + id + ", idConsultatie=" + idConsultatie + ", valoare=" + valoare + ", data=" + data
				+ ", tva=" + tva + "]";
	}
	
	
}