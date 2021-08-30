package it.uniroma1.textadv.characters;

/**
 * Classe che modella un generico gatto
 * @author matte
 *
 */
public class Gatto extends Animale{

	/**
	 * Verso del gatto
	 */
	private static String VERSO = "Miaooooooo!!";

	/**
	 * Costruttore della classe Gatto
	 * @param nome Nome dell'animale
	 */
	public Gatto(String nome) {
		super(nome);
	}
	
	@Override
	public String accarezza() {
		return VERSO;
	}
	
}
