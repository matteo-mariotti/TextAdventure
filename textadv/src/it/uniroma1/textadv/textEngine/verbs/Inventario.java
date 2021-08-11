package it.uniroma1.textadv.textEngine.verbs;

import it.uniroma1.textadv.characters.Giocatore;

public class Inventario extends Verbo {

	public void esegui() {
		if (Giocatore.instanceOf().getInventario().isEmpty())
			System.out.println("Inventario vuoto");
		else
			Giocatore.instanceOf().getInventario().stream().forEach(x -> System.out.println(x));
	}

}
