package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

import modelo.OSDE210;
import modelo.OSDE310;
import modelo.OSDE410;
import modelo.OSDE450;
import modelo.OSDE510;
import modelo.ObraSocial;
import modelo.SinObra;

public class ObraSocialDAO {
	String driver = "com.mysql.cj.jdbc.Driver";
	ConfigDB conf = new ConfigDB();
	String url = "jdbc:mysql://" + conf.getHost() + ":" + conf.getPort() + "/" + conf.getName();
	String user = conf.getUser();
	String password = conf.getPassword();
	
	public ObraSocial Consulta(int idObraSocial){	
		
		Connection conn = null;
		System.out.println(idObraSocial);

		try {		

			Class.forName (driver);
			conn= DriverManager.getConnection(url, user, password);
			String sql = "SELECT * FROM obrasocial WHERE idObraSocial = " + idObraSocial;
			java.sql.Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);

			rs.next();
			if(idObraSocial==1) {
				ObraSocial obraSocial = new SinObra(rs.getInt("idObraSocial"),rs.getString("nombreObra"),rs.getInt("coberturaImplanteMax"),rs.getInt("coberturaProtesisMax"));
				return obraSocial;
			}
			if(idObraSocial==2) {
				ObraSocial obraSocial = new OSDE210(rs.getInt("idObraSocial"),rs.getString("nombreObra"),rs.getInt("coberturaImplanteMax"),rs.getInt("coberturaProtesisMax"));
				return obraSocial;
			}
			if(idObraSocial==3) {
				ObraSocial obraSocial = new OSDE310(rs.getInt("idObraSocial"),rs.getString("nombreObra"),rs.getInt("coberturaImplanteMax"),rs.getInt("coberturaProtesisMax"));
				return obraSocial;
			}
			if(idObraSocial==4) {
				ObraSocial obraSocial = new OSDE410(rs.getInt("idObraSocial"),rs.getString("nombreObra"),rs.getInt("coberturaImplanteMax"),rs.getInt("coberturaProtesisMax"));
				return obraSocial;
			}
			if(idObraSocial==5) {
				ObraSocial obraSocial = new OSDE450(rs.getInt("idObraSocial"),rs.getString("nombreObra"),rs.getInt("coberturaImplanteMax"),rs.getInt("coberturaProtesisMax"));
				return obraSocial;
			}
			if(idObraSocial==6) {
				ObraSocial obraSocial = new OSDE510(rs.getInt("idObraSocial"),rs.getString("nombreObra"),rs.getInt("coberturaImplanteMax"),rs.getInt("coberturaProtesisMax"));
				return obraSocial;
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
		return null;
	}
	
}
