package it.uniroma1.textadv.oggetti;

import it.uniroma1.textadv.ElementoStanza;

/**
 * Classe che modella l'oggetto martello
 * 
 * @author matte
 *
 */
public class Martello extends Oggetto implements Usable, Takeable {
	/**
	 * Stringa di errore restituita quando non viene indicato su cosa usare il
	 * martello
	 */
	private static final String USE_ON = "Devi indicare su cosa usare il martello";

	/**
	 * Stringa di errore quando si vuole usare il martello su un oggetto che non si
	 * può rompere
	 */
	private static final String USE_ERROR = "Non puoi usare il martello su ";

	/**
	 * Costruttore della classe
	 * 
	 * @param nome Nome dell'oggetto
	 */
	public Martello(String nome) {
		super(nome);
	}

	@Override
	public String use() {
		return USE_ON;
	}

	@Override
	public String use(ElementoStanza e) {
		if (e instanceof Breakable)
			return ((Breakable) e).rompi(this);
		return USE_ERROR + e.getNome();
	}

}
