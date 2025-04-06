package modelo;

import java.time.LocalDate;
import java.util.ArrayList;

import dao.TurnoDAO;

public class Afiliado {

	private int documento, telefono, coberturaImplante, coberturaProtesis;
	private String nombre, apellido, domicilio, localidad, ocupacion;
	private LocalDate fechaNacimiento, fechaIntegracion, fechaDesafiliacion;
	private HistorialClinico historial;
	private ObraSocial obraSocial;
	
	private ArrayList<Turno> turnos = new ArrayList<Turno>();

	public Afiliado(int documento,int telefono,String nombre,String apellido,String domicilio,String localidad,String ocupacion,LocalDate fechaNacimiento,
			LocalDate fechaIntegracion,LocalDate fechaDesafiliacion,int coberturaImplante,int coberturaProtesis,HistorialClinico historial,ObraSocial obraSocial) {
		this.documento = documento;
		this.telefono = telefono;
		this.nombre = nombre;
		this.apellido = apellido;
		this.domicilio = domicilio;
		this.localidad = localidad;
		this.ocupacion = ocupacion;
		this.fechaNacimiento = fechaNacimiento;
		this.fechaIntegracion = fechaIntegracion;
		this.fechaDesafiliacion = fechaDesafiliacion;
		this.coberturaImplante = coberturaImplante;
		this.coberturaProtesis = coberturaProtesis;
		this.historial = historial;
		this.obraSocial = obraSocial;
	}
	
	public void llenar_turnos() {
		turnos = new TurnoDAO().ObtenerTodo(documento);
	}
	
	public boolean FiltrarAfiliado(boolean DniNoVacio, boolean NombreNoVacio, boolean ApellidoNoVacio,
			int Dni, String Nombre, String Apellido) {
		
		if(DniNoVacio)
			if(documento != Dni)
				return false;
			
		if(NombreNoVacio)
			if(!nombre.equals(Nombre))
				return false;
		
		if(ApellidoNoVacio)
			if(!apellido.equals(Apellido))
				return false;
		
		return true;
	}
	
	public ArrayList<Turno> FiltrarTurnosss(boolean NroTurnoNoVacio,boolean DesdeNoVacio,boolean HastaNoVacio,boolean comboBoxNoVacio,
			boolean comboBox_1NoVacio,String nroTurno,LocalDate Desde,LocalDate Hasta,int Realizado,int Vigencia){
		
		llenar_turnos();
		int NROTurno = 0;
		
		if(NroTurnoNoVacio)
			NROTurno = Integer.parseInt(nroTurno);
		
		for(int i=0;i<turnos.size();i++) {
			if(!turnos.get(i).FiltrarTurno(NroTurnoNoVacio,DesdeNoVacio,HastaNoVacio,comboBoxNoVacio,comboBox_1NoVacio,NROTurno,Desde,Hasta,Realizado,Vigencia)) {
				turnos.remove(i);
				i--;
			}
		}
		
		return turnos;
	}
	
	public Turno ObtenerUnTurno(int nroTurno) {
		
		llenar_turnos();
		for(Turno turno : turnos) {
			if(turno.getNroTurno() == nroTurno) {
				return turno;
			}
		}
		
		return null;
	}
	
	//SETS Y GETS

	public int getDocumento() {
		return documento;
	}
	public void setDocumento(int documento) {
		this.documento = documento;
	}
	public int getTelefono() {
		return telefono;
	}
	public void setTelefono(int telefono) {
		this.telefono = telefono;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getApellido() {
		return apellido;
	}
	public void setApellido(String apellido) {
		this.apellido = apellido;
	}
	public String getDomicilio() {
		return domicilio;
	}
	public void setDomicilio(String domicilio) {
		this.domicilio = domicilio;
	}
	public String getLocalidad() {
		return localidad;
	}
	public void setLocalidad(String localidad) {
		this.localidad = localidad;
	}
	public String getOcupacion() {
		return ocupacion;
	}
	public void setOcupacion(String ocupacion) {
		this.ocupacion = ocupacion;
	}
	public LocalDate getFechaNacimiento() {
		return fechaNacimiento;
	}
	public void setFechaNacimiento(LocalDate fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}
	public LocalDate getFechaIntegracion() {
		return fechaIntegracion;
	}
	public void setFechaIntegracion(LocalDate fechaIntegracion) {
		this.fechaIntegracion = fechaIntegracion;
	}
	public LocalDate getFechaDesafiliacion() {
		return fechaDesafiliacion;
	}
	public void setFechaDesafiliacion(LocalDate fechaDesafiliacion) {
		this.fechaDesafiliacion = fechaDesafiliacion;
	}
	public HistorialClinico getHistorial() {
		return historial;
	}
	public void setHistorial(HistorialClinico historial) {
		this.historial = historial;
	}
	public ArrayList<Turno> getTurnos() {
		return turnos;
	}
	public void setTurnos(ArrayList<Turno> turnos) {
		this.turnos = turnos;
	}
	public ObraSocial getObraSocial() {
		return obraSocial;
	}
	public void setObraSocial(ObraSocial obraSocial) {
		this.obraSocial = obraSocial;
	}
	public int getCoberturaImplante() {
		return coberturaImplante;
	}
	public void setCoberturaImplante(int coberturaImplante) {
		this.coberturaImplante = coberturaImplante;
	}
	public int getCoberturaProtesis() {
		return coberturaProtesis;
	}
	public void setCoberturaProtesis(int coberturaProtesis) {
		this.coberturaProtesis = coberturaProtesis;
	}
}