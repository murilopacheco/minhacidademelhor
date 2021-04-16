package minhacidademelhor.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
	
	public LocalDate convertToLocalDateViaSqlDate(Date dateToConvert) {
	    return new java.sql.Date(dateToConvert.getTime()).toLocalDate();
	}

}
