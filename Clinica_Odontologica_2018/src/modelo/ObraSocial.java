package modelo;

import java.time.LocalDate;

public abstract class ObraSocial {

	private int idObraSocial, coberturaImplanteMax, coberturaProtesisMax;
	private String nombreObra;

	public ObraSocial(int idObraSocial, String nombreObra, int coberturaImplanteMax, int coberturaProtesisMax) {
		this.idObraSocial = idObraSocial;
		this.nombreObra = nombreObra;
		this.coberturaImplanteMax = coberturaImplanteMax;
		this.coberturaProtesisMax = coberturaProtesisMax;
	}
	
	public abstract int PreciosGratis(Turno turno);
	public abstract int PreciosOrtodoncia(Turno turno, LocalDate localDate);
	public abstract int PreciosConMonto(Turno turno, Afiliado afiliado, boolean esProtesis);
	
	//SETS Y GETS
	
	public int getIdObraSocial() {
		return idObraSocial;
	}
	public void setIdObraSocial(int idObraSocial) {
		this.idObraSocial = idObraSocial;
	}
	public int getCoberturaImplanteMax() {
		return coberturaImplanteMax;
	}
	public void setCoberturaImplanteMax(int coberturaImplanteMax) {
		this.coberturaImplanteMax = coberturaImplanteMax;
	}
	public int getCoberturaProtesisMax() {
		return coberturaProtesisMax;
	}
	public void setCoberturaProtesisMax(int coberturaProtesisMax) {
		this.coberturaProtesisMax = coberturaProtesisMax;
	}
	public String getNombreObra() {
		return nombreObra;
	}
	public void setNombreObra(String nombreObra) {
		this.nombreObra = nombreObra;
	}
	
}