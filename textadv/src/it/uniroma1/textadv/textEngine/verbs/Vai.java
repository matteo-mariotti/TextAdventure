package it.uniroma1.textadv.textEngine.verbs;

import it.uniroma1.textadv.characters.Giocatore;
import it.uniroma1.textadv.rooms.ChiaveNecessariaExeption;
import it.uniroma1.textadv.rooms.DirezioneNonConsentitaException;
import it.uniroma1.textadv.rooms.Room;
import it.uniroma1.textadv.textEngine.Direzione;

/**
 * Verbo vai
 * @author matte
 *
 */
public class Vai extends Verbo implements VerboUnitario{

	@Override
	public String esegui(String s){
		String format = s.toUpperCase();
		try {
			Room r = Giocatore.instanceOf().getStanza().getDestRoom(Direzione.valueOf(format));
			return Giocatore.instanceOf().setRoom(r);
		} catch (IllegalArgumentException | DirezioneNonConsentitaException e) {
			return "La direzione inserita non è valida";
		}catch (ChiaveNecessariaExeption e) {
			return "Questo collegamento è chiuso, devi prima aprirlo";
		}
	}

}
