package vista;

import javax.swing.JPanel;
import javax.swing.JTextField;
import com.toedter.calendar.JDateChooser;
import com.toedter.calendar.JTextFieldDateEditor;

import dao.AfiliadoDAO;
import dao.HistorialClinicoDAO;
import dao.ObraSocialDAO;
import modelo.Afiliado;
import modelo.HistorialClinico;
import modelo.OSDE210;
import modelo.OSDE310;
import modelo.OSDE410;
import modelo.OSDE450;
import modelo.OSDE510;
import modelo.ObraSocial;
import modelo.SinObra;

import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.time.LocalDate;
import java.time.ZoneId;
import java.awt.event.ActionEvent;
import javax.swing.JRadioButton;
import java.awt.Font;
import java.awt.Color;
import javax.swing.JTextArea;

public class AltaAfiliado extends JPanel {
	private JTextField txtDocumento,txtNombre,txtApellido,txtTelefono,txtDomicilio,txtLocalidad,txtOcupacion,txtCoberturaImplante,txtCoberturaProtesis;
	private JButton btnCrearAfiliado,btnVolver;
	private JRadioButton rdbtnEnferCardio,rdbtnMarcapasos,rdbtnProblePresion,rdbtnProbleGastri,rdbtnProbleRenal,rdbtnAsma,rdbtnDiabetes,rdbtnTrataOnco,
	rdbtnDieta,rdbtnAlergMedi,rdbtnEnfInfecto,rdbtnTransfuSangui,rdbtnProbleCoagu,rdbtnEmbarazo,rdbtnOpeRecientes,rdbtnFiebreReumatica,
	rdbtnMediTratMedico,rdbtnConvulDesmayos;
	private JLabel lblNombre,lblDni,lblDomicilio,lblLocalidad,lblOcupacin,lblObservaciones,lblFechaNacimiento,lblCoberturaEnImplantes,lblTelefono,lblApellido,
	lblCoberturaEnProtesis;
	private JTextArea txtArObservaciones;
	private JComboBox comboBox;
	private JDateChooser dateChooser;

