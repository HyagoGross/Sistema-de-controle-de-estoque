package controleEstoque;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import moduloConexao.moduloConexao;
public class Estoque {

	public List<Produto> Produtos = new ArrayList<Produto>(); 

	moduloConexao  objconexao = new moduloConexao();
	
	public void estoqueInicializar(boolean novo) throws SQLException {
		//Puxar todas as informações do banco para criar oque ja foi armazenado no inicio do programa

		String sql = "SELECT * FROM controleestoque.produto;";	
		ResultSet rs = moduloConexao.getResultset(sql); 
		Produtos.clear();
		while(rs.next()) {
			Produto produto = new Produto(rs.getInt(1),rs.getString(2),rs.getFloat(3), rs.getInt(4), novo); 
			Produtos.add(produto); 
		} 
	} 
	public String exibirEstoque() {
		String exibir = "";  
		for(int i= 0; i < Produtos.size(); i++) {
			exibir += Produtos.get(i).toString();  
		} 
		return exibir;
	}
}
