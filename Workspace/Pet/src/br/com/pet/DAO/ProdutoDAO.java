package br.com.pet.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import br.com.pet.domain.Fornecedores;
import br.com.pet.domain.Produtos;
import br.com.pet.factory.ConexaoFactory;

public class ProdutoDAO {
	public void save(Produtos p) throws SQLException{
		StringBuilder sql = new StringBuilder();
		sql.append("INSERT INTO produtos ");
		sql.append("(descricao, quantidade, preco, fornecedores_codigo) ");
		sql.append("VALUES (?,?,?,?)");
		
		Connection conexao = ConexaoFactory.conectar();
		
		PreparedStatement comando = conexao.prepareStatement(sql.toString());
		comando.setString(1, p.getDescricao());
		comando.setInt(2, p.getQuantidade());
		comando.setDouble(3, p.getPreco());
		comando.setInt(4, p.getFornecedores().getCodigo());
		comando.executeUpdate();
	}
	
	public ArrayList<Produtos> listar() throws SQLException{
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT p.codigo, p.descricao, p.quantidade, p.preco, f.codigo, f.descricao ");
		sql.append("FROM produtos p ");
		sql.append("INNER JOIN fornecedores f ON f.codigo = p.fornecedores_codigo ");
		
		Connection conexao = ConexaoFactory.conectar();
		
		PreparedStatement comando = conexao.prepareStatement(sql.toString());
		
		ResultSet resultado  = comando.executeQuery();
		
		ArrayList<Produtos>lista = new ArrayList<Produtos>();
		
		while(resultado.next()){
			
			Fornecedores f = new Fornecedores();
			f.setCodigo(resultado.getInt("f.codigo"));
			f.setDescricao(resultado.getString("f.descricao"));
			
			Produtos p = new Produtos();
			p.setCodigo(resultado.getInt("p.codigo"));
			p.setDescricao(resultado.getString("p.descricao"));
			p.setQuantidade(resultado.getInt("p.quantidade"));
			p.setPreco(resultado.getDouble("p.preco"));
			p.setFornecedores(f);
			
			lista.add(p);
		}
		
		return lista;
	}
	
	public void delete (Produtos p) throws SQLException{
		StringBuilder sql = new StringBuilder();
		sql.append("DELETE FROM produtos ");
		sql.append("WHERE codigo = ?");
		
		Connection conexao = ConexaoFactory.conectar();
		
		PreparedStatement comando = conexao.prepareStatement(sql.toString());
		comando.setInt(1, p.getCodigo());
		comando.executeUpdate();
	} 
	
	public void update (Produtos p) throws SQLException{
		StringBuilder sql = new StringBuilder();
		sql.append("UPDATE produtos ");
		sql.append("SET descricao = ?, quantidade = ?, preco = ?, fornecedores_codigo = ? ");
		sql.append("WHERE codigo = ?");
		
		Connection conexao = ConexaoFactory.conectar();
		
		PreparedStatement comando = conexao.prepareStatement(sql.toString());
		comando.setString(1, p.getDescricao());
		comando.setInt(2, p.getQuantidade());
		comando.setDouble(3, p.getPreco());
		comando.setInt(4, p.getFornecedores().getCodigo());
		comando.setInt(5, p.getCodigo());
		comando.executeUpdate();
		
		
		
	}
	
}
