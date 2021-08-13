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
	public void use() {
		System.out.println("Devi indicare su cosa usare la chiave");
	}

	@Override
	public void use(ElementiStanza e) {
		if (e instanceof Openable)
			((Openable) e).open(this);
		else
			System.out.println("Non aprire svitare " + e.getNome());

	}

}
