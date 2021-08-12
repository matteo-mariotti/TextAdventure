package it.uniroma1.textadv.textEngine;

import it.uniroma1.textadv.ElementiStanza;
import it.uniroma1.textadv.characters.Giocatore;
import it.uniroma1.textadv.oggetti.links.Link;
import it.uniroma1.textadv.rooms.CollegamentoInesistenteException;

public class ObjFinder {

	public static ElementiStanza getArg(String instr) throws OggettoInesistenteException {
		ElementiStanza ogg = Giocatore.instanceOf().getStanza().listaElementi().get(instr.strip());
		if (ogg != null)
			return ogg;
		throw new OggettoInesistenteException();
	}
	
	
}
