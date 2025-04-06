package modelo;

import java.time.LocalDate;
import java.util.ArrayList;
import dao.AfiliadoDAO;

public class Clinica {

	ArrayList<Afiliado> afiliados = new ArrayList<Afiliado>();

	public void llenar_afiliados()
	{
		afiliados = new AfiliadoDAO().ObtenerTodo();
	}
	
	public ArrayList<Afiliado> FiltrarConsultaAfiliado(boolean DniNoVacio, boolean NombreNoVacio, boolean ApellidoNoVacio,
														String Dni, String Nombre, String Apellido){
		
		llenar_afiliados();
		int DNI = 0;
		
		if(DniNoVacio)
			DNI = Integer.parseInt(Dni);
		
			for(int i=0;i<afiliados.size();i++) {
				if(!afiliados.get(i).FiltrarAfiliado(DniNoVacio, NombreNoVacio, ApellidoNoVacio, DNI, Nombre, Apellido)) {
					afiliados.remove(i);
					i--;
				}
			}
		
		return afiliados;
	}
	
	public ArrayList<Turno> FiltrarConsultaTurno(boolean NroTurnoNoVacio,boolean DesdeNoVacio,boolean HastaNoVacio,boolean comboBoxNoVacio,
			boolean comboBox_1NoVacio,String nroTurno,LocalDate Desde,LocalDate Hasta,int Realizado,int Vigencia){
		llenar_afiliados();
		ArrayList<Turno> turnos = new ArrayList<Turno>();
		
		for(int i=0;i<afiliados.size();i++) {
			ArrayList<Turno> turnosss = afiliados.get(i).FiltrarTurnosss(NroTurnoNoVacio,DesdeNoVacio,HastaNoVacio,comboBoxNoVacio,comboBox_1NoVacio,nroTurno,Desde,Hasta,Realizado,Vigencia);
				for(Turno turno : turnosss) {
					turnos.add(turno);}
		}
		
		return turnos;
	}
	
	public Afiliado ObtenerUnAfiliado(int documento) {
		
		llenar_afiliados();
		for(Afiliado afi : afiliados) {
			if(afi.getDocumento() == documento) {
				return afi;
			}
		}
		
		return null;
	}
	
}