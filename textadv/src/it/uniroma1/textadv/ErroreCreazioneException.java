package it.uniroma1.textadv;
/**
 * Eccezione che viene lanciata nel momento in cui è presente un errore nel file di configurazione .game
 * @author matte
 *
 */
@SuppressWarnings("serial")
class ErroreCreazioneException extends RuntimeException {
	
	/**
	 * Costruttore dell'eccezione
	 * @param mess Messaggio di errore
	 */
	ErroreCreazioneException(String mess) {
		super(mess);
	}

}
