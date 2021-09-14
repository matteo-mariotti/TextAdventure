package it.uniroma1.textadv.characters;

import it.uniroma1.textadv.ElementoStanza;

public class Guardia extends Guardiano{

	/**
	 * Messaggio della guardia
	 */
	private final static String MESSAGGIO = "Io sono una guardia, non puoi soffiarmi le cose da sotto il naso!!";

	
	public Guardia(String nome, ElementoStanza distrazione, ElementoStanza tesoro) {
		super(nome, distrazione, tesoro);
	}
	
	private String getMsg() {
		return MESSAGGIO;
	}

}
