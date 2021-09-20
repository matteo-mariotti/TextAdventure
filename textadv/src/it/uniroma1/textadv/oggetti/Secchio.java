package it.uniroma1.textadv.oggetti;

import it.uniroma1.textadv.ElementoStanza;

/**
 * Classe che modella un secchio
 * 
 * @author matte
 *
 */
public class Secchio extends Oggetto implements Usable, Takeable {

	/**
	 * Flag per indicare se il secchio � pieno o meno
	 */
	private boolean pieno = false;

	/**
	 * Costruttore della classe Secchio
	 * 
	 * @param nome Nome del secchio
	 */
	public Secchio(String nome) {
		super(nome);
	}

	/**
	 * Metodo che permette di riempire il secchio
	 */
	void fill() {
		pieno = true;
	}

	/**
	 * Permette di conoscere se il secchio � pieno o meno
	 * 
	 * @return True se il secchio � pieno, false altrimenti
	 */
	boolean filled() {
		return pieno;
	}

	@Override
	public String use() {
		return "Devi indicare su cosa usare il secchio";
	}

	/**
	 * Metodo che permette di svuotare il secchio
	 */
	void empty() {
		pieno = false;
	}

	@Override
	public String use(ElementoStanza e) {
		if (e instanceof Pozzo)
			return ((Pozzo) e).riempi(this);
		if (e instanceof Camino)
			return ((Camino) e).spegni(this);
		return "Non puoi usare il secchio su " + e.getNome();
	}

}
