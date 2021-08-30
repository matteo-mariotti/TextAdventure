package it.uniroma1.textadv.oggetti;

import it.uniroma1.textadv.ElementoStanza;

public abstract class OggettoCheInteragisce extends Oggetto {

	ElementoStanza interazione;

	public OggettoCheInteragisce(String nome, Oggetto inter) {
		super(nome);
		interazione = inter;
	}

	public OggettoCheInteragisce(String nome) {
		this(nome, null);
	}

	public void addInteraction(Oggetto ogg) {
		interazione = ogg;
	}
	
	public boolean getInterazione() {
		if (interazione == null)
			return false;
		return true;
	}
	

}
