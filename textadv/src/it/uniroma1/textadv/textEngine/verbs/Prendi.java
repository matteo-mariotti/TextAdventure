package it.uniroma1.textadv.textEngine.verbs;

import java.util.List;
import java.util.stream.Collectors;

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
	/**
	 * Generico errore mentre cerco di prendere qualcosa
	 */
	private static String ERROREGENERICO = "Si è verificato un errore mentre cercavi di prendere l'oggetto";
	/**
	 * Errore se provo ad andare in una direzione non consentita
	 */
	private static String DIREZIONENONCONSENTITA = "Non puoi andare in questa direzione";
	/**
	 * Errore se serve una chiave per aprire il link/box
	 */
	private static String CHIAVENECESSARIA = "Ti serve una chiave per aprire questo collegamento/Box";

	@Override
	public String esegui(String oggetto, String oggettoContenitore) {
		try {
			ElementoStanza box = ObjFinder.getArg(oggettoContenitore);
			if (box instanceof Box)
				//return checkBox(oggetto, List.of((Box) box));
				return esegui(oggetto);
			return NOTABOX + oggettoContenitore;
		} catch (ElementoInesistenteException e) {
			return ERROREGENERICO;
		} catch (PagamentoNecessarioException e3) {
			return e3.getMessage();
		} catch (ChiaveNecessariaExeption e) {
			return CHIAVENECESSARIA;

		} catch (ImpossibileOttenereOggetto e) {
			return ERROREGENERICO;
		}
	}

	/*private String getOggetto(Box oggBox, String ogg) throws ImpossibileOttenereOggetto, ChiaveNecessariaExeption,
			PagamentoNecessarioException, ElementoInesistenteException, DirezioneNonConsentitaException {
		ElementoStanza o = oggBox.getContenuto(ogg);
		if (o instanceof Link) {
			// Se vuole prendere un link lo sposto
			return Giocatore.instanceOf()
					.setRoom(((Link) o).getConnection(Giocatore.instanceOf().getStanza().getNome()));
		}
		if (o instanceof Takeable)
			return Giocatore.instanceOf().addOggetto(o);
		throw new ImpossibileOttenereOggetto();

	}*/

	// Prendi un oggetto dalla stanza, da un Box, oppure prendi un link
	@Override
	public String esegui(String oggetto) {
		ElementoStanza ogg;
		try {
			ogg = ObjFinder.getArg(oggetto);
			if (ogg.getOwner() != null)
				throw new PagamentoNecessarioException(ogg.getOwner());
			// Se vuole prendere un oggetto lo aggiungo all'insventario
			if (ogg instanceof Takeable)
				return Giocatore.instanceOf().addOggetto(ogg);
			if (ogg instanceof Link) {
				// Se vuole prendere un link lo sposto
				Room r = ((Link) ogg).getConnection(Giocatore.instanceOf().getStanza().getNome());
				//Room r = Giocatore.instanceOf().getStanza().getDestRoom(ogg.getNome());
				return Giocatore.instanceOf().setRoom(r);
			}
			return "Non puoi prendere " + ogg.getNome();
		} catch (ElementoInesistenteException e) {
			return ERROREGENERICO;
		} catch (PagamentoNecessarioException e3) {
			return e3.getMessage();
		} catch (DirezioneNonConsentitaException e) {
			return DIREZIONENONCONSENTITA;
		} catch (ChiaveNecessariaExeption e) {
			return CHIAVENECESSARIA;

		} catch (ImpossibileOttenereOggetto e) {
			return ERROREGENERICO;
		}
	}
/*
	private String checkBox(String oggetto, List<Box> l) {
		for (int i = 0; i < l.size(); i++)
			try {
				return getOggetto(l.get(i), oggetto);
			} catch (ImpossibileOttenereOggetto e) {
				if (i == (l.size() - 1))
					return ERROREGENERICO;
				continue;
			} catch (ChiaveNecessariaExeption e) {
				return CHIAVENECESSARIA;
			} catch (PagamentoNecessarioException e) {
				return e.getMessage();
			} catch (DirezioneNonConsentitaException e) {
				return DIREZIONENONCONSENTITA;
			} catch (ElementoInesistenteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		return Verbo.NON_TROVATO;
	}
*/
}