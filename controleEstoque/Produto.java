package controleEstoque;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import moduloConexao.moduloConexao; 
import java.util.Locale;

public class Produto {
	
	
	List<Entrada> Entradas = new ArrayList<Entrada>();
	List<Saida> Saidas = new ArrayList<Saida>();

	private int id; 
	private String nome;
	private float valor;
	private int quantidade;

	public Produto(int id, String nome, float valor, int quantidade,boolean novo) {
		this.id = id;
		this.nome = nome;
		this.valor = valor;
		this.quantidade = quantidade;
		if(novo) {
			String comando = String.format("INSERT INTO `controleestoque`.`produto` (`idproduto`, `nome`, `valorProduto`, `totalEstoque`) VALUES ('%d', '%s', '%f', '%d');", id, nome, valor, quantidade);
			moduloConexao.updateSql(comando); 
		} 	
		else {
			upDateSql(); 
		}
	}

	public void addEntrada(int id, Date data, int quantidade, float precoDeCusto) {
		//mudar o valor total do estoque
		//add uma entrada
		this.quantidade += quantidade; 
		Entradas.add(new Entrada(id, data, quantidade, precoDeCusto,this.id, true));
		
	}

	public void addSaida(int id, Date data, int quantidade, float valorDeVenda) {
		this.quantidade -= quantidade;
		Saidas.add(new Saida(id, data, quantidade, valorDeVenda, this.id, true));
		
	}
	public void upDateSql() {
		//Pegar esse produto e mandar para a tabela produto
		//Quando ele for criado pela primeira vez precisa criar o item na tabela 
		//quando for atualizado ele precisa atualizar
		//INSERT INTO `controleestoque`.`produto` (`idproduto`, `nome`, `valorProduto`, `totalEstoque`) VALUES ('0', 'Doritos', '10', '5');
		//UPDATE `controleestoque`.`produto` SET `nome` = 'Dorito', `valorProduto` = '11', `totalEstoque` = '4' WHERE (`idproduto` = '1');
		
		moduloConexao.updateSql(String.format("UPDATE `controleestoque`.`produto` SET `nome` = '%s', `valorProduto` = '%.2f', `totalEstoque` = '%d' WHERE (`idproduto` = '%d');",nome, valor, quantidade, id));

	}
	public String toString() {
		
	return String.format("ID: %d\t Nome: %s\t Valor: %.2f\t Quantidade: %d\n",id, nome, valor, quantidade); 
		
	}
	public float getprecoVendas() {
		return valor;
	}
	public int getId() {
		return id;
	}
}
