package controleEstoque;

import java.sql.*;						
import java.util.Scanner;
import moduloConexao.moduloConexao;
import java.sql.Date;
import java.util.Locale;
public class Menu {
	
	private static Estoque estoque = new Estoque();
	
	public static void main(String[] args) throws SQLException {
		
		Locale.setDefault(Locale.US); 
		Connection conexao = null; 
		
		conexao = moduloConexao.conector();
		// apoio ao status de conexao	
		System.out.println(conexao); 

		
		if(conexao != null) {
			estoque.estoqueInicializar(false);
			int i=10;
		Scanner c = new Scanner(System.in);
			while(i!=0) {
				System.out.println("1-Adicionar produto no estoque");
				System.out.println("2-Venda de produto");
				System.out.println("3-exibir estoque");
				System.out.println("0-Sair");
				System.out.println("Qual opção deseja ?");
				i = c.nextInt();
				switch(i) {
				case 1:
					
					addProdutoEstoque(c);
					break;
				case 2:
					addVendaProduto(c);
					break;
					
				case 3:
					exibirEstoque(c);
					break;
				
				default:
				
					break;
				}
			}		
		}else {  
			
		}
																	 
	}
	static void addProdutoEstoque(Scanner c) {
		//passar pro usuario colocar o input de um produto novo e pedir para add esse produto que o usuario colocou la no estoque 
		int id, quantidade;
		float valor, precoCusto;
		String nome;
		
		System.out.println("Deseja inserir um produto existente, se sim digite o id, se não digite 0");
		id = c.nextInt();
		if(id == 0) {
			System.out.println("Nome do produto: ");
			nome = c.next();
			nome +=c.nextLine();                              
			System.out.println("Valor de venda: ");
			valor = c.nextFloat();
			System.out.println("Quantidade: ");
			quantidade = c.nextInt();
			System.out.println("Preço de custo: ");
			precoCusto = c.nextFloat();
			
			Produto produto = new Produto(estoque.Produtos.get(estoque.Produtos.size()-1).getId()+1,nome, valor, 0, true);
			produto.addEntrada(1, new Date(System.currentTimeMillis()), quantidade, precoCusto); 
			estoque.Produtos.add(produto);
		} else { 
			for(int i = 0; i<estoque.Produtos.size(); i++) {
				if(estoque.Produtos.get(i).getId()== id) {
					System.out.println("Quantos produtos você deseja adicionar: ");
					quantidade = c.nextInt();
					System.out.println("Preço de custo: ");
					precoCusto = c.nextFloat();
					estoque.Produtos.get(i).addEntrada(0, new Date(System.currentTimeMillis()), quantidade, precoCusto);
					estoque.Produtos.get(i).upDateSql();
					System.out.println("Alteração efetuada\n\n");
					break; 
				}else if(i == estoque.Produtos.size()-1) {
					System.out.println("Produto não encontrado");
				}
			}
		}	 				 	
				
	}
	static void addVendaProduto(Scanner c) {
		// pegar id de um produto do usuario e alterar no estoque reduzindo-o 
		boolean existe = false;
		int id, quantidade;
		System.out.println("Digite o ID do produto: ");
		id = c.nextInt();
		for(int i=0; i < estoque.Produtos.size(); i++) {
			if(estoque.Produtos.get(i).getId()==id) {
				existe = true;
				id=i;
			}
		}
		if(existe) {
			System.out.println("Quanto deseja tirar do estoque: ");
			quantidade = c.nextInt();
			System.out.println(id);
			System.out.println(estoque.Produtos.get(id).getId());
			estoque.Produtos.get(id).addSaida(estoque.Produtos.get(id).Saidas.size()+1, new Date(System.currentTimeMillis()), quantidade, estoque.Produtos.get(id).getprecoVendas());
			estoque.Produtos.get(id).upDateSql();
			System.out.println("Venda efetuada \n\n");
		} 
		else {
			System.out.println("Produto não cadastrado\n\n");
		}
	} 
	static void exibirEstoque(Scanner c) {
		// exibir a lista de todos os produtos dentro do estoque 
		
		System.out.println(estoque.exibirEstoque()); 
	}

}
