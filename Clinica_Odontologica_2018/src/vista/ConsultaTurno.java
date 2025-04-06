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

import modelo.Afiliado;
import modelo.Clinica;
import modelo.Turno;

import java.awt.Image;

import javax.swing.ListSelectionModel;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import java.awt.Font;
import java.awt.Color;
import com.toedter.calendar.JDateChooser;

import dao.TurnoDAO;

import javax.swing.SwingConstants;

public class ConsultaTurno extends JPanel {
	private JTable table;
	private JTextField txtNroTurno;
	private JButton btnEliminar;
	private JButton btnSeleccionar;
	private JComboBox comboBox,comboBox_1;
	private JDateChooser dateChooser;
	private JDateChooser dateChooser_1;
	private JButton btnVolver;
	private JButton btnBuscar;
	
	String[] columnNames = {"nroTurno", "fechaTurno", "horaTurno", "medicacion", "dienteCara", "realizado",
			"tratamiento", "documento", "Nombre", "Apellido", "ObraSocial"};
	
	Object[][] data = new Object[0][0];
	
    DefaultTableModel modelo = new DefaultTableModel(data, columnNames) {
        public boolean isCellEditable(int row, int column){  
            return false;  
          }
        @Override
        public Class getColumnClass(int column)
        {
            if(column == 0 || column == 4 || column == 7 || column == 10)
                return Integer.class;
            else if(column == 1)
            	return LocalDate.class;
            else if(column == 2)
            	return LocalTime.class;
            else if(column == 5)
            	return Boolean.class;
            else
                return String.class;
        }
    };
    

