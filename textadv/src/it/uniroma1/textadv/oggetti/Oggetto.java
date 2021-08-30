package it.uniroma1.textadv.oggetti;

import it.uniroma1.textadv.ElementoStanza;
import it.uniroma1.textadv.characters.Entita;

public abstract class Oggetto extends ElementoStanza {
			
	public Oggetto(String nome) {
		super(nome);
	}
	
	public String getNome() {
		return nome;
	}
	
}
