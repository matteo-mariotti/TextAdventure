package it.uniroma1.textadv.oggetti;

import it.uniroma1.textadv.ElementoStanza;
import it.uniroma1.textadv.oggetti.links.Link;
import it.uniroma1.textadv.rooms.ChiaveNecessariaExeption;
import it.uniroma1.textadv.rooms.PagamentoNecessarioException;

/**
 * Classe che modella un generico contenitore
 * 
 * @author matte
 *
 */
public abstract class Box extends OggettoCheInteragisce {
	/**
	 * Stato del contenitore, aperto o chiuso
	 */
	private boolean bOpen = false;

	/**
	 * Fornisce lo stato del contenitore
	 * 
	 * @return True se aperto, false altrimenti
	 */
	public boolean getStatus() {
		return bOpen;
	}

	/**
	 * Permette di ottenere lo stato del contenitore
	 * 
	 * @param stat
	 */
	protected void setStatus(boolean stat) {
		bOpen = stat;
	}

	/**
	 * Costruttore di un generico box
	 * 
	 * @param nome  Nome del box
	 * @param inter Contenuto del box
	 * @param cA    Comportamento di apertura
	 */
	Box(String nome, Oggetto inter, ComportamentoApertura cA) {
		super(nome, inter);
		super.setCompApertura(cA);
	}

	/**
	 * Metodo che permette di ottenere il contenuto del box
	 * 
	 * @param obj Oggetto da prendere
	 * @return Oggetto contenuto
	 * @throws ImpossibileOttenereOggetto   Se l'oggetto richiesto non è contenuto
	 *                                      nel box
	 * @throws ChiaveNecessariaExeption     Se il contenitore è chiuso
	 * @throws PagamentoNecessarioException Se è necessario pagare prima di prendere
	 *                                      l'oggetto
	 */
	public ElementoStanza getContenuto(String obj)
			throws ImpossibileOttenereOggetto, ChiaveNecessariaExeption, PagamentoNecessarioException {
		if (super.getInterazione() && obj.equals(super.interazione.getNome())) {
			ElementoStanza o = super.interazione;
			if (!(super.getStatuss()))
				throw new ChiaveNecessariaExeption();
			if (o.getOwner() != null)
				throw new PagamentoNecessarioException(o.getOwner());
			if (!(super.getOggInter() instanceof Link))
				super.interazione = null;
			return o;
		} else
			throw new ImpossibileOttenereOggetto(); //Lancia una eccezione perche non contiene l'elemento

	}

	@Override
	public String toString() {
		return super.getStatuss()
				? super.interazione == null ? super.getNome() + " è vuoto"
						: super.getNome() + " contiene: " + super.interazione
				: "Questo è un/una " + super.getNome();
	}
}
