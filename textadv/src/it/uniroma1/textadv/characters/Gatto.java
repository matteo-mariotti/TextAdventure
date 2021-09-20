package it.uniroma1.textadv.characters;

/**
 * Classe che modella un generico gatto
 * @author matte
 *
 */
public class Gatto extends Animale{

	/**
	 * Costruttore della classe Gatto
	 * @param nome Nome dell'animale
	 */
	public Gatto(String nome) {
		super(nome, new Miagola(), new Miagola());
	}

	
}
