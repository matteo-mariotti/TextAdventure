package it.uniroma1.textadv.textEngine.verbs;

import it.uniroma1.textadv.oggetti.ImpossibileOttenereOggetto;
import it.uniroma1.textadv.rooms.ChiaveNecessariaExeption;
import it.uniroma1.textadv.rooms.DirezioneNonConsentitaException;
import it.uniroma1.textadv.rooms.ElementoInesistenteException;
import it.uniroma1.textadv.rooms.PagamentoNecessarioException;

/**
 * Interfaccia funzionale che rappresenta i verbi che prendono due argomenti
 * 
 * @author matte
 *
 */
@FunctionalInterface
interface VerboBinario {
	/**
	 * Cosa fa il verbo
	 * 
	 * @param arg1 Argomento 1
	 * @param arg2 Argomento 2
	 * @return Stringa con il risultato dell'operazione
	 * @throws ChiaveNecessariaExeption        Se serve aprire prima il link/box
	 * @throws DirezioneNonConsentitaException Se non posso andare in quella
	 *                                         direzione
	 * @throws ElementoInesistenteException    Se non trovo l'elemento cercato dal
	 *                                         giocatore
	 * @throws PagamentoNecessarioException    Se devo prima pagare qualcuno
	 * @throws ImpossibileOttenereOggetto      Se non posso ottenere l'oggetto
	 */
	String esegui(String arg1, String arg2) throws ImpossibileOttenereOggetto, ChiaveNecessariaExeption,
			PagamentoNecessarioException, ElementoInesistenteException, DirezioneNonConsentitaException;

}
