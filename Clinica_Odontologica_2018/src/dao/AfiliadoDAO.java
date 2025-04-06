package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

import modelo.Afiliado;
import dao.HistorialClinicoDAO;
import dao.ObraSocialDAO;

public class AfiliadoDAO {
	String driver = "com.mysql.cj.jdbc.Driver";
	ConfigDB conf = new ConfigDB();
	String url = "jdbc:mysql://" + conf.getHost() + ":" + conf.getPort() + "/" + conf.getName();
	String user = conf.getUser();
	String password = conf.getPassword();
	
	public void Guardar(Afiliado afi)
	{	
	
		Connection conn = null;
		
		try {
			Class.forName (driver);
			conn= DriverManager.getConnection(url, user, password);
			
			String sql="INSERT INTO afiliado (documento, nombre, apellido, fechaNacimiento, fechaIntegracion, fechaDesafiliacion, domicilio, localidad, telefono, ocupacion, coberturaImplante, coberturaProtesis, idObraSocial) VALUES ((?),(?),(?),(?),(?),(?),(?),(?),(?),(?),(?),(?),(?))";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, afi.getDocumento());
			pstmt.setString(2, afi.getNombre());
			pstmt.setString(3, afi.getApellido());
			pstmt.setDate(4, Date.valueOf(afi.getFechaNacimiento()));
			pstmt.setDate(5, Date.valueOf(afi.getFechaIntegracion()));
			pstmt.setNull(6, java.sql.Types.DATE);
			pstmt.setString(7, afi.getDomicilio());
			pstmt.setString(8, afi.getLocalidad());
			pstmt.setInt(9, afi.getTelefono());
			pstmt.setString(10, afi.getOcupacion());
			pstmt.setInt(11, afi.getCoberturaImplante());
			pstmt.setInt(12, afi.getCoberturaProtesis());
			pstmt.setInt(13, afi.getObraSocial().getIdObraSocial());
			pstmt.executeUpdate();
			
			new HistorialClinicoDAO().Guardar(afi.getHistorial());
			
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
	
	public void Actualizar(Afiliado afi) {
			
			Connection conn = null;
			
			try {
				Class.forName (driver);
				conn= DriverManager.getConnection(url, user, password);
				
				String sql="UPDATE afiliado SET nombre=(?), apellido=(?), fechaNacimiento=(?), domicilio=(?), localidad=(?), telefono=(?), ocupacion=(?), coberturaImplante=(?), coberturaProtesis=(?), idObraSocial=(?) WHERE documento = (?)";
				PreparedStatement pstmt = conn.prepareStatement(sql);
				pstmt.setString(1,afi.getNombre());
				pstmt.setString(2,afi.getApellido());
				pstmt.setDate(3,Date.valueOf(afi.getFechaNacimiento()));
				pstmt.setString(4,afi.getDomicilio());
				pstmt.setString(5,afi.getLocalidad());
				pstmt.setInt(6,afi.getTelefono());
				pstmt.setString(7,afi.getOcupacion());
				pstmt.setInt(8,afi.getCoberturaImplante());
				pstmt.setInt(9,afi.getCoberturaProtesis());
				pstmt.setInt(10,afi.getObraSocial().getIdObraSocial());
				pstmt.setInt(11,afi.getDocumento());
				pstmt.executeUpdate();
				
				new HistorialClinicoDAO().Actualizar(afi.getHistorial());
				
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
	
	public ArrayList<Afiliado> ObtenerTodo() {
		
		ArrayList<Afiliado> afiliados = new ArrayList<Afiliado>();
		Connection conn = null;
		
		try {
			Class.forName (driver);
			conn= DriverManager.getConnection(url, user, password);
			String sql = "Select * From afiliado as a INNER JOIN obrasocial as o ON a.idObraSocial = o.idObraSocial";
			java.sql.Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			
			while(rs.next()) {
				
				Date sqlDate = rs.getDate("fechaDesafiliacion");
				LocalDate localDate;
				if(sqlDate == null)
					localDate = null;
				else
					localDate = sqlDate.toLocalDate();
				
				Afiliado afi = new Afiliado(rs.getInt("documento"),rs.getInt("telefono"),rs.getString("nombre"),rs.getString("apellido"),
						rs.getString("domicilio"),rs.getString("localidad"),rs.getString("ocupacion"),rs.getDate("fechaNacimiento").toLocalDate(),
						rs.getDate("fechaIntegracion").toLocalDate(),localDate,rs.getInt("coberturaImplante"),
						rs.getInt("coberturaProtesis"),new HistorialClinicoDAO().Consulta(rs.getInt("documento")),new ObraSocialDAO().Consulta(rs.getInt("idObraSocial")));
				afi.llenar_turnos();
				afiliados.add(afi);
			}
			
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
		
		return afiliados;
	}
	
	public void Desafiliar(int documento)
	{	
		
		Connection conn = null;
		
		try {
			Class.forName (driver);
			conn= DriverManager.getConnection(url, user, password);
			
			String sql="UPDATE afiliado SET fechaDesafiliacion = now() WHERE documento = (?)";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, documento);
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
}