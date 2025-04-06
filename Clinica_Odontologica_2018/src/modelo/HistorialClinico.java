package modelo;

public class HistorialClinico {
	private int documento;
	private String observaciones;
	private boolean enferCardiovascular, marcapasos, problePresion, probleGastrico, probleRenal, asma, diabetes, tratOnco, dieta,
	alergiaMedicamento, enfInfecto, transSanguinea, probleCoagulacion, embarazo, opeReciente, fiebreReumatica, mediTratMedico, convulDesmayos;
	
	public HistorialClinico(int documento, String observaciones, boolean enferCardiovascular, boolean marcapasos,
			boolean problePresion, boolean probleGastrico, boolean probleRenal, boolean asma, boolean diabetes,
			boolean tratOnco, boolean dieta, boolean alergiaMedicamento, boolean enfInfecto, boolean transSanguinea,
			boolean probleCoagulacion, boolean embarazo, boolean opeReciente, boolean fiebreReumatica,
			boolean mediTratMedico, boolean convulDesmayos) {
		this.documento = documento;
		this.observaciones = observaciones;
		this.enferCardiovascular = enferCardiovascular;
		this.marcapasos = marcapasos;
		this.problePresion = problePresion;
		this.probleGastrico = probleGastrico;
		this.probleRenal = probleRenal;
		this.asma = asma;
		this.diabetes = diabetes;
		this.tratOnco = tratOnco;
		this.dieta = dieta;
		this.alergiaMedicamento = alergiaMedicamento;
		this.enfInfecto = enfInfecto;
		this.transSanguinea = transSanguinea;
		this.probleCoagulacion = probleCoagulacion;
		this.embarazo = embarazo;
		this.opeReciente = opeReciente;
		this.fiebreReumatica = fiebreReumatica;
		this.mediTratMedico = mediTratMedico;
		this.convulDesmayos = convulDesmayos;
	}

	//GETS Y SETS
	
	public boolean isAlergiaMedicamento() {
		return alergiaMedicamento;
	}
	public void setAlergiaMedicamento(boolean alergiaMedicamento) {
		this.alergiaMedicamento = alergiaMedicamento;
	}
	public String getObservaciones() {
		return observaciones;
	}
	public void setObservaciones(String observaciones) {
		this.observaciones = observaciones;
	}
	public boolean isEnferCardiovascular() {
		return enferCardiovascular;
	}
	public void setEnferCardiovascular(boolean enferCardiovascular) {
		this.enferCardiovascular = enferCardiovascular;
	}
	public boolean isMarcapasos() {
		return marcapasos;
	}
	public void setMarcapasos(boolean marcapasos) {
		this.marcapasos = marcapasos;
	}
	public boolean isAsma() {
		return asma;
	}
	public void setAsma(boolean asma) {
		this.asma = asma;
	}
	public boolean isDiabetes() {
		return diabetes;
	}
	public void setDiabetes(boolean diabetes) {
		this.diabetes = diabetes;
	}
	public boolean isEmbarazo() {
		return embarazo;
	}
	public void setEmbarazo(boolean embarazo) {
		this.embarazo = embarazo;
	}
	
	public boolean isProblePresion() {
		return problePresion;
	}
	public void setProblePresion(boolean problePresion) {
		this.problePresion = problePresion;
	}
	public boolean isProbleGastrico() {
		return probleGastrico;
	}
	public void setProbleGastrico(boolean probleGastrico) {
		this.probleGastrico = probleGastrico;
	}
	public boolean isProbleRenal() {
		return probleRenal;
	}
	public void setProbleRenal(boolean probleRenal) {
		this.probleRenal = probleRenal;
	}
	public boolean isTratOnco() {
		return tratOnco;
	}
	public void setTratOnco(boolean tratOnco) {
		this.tratOnco = tratOnco;
	}
	public boolean isDieta() {
		return dieta;
	}
	public void setDieta(boolean dieta) {
		this.dieta = dieta;
	}
	public boolean isEnfInfecto() {
		return enfInfecto;
	}
	public void setEnfInfecto(boolean enfInfecto) {
		this.enfInfecto = enfInfecto;
	}
	public boolean isTransSanguinea() {
		return transSanguinea;
	}
	public void setTransSanguinea(boolean transSanguinea) {
		this.transSanguinea = transSanguinea;
	}
	public boolean isProbleCoagulacion() {
		return probleCoagulacion;
	}
	public void setProbleCoagulacion(boolean probleCoagulacion) {
		this.probleCoagulacion = probleCoagulacion;
	}
	public boolean isOpeReciente() {
		return opeReciente;
	}
	public void setOpeReciente(boolean opeReciente) {
		this.opeReciente = opeReciente;
	}
	public boolean isFiebreReumatica() {
		return fiebreReumatica;
	}
	public void setFiebreReumatica(boolean fiebreReumatica) {
		this.fiebreReumatica = fiebreReumatica;
	}
	public boolean isMediTratMedico() {
		return mediTratMedico;
	}
	public void setMediTratMedico(boolean mediTratMedico) {
		this.mediTratMedico = mediTratMedico;
	}
	public boolean isConvulDesmayos() {
		return convulDesmayos;
	}
	public void setConvulDesmayos(boolean convulDesmayos) {
		this.convulDesmayos = convulDesmayos;
	}
	public int getDocumento() {
		return documento;
	}
	public void setDocumento(int documento) {
		this.documento = documento;
	}
}