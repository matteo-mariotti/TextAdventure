package it.uniroma1.textadv.oggetti;

import it.uniroma1.textadv.ElementoStanza;
import it.uniroma1.textadv.rooms.ChiaveNecessariaExeption;

/**
 * Classe che modella un generico oggetto nella stanza
 * 
 * @author matte
 *
 */
public abstract class Oggetto extends ElementoStanza {
	
	private ComportamentoApertura cApertura = new NonApribile();
	
	
	/**
	 * Costruttore della classe
	 * 
	 * @param nome Nome dell'oggetto
	 */
	public Oggetto(String nome) {
		super(nome);
	}
	
	/**
	 * Permette di impostare il comportamento quando si apre l'oggett
	 * @param c Comportamento apertura
	 */
	public void setCompApertura(ComportamentoApertura c) {
		cApertura = c;
	}
	
	/**
	 * Permette di aprire l'oggetto
	 * @param c Oggetto "chiave" 
	 * @return Stringa con il risultato dell'operazione
	 * @throws ChiaveNecessariaExeption Se serve una chiave
	 */
	public String open(OggettoCheInteragisce c) throws ChiaveNecessariaExeption {
		return cApertura.open(c, this);
	}
	
	/**
	 * Permette di sapere se l'oggetto è aperto o chiuso
	 * @return True se aperto, false altrimenti
	 */
	public boolean getStatuss() {
		if (cApertura instanceof GiaAperto)
			return true;
		return false;
	}

	/**
	 * Metodo che permette di ottenere il nome dell'oggetto
	 */
	public String getNome() {
		return nome;
	}

}
