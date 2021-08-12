package it.uniroma1.textadv.oggetti;

import it.uniroma1.textadv.ElementiStanza;

public class Cacciavite extends Oggetto implements Usable{
	
	public Cacciavite(String nome) {
		super(nome);
	}

	@Override
	public void use() {
		System.out.println("Devi specificare su cosa usare il cacciavite");
	}

	@Override
	public void use(ElementiStanza e) {
		if (e instanceof Unscrewable)
			((Unscrewable) e).svita();
		else
			System.out.println("Non posso svitare " + e.getNome());
	}

}
