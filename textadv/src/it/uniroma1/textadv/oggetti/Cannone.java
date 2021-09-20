package it.uniroma1.textadv.oggetti;

/**
 * Classe che modella un cannone
 * 
 * @author matte
 *
 */
public class Cannone extends Oggetto {
	/**
	 * Costruttore della classe
	 * 
	 * @param nome Nome del cannone
	 */
	public Cannone(String nome) {
		super(nome);
	}
	
	@Override
	public String describe() {
		return "Boooommmmm!";
	}

}
