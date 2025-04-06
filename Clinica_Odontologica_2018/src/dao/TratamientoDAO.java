package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

import modelo.Tratamiento;

public class TratamientoDAO {
	String driver = "com.mysql.cj.jdbc.Driver";
	ConfigDB conf = new ConfigDB();
	String url = "jdbc:mysql://" + conf.getHost() + ":" + conf.getPort() + "/" + conf.getName();
	String user = conf.getUser();
	String password = conf.getPassword();
	
	public Tratamiento Consulta(int idTratamiento){	
		
		Connection conn = null;

		try {		

			Class.forName (driver);
			conn= DriverManager.getConnection(url, user, password);
			String sql = "SELECT * FROM tratamiento WHERE idTratamiento = " + idTratamiento;
			java.sql.Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);

			rs.next();
			return new Tratamiento(rs.getInt("idTratamiento"),rs.getString("nombreTratamiento"),rs.getInt("precio"));
			
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
