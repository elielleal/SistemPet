package br.com.pet.bean;

import java.sql.SQLException;
import java.util.ArrayList;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.com.pet.DAO.AgendarDAO;
import br.com.pet.DAO.ClientesDAO;

import br.com.pet.domain.Agendar;
import br.com.pet.domain.Clientes;
import br.com.pet.domain.Fornecedores;
import br.com.pet.domain.Produtos;
import br.com.pet.util.JSFUtil;

@ManagedBean(name = "MBAgendar")
@ViewScoped
public class AgendarBean {
	private Agendar agendar;
	private ArrayList<Agendar> itens;
	private ArrayList<Agendar> itensFiltrados;
	private ArrayList<Clientes> comboClientes;

	public Agendar getAgendar() {
		return agendar;
	}

	public void setAgendar(Agendar agendar) {
		this.agendar = agendar;
	}

	public ArrayList<Agendar> getItens() {
		return itens;
	}

	public void setItens(ArrayList<Agendar> itens) {
		this.itens = itens;
	}

	public ArrayList<Agendar> getItensFiltrados() {
		return itensFiltrados;
	}

	public void setItensFiltrados(ArrayList<Agendar> itensFiltrados) {
		this.itensFiltrados = itensFiltrados;
	}

	public ArrayList<Clientes> getComboClientes() {
		return comboClientes;
	}

	public void setComboClientes(ArrayList<Clientes> comboClientes) {
		this.comboClientes = comboClientes;
	}

	@PostConstruct
	public void prepararPesquisa() {
		try {
			AgendarDAO fdao = new AgendarDAO();

			itens = fdao.listar();

		} catch (SQLException e) {
			JSFUtil.adicionarMensagemErro("e.getMessage()");
			e.printStackTrace();
		}
	}

	public void prepararNovo() {

		try {
			agendar = new Agendar();

			ClientesDAO dao = new ClientesDAO();
			comboClientes = dao.Listar();
		} catch (SQLException e) {
			JSFUtil.adicionarMensagemErro("e.getMessage()");
			e.printStackTrace();
		}
	}

	public void novo() {
		try {
			AgendarDAO fdao = new AgendarDAO();
			fdao.save(agendar);

			itens = fdao.listar();

			JSFUtil.adicionarMensagemSucesso("Agenda Salvo com Sucesso");
		} catch (Exception e) {
			JSFUtil.adicionarMensagemErro("e.getMessage()");
			e.printStackTrace();
		}
	}

	public void excluir() {
		try {
			AgendarDAO fdao = new AgendarDAO();
			fdao.delete(agendar);

			itens = fdao.listar();

			JSFUtil.adicionarMensagemSucesso("Agenda Excluído com Sucesso");
		} catch (Exception e) {
			JSFUtil.adicionarMensagemErro("e.getMessage()");
			e.printStackTrace();
		}
	}

	public void editar() {
		try {
			AgendarDAO fdao = new AgendarDAO();
			fdao.update(agendar);

			itens = fdao.listar();

			JSFUtil.adicionarMensagemSucesso("Agenda editado com sucesso!");

		} catch (SQLException e) {
			JSFUtil.adicionarMensagemErro("ex.getMessage()");
			e.printStackTrace();
		}
	}

	public void prepararEditar() {

		try {
			agendar = new Agendar();
			ClientesDAO dao = new ClientesDAO();
			comboClientes = dao.Listar();
		} catch (SQLException e) {
			JSFUtil.adicionarMensagemErro("ex.getMessage()");
			e.printStackTrace();
		}
	}
}
