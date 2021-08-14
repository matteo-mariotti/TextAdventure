package it.uniroma1.textadv.textEngine.verbs;

import it.uniroma1.textadv.characters.Giocatore;
import it.uniroma1.textadv.rooms.ChiaveNecessariaExeption;
import it.uniroma1.textadv.rooms.CollegamentoInesistenteException;
import it.uniroma1.textadv.rooms.DirezioneNonConsentitaException;
import it.uniroma1.textadv.rooms.Room;

public class Entra extends Verbo{
	
	public void esegui(String stanza) {
		try {
			Room l = Giocatore.instanceOf().getStanza().getStanzaconnessa(stanza.strip());
			Giocatore.instanceOf().setRoom(l);
			System.out.println("Ti trovi ora in: " + l.getNome());
		} catch (ChiaveNecessariaExeption e2) {
		
			System.out.println("Ti serve una chiave per aprire questa stanza");
		
		} catch (CollegamentoInesistenteException | DirezioneNonConsentitaException e1) {
			
			Room l;
			try {
				l = Giocatore.instanceOf().getStanza().getDestRoom(stanza);
				Giocatore.instanceOf().setRoom(l);
				System.out.println("Ti trovi ora in: " + l.getNome());
			} catch (CollegamentoInesistenteException | DirezioneNonConsentitaException | ChiaveNecessariaExeption e) {
				System.out.println("Non esiste questa stanza/link!");
			}
			
			

		}
	}

}