	public AltaAfiliado(JFrame frame) {
		setLayout(null);
		
		txtDocumento = new JTextField();
		txtDocumento.setBounds(28, 51, 86, 27);
		add(txtDocumento);
		txtDocumento.setColumns(10);
		
		txtNombre = new JTextField();
		txtNombre.setColumns(10);
		txtNombre.setBounds(134, 51, 86, 27);
		add(txtNombre);
		
		txtApellido = new JTextField();
		txtApellido.setColumns(10);
		txtApellido.setBounds(240, 51, 86, 27);
		add(txtApellido);
		
		txtTelefono = new JTextField();
		txtTelefono.setColumns(10);
		txtTelefono.setBounds(342, 51, 86, 27);
		add(txtTelefono);
		
		txtDomicilio = new JTextField();
		txtDomicilio.setColumns(10);
		txtDomicilio.setBounds(28, 93, 86, 27);
		add(txtDomicilio);
		
		txtLocalidad = new JTextField();
		txtLocalidad.setColumns(10);
		txtLocalidad.setBounds(134, 93, 86, 27);
		add(txtLocalidad);
		
		txtOcupacion = new JTextField();
		txtOcupacion.setColumns(10);
		txtOcupacion.setBounds(240, 93, 86, 27);
		add(txtOcupacion);
		
		dateChooser = new JDateChooser();
		dateChooser.setBounds(28, 174, 131, 23);
		add(dateChooser);
		
		lblObservaciones = new JLabel("Observaciones");
		lblObservaciones.setBounds(12, 462, 108, 14);
		add(lblObservaciones);
		
		lblFechaNacimiento = new JLabel("Fecha Nacimiento");
		lblFechaNacimiento.setBounds(30, 158, 119, 17);
		add(lblFechaNacimiento);
		
		lblCoberturaEnImplantes = new JLabel("Cobertura en Implantes");
		lblCoberturaEnImplantes.setBounds(299, 136, 151, 14);
		add(lblCoberturaEnImplantes);
		
		lblCoberturaEnProtesis = new JLabel("Cobertura en Pr\u00F3tesis");
		lblCoberturaEnProtesis.setBounds(302, 176, 148, 22);
		add(lblCoberturaEnProtesis);
		
		comboBox = new JComboBox();
		comboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println(comboBox.getSelectedIndex());
				ObraSocial obraSocial = new ObraSocialDAO().Consulta(comboBox.getSelectedIndex()+1);
				txtCoberturaImplante.setText(Integer.toString(obraSocial.getCoberturaImplanteMax()));
				txtCoberturaProtesis.setText(Integer.toString(obraSocial.getCoberturaProtesisMax()));
			}
		});
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"Sin Obra", "OSDE210", "OSDE310", "OSDE410", "OSDE450", "OSDE510"}));
		comboBox.setBounds(184, 169, 100, 29);
		add(comboBox);
		
		txtCoberturaImplante = new JTextField();
		txtCoberturaImplante.setEditable(false);
		txtCoberturaImplante.setText("0");
		txtCoberturaImplante.setBounds(317, 153, 86, 22);
		add(txtCoberturaImplante);
		txtCoberturaImplante.setColumns(10);
	
		txtCoberturaProtesis = new JTextField();
		txtCoberturaProtesis.setEditable(false);
		txtCoberturaProtesis.setText("0");
		txtCoberturaProtesis.setColumns(10);
		txtCoberturaProtesis.setBounds(317, 198, 86, 22);
		add(txtCoberturaProtesis);
		
		btnCrearAfiliado = new JButton("CREAR AFILIADO");
		btnCrearAfiliado.setBackground(new Color(152, 251, 152));
		btnCrearAfiliado.setFont(new Font("SansSerif", Font.BOLD, 12));
		btnCrearAfiliado.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			    LocalDate localDate = dateChooser.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
				
				HistorialClinico hc = new HistorialClinico(Integer.parseInt(txtDocumento.getText()),txtArObservaciones.getText(), 
					rdbtnEnferCardio.isSelected(),rdbtnMarcapasos.isSelected(),rdbtnProblePresion.isSelected(),
					rdbtnProbleGastri.isSelected(),rdbtnProbleRenal.isSelected(),rdbtnAsma.isSelected(),
					rdbtnDiabetes.isSelected(),rdbtnTrataOnco.isSelected(),rdbtnDieta.isSelected(),rdbtnAlergMedi.isSelected(),
					rdbtnEnfInfecto.isSelected(),rdbtnTransfuSangui.isSelected(),rdbtnProbleCoagu.isSelected(),
					rdbtnEmbarazo.isSelected(),rdbtnOpeRecientes.isSelected(),rdbtnFiebreReumatica.isSelected(),
					rdbtnMediTratMedico.isSelected(),rdbtnConvulDesmayos.isSelected());
			    
				Afiliado afi = new Afiliado(Integer.parseInt(txtDocumento.getText()),Integer.parseInt(txtTelefono.getText()),txtNombre.getText(),
						txtApellido.getText(),txtDomicilio.getText(),txtLocalidad.getText(),txtOcupacion.getText(),localDate,LocalDate.now(),
						null,Integer.parseInt(txtCoberturaImplante.getText()),Integer.parseInt(txtCoberturaProtesis.getText()),hc,new ObraSocialDAO().Consulta((comboBox.getSelectedIndex())+1));
				
				new AfiliadoDAO().Guardar(afi);
			}
		});
		btnCrearAfiliado.setBounds(145, 609, 156, 23);
		add(btnCrearAfiliado);
		
		btnVolver = new JButton("Volver");
		btnVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.setSize(635, 345);
				frame.setLocationRelativeTo(null);
                frame.setContentPane(new MenuAfiliado(frame));
                frame.validate();
			}
		});
		btnVolver.setBounds(0, 0, 89, 23);
		add(btnVolver);
		
		rdbtnEnferCardio = new JRadioButton("Enfermedades cardiovasculares");
		rdbtnEnferCardio.setBounds(6, 270, 210, 23);
		add(rdbtnEnferCardio);
		
		rdbtnMarcapasos = new JRadioButton("Marcapasos");
		rdbtnMarcapasos.setBounds(6, 290, 180, 23);
		add(rdbtnMarcapasos);
		
		rdbtnProblePresion = new JRadioButton("Problemas de presión");
		rdbtnProblePresion.setBounds(6, 310, 180, 23);
		add(rdbtnProblePresion);
		
		rdbtnProbleGastri = new JRadioButton("Problemas gástrico-hepáticos");
		rdbtnProbleGastri.setBounds(6, 330, 214, 23);
		add(rdbtnProbleGastri);
		
		rdbtnProbleRenal = new JRadioButton("Problemas renales");
		rdbtnProbleRenal.setBounds(6, 350, 180, 23);
		add(rdbtnProbleRenal);
		
		rdbtnAsma = new JRadioButton("Asma");
		rdbtnAsma.setBounds(6, 370, 180, 23);
		add(rdbtnAsma);
		
		rdbtnDiabetes = new JRadioButton("Diabetes");
		rdbtnDiabetes.setBounds(6, 390, 180, 23);
		add(rdbtnDiabetes);
		
		rdbtnTrataOnco = new JRadioButton("Tratamientos oncológicos");
		rdbtnTrataOnco.setBounds(6, 410, 180, 23);
		add(rdbtnTrataOnco);
		
		rdbtnDieta = new JRadioButton("Dieta");
		rdbtnDieta.setBounds(6, 430, 180, 23);
		add(rdbtnDieta);
		
		rdbtnAlergMedi = new JRadioButton("Alergias a medicamentos");
		rdbtnAlergMedi.setBounds(240, 270, 180, 23);
		add(rdbtnAlergMedi);
		
		rdbtnEnfInfecto = new JRadioButton("Enf. infectocontagiosas");
		rdbtnEnfInfecto.setBounds(240, 290, 180, 23);
		add(rdbtnEnfInfecto);

		rdbtnTransfuSangui = new JRadioButton("Transfusiones sanguineas");
		rdbtnTransfuSangui.setBounds(240, 310, 180, 23);
		add(rdbtnTransfuSangui);
		
		rdbtnProbleCoagu = new JRadioButton("Problemas de coagulación");
		rdbtnProbleCoagu.setBounds(240, 330, 180, 23);
		add(rdbtnProbleCoagu);
		
		rdbtnEmbarazo = new JRadioButton("Embarazo");
		rdbtnEmbarazo.setBounds(240, 350, 180, 23);
		add(rdbtnEmbarazo);
		
		rdbtnOpeRecientes = new JRadioButton("Operaciones recientes");
		rdbtnOpeRecientes.setBounds(240, 370, 180, 23);
		add(rdbtnOpeRecientes);
		
		rdbtnFiebreReumatica = new JRadioButton("Fiebre Reumática");
		rdbtnFiebreReumatica.setBounds(240, 390, 180, 23);
		add(rdbtnFiebreReumatica);
		
		rdbtnMediTratMedico = new JRadioButton("Medicación y/o trat. médico");
		rdbtnMediTratMedico.setBounds(240, 410, 188, 23);
		add(rdbtnMediTratMedico);
		
		rdbtnConvulDesmayos = new JRadioButton("Convulsiones y/o desmayos");
		rdbtnConvulDesmayos.setBounds(240, 430, 188, 23);
		add(rdbtnConvulDesmayos);
		
		lblTelefono = new JLabel("<html>Teléfono <font color=\"red\">*</font></html>");
		lblTelefono.setBounds(344, 36, 76, 16);
		add(lblTelefono);
		
		lblApellido = new JLabel("Apellido");
		lblApellido.setBounds(243, 36, 70, 16);
		add(lblApellido);
		
		lblNombre = new JLabel("Nombre");
		lblNombre.setBounds(137, 36, 67, 16);
		add(lblNombre);
		
		lblDni = new JLabel("DNI");
		lblDni.setBounds(31, 36, 55, 16);
		add(lblDni);
		
		lblDomicilio = new JLabel("Domicilio");
		lblDomicilio.setBounds(31, 78, 70, 16);
		add(lblDomicilio);
		
		lblLocalidad = new JLabel("Localidad");
		lblLocalidad.setBounds(137, 78, 76, 16);
		add(lblLocalidad);
		
		lblOcupacin = new JLabel("Ocupaci\u00F3n");
		lblOcupacin.setBounds(240, 78, 86, 16);
		add(lblOcupacin);
		
		txtArObservaciones = new JTextArea();
		txtArObservaciones.setWrapStyleWord(true);
		txtArObservaciones.setLineWrap(true);
		txtArObservaciones.setBounds(12, 478, 424, 120);
		add(txtArObservaciones);
	}
}