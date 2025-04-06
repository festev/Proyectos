package pruebas;

import static org.junit.Assert.*;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;

import org.junit.Test;

import dao.AfiliadoDAO;
import dao.HistorialClinicoDAO;
import dao.TurnoDAO;
import modelo.Afiliado;
import modelo.Clinica;
import modelo.HistorialClinico;

public class PruebaLoca {

	@Test
	public void test() {
		
		String[] columnNames = {"documento", "nombre", "apellido", "fechaNacimiento", "fechaIntegracion", "fechaDesafiliacion",
				"domicilio", "localidad", "telefono", "ocupacion", "coberturaImplante", "coberturaProtesis", "ObraSocial"};
		
		System.out.println(columnNames.length);
		
		String hora = "21";
		String minuto = "12";
		String segundo = "0";
		
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.HOUR_OF_DAY, Integer.parseInt(hora));
		cal.set(Calendar.MINUTE, Integer.parseInt(minuto));
		cal.set(Calendar.SECOND, Integer.parseInt(segundo));
		
		boolean esMenor = Calendar.getInstance().before(cal);
		
		System.out.println(esMenor);
		
		fail("No implementado aun");
	}

	@Test
	public void test2() {
		
		//Extraccion extr = new Extraccion();
		
		//System.out.println(extr.getIdTratamiento() + " " + extr.getNombreTratamiento() + " " + extr.getPrecio());
		
		new AfiliadoDAO().ObtenerTodo();
		
		fail("No implementado aun");
	}
	
	@Test
	public void VerSiFuncionaElMetodoConsultaDelHistorialClinicoDAO(){
		
		HistorialClinicoDAO histoDAO = new HistorialClinicoDAO();
		//ArrayList<HistorialClinico> historial = histoDAO.Consulta("Select * from historialclinico Where documento = 6455");
		
		fail("No implementado aun");
	}
	
	@Test
	public void ExperimentandoConFechas(){
		
		Afiliado afiliado = new Clinica().ObtenerUnAfiliado(43084027);
		
		LocalDate fechaHoy = LocalDate.now();
		LocalDate Nacimiento = LocalDate.of(2010, 12, 20);
		LocalDate prueba = LocalDate.of(2016, 2, 29);
		Date Hoy = Date.valueOf(LocalDate.now());
		
		System.out.println(fechaHoy);
		System.out.println(Hoy);
		System.out.println(Nacimiento.plusYears(8));
		System.out.println(Nacimiento.plusYears(18));
		System.out.println(prueba.plusYears(1));
		System.out.println(fechaHoy.isAfter(Nacimiento.plusYears(8)) && fechaHoy.isBefore(Nacimiento.plusYears(18)));
		
		fail("No implementado aun");
	}
	
}