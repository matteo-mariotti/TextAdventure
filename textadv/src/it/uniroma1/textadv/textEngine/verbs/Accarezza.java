package it.uniroma1.textadv.textEngine.verbs;

import it.uniroma1.textadv.ElementoStanza;
import it.uniroma1.textadv.characters.Animale;
import it.uniroma1.textadv.oggetti.ImpossibileOttenereOggetto;
import it.uniroma1.textadv.rooms.ChiaveNecessariaExeption;
import it.uniroma1.textadv.rooms.ElementoInesistenteException;
import it.uniroma1.textadv.rooms.PagamentoNecessarioException;
import it.uniroma1.textadv.textEngine.ObjFinder;
/**
 * Classe che modella il verbo Accarezzare
 * @author matte
 *
 */
public class Accarezza extends Verbo implements VerboUnitario {

	/**
	 * Stringa di errore da restituire se sto cercando di accarezzare qualcosa che non è un Animale
	 */
	private static final String NON_ANIMALE = "Non puoi accarezzarlo!";
	
	@Override
	public String esegui(String entita) throws ImpossibileOttenereOggetto, ChiaveNecessariaExeption, PagamentoNecessarioException, ElementoInesistenteException {
			ElementoStanza ent = ObjFinder.getArg(entita);
			if (ent instanceof Animale) {
				Animale a = (Animale) ent;
				return a.accarezza();
			}
			return NON_ANIMALE;
	}

}
