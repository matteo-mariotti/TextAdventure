package it.uniroma1.textadv.textEngine.verbs;

import java.util.stream.Collectors;

import it.uniroma1.textadv.characters.Giocatore;

/**
 * Classe che modella il "verbo" inventario
 * @author matte
 *
 */
public class Inventario extends Verbo {

	/**
	 * Stringa da restituire se l'inventario è vuoto
	 */
	private static final String EMPTY = "Inventario vuoto";
	
	@Override
	public String esegui() {
		if (Giocatore.instanceOf().getInventario().isEmpty())
			return EMPTY;
		return Giocatore.instanceOf().getInventario().values().stream().map(x -> x.toString()).collect(Collectors.joining("\n"));
	}

}
