package minhacidademelhor.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import minhacidademelhor.model.Endereco;
import minhacidademelhor.model.Pessoa;
import minhacidademelhor.model.Sexo;
import minhacidademelhor.model.dao.utilDao.ConnectionFactory;

public class PessoaDao {
	
	Connection con;
	private Statement statement;
	private PreparedStatement preparedStatement;
	
	public PessoaDao() {
		ConnectionFactory connectionFactory = new ConnectionFactory();
		con = connectionFactory.getConnection();
	}
	
	public List<Pessoa> listarPessoas(){
		List<Pessoa> pessoas = new ArrayList();
		ResultSet set;
		
		try {
			statement = con.createStatement();
			set = statement.executeQuery("select * from pessoa;");
			
			while (set.next()) {
				Pessoa pessoa = new Pessoa();
				pessoa.setId(set.getInt("id"));
				pessoa.setNome(set.getString("nome"));
				pessoa.setCpf(set.getString("cpf"));
				pessoa.setEmail(set.getString("email"));
//				pessoa.setSexo.d( set.getString("sexo"));
				pessoa.setTelefone(set.getString("telefone"));
				pessoa.setDataNascimento(convertToLocalDateViaSqlDate(set.getDate("dataNascimento")));
				
				pessoas.add(pessoa);
			}
			
		} catch (Exception e) {
			System.err.println("erro ao listar pessoas: " + e.getMessage());
		}
		
		return pessoas;
	}
	
	public boolean salvarPessoa(Pessoa pessoa) {
		boolean isSalvo = false;
		String query = "insert into pessoa (nome,cpf,email,telefone,sexo,dataNascimento)"
				+ "values (?,?,?,?,?,?)";
		
		try {
			con.setAutoCommit(false);
			preparedStatement = con.prepareStatement(query);
			preparedStatement.setString(1, pessoa.getNome());
			preparedStatement.setString(2, pessoa.getCpf());
			preparedStatement.setString(3, pessoa.getEmail());
			preparedStatement.setString(4, pessoa.getTelefone());
			preparedStatement.setString(5, pessoa.getSexo().getDescricao());
			preparedStatement.setDate(6, java.sql.Date.valueOf(pessoa.getDataNascimento()));
			
			preparedStatement.execute();
			con.commit();
			isSalvo = true;
			
			
		} catch (Exception e) {
			System.err.println("Erro ao inserir pessoa " +  e.getMessage());
			isSalvo = false;
		}
		
		return isSalvo;
		
	}
	
	public boolean editarPessoa(Pessoa pessoa) {
		boolean isSalvo = false;
		String query = "UPDATE pessoa "
				+ "SET nome = ?, "
				+ "cpf = ?, "
				+ "email = ?, "
				+ "telefone = ?, "
				+ "sexo = ?, "
				+ "dataNascimento = ? "
				+ "WHERE id = ?";
		
		try {
			con.setAutoCommit(false);
			preparedStatement = con.prepareStatement(query);
			preparedStatement.setString(1, pessoa.getNome());
			preparedStatement.setString(2, pessoa.getCpf());
			preparedStatement.setString(3, pessoa.getEmail());
			preparedStatement.setString(4, pessoa.getTelefone());
			preparedStatement.setString(5, pessoa.getSexo().getDescricao());
			preparedStatement.setDate(6, java.sql.Date.valueOf(pessoa.getDataNascimento()));
			preparedStatement.setInt(7,pessoa.getId());
			
			preparedStatement.executeUpdate();
			con.commit();
			isSalvo = true;
			
			
		} catch (Exception e) {
			System.err.println("Erro ao editar pessoa " +  e.getMessage());
			isSalvo = false;
		}
		
		return isSalvo;
		
	}
	
	public boolean deletarPessoa(int id) {
		boolean isSalvo = false;
		String query = "delete from pessoa where id = ?";
		
		try {
			con.setAutoCommit(false);
			preparedStatement = con.prepareStatement(query);
			preparedStatement.setInt(1, id);
			
			preparedStatement.execute();
			con.commit();
			isSalvo = true;
			
			
		} catch (Exception e) {
			System.err.println("Erro ao deletar pessoa " +  e.getMessage());
			isSalvo = false;
		}
		
		return isSalvo;
		
	}
	
