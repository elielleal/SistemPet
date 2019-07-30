package br.com.pet.bean;

import java.sql.SQLException;
import java.util.ArrayList;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.com.pet.DAO.ClientesDAO;
import br.com.pet.domain.Clientes;
import br.com.pet.util.JSFUtil;

@ManagedBean(name = "MBClientes")
@ViewScoped
public class ClientesBean {
	
	private Clientes clientes;
	private ArrayList<Clientes> itens;
	private ArrayList<Clientes> itensFiltrados;
	
	public Clientes getClientes() {
		return clientes;
	}

	public void setClientes(Clientes clientes) {
		this.clientes = clientes;
	}

	public ArrayList<Clientes> getItens() {
		return itens;
	}

	public void setItens(ArrayList<Clientes> itens) {
		this.itens = itens;
	}

	public ArrayList<Clientes> getItensFiltrados() {
		return itensFiltrados;
	}

	public void setItensFiltrados(ArrayList<Clientes> itensFiltrados) {
		this.itensFiltrados = itensFiltrados;
	}

	@PostConstruct
	public void prepararPesquisa(){
		try {
			ClientesDAO fdao = new ClientesDAO();
			
			itens = fdao.Listar();
			
		} catch (SQLException e) {
			JSFUtil.adicionarMensagemErro("e.getMessage()");
			e.printStackTrace();
		}
	}
	
	public void prepararNovo(){
		clientes = new Clientes();
	}
	
	public void novo(){
		try {
			ClientesDAO fdao = new ClientesDAO();
			fdao.save(clientes);
			
						
			itens = fdao.Listar();
			
			JSFUtil.adicionarMensagemSucesso("Cliente Salvo com Sucesso");
		} catch (Exception e) {
			JSFUtil.adicionarMensagemErro("e.getMessage()");
			e.printStackTrace();
		}
	}
	
	
	
	public void excluir(){
		try {
			ClientesDAO fdao = new ClientesDAO();
			fdao.delete(clientes);
			
						
			itens = fdao.Listar();
			
			JSFUtil.adicionarMensagemSucesso("Clientes Excluído com Sucesso");
		} catch (Exception e) {
			JSFUtil.adicionarMensagemErro("Não é possivel excluir um Clientes associado a uma Agenda()");
			e.printStackTrace();
		}
	}
	
	public void editar(){
		try {
			ClientesDAO fdao = new ClientesDAO();
			fdao.update(clientes);
			
						
			itens = fdao.Listar();
			
			JSFUtil.adicionarMensagemSucesso("Clientes Editado com Sucesso");
		} catch (Exception e) {
			JSFUtil.adicionarMensagemErro("e.getMessage()");
			e.printStackTrace();
		}
	}
}
