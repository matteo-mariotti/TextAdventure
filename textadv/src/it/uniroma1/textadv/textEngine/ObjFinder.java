package it.uniroma1.textadv.textEngine;

import it.uniroma1.textadv.ElementiStanza;
import it.uniroma1.textadv.characters.Giocatore;
import it.uniroma1.textadv.rooms.ElementoInesistenteException;
import it.uniroma1.textadv.rooms.PagamentoNecessarioException;

public class ObjFinder {

	public static ElementiStanza getArg(String instr) throws ElementoInesistenteException {
		ElementiStanza ogg = Giocatore.instanceOf().getStanza().listaElementi().get(instr.strip());
		if (ogg != null)
			return ogg;
		ogg = Giocatore.instanceOf().getStanza().getLink(instr);
		return ogg;
	}

}
