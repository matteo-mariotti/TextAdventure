package it.uniroma1.textadv.characters;

import java.util.HashMap;
import java.util.Map;

import it.uniroma1.textadv.ElementiStanza;

public abstract class Entita extends ElementiStanza {
	
	Entita(String nome) {
		super(nome);
	}
	
	public String speak() {
		return "Ciao, io sono " + super.getNome();
	}
	
}

