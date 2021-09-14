package it.uniroma1.textadv.rooms;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import it.uniroma1.textadv.ElementoStanza;
import it.uniroma1.textadv.oggetti.links.Link;
import it.uniroma1.textadv.textEngine.Direzione;

/**
 * Classe che modella una stanza di gioco
 * 
 * @author matte
 *
 */
public class Room {

	/**
	 * Classe che modella una Tupla contenente il link e/o la stanza connessa
	 * 
	 * @author matte
	 *
	 */
	public static class LinkTuple {

		/**
		 * Stanza collegata
		 */
		Room stanza;
		/**
		 * Link collegato
		 */
		Link l;

		/**
		 * Costruttore della classe
		 * 
		 * @param stanza Stanza da collegare
		 * @param l      Link da collegare
		 */
		public LinkTuple(Room stanza, Link l) {
			this.stanza = stanza;
			this.l = l;
		}

		/**
		 * Permette di ottenere la stanza connessa
		 * @return Stanza
		 */
		public Room getRoom() {
			return stanza;
		}

		/**
		 * Permette di ottenere il link connesso
		 * 
		 * @return Link
		 */
		public Link getLink() {
			return l;
		}

		/**
		 * Permette di ottenere il nome del link
		 * 
		 * @return Stringa con il nome del link
		 */
		public String getNome() {
			return l != null ? l.getNome() : "Link non presente";
		}

		/**
		 * Stringa con il nome della stanza
		 * 
		 * @return Stringa con il nome della stanza
		 */
		public String getNomeStanza() {
			return stanza != null ? stanza.getNome() : "Stanza non presente";
		}

		/**
		 * Metodo che permette di verificare se il link è chiuso o aperto
		 * 
		 * @return True o false se il link è bloccato o meno
		 */
		public boolean locked() {
			if (l != null)
				return l.status();
			return false;
		}

		@Override
		public String toString() {
			return l == null ? stanza.getNome() : l.getNome();
		}
	}

	/**
	 * Mappa delle stanza per implementare il Multiton
	 */
	private static Map<String, Room> listaStanze = new HashMap<>();

	/**
	 * Nome della stanza
	 */
	private String nomeStanza;
	/**
	 * Descrizione della stanza
	 */
	private String description;
	/**
	 * Mappa degli elementi presenti nella stanza
	 */
	private Map<String, ElementoStanza> elementi = new HashMap<>();
	/**
	 * Collegamento a Nord
	 */
	private LinkTuple linkN;
	/**
	 * Collegamento a Sud
	 */
	private LinkTuple linkS;
	/**
	 * Collegamento a Ovest
	 */
	private LinkTuple linkW;
	/**
	 * Collegamento a Est
	 */
	private LinkTuple linkE;
	/**
	 *	Collegamento bonus 
	 */
	private LinkTuple bonusB;
	
	
	/**
	 * Metodo per ottenere l'unica istanza di una room con quel nome
	 * 
	 * @param nome Nome della stanza
	 * @return Istanza della stanza
	 */
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

	/**
	 * Metodo per aggiungere una descrizione alla stanza
	 * 
	 * @param description Descrizione della stanza
	 */
	public void addDescription(String description) {
		this.description = description;
	}

	/**
	 * Fornisce il nome della stanza
	 * 
	 * @return Nome della stanza
	 */
	public String getNome() {
		return nomeStanza;
	}

	/**
	 * Permette di aggiungere un collegamento alla stanza
	 * 
	 * @param link Collegamento da aggiungere
	 */
	public void linkN(LinkTuple link) {
		linkN = link;
	}

	/**
	 * Permette di aggiungere un collegamento alla stanza
	 * 
	 * @param link Collegamento da aggiungere
	 */
	public void linkS(LinkTuple link) {
		linkS = link;
	}

	/**
	 * Permette di aggiungere un collegamento alla stanza
	 * 
	 * @param link Collegamento da aggiungere
	 */
	public void linkW(LinkTuple link) {
		linkW = link;
	}

	/**
	 * Permette di aggiungere un collegamento alla stanza
	 * 
	 * @param link Collegamento da aggiungere
	 */
	public void linkE(LinkTuple link) {
		linkE = link;
	}
	
	/**
	 * Permette di aggiungere un collegamento alla stanza
	 * 
	 * @param link Collegamento da aggiungere
	 */
	public void bonusB(LinkTuple link) {
		bonusB = link;
	}

	/**
	 * Fornita una direzione in cui mi voglio recare fornisce l'istanza della stanza
	 * presente in quella direzione
	 * 
	 * @param d Direzione in cui andare
	 * @return Stanza destinazione
	 * @throws DirezioneNonConsentitaException Se non posso andare in quella
	 *                                         direzione
	 * @throws ChiaveNecessariaExeption        Se il collegamento è chiuso
	 */
	public Room getDestRoom(Direzione d) throws DirezioneNonConsentitaException, ChiaveNecessariaExeption {
		return switch (d) {
		case NORD, N -> getDirection(linkN);
		case SUD, S -> getDirection(linkS);
		case WEST, O, OVEST, W -> getDirection(linkW);
		case EAST, EST, E -> getDirection(linkE);
		case B -> getDirection(bonusB);
		};
	}

