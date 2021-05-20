package minhacidademelhor;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.util.List;

import org.junit.jupiter.api.Test;

import minhacidademelhor.controller.PessoaController;
import minhacidademelhor.model.Pessoa;
import minhacidademelhor.model.Sexo;

class PessoaTeste {
	PessoaController pessoaController = new PessoaController();
	List<Pessoa> pessoasTeste = pessoaController.listarPessoas();
	
	@Test
	void listarPessoaTeste() {
		Pessoa pessoa = new Pessoa();
		pessoa.setId(1);
		List<Pessoa> pessoas = pessoaController.listarPessoas();
		assertTrue(!pessoas.isEmpty());
		
		pessoa.setCpf("111.111.111-11");
		assertFalse(pessoa.getCpf().equals(pessoaController.listarPessoas().get(0).getCpf()));
	}
	
	@Test
	void salvarPessoa() {
		Pessoa pessoa = new Pessoa();
		pessoa.setNome("Pessoa Teste");
		pessoa.setCpf("666.666.666-66");
		pessoa.setEmail("pessoaTeste@pessoaTeste.com");
		pessoa.setSexo(Sexo.Feminino);
		pessoa.setTelefone("(62)3233-4444");
		pessoa.setDataNascimento(LocalDate.of(2005, 05, 15));
		
		assertTrue(pessoaController.salvarPessoa(pessoa));
		
	}
	

}
