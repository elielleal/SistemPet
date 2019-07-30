package br.com.pet.bean;

import java.sql.SQLException;
import java.util.ArrayList;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.com.pet.DAO.FornecedoresDAO;
import br.com.pet.DAO.ProdutoDAO;
import br.com.pet.domain.Fornecedores;
import br.com.pet.domain.Produtos;
import br.com.pet.util.JSFUtil;

@ManagedBean(name = "MBProduto")
@ViewScoped
public class ProdutosBean {
	
	private Produtos produtos;
	private ArrayList<Produtos> itens;
	private ArrayList<Produtos> itensFiltrados;
private ArrayList<Fornecedores>comboFornecedores;
	
	public ArrayList<Fornecedores> getComboFornecedores() {
		return comboFornecedores;
	}
	public void setComboFornecedores(ArrayList<Fornecedores> comboFornecedores) {
		this.comboFornecedores = comboFornecedores;
	}
	
	public Produtos getProdutos() {
		return produtos;
	}

	public void setProdutos(Produtos produtos) {
		this.produtos = produtos;
	}

	public ArrayList<Produtos> getItens() {
		return itens;
	}
	
	public void setItens(ArrayList<Produtos> itens) {
		this.itens = itens;
	}
	
	public ArrayList<Produtos> getItensFiltrados() {
		return itensFiltrados;
	}
	public void setItensFiltrados(ArrayList<Produtos> itensFiltrados) {
		this.itensFiltrados = itensFiltrados;
	}
	
	@PostConstruct
	public void prepararPesquisa(){
		try {
			ProdutoDAO fdao = new ProdutoDAO();
			
			itens = fdao.listar();
			
		} catch (SQLException e) {
			JSFUtil.adicionarMensagemErro("e.getMessage()");
			e.printStackTrace();
		}
	}
	public void prepararNovo(){
		
		try {
			produtos = new Produtos();
			
			FornecedoresDAO dao = new FornecedoresDAO();
			comboFornecedores = dao.Listar();
		} catch (SQLException e) {
			JSFUtil.adicionarMensagemErro("e.getMessage()");
			e.printStackTrace();
		}
	}
	
	public void novo(){
		try {
			ProdutoDAO fdao = new ProdutoDAO();
			fdao.save(produtos);
			
						
			itens = fdao.listar();
			
			JSFUtil.adicionarMensagemSucesso("Produto Salvo com Sucesso");
		} catch (Exception e) {
			JSFUtil.adicionarMensagemErro("e.getMessage()");
			e.printStackTrace();
		}
	}
	
	public void excluir(){
		try {
			ProdutoDAO fdao = new ProdutoDAO();
			fdao.delete(produtos);
			
						
			itens = fdao.listar();
			
			JSFUtil.adicionarMensagemSucesso("Produto Excluído com Sucesso");
		} catch (Exception e) {
			JSFUtil.adicionarMensagemErro("e.getMessage()");
			e.printStackTrace();
		}
	}
	
	public void editar(){
		try {
			ProdutoDAO fdao = new ProdutoDAO();
			fdao.update(produtos);
			
			
			itens = fdao.listar();
			
			
			JSFUtil.adicionarMensagemSucesso("Produto editado com sucesso!");
			
		} catch (SQLException e) {
			JSFUtil.adicionarMensagemErro("ex.getMessage()");
			e.printStackTrace();
		}
	}


	public void prepararEditar(){
		
		
		
		try {
			produtos = new Produtos();
			FornecedoresDAO dao = new FornecedoresDAO();
			comboFornecedores = dao.Listar();
		} catch (SQLException e) {
			JSFUtil.adicionarMensagemErro("ex.getMessage()");
			e.printStackTrace();
		}
	}

}
