package it.uniroma1.textadv.oggetti;

import it.uniroma1.textadv.ElementoStanza;

/**
 * Classe che rappresenta un cacciavite
 * @author matte
 *
 */
public class Cacciavite extends Oggetto implements Usable, Takeable{
	
	/**
	 * Costruttore del cacciavite
	 * @param nome Nome dell'oggetto
	 */
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
