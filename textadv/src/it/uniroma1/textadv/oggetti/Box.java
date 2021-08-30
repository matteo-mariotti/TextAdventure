package it.uniroma1.textadv.oggetti;

import java.util.Map;

import it.uniroma1.textadv.ElementoStanza;
import it.uniroma1.textadv.rooms.ChiaveNecessariaExeption;

public interface Box {

	ElementoStanza getContenuto(String obj) throws ImpossibileOttenereOggetto, ChiaveNecessariaExeption;
	
	
	
}

