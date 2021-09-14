package it.uniroma1.textadv.oggetti;

/**
 * Classe che modella un pozzo
 * @author matte
 *
 */
public class Pozzo extends Oggetto {
	
	/**
	 * Costruttore della classe pozzo
	 * @param nome Nome del pozzo
	 */
	public Pozzo(String nome) {
		super(nome);
	}

	/**
	 * Metodo che permette di riempire un secchio
	 * @param sec Secchio da riempiere 
	 * @return Stringa con il risultato dell'operazione
	 */
	public String riempi(Secchio sec) {
		sec.fill();
		return "" + sec + "è stato riempito";
	}
	
}