	public ConsultaTurno(JFrame frame, boolean hayAfiliado, Afiliado afiliado) {
		setLayout(null);
		
		btnSeleccionar = new JButton("Seleccionar Turno");
		btnSeleccionar.setFont(new Font("Arial Black", Font.BOLD, 15));
		btnSeleccionar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			     //int dni = Integer.parseInt(String.valueOf(table.getValueAt(table.getSelectedRow(), 0)));
			     	 
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
				
			    //Afiliado afi = new Clinica().ObtenerUnAfiliado(dni);
			    if(hayAfiliado) {
			    	Turno turno = afiliado.ObtenerUnTurno(Integer.parseInt(table.getValueAt(table.getSelectedRow(), 0).toString()));
					frame.setSize(430, 550);
					frame.setLocationRelativeTo(null);
			    	frame.setContentPane(new ConsultaModificacionTurno(frame,hayAfiliado,afiliado,turno));
			    	frame.validate();
			    }
			    else {
			    	Afiliado afi = new Clinica().ObtenerUnAfiliado(Integer.parseInt(table.getValueAt(table.getSelectedRow(), 7).toString()));
			    	Turno turno = afi.ObtenerUnTurno(Integer.parseInt(table.getValueAt(table.getSelectedRow(), 0).toString()));
					frame.setSize(430, 550);
					frame.setLocationRelativeTo(null);
			    	frame.setContentPane(new ConsultaModificacionTurno(frame,hayAfiliado,afi,turno));
			    	frame.validate();
			    }
			}
		});
		btnSeleccionar.setEnabled(false);
		btnSeleccionar.setBounds(72, 248, 256, 46);
		add(btnSeleccionar);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 86, 670, 162);
		add(scrollPane);
		
		table = new JTable(data, columnNames);
	    table.getTableHeader().setReorderingAllowed(false);
		table.setFillsViewportHeight(true);
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		scrollPane.setViewportView(table);
		table.getSelectionModel().addListSelectionListener(new ListSelectionListener(){
	        public void valueChanged(ListSelectionEvent event) {
	            // COSA INTERESANTE: System.out.println(table.getValueAt(table.getSelectedRow(), 0).toString());
	        	// COSA INTERESANTE X2: if(!table.getSelectionModel().isSelectionEmpty())
				btnSeleccionar.setEnabled(true);
				btnEliminar.setEnabled(true);
				btnEliminar.setForeground(new Color(128, 0, 0));
	        }
	    });
		
		txtNroTurno = new JTextField();
		txtNroTurno.setBounds(10, 55, 86, 27);
		add(txtNroTurno);
		txtNroTurno.setColumns(10);
		
		btnVolver = new JButton("Volver");
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
		
		btnBuscar = new JButton("Buscar");
		btnBuscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				boolean NroTurnoNoVacio = !(txtNroTurno.getText().isEmpty()), DesdeNoVacio = !(dateChooser.getDate()==null),
						HastaNoVacio = !(dateChooser_1.getDate()==null),comboBoxNoVacio = false,comboBox_1NoVacio = false;
				if(comboBox.getSelectedIndex()>0) {comboBoxNoVacio = true;}
				if(comboBox_1.getSelectedIndex()>0) {comboBox_1NoVacio = true;}
				
			    LocalDate localDate = null;
			    LocalDate localDate_1 = null;
			    
			    if(dateChooser.getDate() != null)
			    	localDate = dateChooser.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
			    if(dateChooser_1.getDate() != null)
			    	localDate_1 = dateChooser_1.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
			    
				if(hayAfiliado) {
					
					ArrayList<Turno> turnos = afiliado.FiltrarTurnosss(NroTurnoNoVacio,DesdeNoVacio,HastaNoVacio,comboBoxNoVacio,comboBox_1NoVacio,txtNroTurno.getText(),localDate,localDate_1,comboBox.getSelectedIndex(),comboBox_1.getSelectedIndex());
					Object[][] data = new Object[turnos.size()][columnNames.length];

					int i = 0;
					for(Turno turno : turnos) {
					
						data[i][0] = turno.getNroTurno();
						data[i][1] = turno.getFechaTurno();
						data[i][2] = turno.getHoraTurno();
						data[i][3] = turno.getMedicacion();
						if(turno.getDienteCara()==0) {data[i][4] = "";}else {
							data[i][4] = turno.getDienteCara();}
						data[i][5] = turno.isRealizado();
						data[i][6] = turno.getTratamiento().getNombreTratamiento();
						data[i][7] = turno.getDocumento();
						data[i][8] = afiliado.getNombre();
						data[i][9] = afiliado.getApellido();
						data[i][10] = afiliado.getObraSocial().getNombreObra();
					
						i++;
					}
					modelo = new DefaultTableModel(data, columnNames) {
				        public boolean isCellEditable(int row, int column){  
				            return false;  
				          }
				        @Override
				        public Class getColumnClass(int column)
				        {
				            if(column == 0 || column == 4 || column == 7 || column == 10)
				                return Integer.class;
				            else if(column == 1)
				            	return LocalDate.class;
				            else if(column == 2)
				            	return LocalTime.class;
				            else if(column == 5)
				            	return Boolean.class;
				            else
				                return String.class;
				        }
				    };
				}
				else {
					ArrayList<Turno> turnos = new Clinica().FiltrarConsultaTurno(NroTurnoNoVacio,DesdeNoVacio,HastaNoVacio,comboBoxNoVacio,comboBox_1NoVacio,txtNroTurno.getText(),localDate,localDate_1,comboBox.getSelectedIndex(),comboBox_1.getSelectedIndex());
					
					Object[][] data = new Object[turnos.size()][columnNames.length];

					int i = 0;
					for(Turno turno : turnos) {
					
						Afiliado afi = new Clinica().ObtenerUnAfiliado(turno.getDocumento());
						
						data[i][0] = turno.getNroTurno();
						data[i][1] = turno.getFechaTurno();
						data[i][2] = turno.getHoraTurno();
						data[i][3] = turno.getMedicacion();
						if(turno.getDienteCara()==0) {data[i][4] = "";}else {
							data[i][4] = turno.getDienteCara();}
						data[i][5] = turno.isRealizado();
						data[i][6] = turno.getTratamiento().getNombreTratamiento();
						data[i][7] = turno.getDocumento();
						data[i][8] = afi.getNombre();
						data[i][9] = afi.getApellido();
						data[i][10] = afi.getObraSocial().getNombreObra();
					
						i++;
					}
					modelo = new DefaultTableModel(data, columnNames) {
				        public boolean isCellEditable(int row, int column){  
				            return false;  
				          }
				        @Override
				        public Class getColumnClass(int column)
				        {
				            if(column == 0 || column == 4 || column == 7 || column == 10)
				                return Integer.class;
				            else if(column == 1)
				            	return LocalDate.class;
				            else if(column == 2)
				            	return LocalTime.class;
				            else if(column == 5)
				            	return Boolean.class;
				            else
				                return String.class;
				        }
				    };
				}
				
				table.setModel(modelo);
				modelo.fireTableDataChanged(); //esto creo que no es necesario en esta ocasión, pero por ahora lo conservo.
				table.clearSelection();
				TableRowSorter<TableModel> rowSorter = new TableRowSorter<TableModel>(modelo);
				rowSorter.toggleSortOrder(1);
				rowSorter.toggleSortOrder(1);
				table.setRowSorter(rowSorter);
				btnSeleccionar.setEnabled(false);
				btnEliminar.setEnabled(false);
				btnEliminar.setForeground(btnSeleccionar.getForeground());
				
			}
		});
		btnBuscar.setBounds(591, 55, 89, 27);
		add(btnBuscar);
		
		JLabel lblNroTurno = new JLabel("nroTurno");
		lblNroTurno.setBounds(10, 42, 79, 14);
		add(lblNroTurno);
		
		JLabel lblDesde = new JLabel("Desde");
		lblDesde.setBounds(110, 41, 46, 14);
		add(lblDesde);
		
		JLabel lbHasta = new JLabel("Hasta");
		lbHasta.setBounds(255, 41, 46, 14);
		add(lbHasta);
		
		btnEliminar = new JButton("ELIMINAR TURNO");
		btnEliminar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(!hayAfiliado) {
					Afiliado afi = new Clinica().ObtenerUnAfiliado(Integer.parseInt(table.getValueAt(table.getSelectedRow(), 7).toString()));
					if(afi.ObtenerUnTurno(Integer.parseInt(table.getValueAt(table.getSelectedRow(), 0).toString())).Vigente()) {
						new TurnoDAO().Cancelar(Integer.parseInt(table.getValueAt(table.getSelectedRow(), 0).toString()));
						btnBuscar.doClick();
					}
					else
						JOptionPane.showMessageDialog(null, "Este turno no es vigente. Solo puede cancelar turnos vigentes.", "Error", JOptionPane.ERROR_MESSAGE);
				}
				else {
					if(afiliado.ObtenerUnTurno(Integer.parseInt(table.getValueAt(table.getSelectedRow(), 0).toString())).Vigente()) {
						new TurnoDAO().Cancelar(Integer.parseInt(table.getValueAt(table.getSelectedRow(), 0).toString()));
						btnBuscar.doClick();
					}
					else
						JOptionPane.showMessageDialog(null, "Este turno no es vigente. Solo puede cancelar turnos vigentes.", "Error", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		btnEliminar.setFont(new Font("Arial Black", Font.BOLD, 15));
		btnEliminar.setEnabled(false);
		btnEliminar.setBounds(356, 248, 256, 46);
		add(btnEliminar);
		
		dateChooser = new JDateChooser();
		dateChooser.setBounds(108, 55, 128, 27);
		add(dateChooser);
		
		dateChooser_1 = new JDateChooser();
		dateChooser_1.setBounds(250, 55, 128, 27);
		add(dateChooser_1);
		
		comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"", "Realizado", "No Realizado"}));
		comboBox.setBounds(393, 55, 87, 27);
		add(comboBox);
		
		JLabel lblBuscandoPor = new JLabel("Buscando por Afiliado");
		lblBuscandoPor.setForeground(new Color(0, 0, 0));
		if(hayAfiliado==false) {lblBuscandoPor.setText("Buscando por Turno");}
		lblBuscandoPor.setFont(new Font("Leelawadee UI", Font.BOLD, 17));
		lblBuscandoPor.setHorizontalAlignment(SwingConstants.CENTER);
		lblBuscandoPor.setBounds(231, 1, 192, 19);
		add(lblBuscandoPor);
		
		comboBox_1 = new JComboBox();
		comboBox_1.setModel(new DefaultComboBoxModel(new String[] {"", "Vigente", "No Vigente"}));
		comboBox_1.setBounds(492, 55, 87, 27);
		add(comboBox_1);
		
		JLabel lblRealizado = new JLabel("¿Realizado?");
		lblRealizado.setBounds(393, 41, 87, 14);
		add(lblRealizado);
		
		JLabel lblVigente = new JLabel("¿Vigente?");
		lblVigente.setBounds(493, 41, 86, 14);
		add(lblVigente);
		
		btnBuscar.doClick();
	}
}