	public Pessoa buscarPessoaPorID(int id) {
		String query = "select * from pessoa where id = " + id;
		ResultSet set;
		List<Pessoa> pessoas = new ArrayList();
		
		try {
			preparedStatement = con.prepareStatement(query);
			
			set = preparedStatement.executeQuery();
			
			while (set.next()) {
				Pessoa pessoa = new Pessoa();
				pessoa.setId(set.getInt("id"));
				pessoa.setNome(set.getString("nome"));
				pessoa.setCpf(set.getString("cpf"));
				pessoa.setEmail(set.getString("email"));
//				pessoa.setSexo.d( set.getString("sexo"));
				pessoa.setTelefone(set.getString("telefone"));
				pessoa.setDataNascimento(convertToLocalDateViaSqlDate(set.getDate("dataNascimento")));
				
				pessoas.add(pessoa);
			}
			
		} catch (Exception e) {
			System.err.println("Erro ao deletar pessoa " +  e.getMessage());
		}
		
		return pessoas.get(0);
		
	}
	
	public boolean salvarPessoaComEderecos(Pessoa pessoa) throws SQLException {
		boolean isSalvo = false;
		String queryPessoa = "insert into pessoa (nome,cpf,email,telefone,sexo,dataNascimento)"
				+ "values (?,?,?,?,?,?)";
		
		String queryEndereco = "INSERT INTO endereco("
				+ "rua, numero, complemento, bairro, cep, cidade, estado, tipo, idPessoa) "
				+ "VALUES (?,?,?,?,?,?,?,?,?)";
		
		
		try {
			con.setAutoCommit(false);
			
			preparedStatement = con.prepareStatement(queryPessoa);
			preparedStatement.setString(1, pessoa.getNome());
			preparedStatement.setString(2, pessoa.getCpf());
			preparedStatement.setString(3, pessoa.getEmail());
			preparedStatement.setString(4, pessoa.getTelefone());
			preparedStatement.setString(5, pessoa.getSexo().getDescricao());
			preparedStatement.setDate(6, java.sql.Date.valueOf(pessoa.getDataNascimento()));
			
			preparedStatement.execute();
			statement = con.createStatement();
			int idTemp = 0;
			try {
				ResultSet set = statement.executeQuery("select last_insert_id() as id");
				while (set.next()) {
					idTemp = set.getInt("id");
					
				}
			} catch (Exception e) {
				// TODO: handle exception
				con.rollback();
			}
			final int idPessoa = idTemp;
			
			
//			for (int i = 0; i < pessoa.getEnderecos().size(); i++) {
//				Endereco endereco = pessoa.getEnderecos().get(i);
//				
//			}
			
			pessoa.getEnderecos().forEach(endereco ->{
				try {
					preparedStatement = con.prepareStatement(queryEndereco);
					preparedStatement.setString(1, endereco.getRua());
					preparedStatement.setString(2, endereco.getNumero());
					preparedStatement.setString(3, endereco.getComplemento());
					preparedStatement.setString(4, endereco.getBairro());
					preparedStatement.setString(5, endereco.getCep());
					preparedStatement.setString(6, endereco.getCidade());
					preparedStatement.setString(7, endereco.getEstado());
					preparedStatement.setString(8, endereco.getTipoEndereco());
					preparedStatement.setInt(9, idPessoa);
					
					preparedStatement.execute();
					
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					try {
						con.rollback();
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			});
			
			con.commit();
			isSalvo = true;
			
			
		} catch (Exception e) {
			System.err.println("Erro ao inserir pessoa " +  e.getMessage());
			isSalvo = false;
			con.rollback();
		}
		
		return isSalvo;
		
	}
	
	public LocalDate convertToLocalDateViaSqlDate(Date dateToConvert) {
	    return new java.sql.Date(dateToConvert.getTime()).toLocalDate();
	}

}
