package minhacidademelhor.controller;

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
	}

	public boolean salvarPessoa(Pessoa pessoa) {
		pessoaDao = new PessoaDao();
		boolean isSalvo = pessoaDao.salvarPessoa(pessoa);
		return isSalvo;
	}
}
