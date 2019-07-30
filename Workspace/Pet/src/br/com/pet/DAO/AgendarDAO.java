package br.com.pet.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import br.com.pet.domain.Agendar;
import br.com.pet.domain.Clientes;
import br.com.pet.factory.ConexaoFactory;

public class AgendarDAO {
		public void save(Agendar a) throws SQLException{
			StringBuilder sql = new StringBuilder();
			sql.append("INSERT INTO agendar ");
			sql.append("(nomeDono, telefone, email, horarioAgend, clientes_codigo) ");
			sql.append("VALUES (?,?,?,?,?)");
			
			Connection conexao = ConexaoFactory.conectar();
			
			PreparedStatement comando = conexao.prepareStatement(sql.toString());
			comando.setString(1, a.getNomeDono());
			comando.setString(2, a.getTelefone());
			comando.setString(3, a.getEmail());
			comando.setString(4, a.getHorarioAgend());
			comando.setInt(5, a.getClientes().getCodigo());
			comando.executeUpdate();
		}
		
		public ArrayList<Agendar> listar() throws SQLException{
			StringBuilder sql = new StringBuilder();
			sql.append("SELECT a.codigo, a.nomeDono, a.telefone, a.email, a.horarioAgend, c.codigo, c.nome ");
			sql.append("FROM agendar a ");
			sql.append("INNER JOIN clientes c ON c.codigo = a.clientes_codigo ");
			
			Connection conexao = ConexaoFactory.conectar();
			
			PreparedStatement comando = conexao.prepareStatement(sql.toString());
			
			ResultSet resultado  = comando.executeQuery();
			
			ArrayList<Agendar>lista = new ArrayList<Agendar>();
			
			while(resultado.next()){
				
				Clientes c = new Clientes();
				c.setCodigo(resultado.getInt("c.codigo"));
				c.setNome(resultado.getString("c.nome"));
				
				Agendar a = new Agendar();
				a.setCodigo(resultado.getInt("a.codigo"));
				a.setNomeDono(resultado.getString("a.nomeDono"));
				a.setTelefone(resultado.getString("a.telefone"));
				a.setEmail(resultado.getString("a.email"));
				a.setHorarioAgend(resultado.getString("a.horarioAgend"));
				a.setClientes(c);
				
				lista.add(a);
			}
			
			return lista;
		}
		
		public void delete (Agendar a) throws SQLException{
			StringBuilder sql = new StringBuilder();
			sql.append("DELETE FROM agendar ");
			sql.append("WHERE codigo = ?");
			
			Connection conexao = ConexaoFactory.conectar();
			
			PreparedStatement comando = conexao.prepareStatement(sql.toString());
			comando.setInt(1, a.getCodigo());
			comando.executeUpdate();
		} 
		
		public void update (Agendar a) throws SQLException{
			StringBuilder sql = new StringBuilder();
			sql.append("UPDATE agendar ");
			sql.append("SET  nomeDono = ?, telefone = ?, email = ?, horarioAgend = ?, clientes_codigo = ? ");
			sql.append("WHERE codigo = ?");
			
			Connection conexao = ConexaoFactory.conectar();
			
			PreparedStatement comando = conexao.prepareStatement(sql.toString());
			comando.setString(1, a.getNomeDono());
			comando.setString(2, a.getTelefone());
			comando.setString(3, a.getEmail());
			comando.setString(4, a.getHorarioAgend());
			comando.setInt(5, a.getClientes().getCodigo());
			comando.setInt(6, a.getCodigo());
			comando.executeUpdate();
			
			
			
		}
		
}