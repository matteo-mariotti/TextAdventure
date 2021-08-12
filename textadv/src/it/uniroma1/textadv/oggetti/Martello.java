package it.uniroma1.textadv.oggetti;

import it.uniroma1.textadv.ElementiStanza;

public class Martello extends Oggetto implements Usable {
	
	public Martello(String nome) {
		super(nome);
	}

	@Override
	public void use() {
		System.out.println("Devi indicare su cosa usare il martello");
	}

	@Override
	public void use(ElementiStanza e) {
		if (e instanceof Breakable)
			((Breakable) e).rompi(this);
	}
	
	

}
