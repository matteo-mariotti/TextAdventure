package it.uniroma1.textadv.characters;

import it.uniroma1.textadv.ElementoStanza;
/**
 * Classe che modella una guardia
 * @author matte
 *
 */
public class Guardia extends Guardiano{

	/**
	 * Messaggio della guardia
	 */
	private final static String MESSAGGIO = "Io sono una guardia, non puoi soffiarmi le cose da sotto il naso!!";

	/**
	 * Costruttore della classe
	 * @param nome Nome della guardia
	 * @param distrazione Elemento che distrae la guardia
	 * @param tesoro Oggetto protetto dalla guardia
	 */
	public Guardia(String nome, ElementoStanza distrazione, ElementoStanza tesoro) {
		super(nome, distrazione, tesoro);
	}
	
	/**
	 * Metodo per ottenere il messaggio della guardia
	 * @return Messaggio
	 */
	@SuppressWarnings("unused")
	private String getMsg() {
		return MESSAGGIO;
	}

}
