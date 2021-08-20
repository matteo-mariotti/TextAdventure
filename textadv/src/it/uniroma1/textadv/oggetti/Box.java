package it.uniroma1.textadv.oggetti;

import java.util.Map;

import it.uniroma1.textadv.ElementiStanza;
import it.uniroma1.textadv.rooms.ChiaveNecessariaExeption;

public interface Box {

	ElementiStanza getContenuto(String obj) throws ImpossibileOttenereOggetto, ChiaveNecessariaExeption;
	
	
	
}

