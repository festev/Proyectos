package vista;

import javax.swing.JFrame;
import javax.swing.JPanel;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Calendar;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JToggleButton;
import javax.swing.ListSelectionModel;

import com.toedter.calendar.JDateChooser;
import com.toedter.calendar.JTextFieldDateEditor;

import dao.AfiliadoDAO;
import dao.FacturaDAO;
import dao.TratamientoDAO;
import dao.TurnoDAO;
import modelo.Afiliado;
import modelo.Clinica;
import modelo.Factura;
import modelo.Tratamiento;
import modelo.Turno;

import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;

import java.awt.Font;
import java.awt.Color;
import javax.swing.JSplitPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.border.TitledBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import javax.swing.border.EtchedBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.CompoundBorder;
import javax.swing.border.BevelBorder;

public class ConsultaModificacionTurno extends JPanel {
	private JTextField txtNroTurno;
	private JTextField txtDiente;
	private JTextField txtMedicacion;
	private JTextField txtDNI;
	private JTextField txtHora;
	private JDateChooser dateChooser;
	private JRadioButton rdbtnRealizado;
	private JComboBox comboBox;
	private JButton btnVolver;
	private JButton btnGuardar;
	private JToggleButton tglbtnConsuModi;
	private JTable table;
	private JScrollPane scrollPane;
	
	String[] columnNames = {"NroFactura", "FechaCreacion", "HoraCreacion", "Total", "NroTurno"};

