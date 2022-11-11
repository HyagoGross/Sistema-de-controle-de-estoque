package controleEstoque;

import moduloConexao.moduloConexao;
import java.sql.Date;

public class Entrada {
	private int id;
	private Date data;
	private int quantidade;
	private float precoDeCusto;
	private int idProduto;

	public Entrada(int id, Date data, int quantidade, float precoDeCusto, int idProduto, boolean novo) {
		this.idProduto = idProduto;
		this.id = id;
		this.data = data;
		this.quantidade = quantidade;
		this.precoDeCusto = precoDeCusto;
		if(novo) {
			create(); 
		}
	}
	public void create() {
		//INSERT INTO `controleestoque`.`entrada` (`identrada`, `data`, `quantidade`, `preçoDeCusto`) VALUES ('0', '2022-11-01', '5', '10');
		
		if(id!=0) {    
		moduloConexao.updateSql(String.format("INSERT INTO `controleestoque`.`entrada` (`identrada`, `data`, `quantidade`, `preçoDeCusto`, `produto_idproduto`) VALUES ('%d', '%s', '%d', '%.2f', '%d');", id, data, quantidade, precoDeCusto, idProduto));
		}else {
			moduloConexao.updateSql(String.format("INSERT INTO `controleestoque`.`entrada` ( `data`, `quantidade`, `preçoDeCusto`, `produto_idproduto`) VALUES ( '%s', '%d', '%.2f', '%d');",data, quantidade, precoDeCusto, idProduto));
		}
	} 

}         
