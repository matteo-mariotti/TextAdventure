package it.uniroma1.textadv.rooms;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import it.uniroma1.textadv.ElementiStanza;
import it.uniroma1.textadv.characters.Entita;
import it.uniroma1.textadv.oggetti.Oggetto;
import it.uniroma1.textadv.oggetti.links.Link;
import it.uniroma1.textadv.textEngine.Direzione;
import it.uniroma1.textadv.textEngine.OggettoInesistenteException;

public class Room {

	public static class LinkTuple {

		Room stanza;
		Link l;

		public LinkTuple(Room stanza, Link l) {
			this.stanza = stanza;
			this.l = l;
		}

		public Room getRoom() {
			return stanza;
		}

		public Link getLink() {
			return l;
		}

		public String getNome() {
			return l.getNome();
		}

		public String getNomeStanza() {
			return stanza.getNome();
		}

		public boolean locked() {
			if (l!=null)
				return l.status();
			return false;
		}
		
		public String toString() {
			return l == null ? stanza.getNome() : l.getNome();
		}
	}

	private static Map<String, Room> listaStanze = new HashMap<>();

	private String nomeStanza;
	private String description;
	private Map<String, ElementiStanza> elementi = new HashMap<>();
	private LinkTuple linkN;
	private LinkTuple linkS;
	private LinkTuple linkW;
	private LinkTuple linkE;

	public static Room getInstance(String nome) {
		if (listaStanze.containsKey(nome))
			return listaStanze.get(nome);
		else {
			Room r = new Room(nome);
			listaStanze.put(nome, r);
			return r;
		}
	}

	private Room(String nome) {
		nomeStanza = nome;
	}

	public Room addDescription(String description) {
		this.description = description;
		return this;
	}

	public String getNome() {
		return nomeStanza;
	}

	public void linkN(LinkTuple link) {
		linkN = link;
	}

	public void linkS(LinkTuple link) {
		linkS = link;
	}

	public void linkW(LinkTuple link) {
		linkW = link;
	}
	
	public void linkE(LinkTuple link) {
		linkE = link;
	}
	
	public Room getDestRoom(Direzione d) throws DirezioneNonConsentitaException, ChiaveNecessariaExeption {
		return switch(d) {
		case NORD, N -> getDirection(linkN);
		case SUD, S -> getDirection(linkS);
		case WEST, O, OVEST, W -> getDirection(linkW);
		case EAST, EST, E -> getDirection(linkE);
		};
	}

	public ElementiStanza getElemento(String nomeElemento) throws OggettoInesistenteException {
		if (elementi.containsKey(nomeElemento)) {
			ElementiStanza e =elementi.get(nomeElemento);
			elementi.remove(nomeElemento);
			return e;}
		throw new OggettoInesistenteException();
	}
	
	public Link getLink(String s) throws CollegamentoInesistenteException {
		List<LinkTuple> l =  new ArrayList<>();
		l.add(linkE);
		l.add(linkN);
		l.add(linkS);
		l.add(linkW);
		List<Link>	l1  = l.stream().filter(x->x!=null).filter(x->x.getLink()!=null).filter(x -> x.getNome().equals(s)).map(x -> x.getLink()).collect(Collectors.toList());
		if (l.isEmpty())
			throw new CollegamentoInesistenteException();
		return l1.get(0);
	}

	private Room getDirection(LinkTuple link) throws DirezioneNonConsentitaException, ChiaveNecessariaExeption {
		if (link == null)
			throw new DirezioneNonConsentitaException();	
		if (link.locked())
			throw new ChiaveNecessariaExeption(); 
		return link.getRoom();
	}
	
	private Room getDirection(LinkTuple l, String s) throws DirezioneNonConsentitaException, ChiaveNecessariaExeption {
		if (l == null)
			throw new DirezioneNonConsentitaException();
		if (l.getLink() != null) {
			if (l.getNome().equals(s) || l.getNomeStanza().equals(s)) {
				if (l.locked())
					throw new ChiaveNecessariaExeption(); 
				return l.getRoom();}
		}
		return l.getRoom();
	}

	public void addElementi(ElementiStanza ogg) {
		elementi.put(ogg.getNome(), ogg);
	}

	public String describe() {
		return toString();
	}

	public Map<String, ElementiStanza> listaElementi() {
		return elementi;
	}

	public String toString() {
		StringBuffer s = new StringBuffer();
		s.append("Nome: " + nomeStanza + " ");
		s.append("Descrizione: " + description + " ");
		s.append(linkN == null ? "" : "N: " + linkN + " ");
		s.append(linkS == null ? "" : "S: " + linkS + " ");
		s.append(linkE == null ? "" : "E: " + linkE + " ");
		s.append(linkW == null ? "" : "W: " + linkW + " ");
		s.append("La stanza contiene: ");
		s.append(elementi.keySet());
		return s.toString();
	}
}