	public ConsultaModificacionTurno(JFrame frame, boolean hayAfiliado, Afiliado afiliado, Turno turno){
		if(turno.Vigente())
			setBorder(new TitledBorder(new EmptyBorder(0, 0, 0, 0), "Vigente", TitledBorder.RIGHT, TitledBorder.TOP, null, new Color(50, 205, 50)));
		else
			setBorder(new TitledBorder(new EmptyBorder(0, 0, 0, 0), "No Vigente", TitledBorder.RIGHT, TitledBorder.TOP, null, new Color(255, 0, 0)));
		
		setLayout(null);
		
		Object[][] data = new Object[turno.getFacturas().size()][columnNames.length];

		int i = 0;
		for(Factura factura : turno.getFacturas()) {
			data[i][0] = factura.getNroFactura();
			data[i][1] = factura.getFechaCreacion();
			data[i][2] = factura.getHoraCreacion();
			data[i][3] = factura.getTotalPagar();
			data[i][4] = factura.getNroTurno();
			
			i++;
		}
		
		JLabel nroTurno = new JLabel("NroTurno");
		nroTurno.setBounds(54, 55, 86, 14);
		add(nroTurno);
		
		txtNroTurno = new JTextField();
		txtNroTurno.setEnabled(false);
		txtNroTurno.setText(Integer.toString(turno.getNroTurno()));
		txtNroTurno.setBounds(52, 70, 81, 27);
		add(txtNroTurno);
		txtNroTurno.setColumns(10);
		
		JLabel lblFecha = new JLabel("Fecha");
		lblFecha.setBounds(54, 105, 86, 14);
		add(lblFecha);
		
		dateChooser = new JDateChooser();
		JTextFieldDateEditor editor = (JTextFieldDateEditor) dateChooser.getDateEditor();
		editor.setEditable(false);
		dateChooser.setEnabled(false);
		dateChooser.setDate(Date.valueOf(turno.getFechaTurno()));
		dateChooser.setBounds(52, 120, 125, 27);
		add(dateChooser);
		
		comboBox = new JComboBox();
		comboBox.setEnabled(false);
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"Consulta Diagnóstico", "Consulta Urgencia", "Conducto", "Extracción", "Limpieza", "Arreglo", "Cirugía Bucal", "Radiología Dental", "Ortodoncia", "Implante", "Prótesis"}));
		comboBox.setSelectedIndex(turno.getTratamiento().getIdTratamiento()-1);
		comboBox.setBounds(215, 120, 170, 27);
		add(comboBox);
		
		JLabel lblTratamiento = new JLabel("Tratamiento");
		lblTratamiento.setBounds(217, 105, 105, 14);
		add(lblTratamiento);
		
		btnVolver = new JButton("Volver");
		btnVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			    frame.setSize(707, 345);
				frame.setLocationRelativeTo(null);
                frame.setContentPane(new ConsultaTurno(frame,hayAfiliado,afiliado));
                frame.validate();
			}
		});
		btnVolver.setBounds(0, 0, 89, 23);
		add(btnVolver);
		
		rdbtnRealizado = new JRadioButton("Realizado");
		rdbtnRealizado.setEnabled(false);
		rdbtnRealizado.setSelected(turno.isRealizado());
		rdbtnRealizado.setBounds(52, 210, 109, 23);
		add(rdbtnRealizado);
		
		btnGuardar = new JButton("Guardar Cambios");
		btnGuardar.setEnabled(false);
		btnGuardar.setFont(new Font("Britannic Bold", Font.PLAIN, 17));
		btnGuardar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			    LocalDate localDate = dateChooser.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
			    LocalTime localTime = LocalTime.parse(txtHora.getText());
				   
			    int diente;
			    if(txtDiente.getText().equals("")) {diente=0;}
			    	else {diente = Integer.parseInt(txtDiente.getText());}
			    
				Turno tur = new Turno(turno.getNroTurno(),localDate,localTime,txtMedicacion.getText(),diente,
						rdbtnRealizado.isSelected(),new TratamientoDAO().Consulta((comboBox.getSelectedIndex())+1),turno.getDocumento());
				
				/*if(rdbtnRealizado.isSelected()) {
					String cant = turno[2];
					 int numero=Integer.parseInt(cant);
					 numero--;
					Factura fac=new Factura(Integer.parseInt(txtNroTurno.getText()),sqlDate,PrecioTotal(hola,numero),true);
				FacturaDAO facDAO =new FacturaDAO();
				facDAO.Guardar(fac);
				}*/
				
				new TurnoDAO().Actualizar(tur);
				
				if(tur.isRealizado()) {
					Factura factura = new Factura(LocalDate.now(),LocalTime.now(),
							CalcularPrecio((comboBox.getSelectedIndex())+1, tur, afiliado),tur.getNroTurno());
					new FacturaDAO().Guardar(factura);
					System.out.println(afiliado.getCoberturaImplante());
				}
				
			}
	});
		btnGuardar.setBounds(199, 249, 200, 30);
		add(btnGuardar);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(17, 302, 379, 199);
		add(scrollPane);
		
		table = new JTable(data, columnNames);
		table.setFillsViewportHeight(true);
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		scrollPane.setViewportView(table);
		
		DefaultTableModel modelo = new DefaultTableModel(data, columnNames) {
	        public boolean isCellEditable(int row, int column){  
	            return false;  
	          }
	        @Override
	        public Class getColumnClass(int column)
	        {
	            if(column == 0 || column == 3 || column == 4)
	                return Integer.class;
	            else if(column == 1)
	                return Date.class;
	            else
	            	return Time.class;
	        }
	    };
		TableRowSorter<TableModel> rowSorter = new TableRowSorter<TableModel>(modelo);
		rowSorter.toggleSortOrder(0);
		table.setRowSorter(rowSorter);
		
		txtDiente = new JTextField();
		txtDiente.setEnabled(false);
		txtDiente.setBounds(215, 210, 81, 27);
		if(turno.getDienteCara()==0)
			txtDiente.setText("");
		else
			txtDiente.setText(Integer.toString(turno.getDienteCara()));
		add(txtDiente);
		txtDiente.setColumns(10);
		
		JLabel lblDienteCara = new JLabel("Diente Cara");
		lblDienteCara.setBounds(217, 197, 86, 14);
		add(lblDienteCara);
		
		txtMedicacion = new JTextField();
		txtMedicacion.setEnabled(false);
		txtMedicacion.setBounds(215, 165, 170, 27);
		txtMedicacion.setText(turno.getMedicacion());
		add(txtMedicacion);
		txtMedicacion.setColumns(10);
		
		JLabel lblMedicacion = new JLabel("Medicación");
		lblMedicacion.setBounds(217, 152, 146, 14);
		add(lblMedicacion);
		
		txtDNI = new JTextField();
		txtDNI.setEnabled(false);
		txtDNI.setText(Integer.toString(turno.getDocumento()));
		txtDNI.setColumns(10);
		txtDNI.setBounds(215, 70, 81, 27);
		add(txtDNI);
		
		JLabel lblDni = new JLabel("DNI");
		lblDni.setBounds(217, 55, 46, 14);
		add(lblDni);
		
		JLabel lblHora = new JLabel("Hora");
		lblHora.setBounds(54, 152, 86, 14);
		add(lblHora);
		
		txtHora = new JTextField();
		txtHora.setEnabled(false);
		txtHora.setText(turno.getHoraTurno().toString());
		txtHora.setColumns(10);
		txtHora.setBounds(52, 165, 81, 27);
		add(txtHora);
		
		JLabel lblHoraEjemplo = new JLabel("hh:mm");
		lblHoraEjemplo.setForeground(Color.DARK_GRAY);
		lblHoraEjemplo.setFont(new Font("Tahoma", Font.PLAIN, 10));
		lblHoraEjemplo.setBounds(97, 153, 52, 14);
		add(lblHoraEjemplo);
		
		tglbtnConsuModi = new JToggleButton("CONSULTANDO");
		tglbtnConsuModi.setForeground(new Color(0, 128, 128));
		tglbtnConsuModi.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(tglbtnConsuModi.isSelected()) {
					Modificando(turno.Vigente());
					tglbtnConsuModi.setText("MODIFICANDO");
					tglbtnConsuModi.setForeground(new Color(210, 105, 30));
				}
				else {
					Consultando();
					tglbtnConsuModi.setText("CONSULTANDO");
					tglbtnConsuModi.setForeground(new Color(0, 128, 128));
					//Refrescar(new Clinica().ObtenerUnAfiliado(afiliado.getDocumento()));  HACER QUE SE RESETEE
					
				}
			}
		});
		tglbtnConsuModi.setFont(new Font("Arial Black", Font.PLAIN, 13));
		tglbtnConsuModi.setBounds(12, 249, 165, 30);
		add(tglbtnConsuModi);
		}
	
	/*
	int reply = JOptionPane.showConfirmDialog(null, "¿Estás seguro que queres cambiar a *MODIFICANDO*?", "",  JOptionPane.YES_NO_OPTION);
	if (reply == JOptionPane.YES_OPTION) {
		System.exit(0);
	}
	 */
	
	public void Consultando() {
		dateChooser.setEnabled(false);
		comboBox.setEnabled(false);
		txtHora.setEnabled(false);
		txtMedicacion.setEnabled(false);
		rdbtnRealizado.setEnabled(false);
		txtDiente.setEnabled(false);
		btnGuardar.setEnabled(false);
	}
	
	public void Modificando(boolean Vigencia) {
		
		dateChooser.setEnabled(Vigencia);
		txtHora.setEnabled(Vigencia);
		comboBox.setEnabled(true);
		rdbtnRealizado.setEnabled(!Vigencia);
		txtDiente.setEnabled(!Vigencia);
		txtMedicacion.setEnabled(!Vigencia);
		btnGuardar.setEnabled(true);
	}
	
	public int CalcularPrecio(int idTratamiento, Turno turno, Afiliado afiliado) {
		
		if(idTratamiento < 9)
			return afiliado.getObraSocial().PreciosGratis(turno);
		else if(idTratamiento == 9)
			return afiliado.getObraSocial().PreciosOrtodoncia(turno, afiliado.getFechaNacimiento());
		else {
			boolean esProtesis = true;
			if(idTratamiento == 10)
				esProtesis = false;
			return afiliado.getObraSocial().PreciosConMonto(turno, afiliado, esProtesis);
		}
	}
	
}