package minhacidademelhor.model;

public enum Sexo {
	
	Feminino("FEMININO"),
	Masculino("MASCULINO");
	
	private String descricao;
	
	Sexo(String descricao){
		this.descricao = descricao;
	}

	public String getDescricao() {
		return descricao;
	}

}
