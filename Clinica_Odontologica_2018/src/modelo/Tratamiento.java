package modelo;

public class Tratamiento {
	
	private int idTratamiento;
	private String nombreTratamiento;
	private int precio;
	
	public Tratamiento(int idTratamiento,String nombreTratamiento,int precio) {
		this.idTratamiento=idTratamiento;
		this.nombreTratamiento=nombreTratamiento;
		this.precio=precio;
	}
	
	//SETS Y GETS
	public int getIdTratamiento() {
		return idTratamiento;
	}
	public void setIdTratamiento(int idTratamiento) {
		this.idTratamiento = idTratamiento;
	}
	public String getNombreTratamiento() {
		return nombreTratamiento;
	}
	public void setNombreTratamiento(String nombreTratamiento) {
		this.nombreTratamiento = nombreTratamiento;
	}
	public int getPrecio() {
		return precio;
	}
	public void setPrecio(int precio) {
		this.precio = precio;
	}
}
