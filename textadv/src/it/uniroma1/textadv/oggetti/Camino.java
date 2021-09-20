package it.uniroma1.textadv.oggetti;

import it.uniroma1.textadv.ElementoStanza;
import it.uniroma1.textadv.rooms.ChiaveNecessariaExeption;
/**
 * Classe che modella un camino
 * @author matte
 *
 */
public class Camino extends Box{
	/**
	 * Flag che indica se il camino è acceso o spento
	 */
	private boolean bAcceso = true;

	/**
	 * Costruttore del camino
	 * @param nome Nome dell'oggetto
	 * @param inter Oggetto contenuto nel camino
	 */
	public Camino(String nome, Oggetto inter) {
		super(nome, inter, new NonApribile());
	}
	
	@Override
	public String toString() {
		return "Il camino è " + (bAcceso ? "acceso" : "spento") + " e contiene: " + super.interazione;
	}

	/**
	 * Metodo per spegnere il camino
	 * @param sec Secchio con cui spegnere il camino
	 * @return Stringa con il risultato dell'operazione
	 */
	String spegni(Secchio sec) {
		if (sec.filled()) {
			bAcceso = false;
			sec.empty();
			return "Camino spento";
		} else {
			return "Il secchio era vuoto!!!";
		}

	}
	
	@Override
	public ElementoStanza getContenuto(String obj) throws ImpossibileOttenereOggetto, ChiaveNecessariaExeption{
		if (bAcceso)
			throw new ChiaveNecessariaExeption();
		else if (super.interazione != null && obj.equals(super.interazione.getNome())) {
			ElementoStanza o = super.interazione;
			super.interazione = null;
			return o;
		} else
			throw new ImpossibileOttenereOggetto(); //Lancia una eccezione perche non contiene l'elemento
	}

}
