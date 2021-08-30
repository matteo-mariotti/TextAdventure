package it.uniroma1.textadv.oggetti;

import it.uniroma1.textadv.ElementoStanza;

public class Cacciavite extends Oggetto implements Usable{
	
	public Cacciavite(String nome) {
		super(nome);
	}

	@Override
	public String use() {
		return "Devi specificare su cosa usare il cacciavite";
	}

	@Override
	public String use(ElementoStanza e) {
		if (e instanceof Unscrewable)
			return ((Unscrewable) e).svita();
		return "Non posso svitare " + e.getNome();
	}

}
