package it.uniroma1.textadv.oggetti;

import it.uniroma1.textadv.ElementiStanza;

public abstract class OggettoCheInteragisce extends Oggetto {

	ElementiStanza interazione;

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
	
	public abstract void azione();

}
