package it.uniroma1.textadv.textEngine.verbs;

import java.util.List;
import java.util.stream.Collectors;

import it.uniroma1.textadv.ElementoStanza;
import it.uniroma1.textadv.characters.Giocatore;
import it.uniroma1.textadv.oggetti.Box;
import it.uniroma1.textadv.oggetti.ImpossibileOttenereOggetto;
import it.uniroma1.textadv.oggetti.links.Link;
import it.uniroma1.textadv.rooms.ChiaveNecessariaExeption;
import it.uniroma1.textadv.rooms.DirezioneNonConsentitaException;
import it.uniroma1.textadv.rooms.ElementoInesistenteException;
import it.uniroma1.textadv.rooms.PagamentoNecessarioException;
import it.uniroma1.textadv.rooms.Room;
import it.uniroma1.textadv.textEngine.ObjFinder;

public class Prendi extends Verbo {

	private static String NOTABOX = "Non puoi prendere oggetti da ";
	private static String ERROREGENERICO = "Si è verificato un errore mentre cercavi di prendere l'oggetto";
	private static String CONTENITOREBLOCCATO = "Prima di prendere l'oggetto devi aprire il contenitore che lo contiene";
	private static String DIREZIONENONCONSENTITA = "Non puoi andare in questa direzione";
	private static String CHIAVENECESSARIA = "Ti serve una chiave per aprire questo collegamento";

	public String esegui(String oggetto, String oggettoContenitore) {
		try {
			ElementoStanza box = ObjFinder.getArg(oggettoContenitore);
			if (box instanceof Box)
				return getOggetto((Box) box, oggetto);
			return NOTABOX + oggettoContenitore;
		} catch (ElementoInesistenteException e) {
			return Verbo.NON_TROVATO;
		}
	}

	private String getOggetto(Box oggBox, String ogg) {
		try {
				ElementoStanza o = oggBox.getContenuto(ogg);
				return Giocatore.instanceOf().addOggetto(o);
		} catch (ImpossibileOttenereOggetto e) {
			return ERROREGENERICO;
		} catch (ChiaveNecessariaExeption e) {
			return CONTENITOREBLOCCATO;
		}
	}

	// Prendi un oggetto dalla stanza, da un Box, oppure prendi un link
	public String esegui(String oggetto) {
		ElementoStanza ogg;
		try {
			ogg = ObjFinder.getArg(oggetto);
			if (ogg.getOwner() != null)
				throw new PagamentoNecessarioException(ogg.getOwner());
			if (ogg instanceof Link) {
				// Se vuole prendere un link lo sposto
				Room r = Giocatore.instanceOf().getStanza().getDestRoom(ogg.getNome());
				return Giocatore.instanceOf().setRoom(r);
			}
			// Se vuole prendere un oggetto lo aggiungo all'inventario
			return Giocatore.instanceOf().addOggetto(ogg);
		} catch (ElementoInesistenteException e) {
			return checkBox(oggetto);
		} catch (PagamentoNecessarioException e3) {
			return e3.getMessage();
		} catch (DirezioneNonConsentitaException e) {
			return DIREZIONENONCONSENTITA;
		} catch (ChiaveNecessariaExeption e) {
			return CHIAVENECESSARIA;

		}
	}

	private String checkBox(String oggetto) {
		// Vedo se vuole prendere un oggetto da un box (prendi chiave teletrasporto)
		List<Box> l = Giocatore.instanceOf().getStanza().listaElementi().values().stream().filter(x -> x instanceof Box)
				.map(x -> (Box) x).collect(Collectors.toList());
		for (int i = 0; i < l.size(); i++) {
			return getOggetto(l.get(i), oggetto);
		}
		return Verbo.NON_TROVATO;
	}

}
