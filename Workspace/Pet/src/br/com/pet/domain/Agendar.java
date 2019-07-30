package br.com.pet.domain;

public class Agendar {
	private int codigo;
	private String nomeDono;
	private String telefone;
	private String email;
	private String horarioAgend;
	private Clientes clientes = new Clientes();

	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	public String getNomeDono() {
		return nomeDono;
	}

	public void setNomeDono(String nomeDono) {
		this.nomeDono = nomeDono;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getHorarioAgend() {
		return horarioAgend;
	}

	public void setHorarioAgend(String horarioAgend) {
		this.horarioAgend = horarioAgend;
	}

	public Clientes getClientes() {
		return clientes;
	}

	public void setClientes(Clientes clientes) {
		this.clientes = clientes;
	}

}
