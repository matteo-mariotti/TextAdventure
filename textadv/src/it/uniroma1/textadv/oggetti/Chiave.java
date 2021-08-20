package it.uniroma1.textadv.oggetti;

import it.uniroma1.textadv.ElementiStanza;

public class Chiave extends OggettoCheInteragisce implements Usable {

	public Chiave(String nome, Oggetto inter) {
		super(nome, inter);
	}

	public Chiave(String nome) {
		super(nome);
	}

	@Override
	public String use() {
		return "Devi indicare su cosa usare la chiave";
	}

	@Override
	public String use(ElementiStanza e) {
		if (e instanceof Openable) {
			((Openable) e).unlock(this);
			return ((Openable) e).open();	
		}
		else
			return "Non puoi aprire " + e.getNome();

	}

}
