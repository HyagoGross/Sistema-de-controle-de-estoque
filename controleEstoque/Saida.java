package controleEstoque;

import java.sql.Date;
import moduloConexao.moduloConexao;

public class Saida {

	private int id;
	private Date data;
	private int quantidade;
	private float valorDeVenda;
	private int idProduto;

	public Saida(int id, Date data, int quantidade, float valorDeVenda, int idProduto, boolean novo) {
		this.idProduto = idProduto;
		this.id = id;
		this.data = data;
		this.quantidade = quantidade;
		this.valorDeVenda = valorDeVenda;
		if(novo) {
			create();
		}
	}
	public void create() {
		//INSERT INTO `controleestoque`.`saida` (`idsaida`, `data`, `quantidade`, `valorDeVenda`, `produto_idproduto`) VALUES ('0', '2022-11-01', '5', '10', '1');
		if(id!=0) {
		moduloConexao.updateSql(String.format("INSERT INTO `controleestoque`.`saida` (`idsaida`, `data`, `quantidade`, `valorDeVenda`, `produto_idproduto`) VALUES ('%d', '%s', '%d', '%.2f', '%d');", id, data, quantidade, valorDeVenda, idProduto));
		}else {
			moduloConexao.updateSql(String.format("INSERT INTO `controleestoque`.`saida` ( `data`, `quantidade`, `valorDeVenda`, `produto_idproduto`) VALUES ('%s', '%d', '%.2f', '%d');",data, quantidade, valorDeVenda, idProduto)); 

		}
	} 

}
