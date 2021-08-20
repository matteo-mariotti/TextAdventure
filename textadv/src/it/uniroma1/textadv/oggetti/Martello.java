package it.uniroma1.textadv.oggetti;

import it.uniroma1.textadv.ElementiStanza;

public class Martello extends Oggetto implements Usable {
	
	public Martello(String nome) {
		super(nome);
	}

	@Override
	public String use() {
		return "Devi indicare su cosa usare il martello";
	}

	@Override
	public String use(ElementiStanza e) {
		if (e instanceof Breakable)
			return ((Breakable) e).rompi(this);
		return "Non puoi usare il martello su " + e.getNome();
	}
	
	

}
