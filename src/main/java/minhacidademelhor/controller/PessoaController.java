package minhacidademelhor.controller;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import minhacidademelhor.model.Pessoa;
import minhacidademelhor.model.dao.PessoaDao;

public class PessoaController {
	
	PessoaDao pessoaDao;
	
	public List<Pessoa> listarPessoas(){
		pessoaDao = new PessoaDao();
		 List<Pessoa> pessoas; 
		 return pessoas = pessoaDao.listarPessoas();
//		 return null;
	}

	public boolean salvarPessoa(Pessoa pessoa) {
		pessoaDao = new PessoaDao();
		boolean isSalvo = pessoaDao.salvarPessoa(pessoa);
		return isSalvo;
	}
	
	public boolean editarPessoa(Pessoa pessoa) {
		pessoaDao = new PessoaDao();
		boolean isSalvo = pessoaDao.editarPessoa(pessoa);
		return isSalvo;
	}
	
	public boolean editarPessoa(int id) {
		pessoaDao = new PessoaDao();
		boolean isSalvo = pessoaDao.deletarPessoa(id);
		return isSalvo;
	}
	
	public boolean salvarPessoaComEderecos(Pessoa pessoa) throws SQLException {
		pessoaDao = new PessoaDao();
		boolean isSalvo = pessoaDao.salvarPessoaComEderecos(pessoa);
		return isSalvo;
	}
}
