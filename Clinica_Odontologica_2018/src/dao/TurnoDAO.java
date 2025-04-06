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

import modelo.Turno;
import dao.TratamientoDAO;

public class TurnoDAO {

	String driver = "com.mysql.cj.jdbc.Driver";
	ConfigDB conf = new ConfigDB();
	String url = "jdbc:mysql://" + conf.getHost() + ":" + conf.getPort() + "/" + conf.getName();
	String user = conf.getUser();
	String password = conf.getPassword();
	
	public void Guardar(Turno turno)
	{	
		Connection conn = null;
		
		try {
			Class.forName (driver);
			conn= DriverManager.getConnection(url, user, password);
			
			String sql="INSERT INTO turno (fechaTurno, horaTurno, realizado, idTratamiento, documento) VALUES ((?),(?),(?),(?),(?))";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setDate(1, Date.valueOf(turno.getFechaTurno()));
			pstmt.setTime(2, Time.valueOf(turno.getHoraTurno()));
			pstmt.setBoolean(3, turno.isRealizado());
			pstmt.setInt(4, turno.getTratamiento().getIdTratamiento());
			pstmt.setInt(5,turno.getDocumento());
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
	
	public ArrayList<Turno> ObtenerTodo(int documento){	
		
		ArrayList<Turno> turnos = new ArrayList<Turno>();
		Connection conn = null;

		try {		

			Class.forName (driver);
			conn= DriverManager.getConnection(url, user, password);
			String sql = "SELECT * FROM turno WHERE documento = " + documento;
			java.sql.Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);

			while(rs.next()) {
				Turno turno = new Turno(rs.getInt("nroTurno"),rs.getDate("fechaTurno").toLocalDate(),rs.getTime("horaTurno").toLocalTime(),rs.getString("medicacion"), 
				rs.getInt("dienteCara"),rs.getBoolean("realizado"),new TratamientoDAO().Consulta(rs.getInt("idTratamiento")),rs.getInt("documento"));
				turno.llenar_facturas();
				turnos.add(turno);
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
		return turnos;
	}
	
	public void Actualizar(Turno tur) {
		
			Connection conn = null;
			
			try {
				Class.forName (driver);
				conn= DriverManager.getConnection(url, user, password);
				
				String sql="UPDATE turno SET fechaTurno=(?), horaTurno=(?), medicacion=(?), dienteCara=(?), realizado=(?), idTratamiento=(?) WHERE NroTurno = (?)";
				PreparedStatement pstmt = conn.prepareStatement(sql);
				pstmt.setDate(1,Date.valueOf(tur.getFechaTurno()));
				pstmt.setTime(2,Time.valueOf(tur.getHoraTurno()));
				pstmt.setString(3,tur.getMedicacion());
				if(tur.getDienteCara() == 0) {pstmt.setNull(4, java.sql.Types.INTEGER);} else
				pstmt.setInt(4,tur.getDienteCara());
				pstmt.setBoolean(5,tur.isRealizado());
				pstmt.setInt(6,tur.getTratamiento().getIdTratamiento());
				pstmt.setInt(7,tur.getNroTurno());
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
	
	
	public void Cancelar(int nroTurno)
	{	
		
		Connection conn = null;
		
		try {
			Class.forName (driver);
			conn= DriverManager.getConnection(url, user, password);
			
			String sql="DELETE FROM turno WHERE nroTurno = (?)";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, nroTurno);
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