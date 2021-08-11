package it.uniroma1.textadv.textEngine.verbs;

import it.uniroma1.textadv.characters.Giocatore;
import it.uniroma1.textadv.rooms.ChiaveNecessariaExeption;
import it.uniroma1.textadv.rooms.DirezioneNonConsentitaException;
import it.uniroma1.textadv.rooms.Room;
import it.uniroma1.textadv.textEngine.Direzione;

public class Vai extends Verbo {

	public void esegui(String s){
		String format = s.toUpperCase();
		try {
			Room r = Giocatore.instanceOf().getStanza().getDestRoom(Direzione.valueOf(format));
			Giocatore.instanceOf().setRoom(r);
			System.out.println("Ti trovi ora in: " + r.getNome());
		} catch (IllegalArgumentException | DirezioneNonConsentitaException e) {
			System.out.println("La direzione inserita non è valida");
		}catch (ChiaveNecessariaExeption e) {
			System.out.println("Questo collegamento è chiuso, devi prima aprirlo");
		}
	}

}
