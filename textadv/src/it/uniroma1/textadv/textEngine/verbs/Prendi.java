package it.uniroma1.textadv.textEngine.verbs;

import java.util.List;
import java.util.stream.Collectors;

import it.uniroma1.textadv.ElementiStanza;
import it.uniroma1.textadv.characters.Giocatore;
import it.uniroma1.textadv.oggetti.Box;
import it.uniroma1.textadv.rooms.ChiaveNecessariaExeption;
import it.uniroma1.textadv.rooms.CollegamentoInesistenteException;
import it.uniroma1.textadv.rooms.DirezioneNonConsentitaException;
import it.uniroma1.textadv.rooms.PagamentoNecessarioException;
import it.uniroma1.textadv.rooms.Room;
import it.uniroma1.textadv.textEngine.ObjFinder;
import it.uniroma1.textadv.textEngine.OggettoInesistenteException;

public class Prendi extends Verbo {

	public void esegui(String oggetto, String oggettoContenitore) throws OggettoInesistenteException {
		Object ogg2 = ObjFinder.getArg(oggettoContenitore);
		if (ogg2 instanceof Box) {
			Box b = (Box) ogg2;
			ElementiStanza o = b.getContenuto(oggetto);
			Giocatore.instanceOf().addOggetto(o);
			System.out.println("Hai ottenuto: " + o.getNome());
		} else {
			System.out.println("Non puoi prendere oggetti da " + oggettoContenitore);
		}
	}

	// Prendi un oggetto dalla stanza, da un Box, oppure prendi un link
	public void esegui(String oggetto) {
		ElementiStanza ogg;
		try {
			ogg = Giocatore.instanceOf().getStanza().getElemento(oggetto);
			Giocatore.instanceOf().addOggetto(ogg);
			System.out.println("Hai ottenuto: " + ogg.getNome());
		} catch (OggettoInesistenteException e) {
			try {
				Room l = Giocatore.instanceOf().getStanza().getDestRoom(oggetto);
				Giocatore.instanceOf().setRoom(l);
				System.out.println("Ti trovi ora in: " + l.getNome());
			} catch (ChiaveNecessariaExeption e2) {
				System.out.println("Ti serve una chiave per aprire questo collegamento");
			} catch (CollegamentoInesistenteException | DirezioneNonConsentitaException e1) {
				List<Box> l = Giocatore.instanceOf().getStanza().listaElementi().values().stream()
						.filter(x -> x instanceof Box).map(x -> (Box) x).collect(Collectors.toList());
				for (int i = 0; i < l.size(); i++) {
					ElementiStanza o;
					try {
						o = l.get(i).getContenuto(oggetto);
						Giocatore.instanceOf().addOggetto(o);
						System.out.println("Hai ottenuto: " + o.getNome());
					} catch (OggettoInesistenteException e2) {
						if (i != l.size()-1)
							continue;
						System.out.println("Non esiste nè un oggetto nè un link che si chiama così nella stanza");
					}

				}
			}

		} catch (PagamentoNecessarioException e3) {
			System.out.println("Devi pagare " + e3.getNomeOwner() + " per prendere questo oggetto");
		}
	}

}
