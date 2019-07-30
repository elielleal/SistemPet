package br.com.pet.test;

import java.sql.SQLException;
import java.util.ArrayList;

import org.junit.Ignore;
import org.junit.Test;


import br.com.pet.DAO.ProdutoDAO;
import br.com.pet.domain.Fornecedores;
import br.com.pet.domain.Produtos;

public class ProdutoDAOTest {
	@Test
	@Ignore
	public void salvar() throws SQLException {
		Produtos p1 = new Produtos();
		p1.setDescricao("Strodallfgd");
		p1.setQuantidade(10);
		p1.setPreco(50.00);

		Fornecedores f = new Fornecedores();
		f.setCodigo(1);
		p1.setFornecedores(f);
		
		

		ProdutoDAO fdao = new ProdutoDAO();

		fdao.save(p1);
	}
	@Test
	@Ignore
	public void listar()throws SQLException{
	
		ProdutoDAO fdao = new ProdutoDAO();
		ArrayList<Produtos> lista = fdao.listar();
		
		for (Produtos p : lista){
			System.out.println("C�digo do Produto: " + p.getCodigo());
			System.out.println("Descri��o do Produto: " + p.getDescricao());
			System.out.println("Quantidade: " + p.getQuantidade());
			System.out.println("Valor do Produto: " + p.getPreco());
			System.out.println("C�digo do Fornecedor: " + p.getFornecedores().getCodigo());
			System.out.println("Descri��o do Fornecedor: " + p.getFornecedores().getDescricao());
			System.out.println("");
		}
		
	}
	
	@Test
	@Ignore
	public void excluir()throws SQLException{
		Produtos p1 = new Produtos();
		p1.setCodigo(4);
		
		ProdutoDAO dao = new ProdutoDAO();
		dao.delete(p1);
	}
	
	@Test
	@Ignore
	public void editar()throws SQLException{
		Produtos p1 = new Produtos();
		p1.setCodigo(3);
		p1.setDescricao("Dipironas");
		p1.setQuantidade(3);
		p1.setPreco(10.99);
		
		Fornecedores f = new Fornecedores();
		f.setCodigo(2);
		p1.setFornecedores(f);
		
		ProdutoDAO dao = new ProdutoDAO();
		dao.update(p1);
	}
}
