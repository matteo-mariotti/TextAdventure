package it.uniroma1.textadv.characters;

/**
 * Classe che modella un cane
 * 
 * @author matte
 *
 */
public class Cane extends Animale {

	/**
	 * Verso del cane
	 */
	private static final String VERSO = "Bau bau";

	/**
	 * Costruttore del cane
	 * 
	 * @param nome Nome del cane
	 */
	public Cane(String nome) {
		super(nome);
	}

	@Override
	public String accarezza() {
		return VERSO;
	}

}
