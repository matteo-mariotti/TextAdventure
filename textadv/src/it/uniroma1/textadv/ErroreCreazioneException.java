package it.uniroma1.textadv;
/**
 * Eccezione che viene lanciata nel momento in cui � presente un errore nel file di configurazione .game
 * @author matte
 *
 */
@SuppressWarnings("serial")
public class ErroreCreazioneException extends RuntimeException {
	
	public ErroreCreazioneException(String mess) {
		super(mess);
	}

}
