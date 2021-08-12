package it.uniroma1.textadv;

public abstract class ElementiStanza {

	protected String nome;

	public ElementiStanza(String nome) {
		this.nome = nome;
	}
	
	public String getNome() {
		return nome;
	}
	
	public void setName(String nome) {
		this.nome = nome;
	}
	
	public String toString() {
		return nome; 
	}
	
	public String describe() {
		return toString();
	}
	
	public void azione() {
		System.out.println("Azione da svolgere");
	}
	
}

