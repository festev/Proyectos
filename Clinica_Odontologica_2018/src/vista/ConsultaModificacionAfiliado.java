package vista;

import javax.swing.JPanel;
import javax.swing.JTextField;

import dao.AfiliadoDAO;
import dao.HistorialClinicoDAO;
import dao.ObraSocialDAO;
import dao.ObraSocialDAO;
import modelo.Afiliado;
import modelo.Clinica;
import modelo.HistorialClinico;
import modelo.OSDE210;
import modelo.OSDE310;
import modelo.OSDE410;
import modelo.OSDE450;
import modelo.OSDE510;
import modelo.ObraSocial;
import modelo.SinObra;

import javax.swing.JLabel;
import javax.swing.JFrame;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.JRadioButton;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.JToggleButton;
import java.awt.Font;
import java.awt.GraphicsEnvironment;
import java.awt.Image;
import java.awt.Color;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import com.toedter.calendar.JDateChooser;
import com.toedter.calendar.JTextFieldDateEditor;

public class ConsultaModificacionAfiliado extends JPanel {
	
	private JTextField txtDocumento,txtNombre,txtApellido,txtTelefono,txtDomicilio,txtLocalidad,txtOcupacion,
	txtCoberturaImplante,txtCoberturaProtesis,txtFechaIntegracion,txtFechaDesafiliacion;
	
	private JRadioButton rdbtnEnferCardio,rdbtnMarcapasos,rdbtnProblePresion,rdbtnProbleGastri,rdbtnProbleRenal,
	rdbtnAsma,rdbtnDiabetes,rdbtnTrataOnco,rdbtnDieta,rdbtnAlergMedi,rdbtnEnfInfecto,rdbtnTransfuSangui,rdbtnProbleCoagu,
	rdbtnEmbarazo,rdbtnOpeRecientes,rdbtnFiebreReumatica,rdbtnMediTratMedico,rdbtnConvulDesmayos;
	
	private JLabel lblDocumento,lblNombre,lblApellido,lblTelefono,lblDomicilio,lblLocalidad,lblOcupacion,lblFechaIntegracion,
	lblObservaciones,lblFechaNacimiento,lblCoberturaEnImplantes,lblCoberturaEnProtesis,lblFechaDesafiliacion;
	
	private JButton btnVolver,btnGuardar;
	
	private JToggleButton tglbtnConsuModi;
	private JScrollPane scrollPane;
	private JTextArea txtArObservaciones;
	private JComboBox comboBox;
	private JDateChooser dateChooser;