	/**
	 * Permette di prendere un elemento dalla stanza se non appartiene a nessuna
	 * entità
	 * 
	 * @param nomeElemento Nome dell'elemento da prendere
	 * @return Elemento della stanza cercato
	 * @throws ElementoInesistenteException Se non è presente l'elemento che cerco
	 * @throws PagamentoNecessarioException Se è necessario pagare una entità prima
	 *                                      di prendere l'elemento
	 */
	public ElementoStanza getElemento(String nomeElemento)
			throws ElementoInesistenteException, PagamentoNecessarioException {
		if (elementi.containsKey(nomeElemento)) {
			ElementoStanza e = elementi.get(nomeElemento);
			if (e.getOwner() == null) {
				elementi.remove(nomeElemento);
				return e;
			} else
				throw new PagamentoNecessarioException(e.getOwner());
		}
		throw new ElementoInesistenteException();
	}

	/**
	 * Fornisce la Room corrispondente al nome della stanza/link passato in input
	 * 
	 * @param nomeLinkStanza Nome della stanza/link da cercare
	 * @return Stanza corrispondere alla stringa in input
	 * @throws ElementoInesistenteException Se non esiste la stanza/link
	 * @throws DirezioneNonConsentitaException Se non si può andare in quella direzione
	 * @throws ChiaveNecessariaExeption Se serve una chiave per aprire il link/stanza
	 */
	public Room getDestRoom(String nomeLinkStanza)
			throws ElementoInesistenteException, DirezioneNonConsentitaException, ChiaveNecessariaExeption {
		try {
			return getDirection(
					getLinkTuple(nomeLinkStanza, x -> x.getLink() != null, x -> x.getNome().equals(nomeLinkStanza)));
		} catch (ElementoInesistenteException | DirezioneNonConsentitaException e) {
			return getDirection(getLinkTuple(nomeLinkStanza, x -> x.getRoom() != null,
					x -> x.getNomeStanza().equals(nomeLinkStanza)));
		}
	}

	/**
	 * Fornisce il link corrispondente alla stringa passata in input
	 * 
	 * @param s Link da cercare
	 * @return Link trovato
	 * @throws ElementoInesistenteException Se non esiste un link così chiamato
	 */
	public Link getLink(String s) throws ElementoInesistenteException {
		return getLinkTuple(s, x -> x.getLink() != null, x -> x.getNome().equals(s)).getLink();
	}

	/**
	 * Fornisce la LinkTuple corrispondente alla stringa passata secondo i predicati
	 * indicati
	 * 
	 * @param s  Nome del link/stanza
	 * @param p  Predicato filtro 1
	 * @param p1 Predicato filtro 2
	 * @return LinkTuple corrispondente alla ricerca effettuata
	 * @throws ElementoInesistenteException Se la ricerca non produce risultati
	 */
	private LinkTuple getLinkTuple(String s, Predicate<LinkTuple> p, Predicate<LinkTuple> p1)
			throws ElementoInesistenteException {
		List<LinkTuple> l = new ArrayList<>();
		l.add(linkE);
		l.add(linkN);
		l.add(linkS);
		l.add(linkW);
		l.add(bonusB);
		l = l.stream().filter(x -> x != null).filter(p).filter(p1).collect(Collectors.toList());
		if (l.isEmpty())
			throw new ElementoInesistenteException();
		return l.get(0);
	}

	/**
	 * Fornita una LinkTuple restituisce la stanza corrispondente
	 * 
	 * @param link LinkTuple cercata
	 * @return Stanza corrispondente
	 * @throws DirezioneNonConsentitaException Se non posso andare in quella
	 *                                         direzione
	 * @throws ChiaveNecessariaExeption        Se serve una chiave per aprire il
	 *                                         link
	 */
	private Room getDirection(LinkTuple link) throws DirezioneNonConsentitaException, ChiaveNecessariaExeption {
		if (link == null)
			throw new DirezioneNonConsentitaException();
		if (link.locked())
			throw new ChiaveNecessariaExeption();
		return link.getRoom();
	}

	/**
	 * Permette di aggiugere elementi alla stanza
	 * 
	 * @param ogg Elemento da aggiungere
	 */
	public void addElementi(ElementoStanza ogg) {
		elementi.put(ogg.getNome(), ogg);
	}

	/**
	 * Descrive la stanza
	 * 
	 * @return Descrizione della stanza
	 */
	public String describe() {
		return toString();
	}

	/**
	 * Permette di ottenere la mappa degli elementi della stanza
	 * 
	 * @return Mappa degli elementi
	 */
	public Map<String, ElementoStanza> listaElementi() {
		return elementi;
	}

	@Override
	public String toString() {
		StringBuffer s = new StringBuffer();
		s.append("Nome: " + nomeStanza + " ");
		s.append("\nDescrizione: " + description + " ");
		s.append(linkN == null ? "" : "\nN: " + linkN + " ");
		s.append(linkS == null ? "" : "\nS: " + linkS + " ");
		s.append(linkE == null ? "" : "\nE: " + linkE + " ");
		s.append(linkW == null ? "" : "\nW: " + linkW + " ");
		s.append(bonusB == null ? "" : "\nB: " + bonusB + " ");
		s.append("\nLa stanza contiene: ");
		s.append(elementi.keySet());
		return s.toString();
	}
}
