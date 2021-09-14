package it.uniroma1.textadv.oggetti;
/**
 * Classe che modella un cassetto
 * @author matte
 *
 */
public class Cassetto extends Box implements Openable{
	
	/**
	 * Costruttore della classe
	 * @param nome Nome dell'oggetto
	 * @param inter Oggetto contenuto
	 */
	public Cassetto(String nome, Oggetto inter) {
		super(nome, inter);
	}
	
	@Override
	public boolean unlock(OggettoCheInteragisce ogg) {
		return true;
	}

	@Override
	public String open() {
		super.setStatus(true);
		return "Il cassetto è ora aperto";
	}

	
}