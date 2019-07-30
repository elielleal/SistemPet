package br.com.pet.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import br.com.pet.domain.Clientes;
import br.com.pet.domain.Fornecedores;
import br.com.pet.factory.ConexaoFactory;

public class ClientesDAO {
	public void save(Clientes c) throws SQLException{
		StringBuilder sql = new StringBuilder();
		sql.append("INSERT INTO clientes ");
		sql.append("(nome, telefone, email, cidade) ");
		sql.append("VALUES (?,?,?,?)");
		
		Connection conexao = ConexaoFactory.conectar();
		
		PreparedStatement comando = conexao.prepareStatement(sql.toString());
		comando.setString(1, c.getNome());
		comando.setString(2, c.getTelefone());
		comando.setString(3, c.getEmail());
		comando.setString(4, c.getCidade());
		comando.executeUpdate();
	}
	
	public void delete (Clientes c) throws SQLException{
		StringBuilder sql = new StringBuilder();
		sql.append("DELETE FROM clientes ");
		sql.append("WHERE codigo = ?");
		
		Connection conexao = ConexaoFactory.conectar();
		
		PreparedStatement comando = conexao.prepareStatement(sql.toString());
		comando.setInt(1, c.getCodigo());
		comando.executeUpdate();
	} 
	
	public void update (Clientes c) throws SQLException{
		StringBuilder sql = new StringBuilder();
		sql.append("UPDATE clientes ");
		sql.append("SET nome = ?, telefone = ?, email = ?, cidade = ? ");
		sql.append("WHERE codigo = ?");
		
		Connection conexao = ConexaoFactory.conectar();
		
		PreparedStatement comando = conexao.prepareStatement(sql.toString());
		comando.setString(1, c.getNome());
		comando.setString(2, c.getTelefone());
		comando.setString(3, c.getEmail());
		comando.setString(4, c.getCidade());
		comando.setInt(5, c.getCodigo());
		comando.executeUpdate();
		
		
		
	}
	
	public Fornecedores buscarPorCodigo(Clientes c) throws SQLException{
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT codigo, nome, telefone, email, cidade ");
		sql.append("FROM clientes ");
		sql.append("WHERE codigo = ? ");
		
		Connection conexao = ConexaoFactory.conectar();
		
		PreparedStatement comando = conexao.prepareStatement(sql.toString());
		
		comando.setInt(1, c.getCodigo());
		
		ResultSet resultado  = comando.executeQuery();
		Fornecedores retorno = null;
		
		if(resultado.next()){
			retorno = new Fornecedores();
			retorno.setCodigo(resultado.getInt("codigo"));
			retorno.setDescricao(resultado.getString("descricao"));
		}
		
		return retorno;
	}
	
	public ArrayList<Clientes>buscarPorDescricao(Clientes c) throws SQLException{
		
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT codigo, nome, telefone, email, cidade ");
		sql.append("FROM clientes ");
		sql.append("WHERE nome LIKE ? ");
		sql.append("ORDER BY nome ASC ");
		
		Connection conexao = ConexaoFactory.conectar();
		
		PreparedStatement comando = conexao.prepareStatement(sql.toString());
		
		comando.setString(1, "%" + c.getNome() + "%");
		
		ResultSet resultado  = comando.executeQuery();
		
		ArrayList<Clientes>lista = new ArrayList<Clientes>();
		
		while(resultado.next()){
			Clientes item = new Clientes();
			item.setCodigo(resultado.getInt("codigo"));
			item.setNome(resultado.getString("nome"));
			item.setTelefone(resultado.getString("telefone"));
			item.setEmail(resultado.getString("Email"));
			item.setCidade(resultado.getString("Cidade"));
			
			lista.add(item);
		}
		
		return lista;
		
	}
	
	public ArrayList<Clientes> Listar() throws SQLException{
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT codigo, nome, telefone, email, cidade ");
		sql.append("FROM clientes ");
		sql.append("ORDER BY nome ASC ");
		
		Connection conexao = ConexaoFactory.conectar();
		
		PreparedStatement comando = conexao.prepareStatement(sql.toString());
		
		ResultSet resultado  = comando.executeQuery();
		
		ArrayList<Clientes>lista = new ArrayList<Clientes>();
		
		while(resultado.next()){
			Clientes c = new Clientes();
			c.setCodigo(resultado.getInt("codigo"));
			c.setNome(resultado.getString("nome"));
			c.setTelefone(resultado.getString("telefone"));
			c.setEmail(resultado.getString("email"));
			c.setCidade(resultado.getString("cidade"));
			
			lista.add(c);
		}
		
		return lista;
	}
}
