package it.uniroma1.textadv.oggetti;

import it.uniroma1.textadv.rooms.ChiaveNecessariaExeption;

public interface Openable {
	
	String open();
	
	//String open(Oggetto ogg);
	
	boolean unlock(Oggetto ogg) throws ChiaveNecessariaExeption;
		
}
