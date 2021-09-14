package it.uniroma1.textadv.textEngine.verbs;

import it.uniroma1.textadv.ElementoStanza;
import it.uniroma1.textadv.characters.Giocatore;
import it.uniroma1.textadv.oggetti.ImpossibileOttenereOggetto;
import it.uniroma1.textadv.rooms.ChiaveNecessariaExeption;
import it.uniroma1.textadv.rooms.ElementoInesistenteException;
import it.uniroma1.textadv.rooms.PagamentoNecessarioException;
import it.uniroma1.textadv.textEngine.ObjFinder;

/**
 * Classe che modella il verbo guardare
 * @author matte
 *
 */
public class Guarda extends Verbo implements VerboUnitario{

	@Override
	public String esegui(String elemento){
		ElementoStanza ogg;
		try {
			ogg = ObjFinder.getArg(elemento);
			return ogg.describe();
		} catch (ElementoInesistenteException e) {
			return Verbo.NON_TROVATO;
		} catch (ImpossibileOttenereOggetto e) {
			return "Err";
		} catch (ChiaveNecessariaExeption e) {
			return "Err";
		} catch (PagamentoNecessarioException e) {
			return "Err";
		}
	}
	
	@Override
	public String esegui() {
		return Giocatore.instanceOf().getStanza().describe();
	}
}
