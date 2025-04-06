package modelo;

import java.time.LocalDate;

public class SinObra extends ObraSocial {
	
	public SinObra(int idObraSocial, String nombreObra, int coberturaImplanteMax, int coberturaProtesisMax) {
		super(idObraSocial,nombreObra,coberturaImplanteMax,coberturaProtesisMax);
	}
	
	public int PreciosGratis(Turno turno) {
		return turno.getTratamiento().getPrecio();
	}
	
	public int PreciosOrtodoncia(Turno turno, LocalDate fechaNacimiento) {
		return turno.getTratamiento().getPrecio();
	}
	
	public int PreciosConMonto(Turno turno, Afiliado afiliado, boolean esProtesis) {
		return turno.getTratamiento().getPrecio();
	}
	
	//SETS Y GETS
	
}