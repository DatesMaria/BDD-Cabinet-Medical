package model.cabinetMedical;
import java.time.LocalDate;
import java.util.Date;

public class Consultatie {

	private int id;
	private int idPacient;
	private int idMedic;
	private Integer idReteta;
	private Integer idFactura;
	private Status status;
	private LocalDate data;
	private String detalii;
	
	public enum Status{
		FINALIZATA, IN_DESFASURARE;
	}
	
	public Consultatie(int id, int id_pacient, int id_medic, Integer id_reteta, Integer id_factura, Status status, LocalDate data,
			String detalii) {
		super();
		this.id = id;
		this.idPacient = id_pacient;
		this.idMedic = id_medic;
		this.idReteta = id_reteta;
		this.idFactura = id_factura;
		this.status = status;
		this.data = data;
		this.detalii = detalii;
	}


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public int getIdPacient() {
		return idPacient;
	}


	public void setIdPacient(int id_pacient) {
		this.idPacient = id_pacient;
	}


	public int getIdMedic() {
		return idMedic;
	}


	public void setIdMedic(int id_medic) {
		this.idMedic = id_medic;
	}


	public Integer getIdReteta() {
		return idReteta;
	}


	public void setIdReteta(Integer id_reteta) {
		this.idReteta = id_reteta;
	}


	public Integer getIdFactura() {
		return idFactura;
	}


	public void setIdFactura(Integer id_factura) {
		this.idFactura = id_factura;
	}


	public Status getStatus() {
		return status;
	}


	public void setStatus(Status status) {
		this.status = status;
	}


	public LocalDate getData() {
		return data;
	}


	public void setData(LocalDate data) {
		this.data = data;
	}


	public String getDetalii() {
		return detalii;
	}


	public void setDetalii(String detalii) {
		this.detalii = detalii;
	}


	@Override
	public String toString() {
		return "Consultatie [id=" + id + ", idPacient=" + idPacient + ", idMedic=" + idMedic + ", idReteta="
				+ idReteta + ", idFactura=" + idFactura + ", status=" + status + ", data=" + data + ", detalii="
				+ detalii + "]";
	}
	
	
	
}