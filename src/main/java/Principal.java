

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import minhacidademelhor.controller.PessoaController;
import minhacidademelhor.model.Endereco;
import minhacidademelhor.model.Pessoa;
import minhacidademelhor.model.Sexo;

public class Principal {
	
	public static void main(String args[] ){
		
		//		Pessoa p = new Pessoa();
//		p.setSexo(Sexo.Feminino);
//		
//		System.out.println(p.getSexo().getDescricao());
//		System.out.println(Sexo.values());
		
		PessoaController pessoaController = new PessoaController();
		Pessoa pessoa = new Pessoa();
		pessoa.setNome("Pessoa Transaction falha");
		pessoa.setCpf("666.666.666-66");
		pessoa.setEmail("pessoaT@pessoaT.com");
		pessoa.setSexo(Sexo.Feminino);
		pessoa.setTelefone("(62)3233-4444");
		pessoa.setDataNascimento(LocalDate.of(2005, 05, 15));
		
//		pessoaController.salvarPessoa(pessoa);
		
		Pessoa pessoaEdit = new Pessoa();
		
		
		List<Pessoa> pessoas = pessoaController.listarPessoas();
		for (Iterator iterator = pessoas.iterator(); iterator.hasNext();) {
			Pessoa pessoa2 = (Pessoa) iterator.next();
			System.out.println("Id: " + pessoa2.getId());
			System.out.println("nome: "+ pessoa2.getNome());
			System.out.println("cpf: "+ pessoa2.getCpf());
			System.out.println("data de Nascimento: "+ pessoa2.getDataNascimento().toString());
			if(pessoa2.getId() == 1) {
				pessoaEdit = pessoa2;
			}
			
		}
		
		pessoaEdit.setNome("pessoaEdit");
		pessoaEdit.setCpf("333.333.333-33");
		pessoaEdit.setEmail("pessoaEdit@pessoaEdit.com");
		pessoaEdit.setSexo(Sexo.Feminino);
		pessoaEdit.setTelefone("(62)3233-4444");
		pessoaEdit.setDataNascimento(LocalDate.of(2000, 3, 1));
		
//		boolean editado = pessoaController.editarPessoa(pessoaEdit);
//		System.out.println(editado);
		
//		boolean deletado = pessoaController.editarPessoa(2);
//		System.out.println(deletado);
		
		Endereco endereco = new Endereco();
		endereco.setRua("Rua teste falha");
		endereco.setNumero("01");
		endereco.setCep("74000-000");
		endereco.setCidade("Goiânia");
		endereco.setBairro("Bairro teste");
		endereco.setComplemento("Complemento endereço");
		endereco.setEstado(null);
		endereco.setTipoEndereco("Comercial");
		
		List<Endereco> enderecos = new ArrayList();
		enderecos.add(endereco);
		
		pessoa.setEnderecos(enderecos);
		
		try {
			pessoaController.salvarPessoaComEderecos(pessoa);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
