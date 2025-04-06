package modelo;

import java.sql.Date;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;

public class Factura {
	
	private int nroFactura,nroTurno;
	private LocalDate fechaCreacion;
	private LocalTime horaCreacion;
	private int totalPagar;
	
	public Factura(int nroFactura,LocalDate fechaCreacion,LocalTime horaCreacion,int totalPagar,int nroTurno) {
		this.nroFactura = nroFactura;
		this.fechaCreacion = fechaCreacion;
		this.horaCreacion = horaCreacion;
		this.totalPagar = totalPagar;
		this.nroTurno = nroTurno;
	}
	
	public Factura(LocalDate fechaCreacion,LocalTime horaCreacion,int totalPagar,int nroTurno) {
		this.fechaCreacion = fechaCreacion;
		this.horaCreacion = horaCreacion;
		this.totalPagar = totalPagar;
		this.nroTurno = nroTurno;
	}
	
	//SETS Y GETS
	public int getNroTurno() {
		return nroTurno;
	}
	public void setNroTurno(int nroTurno) {
		this.nroTurno = nroTurno;
	}
	public LocalDate getFechaCreacion() {
		return fechaCreacion;
	}
	public void setFechaCreacion(LocalDate fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}
	public int getTotalPagar() {
		return totalPagar;
	}
	public void setTotalPagar(int totalPagar) {
		this.totalPagar = totalPagar;
	}
	public int getNroFactura() {
		return nroFactura;
	}
	public void setNroFactura(int nroFactura) {
		this.nroFactura = nroFactura;
	}
	public LocalTime getHoraCreacion() {
		return horaCreacion;
	}
	public void setHoraCreacion(LocalTime horaCreacion) {
		this.horaCreacion = horaCreacion;
	}
}