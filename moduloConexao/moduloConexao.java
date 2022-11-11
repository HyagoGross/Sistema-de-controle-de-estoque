package moduloConexao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class moduloConexao {

	public static Connection conector() {
		Connection conexao = null;
		// a linha abaixo "chama" o driver
		String driver = "com.mysql.jdbc.Driver";
		// armazenando informações referente ao banco
		String url = "jdbc:mysql://localhost:3306/controleestoque"; 
		String user= "root";
		String password= "Interstellar";

		try {
			Class.forName(driver);
			conexao = DriverManager.getConnection(url, user, password);
			return conexao;
		} catch (Exception e) {
			System.out.println(e);
			return null;
		}
	}
	public static ResultSet getResultset(String enviar) {
		Connection conn = moduloConexao.conector(); 
		try{	
			ResultSet rs;
			Statement st = conn.createStatement(); 
			rs = st.executeQuery(enviar);  
			return rs;
		}catch(Exception e) {
			System.out.println(e);
		}
		return null;
	}
	public static void updateSql(String enviar) {
		Connection conn = moduloConexao.conector(); 
		try{	
			
			Statement st = conn.createStatement();   
			
			st.executeLargeUpdate(enviar); 
		}catch(Exception e) {
			System.out.println(e);
		}
		
	}

}