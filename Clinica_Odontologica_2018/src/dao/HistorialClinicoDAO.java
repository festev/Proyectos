package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import modelo.Afiliado;
import modelo.HistorialClinico;

public class HistorialClinicoDAO {
	String driver = "com.mysql.cj.jdbc.Driver";
	ConfigDB conf = new ConfigDB();
	
	String url = "jdbc:mysql://" + conf.getHost() + ":" + conf.getPort() + "/" + conf.getName();
	String user = conf.getUser();
	String password = conf.getPassword();

	//String url = "jdbc:mysql://localhost:3306/clinicaodontologica";
	
	public void Guardar(HistorialClinico hc)
	{	
		Connection conn = null;
		
		try {
			Class.forName (driver);
			conn= DriverManager.getConnection(url, user, password);
			
			String sql="INSERT INTO historialclinico (documento, enferCardiovascular, marcapasos, problePresion, probleGastrico, probleRenal, asma, diabetes, tratOnco, dieta, alergiaMedicamento, enfInfecto, transSanguinea, probleCoagulacion, embarazo, opeReciente, fiebreReumatica, mediTratMedico, convulDesmayos, observaciones) VALUES ((?),(?),(?),(?),(?),(?),(?),(?),(?),(?),(?),(?),(?),(?),(?),(?),(?),(?),(?),(?))";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, hc.getDocumento());
			pstmt.setBoolean(2, hc.isEnferCardiovascular());
			pstmt.setBoolean(3, hc.isMarcapasos());
			pstmt.setBoolean(4, hc.isProblePresion());
			pstmt.setBoolean(5, hc.isProbleGastrico());
			pstmt.setBoolean(6, hc.isProbleRenal());
			pstmt.setBoolean(7, hc.isAsma());
			pstmt.setBoolean(8, hc.isDiabetes());
			pstmt.setBoolean(9, hc.isTratOnco());
			pstmt.setBoolean(10, hc.isDieta());
			pstmt.setBoolean(11, hc.isAlergiaMedicamento());
			pstmt.setBoolean(12, hc.isEnfInfecto());
			pstmt.setBoolean(13, hc.isTransSanguinea());
			pstmt.setBoolean(14, hc.isProbleCoagulacion());
			pstmt.setBoolean(15, hc.isEmbarazo());
			pstmt.setBoolean(16, hc.isOpeReciente());
			pstmt.setBoolean(17, hc.isFiebreReumatica());
			pstmt.setBoolean(18, hc.isMediTratMedico());
			pstmt.setBoolean(19, hc.isConvulDesmayos());
			pstmt.setString(20, hc.getObservaciones());

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
	
public void Actualizar(HistorialClinico histo) {
		
		String driver = "com.mysql.cj.jdbc.Driver";
		String url = "jdbc:mysql://" + conf.getHost() + ":" + conf.getPort() + "/" + conf.getName();
		
			Connection conn = null;
			
			try {
				Class.forName (driver);
				conn= DriverManager.getConnection(url, user, password);
				
				String sql="UPDATE historialclinico SET EnferCardiovascular=(?), Marcapasos=(?), ProblePresion=(?), ProbleGastrico=(?), ProbleRenal=(?), Asma=(?), Diabetes= (?), TratOnco=(?), Dieta=(?), AlergiaMedicamento=(?), EnfInfecto=(?), TransSanguinea=(?), ProbleCoagulacion=(?), Embarazo=(?), OpeReciente=(?), FiebreReumatica=(?), MediTratMedico=(?), ConvulDesmayos=(?), Observaciones=(?) WHERE documento = (?)";
				PreparedStatement pstmt = conn.prepareStatement(sql);
				pstmt.setBoolean(1, histo.isEnferCardiovascular());
				pstmt.setBoolean(2, histo.isMarcapasos());
				pstmt.setBoolean(3, histo.isProblePresion());
				pstmt.setBoolean(4, histo.isProbleGastrico());
				pstmt.setBoolean(5, histo.isProbleRenal());
				pstmt.setBoolean(6, histo.isAsma());
				pstmt.setBoolean(7, histo.isDiabetes());
				pstmt.setBoolean(8, histo.isTratOnco());
				pstmt.setBoolean(9, histo.isDieta());
				pstmt.setBoolean(10, histo.isAlergiaMedicamento());
				pstmt.setBoolean(11, histo.isEnfInfecto());
				pstmt.setBoolean(12, histo.isTransSanguinea());
				pstmt.setBoolean(13, histo.isProbleCoagulacion());
				pstmt.setBoolean(14, histo.isEmbarazo());
				pstmt.setBoolean(15, histo.isOpeReciente());
				pstmt.setBoolean(16, histo.isFiebreReumatica());
				pstmt.setBoolean(17, histo.isMediTratMedico());
				pstmt.setBoolean(18, histo.isConvulDesmayos());
				pstmt.setString(19, histo.getObservaciones());
				pstmt.setInt(20, histo.getDocumento());
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
	
	public HistorialClinico Consulta(int documento) {
		
		Connection conn = null;
		
		try {
			Class.forName (driver);
			conn= DriverManager.getConnection(url, user, password);
			String sql = "Select * from historialclinico Where documento = " + Integer.toString(documento);
			java.sql.Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			
			rs.next();
			return(new HistorialClinico(rs.getInt("documento"),rs.getString("observaciones"),rs.getBoolean("enferCardiovascular"),
			rs.getBoolean("marcapasos"),rs.getBoolean("problePresion"),rs.getBoolean("probleGastrico"),rs.getBoolean("probleRenal"),
			rs.getBoolean("asma"),rs.getBoolean("diabetes"),rs.getBoolean("tratOnco"),rs.getBoolean("dieta"),
			rs.getBoolean("alergiaMedicamento"),rs.getBoolean("enfInfecto"),rs.getBoolean("transSanguinea"),rs.getBoolean("probleCoagulacion"),
			rs.getBoolean("embarazo"),rs.getBoolean("opeReciente"),rs.getBoolean("fiebreReumatica"),rs.getBoolean("mediTratMedico"),
			rs.getBoolean("convulDesmayos")));
			
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
		return null;
	}
	
}