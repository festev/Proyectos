package modelo;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Calendar;

import dao.FacturaDAO;
import dao.HistorialClinicoDAO;
import dao.TratamientoDAO;
import dao.TurnoDAO;

public class Turno {
	
	private int nroTurno;
	private LocalDate fechaTurno;
	private String medicacion;
	private int dienteCara;
	private boolean realizado;
	private int documento;
	private LocalTime horaTurno;
	private Tratamiento tratamiento;
	private ArrayList<Factura> facturas = new ArrayList<Factura>();
	
	public Turno(int nroTurno,LocalDate fechaTurno,LocalTime horaTurno,String medicacion,int dienteCara,boolean realizado,Tratamiento tratamiento,int documento) {
		this.nroTurno = nroTurno;
		this.fechaTurno = fechaTurno;
		this.medicacion = medicacion;
		this.dienteCara = dienteCara;
		this.realizado = realizado;
		this.documento = documento;
		this.horaTurno = horaTurno;
		this.tratamiento = tratamiento;
	}
	
	public Turno(LocalDate fechaTurno,LocalTime horaTurno,boolean realizado,Tratamiento tratamiento,int documento) {
		this.fechaTurno = fechaTurno;
		this.realizado = realizado;
		this.documento = documento;
		this.horaTurno = horaTurno;
		this.tratamiento = tratamiento;
	}
	
	public void llenar_facturas() {
		facturas = new FacturaDAO().ObtenerTodo(nroTurno);
	}
	
	public boolean FiltrarTurno(boolean NroTurnoNoVacio,boolean DesdeNoVacio,boolean HastaNoVacio,boolean comboBoxNoVacio,
			boolean comboBox_1NoVacio,int NroTurno,LocalDate Desde,LocalDate Hasta,int Realizado,int Vigencia) {
		
		if(NroTurnoNoVacio)
			if(nroTurno != NroTurno)
				return false;
		
		if(DesdeNoVacio)
			if(fechaTurno.isBefore(Desde))
				return false;
		
		if(HastaNoVacio)
			if(fechaTurno.isAfter(Hasta))
				return false;
		
		if(comboBoxNoVacio) {
			if(Realizado == 1)
				if(!realizado)
					return false;
			if(Realizado == 2)
				if(realizado)
					return false;
		}
		
		if(comboBox_1NoVacio) {
			if(Vigencia == 1)
				if(!Vigente())
					return false;
			if(Vigencia == 2)
				if(Vigente())
					return false;
		}
		
		return true;
	}
	
	public boolean Vigente() {
		
		LocalDate fechaHoy = LocalDate.now();
		LocalTime horaHoy = LocalTime.now();
		boolean mismaFecha = fechaTurno.equals(fechaHoy), Vigente = fechaHoy.isBefore(fechaTurno);
		
		if(mismaFecha)
			Vigente = horaHoy.isBefore(horaTurno);
		
		return Vigente;
	}
	
	//SETS Y GETS
	public int getNroTurno() {
		return nroTurno;
	}
	public void setNroTurno(int nroTurno) {
		this.nroTurno = nroTurno;
	}
	public LocalDate getFechaTurno() {
		return fechaTurno;
	}
	public void setFechaTurno(LocalDate fechaTurno) {
		this.fechaTurno = fechaTurno;
	}
	public String getMedicacion() {
		return medicacion;
	}
	public void setMedicacion(String Medicacion) {
		this.medicacion = Medicacion;
	}
	public int getDienteCara() {
		return dienteCara;
	}
	public void setDienteCara(int dienteCara) {
		this.dienteCara = dienteCara;
	}
	public boolean isRealizado() {
		return realizado;
	}
	public void setRealizado(boolean realizado) {
		this.realizado = realizado;
	}
	public int getDocumento() {
		return documento;
	}
	public void setDocumento(int documento) {
		this.documento = documento;
	}
	public LocalTime getHoraTurno() {
		return horaTurno;
	}
	public void setHoraTurno(LocalTime horaTurno) {
		this.horaTurno = horaTurno;
	}
	public Tratamiento getTratamiento() {
		return tratamiento;
	}
	public void setTratamiento(Tratamiento tratamiento) {
		this.tratamiento = tratamiento;
	}
	public ArrayList<Factura> getFacturas() {
		return facturas;
	}
	public void setFacturas(ArrayList<Factura> facturas) {
		this.facturas = facturas;
	}
}