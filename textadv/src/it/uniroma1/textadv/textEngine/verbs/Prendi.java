package it.uniroma1.textadv.textEngine.verbs;

import it.uniroma1.textadv.ElementoStanza;
import it.uniroma1.textadv.characters.Giocatore;
import it.uniroma1.textadv.oggetti.Box;
import it.uniroma1.textadv.oggetti.ImpossibileOttenereOggetto;
import it.uniroma1.textadv.oggetti.Takeable;
import it.uniroma1.textadv.oggetti.links.Link;
import it.uniroma1.textadv.rooms.ChiaveNecessariaExeption;
import it.uniroma1.textadv.rooms.DirezioneNonConsentitaException;
import it.uniroma1.textadv.rooms.ElementoInesistenteException;
import it.uniroma1.textadv.rooms.PagamentoNecessarioException;
import it.uniroma1.textadv.rooms.Room;
import it.uniroma1.textadv.textEngine.ObjFinder;

/**
 * Verbo Prendere
 * 
 * @author matte
 *
 */
public class Prendi extends Verbo implements VerboBinario, VerboUnitario {

	/**
	 * Errore se non posso prendere oggetti da X
	 */
	private static String NOTABOX = "Non puoi prendere oggetti da ";

	@Override
	public String esegui(String oggetto, String oggettoContenitore)
			throws ImpossibileOttenereOggetto, ChiaveNecessariaExeption, PagamentoNecessarioException,
			ElementoInesistenteException, DirezioneNonConsentitaException {
		ElementoStanza box = ObjFinder.getArg(oggettoContenitore);
		if (box instanceof Box)
			return esegui(oggetto);
		return NOTABOX + oggettoContenitore;
	}

	// Prendi un oggetto dalla stanza, da un Box, oppure prendi un link
	@Override
	public String esegui(String oggetto) throws ImpossibileOttenereOggetto, ChiaveNecessariaExeption,
			PagamentoNecessarioException, ElementoInesistenteException, DirezioneNonConsentitaException {
		Giocatore g = Giocatore.instanceOf();
		ElementoStanza ogg;
		ogg = ObjFinder.getArg(oggetto);
		if (ogg.getOwner() != null)
			throw new PagamentoNecessarioException(ogg.getOwner());
		// Se vuole prendere un oggetto lo aggiungo all'inventario
		if (ogg instanceof Takeable) {
			g.getStanza().remove(ogg);
			return g.addOggetto(ogg);
		}
		if (ogg instanceof Link) {
			Link l = (Link) ogg;
			// Se vuole prendere un link lo sposto
			if (l.getStatuss()) {
				Room r = l.getConnection(g.getStanza().getNome());
				return g.setRoom(r);
			}
			throw new ChiaveNecessariaExeption();
		}
		return "Non puoi prendere " + ogg.getNome();
	}
}