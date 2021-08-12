package it.uniroma1.textadv;

import it.uniroma1.textadv.characters.Entita;

public abstract class ElementiStanza {

	protected String nome;
	private Entita owner;

	
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
	
	
	public void setOwner(Entita owner) {
		this.owner = owner;
	}
	
	public Entita getOwner() {
		return owner;
	}
}

