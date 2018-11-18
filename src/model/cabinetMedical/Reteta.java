package model.cabinetMedical;
import java.time.LocalDate;
import java.util.Date;

public class Reteta {
	private int id;
	private int idConsultatie;
	private boolean compensat;
	private LocalDate data;
	private String medicamente;
	
	
	public Reteta(int id, int id_consultatie, boolean compensat, LocalDate data, String medicamente) {
		super();
		this.id = id;
		this.idConsultatie = id_consultatie;
		this.compensat = compensat;
		this.data = data;
		this.medicamente=medicamente;
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


	public boolean isCompensat() {
		return compensat;
	}


	public void setCompensat(boolean compensat) {
		this.compensat = compensat;
	}


	public String getMedicamente() {
		return medicamente;
	}


	public void setMedicamente(String medicamente) {
		this.medicamente = medicamente;
	}


	public LocalDate getData() {
		return data;
	}


	public void setData(LocalDate data) {
		this.data = data;
	}


	@Override
	public String toString() {
		return "Reteta [id=" + id + ", idConsultatie=" + idConsultatie + ", compensat=" + compensat + ", data=" + data
				+ ", medicamente=" + medicamente + "]";
	}

}
