package minhacidademelhor.model;

import java.time.LocalDate;
import java.util.Iterator;
import java.util.List;

import minhacidademelhor.controller.PessoaController;

public class Principal {
	
	public static void main(String args[] ){
//		Pessoa p = new Pessoa();
//		p.setSexo(Sexo.Feminino);
//		
//		System.out.println(p.getSexo().getDescricao());
//		System.out.println(Sexo.values());
		
		PessoaController pessoaController = new PessoaController();
		Pessoa pessoa = new Pessoa();
		pessoa.setNome("Pessoa salvar");
		pessoa.setCpf("222.222.222-22");
		pessoa.setEmail("pessoa@pessoa.com");
		pessoa.setSexo(Sexo.Feminino);
		pessoa.setTelefone("(62)3233-3333");
		pessoa.setDataNascimento(LocalDate.of(2000, 10, 1));
		
		pessoaController.salvarPessoa(pessoa);
		
		
		List<Pessoa> pessoas = pessoaController.listarPessoas();
		for (Iterator iterator = pessoas.iterator(); iterator.hasNext();) {
			Pessoa pessoa2 = (Pessoa) iterator.next();
			System.out.println("nome: "+ pessoa2.getNome());
			System.out.println("cpf: "+ pessoa2.getCpf());
			System.out.println("data de Nascimento: "+ pessoa2.getDataNascimento().toString());
			
		}
	}

}
