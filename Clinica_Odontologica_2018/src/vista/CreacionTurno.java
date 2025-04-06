package vista;

import javax.swing.JFrame;
import javax.swing.JPanel;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import com.toedter.calendar.JDateChooser;

import dao.AfiliadoDAO;
import dao.HistorialClinicoDAO;
import dao.TratamientoDAO;
import dao.TurnoDAO;
import modelo.Afiliado;
import modelo.HistorialClinico;
import modelo.Turno;

import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import java.awt.Font;
import java.awt.Color;

public class CreacionTurno extends JPanel {
	private JTextField txtDNI;
	private JTextField txtHora;
	
	public CreacionTurno(JFrame frame, String documento) {
		setLayout(null);
		
		JLabel lblDni = new JLabel("DNI");
		lblDni.setBounds(50, 88, 46, 14);
		add(lblDni);
		
		txtDNI = new JTextField();
		txtDNI.setEditable(false);
		txtDNI.setText(documento);
		txtDNI.setBounds(48, 103, 100, 27);
		add(txtDNI);
		txtDNI.setColumns(10);
		
		JLabel lblFechaYHora = new JLabel("Fecha\r\n");
		lblFechaYHora.setBounds(50, 154, 86, 14);
		add(lblFechaYHora);
		
		JDateChooser dateChooser = new JDateChooser();
		dateChooser.setBounds(48, 169, 143, 27);
		add(dateChooser);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"Consulta Diagn\u00F3stico", "Consulta Urgencia", "Conducto", "Extracci\u00F3n", "Limpieza", "Arreglo", "Cirug\u00EDa Bucal", "Radiolog\u00EDa Dental", "Ortodoncia", "Implante", "Pr\u00F3tesis"}));
		comboBox.setSelectedIndex(0);
		comboBox.setBounds(219, 169, 170, 27);
		add(comboBox);
		
		JLabel lblTratamiento = new JLabel("Tratamiento");
		lblTratamiento.setBounds(221, 154, 105, 14);
		add(lblTratamiento);
		
		JButton btnVolver = new JButton("Volver");
		btnVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.setSize(635, 345);
				frame.setLocationRelativeTo(null);
                frame.setContentPane(new MenuTurno(frame));
                frame.validate();
			}
		});
		btnVolver.setBounds(0, 0, 89, 23);
		add(btnVolver);
		
		JButton btnCrearTurno = new JButton("CREAR TURNO");
		btnCrearTurno.setBackground(new Color(0, 255, 127));
		btnCrearTurno.setFont(new Font("Arial Black", Font.BOLD, 14));
		btnCrearTurno.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			    LocalDate localDate = dateChooser.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
			    LocalTime localTime = LocalTime.parse(txtHora.getText());
			    
				Turno tur = new Turno(localDate,localTime,false,new TratamientoDAO().Consulta((comboBox.getSelectedIndex())+1),Integer.parseInt(txtDNI.getText()));
				new TurnoDAO().Guardar(tur);
				}
		});
		btnCrearTurno.setBounds(117, 241, 183, 33);
		add(btnCrearTurno);
		
		JLabel lblHora = new JLabel("Hora");
		lblHora.setBounds(221, 88, 46, 14);
		add(lblHora);
		
		txtHora = new JTextField();
		txtHora.setColumns(10);
		txtHora.setBounds(219, 103, 113, 27);
		add(txtHora);
		
		JLabel lblHoraEjemplo = new JLabel("hh:mm");
		lblHoraEjemplo.setForeground(Color.DARK_GRAY);
		lblHoraEjemplo.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblHoraEjemplo.setBounds(290, 88, 52, 14);
		add(lblHoraEjemplo);
		
		
	}
}