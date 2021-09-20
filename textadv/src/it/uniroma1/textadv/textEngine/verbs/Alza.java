package it.uniroma1.textadv.textEngine.verbs;

import it.uniroma1.textadv.ElementoStanza;
import it.uniroma1.textadv.oggetti.ImpossibileOttenereOggetto;
import it.uniroma1.textadv.oggetti.Liftable;
import it.uniroma1.textadv.rooms.ChiaveNecessariaExeption;
import it.uniroma1.textadv.rooms.ElementoInesistenteException;
import it.uniroma1.textadv.rooms.PagamentoNecessarioException;
import it.uniroma1.textadv.textEngine.ObjFinder;
/**
 * Classe che modella il verbo alzare
 * @author matte
 *
 */
public class Alza extends Verbo implements VerboUnitario {

	@Override
	public String esegui(String oggetto) throws ImpossibileOttenereOggetto, ChiaveNecessariaExeption, PagamentoNecessarioException, ElementoInesistenteException {
		ElementoStanza ogg = ObjFinder.getArg(oggetto);
		if (ogg instanceof Liftable)
			return ((Liftable) ogg).alza();
		return "Non puoi alzare " + oggetto;

	}
}
