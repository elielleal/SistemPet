package br.com.pet.DAO;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import br.com.pet.domain.Fornecedores;
import br.com.pet.factory.ConexaoFactory;

public class FornecedoresDAO {
 
	public void save(Fornecedores f) throws SQLException{
		StringBuilder sql = new StringBuilder();
		sql.append("INSERT INTO fornecedores ");
		sql.append("(descricao) ");
		sql.append("VALUES (?)");
		
		Connection conexao = ConexaoFactory.conectar();
		
		PreparedStatement comando = conexao.prepareStatement(sql.toString());
		comando.setString(1, f.getDescricao());
		comando.executeUpdate();
	}
	
	public void delete (Fornecedores f) throws SQLException{
		StringBuilder sql = new StringBuilder();
		sql.append("DELETE FROM fornecedores ");
		sql.append("WHERE codigo = ?");
		
		Connection conexao = ConexaoFactory.conectar();
		
		PreparedStatement comando = conexao.prepareStatement(sql.toString());
		comando.setInt(1, f.getCodigo());
		comando.executeUpdate();
	} 
	
	public void update (Fornecedores f) throws SQLException{
		StringBuilder sql = new StringBuilder();
		sql.append("UPDATE fornecedores ");
		sql.append("SET descricao = ? ");
		sql.append("WHERE codigo = ?");
		
		Connection conexao = ConexaoFactory.conectar();
		
		PreparedStatement comando = conexao.prepareStatement(sql.toString());
		comando.setString(1, f.getDescricao());
		comando.setInt(2, f.getCodigo());
		comando.executeUpdate();
		
		
		
	}
	
	public Fornecedores buscarPorCodigo(Fornecedores f) throws SQLException{
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT codigo, descricao ");
		sql.append("FROM fornecedores ");
		sql.append("WHERE codigo = ? ");
		
		Connection conexao = ConexaoFactory.conectar();
		
		PreparedStatement comando = conexao.prepareStatement(sql.toString());
		
		comando.setInt(1, f.getCodigo());
		
		ResultSet resultado  = comando.executeQuery();
		Fornecedores retorno = null;
		
		if(resultado.next()){
			retorno = new Fornecedores();
			retorno.setCodigo(resultado.getInt("codigo"));
			retorno.setDescricao(resultado.getString("descricao"));
		}
		
		return retorno;
	}
	
	public ArrayList<Fornecedores>buscarPorDescricao(Fornecedores f) throws SQLException{
		
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT codigo, descricao ");
		sql.append("FROM fornecedores ");
		sql.append("WHERE descricao LIKE ? ");
		sql.append("ORDER BY descricao ASC ");
		
		Connection conexao = ConexaoFactory.conectar();
		
		PreparedStatement comando = conexao.prepareStatement(sql.toString());
		
		comando.setString(1, "%" + f.getDescricao() + "%");
		
		ResultSet resultado  = comando.executeQuery();
		
		ArrayList<Fornecedores>lista = new ArrayList<Fornecedores>();
		
		while(resultado.next()){
			Fornecedores item = new Fornecedores();
			item.setCodigo(resultado.getInt("codigo"));
			item.setDescricao(resultado.getString("descricao"));
			
			lista.add(item);
		}
		
		return lista;
		
	}
	
	public ArrayList<Fornecedores> Listar() throws SQLException{
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT codigo, descricao ");
		sql.append("FROM fornecedores ");
		sql.append("ORDER BY descricao ASC ");
		
		Connection conexao = ConexaoFactory.conectar();
		
		PreparedStatement comando = conexao.prepareStatement(sql.toString());
		
		ResultSet resultado  = comando.executeQuery();
		
		ArrayList<Fornecedores>lista = new ArrayList<Fornecedores>();
		
		while(resultado.next()){
			Fornecedores f = new Fornecedores();
			f.setCodigo(resultado.getInt("codigo"));
			f.setDescricao(resultado.getString("descricao"));
			
			lista.add(f);
		}
		
		return lista;
	}
	
	public static void main(String[] args) {
		Fornecedores f1 = new Fornecedores();
		f1.setDescricao("Strodal");
		
		Fornecedores f2 = new Fornecedores();
		f2.setDescricao("Canex");
		
		FornecedoresDAO fao = new FornecedoresDAO();
		try {
			fao.save(f1);
			fao.save(f2);
			System.out.println("Salvo com Sucesso!!");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("Ocorreu um Erro!!");
		}
		
		/*Fornecedores f1 = new Fornecedores();
		f1.setCodigo(2);
		FornecedoresDAO fao = new FornecedoresDAO();
		try {
			fao.delete(f1);
			System.out.println("Deletado com Sucesso!!");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("Ocorreu um Erro!!");
		}
		
		Fornecedores f1 = new Fornecedores();
		f1.setCodigo(1);
		f1.setDescricao("Malta");
		
		FornecedoresDAO fao = new FornecedoresDAO();
		try {
			fao.update(f1);
			System.out.println("Editado com Sucesso!!");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("Ocorreu um Erro!!");
		}
		
		Fornecedores f1 = new Fornecedores();
		f1.setCodigo(1);
		
		Fornecedores f2 = new Fornecedores();
		f2.setCodigo(3);
		
		FornecedoresDAO fao = new FornecedoresDAO();
		try {
			Fornecedores f3 = fao.buscarPorCodigo(f1);
			Fornecedores f4 = fao.buscarPorCodigo(f2);
			
			System.out.println("Resultado 1: " + f3);
			System.out.println("Resultado 2: " + f4);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("Ocorreu um Erro!!");
		}
		
		FornecedoresDAO fao = new FornecedoresDAO();
		
		try {
			ArrayList<Fornecedores>lista = fao.Listar();
			
			for (Fornecedores f : lista){
				System.out.println("Resultado " + f);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("Ocorreu um Erro!!");
		}*/
		
		/*Fornecedores f1 = new Fornecedores();
		f1.setDescricao("Ma");
		
		FornecedoresDAO fao = new FornecedoresDAO();
		
		try {
			ArrayList<Fornecedores>lista = fao.buscarPorDescricao(f1);
			
			for (Fornecedores f : lista){
				System.out.println("Resultado " + f);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("Ocorreu um Erro!!");
		}*/
	}
}