package it.uniroma1.textadv.oggetti;

/**
 * Classe che modella l'oggetto armadio
 * 
 * @author matte
 *
 */
public class Armadio extends Box {

	/**
	 * Costruttore dell'Armadio
	 * 
	 * @param nome  Nome dell'armadio
	 * @param inter Oggetto contenuto
	 */
	public Armadio(String nome, Oggetto inter) {
		super(nome, inter, new ApriConChiave());
	}
}
