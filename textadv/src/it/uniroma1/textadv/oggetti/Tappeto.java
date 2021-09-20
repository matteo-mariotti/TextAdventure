package it.uniroma1.textadv.oggetti;
/**
 * Classe che modella un tappeto
 * @author matte
 *
 */

public class Tappeto extends Box implements Liftable{

	/**
	 * Costruttore
	 * @param nome Nome del tappeto
	 * @param inter Oggetto sotto al tappeto
	 */
	public Tappeto(String nome, Oggetto inter) {
		super(nome, inter, new GiaAperto());
	}

	@Override
	public String alza() {
		super.setStatus(true);
		if (super.getInterazione())
			return "Sotto il tappeto c'è " + super.interazione;
		return "Sotto il tappeto non c'è nulla";
	}



}

