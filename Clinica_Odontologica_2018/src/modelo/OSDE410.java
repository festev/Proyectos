package modelo;

import java.time.LocalDate;

import dao.AfiliadoDAO;

public class OSDE410 extends ObraSocial {
	
	public OSDE410(int idObraSocial, String nombreObra, int coberturaImplanteMax, int coberturaProtesisMax) {
		super(idObraSocial,nombreObra,coberturaImplanteMax,coberturaProtesisMax);
	}
	
	public int PreciosGratis(Turno turno) {
		return 0;
	}
	
	public int PreciosOrtodoncia(Turno turno, LocalDate fechaNacimiento) {
		
		LocalDate fechaHoy = LocalDate.now();
		
		if(fechaHoy.isAfter(fechaNacimiento.plusYears(8)))
			return 0;
		else
			return turno.getTratamiento().getPrecio();
	}
	
	public int PreciosConMonto(Turno turno,Afiliado afiliado, boolean esProtesis) {
		int MontoProtesis = afiliado.getCoberturaProtesis()-turno.getTratamiento().getPrecio();
		int MontoImplante = afiliado.getCoberturaImplante()-turno.getTratamiento().getPrecio();
		
		if(esProtesis) {
			if(MontoProtesis<0) {
				afiliado.setCoberturaProtesis(0);
				new AfiliadoDAO().Actualizar(afiliado);
				return Math.abs(MontoProtesis);
			}
			else if(afiliado.getCoberturaProtesis() == 0)
				return turno.getTratamiento().getPrecio();
			else {
				afiliado.setCoberturaProtesis(MontoProtesis);
				new AfiliadoDAO().Actualizar(afiliado);
				return 0;
			}
		}
		else {
			if(MontoImplante<0) {
				afiliado.setCoberturaImplante(0);
				new AfiliadoDAO().Actualizar(afiliado);
				return Math.abs(MontoImplante);
			}
			else if(afiliado.getCoberturaImplante() == 0)
				return turno.getTratamiento().getPrecio();
			else {
				afiliado.setCoberturaImplante(MontoImplante);
				new AfiliadoDAO().Actualizar(afiliado);
				return 0;
			}
		}
	}
	
	//GETS Y SETS
	
}