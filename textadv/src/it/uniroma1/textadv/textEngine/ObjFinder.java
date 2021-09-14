package it.uniroma1.textadv.textEngine;

import java.util.List;
import java.util.stream.Collectors;

import it.uniroma1.textadv.ElementoStanza;
import it.uniroma1.textadv.characters.Giocatore;
import it.uniroma1.textadv.oggetti.Box;
import it.uniroma1.textadv.oggetti.ImpossibileOttenereOggetto;
import it.uniroma1.textadv.rooms.ChiaveNecessariaExeption;
import it.uniroma1.textadv.rooms.ElementoInesistenteException;
import it.uniroma1.textadv.rooms.PagamentoNecessarioException;

public class ObjFinder {

	public static ElementoStanza getArg(String instr) throws ImpossibileOttenereOggetto, ChiaveNecessariaExeption, PagamentoNecessarioException, ElementoInesistenteException{
		try {
		ElementoStanza ogg = Giocatore.instanceOf().getStanza().listaElementi().get(instr.strip());
		if (ogg != null)
			return ogg;
			ogg = Giocatore.instanceOf().getStanza().getLink(instr);
		return ogg;
		} catch (ElementoInesistenteException e) {
			//Vedo se sta in un Box
			return elem(instr);
		}
	}
	
	
	private static ElementoStanza elem(String oggetto) throws ImpossibileOttenereOggetto, ChiaveNecessariaExeption, PagamentoNecessarioException, ElementoInesistenteException {
		List<Box> l = Giocatore.instanceOf().getStanza().listaElementi().values().stream().filter(x -> x instanceof Box)
				.map(x -> (Box) x).filter(x-> x.getOggInter().getNome().equals(oggetto)).collect(Collectors.toList());
		if (l.isEmpty())
			throw new ElementoInesistenteException();
		return l.get(0).getContenuto(oggetto);
	}

}
