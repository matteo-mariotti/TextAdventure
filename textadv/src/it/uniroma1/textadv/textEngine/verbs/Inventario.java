package it.uniroma1.textadv.textEngine.verbs;

import java.util.stream.Collectors;

import it.uniroma1.textadv.characters.Giocatore;

public class Inventario extends Verbo {

	@Override
	public String esegui() {
		if (Giocatore.instanceOf().getInventario().isEmpty())
			return "Inventario vuoto";
		return Giocatore.instanceOf().getInventario().values().stream().map(x -> x.toString()).collect(Collectors.joining("\n"));
	}

}
