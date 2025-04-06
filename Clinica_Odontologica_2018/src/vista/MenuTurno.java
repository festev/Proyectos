package vista;

import javax.swing.JFrame;
import javax.swing.JPanel;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.JTable;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JToggleButton;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

import modelo.Afiliado;
import modelo.Clinica;

import java.awt.Image;

import javax.swing.ListSelectionModel;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import java.awt.Font;
import java.awt.Color;

public class MenuTurno extends JPanel {
	private JTable table;
	private JTextField txtDni;
	private JTextField txtNombre;
	private JTextField txtApellido;
	private JButton btnCrearTurno;
	private JButton btnSeleccionar;
	private JComboBox comboBox;
	
	String[] columnNames = {"documento", "nombre", "apellido", "fechaNacimiento", "fechaIntegracion", "fechaDesafiliacion",
			"domicilio", "localidad", "telefono", "ocupacion", "coberturaImplante", "coberturaProtesis", "ObraSocial"};
	
	Object[][] data = new Object[0][0];
	
    DefaultTableModel modelo = new DefaultTableModel(data, columnNames);

	public MenuTurno(JFrame frame) {
		setLayout(null);
		
		btnSeleccionar = new JButton("Seleccionar Afiliado");
		btnSeleccionar.setFont(new Font("Arial Black", Font.BOLD, 15));
		btnSeleccionar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			     int dni = Integer.parseInt(String.valueOf(table.getValueAt(table.getSelectedRow(), 0)));
			     	 
			     /*for (int i = 0; i < columnNames.length; i++) {
			         afiTexto[i] = String.valueOf(table.getValueAt(table.getSelectedRow(), i));
			     }*/
			        		 
			         //table.getValueAt(table.getSelectedRow(), i).toString();
			         //si utilizo el código de arriba, habrá un error cuando se intente obtener un valor null.
			         //creo que se podría resolver con un if(table.getValueAt(table.getSelectedRow(), i) == null), pero sería ineficiente.
				
			     /*STRING A DATE
			     	ArrayList<Date> fechas = new ArrayList<Date>();
					try {java.util.Date fechaaa = new SimpleDateFormat("yyyy-MM-dd").parse(afiTexto[3]);
					    java.sql.Date sqlDate = new java.sql.Date(fechaaa.getTime());
						fechas.add(sqlDate);} catch (ParseException e1) {e1.printStackTrace();}
					
				Afiliado afi = new Afiliado(Integer.parseInt(afiTexto[0]),Integer.parseInt(afiTexto[8]),Integer.parseInt(afiTexto[10]),
			    		 Integer.parseInt(afiTexto[11]),Integer.parseInt(afiTexto[12]),afiTexto[1],afiTexto[2],afiTexto[9],
			    		 afiTexto[6],afiTexto[7],fechas.get(0),fechas.get(1),fechas.get(2));
			     
				afi.llenar_historial();*/
				Afiliado afi = new Clinica().ObtenerUnAfiliado(dni);
				frame.setSize(707, 345);
				frame.setLocationRelativeTo(null);
                frame.setContentPane(new ConsultaTurno(frame,true,afi));
                frame.validate();
			}
		});
		btnSeleccionar.setEnabled(false);
		btnSeleccionar.setBounds(42, 252, 256, 46);
		add(btnSeleccionar);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 86, 600, 162);
		add(scrollPane);
		
		table = new JTable(data, columnNames);
		table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		table.setFillsViewportHeight(true);
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		scrollPane.setViewportView(table);
		table.getSelectionModel().addListSelectionListener(new ListSelectionListener(){
	        public void valueChanged(ListSelectionEvent event) {
	            // COSA INTERESANTE: System.out.println(table.getValueAt(table.getSelectedRow(), 0).toString());
	        	// COSA INTERESANTE X2: if(!table.getSelectionModel().isSelectionEmpty())
				btnSeleccionar.setEnabled(true);
				btnCrearTurno.setEnabled(true);
				btnCrearTurno.setForeground(new Color(30, 144, 255));
				
	        }
	    });
		
		txtDni = new JTextField();
		txtDni.setBounds(10, 55, 86, 27);
		add(txtDni);
		txtDni.setColumns(10);
		
		txtNombre = new JTextField();
		txtNombre.setColumns(10);
		txtNombre.setBounds(122, 55, 86, 27);
		add(txtNombre);
		
		txtApellido = new JTextField();
		txtApellido.setColumns(10);
		txtApellido.setBounds(234, 55, 86, 27);
		add(txtApellido);
		
		JButton btnVolver = new JButton("Volver");
		btnVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.setSize(450, 300);
				frame.setLocationRelativeTo(null);
                frame.setContentPane(new MenuPrincipal(frame));
                frame.validate();
			}
		});
		btnVolver.setBounds(0, 0, 89, 23);
		add(btnVolver);
		
		JButton btnBuscar = new JButton("Buscar");
		btnBuscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				boolean DniNoVacio = !(txtDni.getText().isEmpty()), NombreNoVacio = !(txtNombre.getText().isEmpty()),
						ApellidoNoVacio = !(txtApellido.getText().isEmpty());
				ArrayList<Afiliado> afiliados = new Clinica().FiltrarConsultaAfiliado(DniNoVacio,NombreNoVacio,ApellidoNoVacio,txtDni.getText(),txtNombre.getText(),txtApellido.getText());
				
				Object[][] data = new Object[afiliados.size()][columnNames.length];

				int i = 0;
				for(Afiliado afi : afiliados) {
					data[i][0] = afi.getDocumento();
					data[i][1] = afi.getNombre();
					data[i][2] = afi.getApellido();
					data[i][3] = afi.getFechaNacimiento();
					data[i][4] = afi.getFechaIntegracion();
					data[i][5] = afi.getFechaDesafiliacion();
					data[i][6] = afi.getDomicilio();
					data[i][7] = afi.getLocalidad();
					data[i][8] = afi.getTelefono();
					data[i][9] = afi.getOcupacion();
					data[i][10] = afi.getCoberturaImplante();
					data[i][11] = afi.getCoberturaProtesis();
					data[i][12] = afi.getObraSocial().getIdObraSocial();
					
					i++;
				}
				
				modelo = new DefaultTableModel(data, columnNames);
				table.setModel(modelo);
				modelo.fireTableDataChanged(); //esto creo que no es necesario en esta ocasión, pero por ahora lo conservo.
				table.clearSelection();
				btnSeleccionar.setEnabled(false);
				btnCrearTurno.setEnabled(false);
				btnCrearTurno.setForeground(btnSeleccionar.getForeground());
				comboBox.setSelectedIndex(0);
				
			}
		});
		btnBuscar.setBounds(348, 53, 89, 29);
		add(btnBuscar);
		
		JLabel lblDni = new JLabel("DNI");
		lblDni.setBounds(13, 42, 46, 14);
		add(lblDni);
		
		JLabel lblNombre = new JLabel("Nombre");
		lblNombre.setBounds(124, 42, 46, 14);
		add(lblNombre);
		
		JLabel lblApellido = new JLabel("Apellido");
		lblApellido.setBounds(236, 42, 46, 14);
		add(lblApellido);
		
		comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"", "Dos o más turnos pedidos", "Más de un turno vencido", "Con prepaga", "Sin prepaga"}));
		comboBox.setBounds(462, 53, 148, 29);
		add(comboBox);
		
		btnCrearTurno = new JButton("CREAR TURNO");
		btnCrearTurno.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String documento = String.valueOf(table.getValueAt(table.getSelectedRow(), 0));
				frame.setSize(430, 330);
				frame.setLocationRelativeTo(null);
                frame.setContentPane(new CreacionTurno(frame, documento));
                frame.validate();
			}
		});
		btnCrearTurno.setFont(new Font("Arial Black", Font.BOLD, 15));
		btnCrearTurno.setEnabled(false);
		btnCrearTurno.setBounds(326, 252, 256, 46);
		add(btnCrearTurno);
		
		JButton btnBuscarPorTurno = new JButton("BUSCAR POR TURNO");
		btnBuscarPorTurno.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				frame.setSize(707, 345);
				frame.setLocationRelativeTo(null);
                frame.setContentPane(new ConsultaTurno(frame,false,null));
                frame.validate();
			}
		});
		btnBuscarPorTurno.setBackground(new Color(0, 139, 139));
		btnBuscarPorTurno.setForeground(new Color(0, 0, 0));
		btnBuscarPorTurno.setFont(new Font("Tw Cen MT Condensed Extra Bold", Font.BOLD, 16));
		btnBuscarPorTurno.setBounds(368, 0, 242, 32);
		add(btnBuscarPorTurno);
		
		JButton btnBuscarFacturas = new JButton("BUSCAR FACTURAS");
		btnBuscarFacturas.setForeground(Color.BLACK);
		btnBuscarFacturas.setFont(new Font("Tw Cen MT Condensed Extra Bold", Font.BOLD, 16));
		btnBuscarFacturas.setBackground(new Color(46, 139, 87));
		btnBuscarFacturas.setBounds(110, 0, 242, 32);
		add(btnBuscarFacturas);
		
	}
}