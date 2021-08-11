package it.uniroma1.textadv.oggetti;

import it.uniroma1.textadv.ElementiStanza;

public abstract class Oggetto extends ElementiStanza {
		
	public Oggetto(String nome) {
		super(nome);
	}
	
	public String getNome() {
		return nome;
	}
	
	
	
}
