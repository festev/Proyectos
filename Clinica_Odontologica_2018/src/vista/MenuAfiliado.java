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
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JToggleButton;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

import dao.AfiliadoDAO;
import modelo.Afiliado;
import modelo.Clinica;

import java.awt.Image;

import javax.swing.ListSelectionModel;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import java.awt.Font;
import java.awt.GraphicsEnvironment;
import java.awt.Color;

public class MenuAfiliado extends JPanel {
	
	private JTextField txtDni,txtNombre,txtApellido;
	private JButton btnDesafiliar,btnSeleccionar,btnVolver,btnBuscar,btnAltaAfiliado;
	private JLabel lblDni,lblNombre,lblApellido;
	private JComboBox comboBox;
	private JTable table;
	private JScrollPane scrollPane;
	
	String[] columnNames = {"documento", "nombre", "apellido", "fechaNacimiento", "fechaIntegracion", "fechaDesafiliacion",
			"domicilio", "localidad", "telefono", "ocupacion", "coberturaImplante", "coberturaProtesis", "ObraSocial"};
	
	Object[][] data = new Object[0][0];
	
    DefaultTableModel modelo = new DefaultTableModel(data, columnNames);

	public MenuAfiliado(JFrame frame) {
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
			    //LO DE ABAJO ES PARA CONSEGUIR LA PANTALLA DEL MONITOR
				//Double y = GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds().getHeight();
				//frame.setBounds(frame.getX(), y.intValue()/8, 465, 700);
			    frame.setSize(465, 700);
				frame.setLocationRelativeTo(null);
                frame.setContentPane(new ConsultaModificacionAfiliado(frame,afi));
                frame.validate();
			}
		});
		btnSeleccionar.setEnabled(false);
		btnSeleccionar.setBounds(10, 252, 256, 46);
		add(btnSeleccionar);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 86, 600, 162);
		add(scrollPane);
		
		table = new JTable(data, columnNames);
		table.setFillsViewportHeight(true);
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		scrollPane.setViewportView(table);
		table.getSelectionModel().addListSelectionListener(new ListSelectionListener(){
	        public void valueChanged(ListSelectionEvent event) {
	            // COSA INTERESANTE: System.out.println(table.getValueAt(table.getSelectedRow(), 0).toString());
	        	// COSA INTERESANTE X2: if(!table.getSelectionModel().isSelectionEmpty())
				btnSeleccionar.setEnabled(true);
				btnDesafiliar.setEnabled(true);
				btnDesafiliar.setForeground(new Color(128, 0, 0));
				
	        }
	    });
		
		txtDni = new JTextField();
		txtDni.setBounds(10, 55, 86, 28);
		add(txtDni);
		txtDni.setColumns(10);
		
		txtNombre = new JTextField();
		txtNombre.setColumns(10);
		txtNombre.setBounds(122, 55, 86, 28);
		add(txtNombre);
		
		txtApellido = new JTextField();
		txtApellido.setColumns(10);
		txtApellido.setBounds(240, 55, 86, 28);
		add(txtApellido);
		
		btnVolver = new JButton("Volver");
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
		
		btnBuscar = new JButton("Buscar");
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
					data[i][12] = afi.getObraSocial().getNombreObra();
					
					i++;
				}
				
				modelo = new DefaultTableModel(data, columnNames);
				table.setModel(modelo);
				modelo.fireTableDataChanged(); //esto creo que no es necesario en esta ocasión, pero por ahora lo conservo.
				table.clearSelection();
				TableRowSorter<TableModel> rowSorter = new TableRowSorter<TableModel>(modelo);
				rowSorter.toggleSortOrder(1);
				table.setRowSorter(rowSorter);
				btnSeleccionar.setEnabled(false);
				btnDesafiliar.setForeground(btnSeleccionar.getForeground());
				btnDesafiliar.setEnabled(false);
				comboBox.setSelectedIndex(0);
				
			}
		});
		btnBuscar.setBounds(349, 54, 89, 31);
		add(btnBuscar);
		
		lblDni = new JLabel("DNI");
		lblDni.setBounds(12, 42, 46, 14);
		add(lblDni);
		
		lblNombre = new JLabel("Nombre");
		lblNombre.setBounds(124, 42, 46, 14);
		add(lblNombre);
		
		lblApellido = new JLabel("Apellido");
		lblApellido.setBounds(242, 42, 46, 14);
		add(lblApellido);
		
		comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"", "Dos o más turnos pedidos", "Más de un turno vencido", "Con prepaga", "Sin prepaga"}));
		comboBox.setBounds(462, 58, 148, 23);
		add(comboBox);
		
		btnAltaAfiliado = new JButton("");
		btnAltaAfiliado.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			    frame.setSize(465, 680);
				frame.setLocationRelativeTo(null);
                frame.setContentPane(new AltaAfiliado(frame));
                frame.validate();
			}
		});
		Image img = new ImageIcon(this.getClass().getResource("/AgregarPacienteIcono.png")).getImage();
		btnAltaAfiliado.setIcon(new ImageIcon(img));
		btnAltaAfiliado.setBounds(557, 240, 53, 58);
		add(btnAltaAfiliado);
		
		btnDesafiliar = new JButton("DESAFILIAR");
		btnDesafiliar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		btnDesafiliar.setFont(new Font("Arial Black", Font.BOLD, 15));
		btnDesafiliar.setEnabled(false);
		btnDesafiliar.setBounds(285, 252, 256, 46);
		add(btnDesafiliar);
		
	}
}