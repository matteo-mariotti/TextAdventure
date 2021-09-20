package it.uniroma1.textadv.textEngine.verbs;

import it.uniroma1.textadv.oggetti.ImpossibileOttenereOggetto;
import it.uniroma1.textadv.rooms.ChiaveNecessariaExeption;
import it.uniroma1.textadv.rooms.DirezioneNonConsentitaException;
import it.uniroma1.textadv.rooms.ElementoInesistenteException;
import it.uniroma1.textadv.rooms.PagamentoNecessarioException;

/**
 * Interfaccia funzionale che rappresenta i verbi che prendono un solo argomento
 * @author matte
 *
 */
@FunctionalInterface
interface VerboUnitario {

	/**
	 * Cosa fa il verbo
	 * @param arg1 Argomento 1
	 * @return Stringa con il risultato dell'operazione
	 * @throws ChiaveNecessariaExeption Se serve aprire prima il link/box
	 * @throws DirezioneNonConsentitaException Se non posso andare in quella direzione
	 * @throws ElementoInesistenteException Se non trovo l'elemento cercato dal giocatore
	 * @throws PagamentoNecessarioException Se devo prima pagare qualcuno
	 * @throws ImpossibileOttenereOggetto Se non posso ottenere l'oggetto
	 */
	String esegui(String arg1) throws DirezioneNonConsentitaException, ChiaveNecessariaExeption, ImpossibileOttenereOggetto, PagamentoNecessarioException, ElementoInesistenteException;

}
