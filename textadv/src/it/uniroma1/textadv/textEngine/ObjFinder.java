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

/**
 * Classe con un solo metodo statico
 * @author matte
 *
 */
public class ObjFinder {

	/**
	 * Metodo statico per ottenere un riferimento ad un oggetto o link nella stanza
	 * @param instr Oggetto o link da ottenere
	 * @return Oggetto cercato
	 * @throws ImpossibileOttenereOggetto Se non posso ottenere l'oggetto
	 * @throws ChiaveNecessariaExeption Se serve una chiave per aprire il link o il box
	 * @throws PagamentoNecessarioException Se devo pagare qualcuno prima di prendere l'oggetto
	 * @throws ElementoInesistenteException Se non trovo l'elemento
	 */
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
	
	/**
	 * Controlla se l'elemento che cerco si trova in uno dei Box della stanza 
	 * @param oggetto Oggetto cercato 
	 * @return Oggetto cercato
	 * @throws ImpossibileOttenereOggetto Se non posso ottenere l'oggetto
	 * @throws ChiaveNecessariaExeption Se serve una chiave per aprire il box
	 * @throws PagamentoNecessarioException Se devo pagare qualcuno prima di prendere l'oggetto
	 * @throws ElementoInesistenteException Se l'oggetto non esiste
	 */
	private static ElementoStanza elem(String oggetto) throws ImpossibileOttenereOggetto, ChiaveNecessariaExeption, PagamentoNecessarioException, ElementoInesistenteException {
		List<Box> l = Giocatore.instanceOf().getStanza().listaElementi().values().stream().filter(x -> x instanceof Box)
				.map(x -> (Box) x).collect(Collectors.toList());
		Box b = l.stream().filter(x->x.getOggInter().getNome().equals(oggetto)).findFirst().orElseThrow(ElementoInesistenteException::new);
		return b.getContenuto(oggetto);
	}

}
