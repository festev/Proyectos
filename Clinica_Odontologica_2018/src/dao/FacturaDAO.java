package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Time;
import java.util.ArrayList;

import modelo.Factura;

public class FacturaDAO {
	
	String driver = "com.mysql.cj.jdbc.Driver";
	ConfigDB conf = new ConfigDB();
	String url = "jdbc:mysql://" + conf.getHost() + ":" + conf.getPort() + "/" + conf.getName();
	String user = conf.getUser();
	String password = conf.getPassword();
	
	public void Guardar(Factura factura)
	{	
		Connection conn = null;
		
		try {
			Class.forName (driver);
			conn= DriverManager.getConnection(url, user, password);
			
			String sql="INSERT INTO factura (nroFactura, fechaCreacion, horaCreacion, totalPagar, nroTurno) VALUES ((?),(?),(?),(?),(?))";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, factura.getNroFactura());
			pstmt.setDate(2, Date.valueOf(factura.getFechaCreacion()));
			pstmt.setTime(3, Time.valueOf(factura.getHoraCreacion()));
			pstmt.setInt(4, factura.getTotalPagar());
			pstmt.setInt(5, factura.getNroTurno());
			pstmt.executeUpdate();
			
		}catch (ClassNotFoundException e) {
			e.printStackTrace();
		}catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if(conn != null)
					conn.close();
			}catch (SQLException e) {
			e.printStackTrace();
			}
		}
		
	}

	public ArrayList<Factura> ObtenerTodo(int nroTurno){	
		
		ArrayList<Factura> facturas = new ArrayList<Factura>();
		Connection conn = null;

		try {		

			Class.forName (driver);
			conn= DriverManager.getConnection(url, user, password);
			String sql = "SELECT * FROM factura WHERE nroTurno = " + Integer.toString(nroTurno);
			java.sql.Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);

			while(rs.next()) {
				facturas.add(new Factura(rs.getInt("nroFactura"),rs.getDate("fechaCreacion").toLocalDate(),rs.getTime("horaCreacion").toLocalTime(),
						rs.getInt("totalPagar"),rs.getInt("nroTurno")));
			}
			
		}catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if(conn != null)
					conn.close(); 
			}
			catch(SQLException e) {	
				e.printStackTrace();
			}
		}
		return facturas;
	}
	
}