package it.uniroma1.textadv.textEngine.verbs;

import it.uniroma1.textadv.ElementoStanza;
import it.uniroma1.textadv.characters.Entita;
import it.uniroma1.textadv.oggetti.ImpossibileOttenereOggetto;
import it.uniroma1.textadv.rooms.ChiaveNecessariaExeption;
import it.uniroma1.textadv.rooms.ElementoInesistenteException;
import it.uniroma1.textadv.rooms.PagamentoNecessarioException;
import it.uniroma1.textadv.textEngine.ObjFinder;

/**
 * Classe che modella il verbo Parlare
 * @author matte
 *
 */
public class Parla extends Verbo implements VerboUnitario{
	
	/**
	 * Errore da restituire se cerco di parlare con qualcosa che non è una entità
	 */
	private static final String NOT_ALLOWED = "Non puoi parlare con ";
	
	@Override
	public String esegui(String entita) {
		try {
			ElementoStanza e = ObjFinder.getArg(entita);
			if (e instanceof Entita)
				return ((Entita) e).speak();
			return NOT_ALLOWED + e.getNome();
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

	
	
}
