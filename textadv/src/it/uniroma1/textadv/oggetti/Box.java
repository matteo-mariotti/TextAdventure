package it.uniroma1.textadv.oggetti;

import it.uniroma1.textadv.ElementiStanza;
import it.uniroma1.textadv.textEngine.OggettoInesistenteException;

public interface Box {

	ElementiStanza getContenuto(String obj) throws OggettoInesistenteException;
	
	
}

