package vista;

import javax.swing.JFrame;
import javax.swing.JPanel;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.SystemColor;

public class MenuPrincipal extends JPanel {

	public MenuPrincipal(JFrame frame) {
		setLayout(null);
		
		JButton btnAfiliados = new JButton("Afiliados");
		btnAfiliados.setBackground(new Color(0, 153, 255));
		btnAfiliados.setForeground(SystemColor.control);
		btnAfiliados.setFont(new Font("Tahoma", Font.BOLD, 18));
		btnAfiliados.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.setSize(635, 345);
				frame.setLocationRelativeTo(null);
                frame.setContentPane(new MenuAfiliado(frame));
                frame.validate();
			}
		});
		btnAfiliados.setBounds(115, 90, 219, 42);
		add(btnAfiliados);
		
		JButton btnTurnos = new JButton("Turnos y Facturas");
		btnTurnos.setBackground(new Color(0, 153, 255));
		btnTurnos.setForeground(SystemColor.control);
		btnTurnos.setFont(new Font("Tahoma", Font.BOLD, 18));
		btnTurnos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.setSize(635, 345);
				frame.setLocationRelativeTo(null);
                frame.setContentPane(new MenuTurno(frame));
                frame.validate();
			}
		});
		btnTurnos.setBounds(115, 143, 219, 42);
		add(btnTurnos);
		
		JLabel lblClinica = new JLabel("Clínica Odontólogo el Señor Atila");
		lblClinica.setFont(new Font("Script MT Bold", Font.BOLD, 22));
		lblClinica.setHorizontalAlignment(SwingConstants.CENTER);
		lblClinica.setBounds(0, 11, 450, 42);
		add(lblClinica);
	}

}