package modelo;

import java.time.LocalDate;

import dao.AfiliadoDAO;

public class OSDE310 extends ObraSocial {
	
	public OSDE310(int idObraSocial, String nombreObra, int coberturaImplanteMax, int coberturaProtesisMax) {
		super(idObraSocial,nombreObra,coberturaImplanteMax,coberturaProtesisMax);
	}
	
	public int PreciosGratis(Turno turno) {
		return 0;
	}
	
	public int PreciosOrtodoncia(Turno turno, LocalDate fechaNacimiento) {
		
		LocalDate fechaHoy = LocalDate.now();
		
		if(fechaHoy.isAfter(fechaNacimiento.plusYears(8)) && fechaHoy.isBefore(fechaNacimiento.plusYears(18)))
			return 0;
		else
			return turno.getTratamiento().getPrecio();
	}
	
	public int PreciosConMonto(Turno turno,Afiliado afiliado, boolean esProtesis) {
		int MontoProtesis = afiliado.getCoberturaProtesis()-turno.getTratamiento().getPrecio();
		
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
		else
			return turno.getTratamiento().getPrecio();
	}
	
	//GETS Y SETS
	
}