	public ConsultaModificacionAfiliado(JFrame frame, Afiliado afiliado) {
		setLayout(null);
		
		txtDocumento = new JTextField();
		txtDocumento.setText(Integer.toString(afiliado.getDocumento()));
		txtDocumento.setEditable(false);
		txtDocumento.setBounds(28, 51, 86, 27);
		add(txtDocumento);
		txtDocumento.setColumns(10);
		
		txtNombre = new JTextField();
		txtNombre.setText(afiliado.getNombre());
		txtNombre.setEditable(false);
		txtNombre.setColumns(10);
		txtNombre.setBounds(134, 51, 86, 27);
		add(txtNombre);
		
		txtApellido = new JTextField();
		txtApellido.setText(afiliado.getApellido());
		txtApellido.setEditable(false);
		txtApellido.setColumns(10);
		txtApellido.setBounds(240, 51, 86, 27);
		add(txtApellido);
		
		txtTelefono = new JTextField();
		txtTelefono.setText(Integer.toString(afiliado.getTelefono()));
		txtTelefono.setEditable(false);
		txtTelefono.setColumns(10);
		txtTelefono.setBounds(342, 51, 86, 27);
		add(txtTelefono);
		
		txtDomicilio = new JTextField();
		txtDomicilio.setText(afiliado.getDomicilio());
		txtDomicilio.setEditable(false);
		txtDomicilio.setColumns(10);
		txtDomicilio.setBounds(28, 93, 86, 27);
		add(txtDomicilio);
		
		txtLocalidad = new JTextField();
		txtLocalidad.setText(afiliado.getLocalidad());
		txtLocalidad.setEditable(false);
		txtLocalidad.setColumns(10);
		txtLocalidad.setBounds(134, 93, 86, 27);
		add(txtLocalidad);
		
		txtOcupacion = new JTextField();
		txtOcupacion.setText(afiliado.getOcupacion());
		txtOcupacion.setEditable(false);
		txtOcupacion.setColumns(10);
		txtOcupacion.setBounds(240, 93, 86, 27);
		add(txtOcupacion);
		
		lblObservaciones = new JLabel("Observaciones");
		lblObservaciones.setBounds(18, 471, 108, 14);
		add(lblObservaciones);
		
		lblFechaNacimiento = new JLabel("Fecha Nacimiento");
		lblFechaNacimiento.setBounds(31, 134, 119, 14);
		add(lblFechaNacimiento);
		
		lblCoberturaEnImplantes = new JLabel("Cobertura en Implantes");
		lblCoberturaEnImplantes.setBounds(311, 134, 151, 14);
		add(lblCoberturaEnImplantes);
		
		lblCoberturaEnProtesis = new JLabel("Cobertura en Protesis");
		lblCoberturaEnProtesis.setBounds(314, 200, 126, 14);
		add(lblCoberturaEnProtesis);
		
		txtCoberturaImplante = new JTextField();
		txtCoberturaImplante.setText(Integer.toString(afiliado.getCoberturaImplante()));
		txtCoberturaImplante.setEditable(false);
		txtCoberturaImplante.setBounds(334, 152, 86, 27);
		add(txtCoberturaImplante);
		txtCoberturaImplante.setColumns(10);
	
		
		txtCoberturaProtesis = new JTextField();
		txtCoberturaProtesis.setText(Integer.toString(afiliado.getCoberturaProtesis()));
		txtCoberturaProtesis.setEditable(false);
		txtCoberturaProtesis.setColumns(10);
		txtCoberturaProtesis.setBounds(334, 219, 86, 27);
		add(txtCoberturaProtesis);
		
		btnVolver = new JButton("Volver");
		btnVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.setSize(635, 345);
				frame.setLocationRelativeTo(null);
                frame.setContentPane(new MenuAfiliado(frame));
                frame.validate();
			}
		});
		btnVolver.setBounds(0, 0, 89, 27);
		add(btnVolver);
		
		rdbtnEnferCardio = new JRadioButton("Enfermedades cardiovasculares");
		rdbtnEnferCardio.setSelected(afiliado.getHistorial().isEnferCardiovascular());
		rdbtnEnferCardio.setEnabled(false);
		rdbtnEnferCardio.setBounds(6, 270, 210, 23);
		add(rdbtnEnferCardio);
		
		rdbtnMarcapasos = new JRadioButton("Marcapasos");
		rdbtnMarcapasos.setSelected(afiliado.getHistorial().isMarcapasos());
		rdbtnMarcapasos.setEnabled(false);
		rdbtnMarcapasos.setBounds(6, 290, 180, 23);
		add(rdbtnMarcapasos);
		
		rdbtnProblePresion = new JRadioButton("Problemas de presión");
		rdbtnProblePresion.setSelected(afiliado.getHistorial().isProblePresion());
		rdbtnProblePresion.setEnabled(false);
		rdbtnProblePresion.setBounds(6, 310, 180, 23);
		add(rdbtnProblePresion);
		
		rdbtnProbleGastri = new JRadioButton("Problemas gástrico-hepáticos");
		rdbtnProbleGastri.setSelected(afiliado.getHistorial().isProbleGastrico());
		rdbtnProbleGastri.setEnabled(false);
		rdbtnProbleGastri.setBounds(6, 330, 214, 23);
		add(rdbtnProbleGastri);
		
		rdbtnProbleRenal = new JRadioButton("Problemas renales");
		rdbtnProbleRenal.setSelected(afiliado.getHistorial().isProbleRenal());
		rdbtnProbleRenal.setEnabled(false);
		rdbtnProbleRenal.setBounds(6, 350, 180, 23);
		add(rdbtnProbleRenal);
		
		rdbtnAsma = new JRadioButton("Asma");
		rdbtnAsma.setSelected(afiliado.getHistorial().isAsma());
		rdbtnAsma.setEnabled(false);
		rdbtnAsma.setBounds(6, 370, 180, 23);
		add(rdbtnAsma);
		
		rdbtnDiabetes = new JRadioButton("Diabetes");
		rdbtnDiabetes.setSelected(afiliado.getHistorial().isDiabetes());
		rdbtnDiabetes.setEnabled(false);
		rdbtnDiabetes.setBounds(6, 390, 180, 23);
		add(rdbtnDiabetes);
		
		rdbtnTrataOnco = new JRadioButton("Tratamientos oncológicos");
		rdbtnTrataOnco.setSelected(afiliado.getHistorial().isTratOnco());
		rdbtnTrataOnco.setEnabled(false);
		rdbtnTrataOnco.setBounds(6, 410, 180, 23);
		add(rdbtnTrataOnco);
		
		rdbtnDieta = new JRadioButton("Dieta");
		rdbtnDieta.setSelected(afiliado.getHistorial().isDieta());
		rdbtnDieta.setEnabled(false);
		rdbtnDieta.setBounds(6, 430, 180, 23);
		add(rdbtnDieta);
		
		rdbtnAlergMedi = new JRadioButton("Alergias a medicamentos");
		rdbtnAlergMedi.setSelected(afiliado.getHistorial().isAlergiaMedicamento());
		rdbtnAlergMedi.setEnabled(false);
		rdbtnAlergMedi.setBounds(240, 270, 180, 23);
		add(rdbtnAlergMedi);
		
		rdbtnEnfInfecto = new JRadioButton("Enf. infectocontagiosas");
		rdbtnEnfInfecto.setSelected(afiliado.getHistorial().isEnfInfecto());
		rdbtnEnfInfecto.setEnabled(false);
		rdbtnEnfInfecto.setBounds(240, 290, 180, 23);
		add(rdbtnEnfInfecto);

		rdbtnTransfuSangui = new JRadioButton("Transfusiones sanguineas");
		rdbtnTransfuSangui.setSelected(afiliado.getHistorial().isTransSanguinea());
		rdbtnTransfuSangui.setEnabled(false);
		rdbtnTransfuSangui.setBounds(240, 310, 180, 23);
		add(rdbtnTransfuSangui);
		
		rdbtnProbleCoagu = new JRadioButton("Problemas de coagulación");
		rdbtnProbleCoagu.setSelected(afiliado.getHistorial().isProbleCoagulacion());
		rdbtnProbleCoagu.setEnabled(false);
		rdbtnProbleCoagu.setBounds(240, 330, 180, 23);
		add(rdbtnProbleCoagu);
		
		rdbtnEmbarazo = new JRadioButton("Embarazo");
		rdbtnEmbarazo.setSelected(afiliado.getHistorial().isEmbarazo());
		rdbtnEmbarazo.setEnabled(false);
		rdbtnEmbarazo.setBounds(240, 350, 180, 23);
		add(rdbtnEmbarazo);
		
		rdbtnOpeRecientes = new JRadioButton("Operaciones recientes");
		rdbtnOpeRecientes.setSelected(afiliado.getHistorial().isOpeReciente());
		rdbtnOpeRecientes.setEnabled(false);
		rdbtnOpeRecientes.setBounds(240, 370, 180, 23);
		add(rdbtnOpeRecientes);
		
		rdbtnFiebreReumatica = new JRadioButton("Fiebre Reumática");
		rdbtnFiebreReumatica.setSelected(afiliado.getHistorial().isFiebreReumatica());
		rdbtnFiebreReumatica.setEnabled(false);
		rdbtnFiebreReumatica.setBounds(240, 390, 180, 23);
		add(rdbtnFiebreReumatica);
		
		rdbtnMediTratMedico = new JRadioButton("Medicación y/o trat. médico");
		rdbtnMediTratMedico.setSelected(afiliado.getHistorial().isMediTratMedico());
		rdbtnMediTratMedico.setEnabled(false);
		rdbtnMediTratMedico.setBounds(240, 410, 188, 23);
		add(rdbtnMediTratMedico);
		
		rdbtnConvulDesmayos = new JRadioButton("Convulsiones y/o desmayos");
		rdbtnConvulDesmayos.setSelected(afiliado.getHistorial().isConvulDesmayos());
		rdbtnConvulDesmayos.setEnabled(false);
		rdbtnConvulDesmayos.setBounds(240, 430, 188, 23);
		add(rdbtnConvulDesmayos);
		
		lblDocumento = new JLabel("DNI");
		lblDocumento.setBounds(31, 34, 86, 14);
		add(lblDocumento);
		
		lblNombre = new JLabel("Nombre");
		lblNombre.setBounds(137, 34, 46, 14);
		add(lblNombre);
		
		lblApellido = new JLabel("Apellido");
		lblApellido.setBounds(243, 34, 46, 14);
		add(lblApellido);
		
		lblTelefono = new JLabel("Telefono");
		lblTelefono.setBounds(345, 34, 61, 14);
		add(lblTelefono);
		
		lblDomicilio = new JLabel("Domicilio");
		lblDomicilio.setBounds(31, 78, 61, 14);
		add(lblDomicilio);
		
		lblLocalidad = new JLabel("Localidad");
		lblLocalidad.setBounds(137, 78, 61, 14);
		add(lblLocalidad);
		
		lblOcupacion = new JLabel("Ocupacion");
		lblOcupacion.setBounds(243, 78, 73, 14);
		add(lblOcupacion);
		
		lblFechaIntegracion = new JLabel("Fecha Integración");
		lblFechaIntegracion.setBounds(31, 178, 119, 14);
		add(lblFechaIntegracion);
		
		txtFechaIntegracion = new JTextField();
		txtFechaIntegracion.setText(new SimpleDateFormat("dd/MM/yyyy").format(Date.valueOf(afiliado.getFechaIntegracion())));
		txtFechaIntegracion.setEditable(false);
		txtFechaIntegracion.setBounds(28, 193, 86, 27);
		add(txtFechaIntegracion);
		txtFechaIntegracion.setColumns(10);
		
		scrollPane = new JScrollPane();
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.setBounds(15, 485, 425, 137);
		add(scrollPane);
		
		txtArObservaciones = new JTextArea();
		txtArObservaciones.setEditable(false);
		txtArObservaciones.setText(afiliado.getHistorial().getObservaciones());
		txtArObservaciones.setWrapStyleWord(true);
		txtArObservaciones.setLineWrap(true);
		scrollPane.setViewportView(txtArObservaciones);
		
		lblFechaDesafiliacion = new JLabel("Fecha Desafiliación");
		lblFechaDesafiliacion.setBounds(31, 222, 119, 14);
		add(lblFechaDesafiliacion);
		
		txtFechaDesafiliacion = new JTextField();
		try{txtFechaDesafiliacion.setText(new SimpleDateFormat("dd/MM/yyyy").format(Date.valueOf(afiliado.getFechaDesafiliacion())));}
		catch(NullPointerException e) {txtFechaDesafiliacion.setText("");}
		txtFechaDesafiliacion.setEditable(false);
		txtFechaDesafiliacion.setColumns(10);
		txtFechaDesafiliacion.setBounds(28, 238, 86, 27);
		add(txtFechaDesafiliacion);
		
		tglbtnConsuModi = new JToggleButton("CONSULTANDO");
		tglbtnConsuModi.setForeground(new Color(0, 128, 128));
		tglbtnConsuModi.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(tglbtnConsuModi.isSelected()) {
					Editables(true);
					tglbtnConsuModi.setText("MODIFICANDO");
					tglbtnConsuModi.setForeground(new Color(210, 105, 30));
				}
				else {
					Editables(false);
					JTextFieldDateEditor editor = (JTextFieldDateEditor) dateChooser.getDateEditor();
					editor.setEnabled(true);
					tglbtnConsuModi.setText("CONSULTANDO");
					tglbtnConsuModi.setForeground(new Color(0, 128, 128));
					Refrescar(new Clinica().ObtenerUnAfiliado(afiliado.getDocumento()));
					
				}
			}
		});
		tglbtnConsuModi.setFont(new Font("Arial Black", Font.PLAIN, 13));
		tglbtnConsuModi.setBounds(15, 629, 165, 30);
		add(tglbtnConsuModi);
		
		btnGuardar = new JButton("Guardar Cambios");
		btnGuardar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			    LocalDate localDate = dateChooser.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
				
				HistorialClinico hc = new HistorialClinico(Integer.parseInt(txtDocumento.getText()), txtArObservaciones.getText(), 
						rdbtnEnferCardio.isSelected(),rdbtnMarcapasos.isSelected(),rdbtnProblePresion.isSelected(),
						rdbtnProbleGastri.isSelected(),rdbtnProbleRenal.isSelected(),rdbtnAsma.isSelected(),
						rdbtnDiabetes.isSelected(),rdbtnTrataOnco.isSelected(),rdbtnDieta.isSelected(),rdbtnAlergMedi.isSelected(),
						rdbtnEnfInfecto.isSelected(),rdbtnTransfuSangui.isSelected(),rdbtnProbleCoagu.isSelected(),
						rdbtnEmbarazo.isSelected(),rdbtnOpeRecientes.isSelected(),rdbtnFiebreReumatica.isSelected(),
						rdbtnMediTratMedico.isSelected(),rdbtnConvulDesmayos.isSelected());
			    
				Afiliado afi = new Afiliado(Integer.parseInt(txtDocumento.getText()),Integer.parseInt(txtTelefono.getText()),txtNombre.getText(),
						txtApellido.getText(),txtDomicilio.getText(),txtLocalidad.getText(),txtOcupacion.getText(),localDate,LocalDate.now(),
						null,Integer.parseInt(txtCoberturaImplante.getText()),Integer.parseInt(txtCoberturaProtesis.getText()),hc,new ObraSocialDAO().Consulta((comboBox.getSelectedIndex())+1));
				
				new AfiliadoDAO().Actualizar(afi);
			}
		});
		btnGuardar.setEnabled(false);
		Image img = new ImageIcon(this.getClass().getResource("/GuardadoIcono.png")).getImage();
		btnGuardar.setIcon(new ImageIcon(img));
		btnGuardar.setFont(new Font("Britannic Bold", Font.PLAIN, 17));
		btnGuardar.setBounds(198, 629, 242, 30);
		add(btnGuardar);
		
		comboBox = new JComboBox();
		comboBox.setEnabled(false);
		comboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Afiliado afi = new Clinica().ObtenerUnAfiliado(afiliado.getDocumento());
				if(afi.getObraSocial().getIdObraSocial()!=(comboBox.getSelectedIndex())+1) {
				ObraSocial obraSocial = new ObraSocialDAO().Consulta(comboBox.getSelectedIndex()+1);
				txtCoberturaImplante.setText(Integer.toString(obraSocial.getCoberturaImplanteMax()));
				txtCoberturaProtesis.setText(Integer.toString(obraSocial.getCoberturaProtesisMax()));
				}
				else {
					txtCoberturaImplante.setText(Integer.toString(afi.getCoberturaImplante()));
					txtCoberturaProtesis.setText(Integer.toString(afi.getCoberturaProtesis()));
				}
			}
		});
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"Sin Obra", "OSDE210", "OSDE310", "OSDE410", "OSDE450", "OSDE510"}));
		comboBox.setSelectedIndex(afiliado.getObraSocial().getIdObraSocial()-1);
		comboBox.setBounds(193, 172, 108, 27);
		add(comboBox);
		
		dateChooser = new JDateChooser();
		JTextFieldDateEditor editor = (JTextFieldDateEditor) dateChooser.getDateEditor();
		editor.setEditable(false);
		dateChooser.setEnabled(false);
		editor.setEnabled(true);
		dateChooser.setDate(Date.valueOf(afiliado.getFechaNacimiento()));
		dateChooser.setBounds(27, 149, 132, 27);
		add(dateChooser);
		
	}
	public void Editables(boolean condicion) {
		txtNombre.setEditable(condicion);txtApellido.setEditable(condicion);
		txtTelefono.setEditable(condicion);txtDomicilio.setEditable(condicion);txtLocalidad.setEditable(condicion);
		txtOcupacion.setEditable(condicion);
		
		rdbtnEnferCardio.setEnabled(condicion);rdbtnMarcapasos.setEnabled(condicion);rdbtnProblePresion.setEnabled(condicion);
		rdbtnProbleGastri.setEnabled(condicion);rdbtnProbleRenal.setEnabled(condicion);rdbtnAsma.setEnabled(condicion);
		rdbtnDiabetes.setEnabled(condicion);rdbtnTrataOnco.setEnabled(condicion);rdbtnDieta.setEnabled(condicion);
		rdbtnAlergMedi.setEnabled(condicion);rdbtnEnfInfecto.setEnabled(condicion);rdbtnTransfuSangui.setEnabled(condicion);
		rdbtnProbleCoagu.setEnabled(condicion);rdbtnEmbarazo.setEnabled(condicion);rdbtnOpeRecientes.setEnabled(condicion);
		rdbtnFiebreReumatica.setEnabled(condicion);rdbtnMediTratMedico.setEnabled(condicion);rdbtnConvulDesmayos.setEnabled(condicion);
		
		btnGuardar.setEnabled(condicion);
		comboBox.setEnabled(condicion);
		dateChooser.setEnabled(condicion);
		txtArObservaciones.setEditable(condicion);
	}
	
	public void Refrescar(Afiliado afiliado) {
		txtDocumento.setText(Integer.toString(afiliado.getDocumento()));
		txtNombre.setText(afiliado.getNombre());
		txtApellido.setText(afiliado.getApellido());
		txtTelefono.setText(Integer.toString(afiliado.getTelefono()));
		txtDomicilio.setText(afiliado.getDomicilio());
		txtLocalidad.setText(afiliado.getLocalidad());
		txtOcupacion.setText(afiliado.getOcupacion());
		comboBox.setSelectedIndex(afiliado.getObraSocial().getIdObraSocial()-1);
		txtCoberturaImplante.setText(Integer.toString(afiliado.getCoberturaImplante()));
		txtCoberturaProtesis.setText(Integer.toString(afiliado.getCoberturaProtesis()));
		rdbtnEnferCardio.setSelected(afiliado.getHistorial().isEnferCardiovascular());
		rdbtnMarcapasos.setSelected(afiliado.getHistorial().isMarcapasos());
		rdbtnProblePresion.setSelected(afiliado.getHistorial().isProblePresion());
		rdbtnProbleGastri.setSelected(afiliado.getHistorial().isProbleGastrico());
		rdbtnProbleRenal.setSelected(afiliado.getHistorial().isProbleRenal());
		rdbtnAsma.setSelected(afiliado.getHistorial().isAsma());
		rdbtnDiabetes.setSelected(afiliado.getHistorial().isDiabetes());
		rdbtnTrataOnco.setSelected(afiliado.getHistorial().isTratOnco());
		rdbtnDieta.setSelected(afiliado.getHistorial().isDieta());
		rdbtnAlergMedi.setSelected(afiliado.getHistorial().isAlergiaMedicamento());
		rdbtnEnfInfecto.setSelected(afiliado.getHistorial().isEnfInfecto());
		rdbtnTransfuSangui.setSelected(afiliado.getHistorial().isTransSanguinea());
		rdbtnProbleCoagu.setSelected(afiliado.getHistorial().isProbleCoagulacion());
		rdbtnEmbarazo.setSelected(afiliado.getHistorial().isEmbarazo());
		rdbtnOpeRecientes.setSelected(afiliado.getHistorial().isOpeReciente());
		rdbtnFiebreReumatica.setSelected(afiliado.getHistorial().isFiebreReumatica());
		rdbtnMediTratMedico.setSelected(afiliado.getHistorial().isMediTratMedico());
		rdbtnConvulDesmayos.setSelected(afiliado.getHistorial().isConvulDesmayos());
		txtFechaIntegracion.setText(new SimpleDateFormat("dd/MM/yyyy").format(Date.valueOf(afiliado.getFechaIntegracion())));
		txtArObservaciones.setText(afiliado.getHistorial().getObservaciones());
		try{txtFechaDesafiliacion.setText(new SimpleDateFormat("dd/MM/yyyy").format(Date.valueOf(afiliado.getFechaDesafiliacion())));}
		catch(NullPointerException e) {txtFechaDesafiliacion.setText("");}
		dateChooser.setDate(Date.valueOf(afiliado.getFechaNacimiento()));
	}
}