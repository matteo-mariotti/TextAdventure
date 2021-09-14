package it.uniroma1.textadv;

import java.io.BufferedReader;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import it.uniroma1.textadv.characters.Entita;
import it.uniroma1.textadv.characters.Giocatore;
import it.uniroma1.textadv.characters.Guardiano;
import it.uniroma1.textadv.oggetti.Oggetto;
import it.uniroma1.textadv.oggetti.Tesoro;
import it.uniroma1.textadv.oggetti.links.Link;
import it.uniroma1.textadv.rooms.DirezioneNonConsentitaException;
import it.uniroma1.textadv.rooms.Room;

/**
 * Classe che provvede ad istanziare il mondo di gioco sulla base del file di
 * configurazione .game fornito
 * 
 * @author matte
 *
 */
public class Mondo {
	/**
	 * Unica istanza del Giocatore presente nel gioco
	 */
	Giocatore player = Giocatore.instanceOf();
	/**
	 * Nome del mondo
	 */
	String worldName;
	/**
	 * Descrizione del mondo
	 */
	String description;
	/**
	 * Stanza di inizio gioco
	 */
	String startingRoom;
	/**
	 * Oggetto con cui si vince il gioco
	 */
	Tesoro oggettoVittoria;

	/**
	 * Metodo per impostare il nome del mondo
	 * 
	 * @param nome Nuovo nome del mondo
	 */
	private void setName(String nome) {
		worldName = nome;
	}

	/**
	 * Metodo per impostare la descrizione del mondo
	 * 
	 * @param desc Descrizione del mondo
	 */
	private void setDesc(String desc) {
		description = desc;
	}

	/**
	 * Metodo di utilita che divide le righe di input su tab
	 * 
	 * @param s
	 * @return
	 */
	private static String[] eliminaTab(String s) {
		return s.split("\t");
	}

	/**
	 * Metodo che fornisce l'oggetto vincente
	 * 
	 * @return Oggetto vincente
	 */
	public Tesoro getWinningObject() {
		return oggettoVittoria;
	}

	/**
	 * Metodo che si occupa di creare il mondo
	 * @param s Nome del file .game da cui creare il mondo
	 * @return Mondo istanziato
	 * @throws IOException Se c'è un errore durante la lettura del file
	 */
	public static Mondo fromFile(String s) throws IOException {
		return fromFile(Paths.get(s));
	}

	/**
	 * Metodo che si occupa di creare il mondo
	 * @param path Path del file .game da cui creare il mondo
	 * @return Mondo istanziato
	 * @throws IOException Se c'è un errore durante la lettura del file
	 */
	public static Mondo fromFile(Path path) throws IOException {
		Mondo m = new Mondo();
		// Creo un buffer per leggere il file
		BufferedReader text = Files.newBufferedReader(path);
		// Leggo la prima riga
		String riga = text.readLine();
		//Lista che uso per verificare se ci sono due elementi con lo stesso nome
		List<String> lista = new ArrayList<>();
		// Mi creo dei dizionari per tenere traccia di ogni elemento e dei suoi dati
		Map<String, List<String>> mappaOggetti = new HashMap<>();
		Map<String, List<String>> mappaStanze = new HashMap<>();
		Map<String, List<String>> mappaLink = new HashMap<>();
		Map<String, List<String>> mappaPersonaggi = new HashMap<>();
		while (riga != null) {
			// La prima riga è sicuramente nella forma [...]
			// Divido la stringa per avere il tipo di dato e il nome, se presente
			if (riga.isEmpty()) {
				riga = text.readLine();
				continue;
			}
			riga = riga.strip();
			String[] info = riga.substring(1, riga.length() - 1).split(":");
			switch (info[0]) {
			case "world" -> {
				m.setName(info[1]);
				m.setDesc(eliminaTab(text.readLine())[1]);
				m.startingRoom = eliminaTab(text.readLine())[1];
				riga = text.readLine();
			}
			case "room" -> {
				ArrayList<String> values = new ArrayList<String>();
				if (mappaStanze.containsKey(info[1]))
					throw new ErroreCreazioneException("Non possono esiste due stanze con lo stesso nome!");
				mappaStanze.put(info[1], values);
				riga = text.readLine();
				while (!(riga.isEmpty()) && !(riga.startsWith("[") && riga.endsWith("]"))) {
					values.add(riga);
					riga = text.readLine();
				}
			}
			case "links" -> {
				mappaLink = addData(text, lista);
				riga = text.readLine();
			}
			case "objects" -> {
				mappaOggetti = addData(text, lista);
				riga = text.readLine();
			}
			case "player" -> {
				riga = text.readLine();
				if (m.player.getNome() == null)
					m.player.setName(riga.split("\t")[0].strip());
				else
					throw new GiocatoreException();
				riga = text.readLine();
			}
			case "characters" -> {
				mappaPersonaggi = addData(text, lista);
				riga = text.readLine();

			}
			default -> riga = text.readLine();
			}
		}
		//System.out.println(mappaOggetti);
		//System.out.println(mappaStanze);
		//System.out.println(mappaLink);
		//System.out.println(mappaPersonaggi);

		// Inizio a creare gli oggetti del mondo
		m.initialize(m, mappaOggetti, mappaStanze, mappaLink, mappaPersonaggi);

		return m;
	}

	/**
	 * Metodo che si occupa di creare gli oggetti, i personaggi e le stanze del
	 * mondo, emette eccezione in caso di errore nel file di configurazione del
	 * mondo
	 * 
	 * @param mondo           Mondo di gioco
	 * @param mappaOggetti    Mappa che contiene gli oggetti con i rispettivi dati
	 * @param mappaStanze     Mappa che contiene le stanze con i relativi dati
	 * @param mappaLink       Mappa con i link e i loro dati
	 * @param mappaPersonaggi Mappa con i personaggi e i loro dati
	 */
	private void initialize(Mondo mondo, Map<String, List<String>> mappaOggetti, Map<String, List<String>> mappaStanze,
			Map<String, List<String>> mappaLink, Map<String, List<String>> mappaPersonaggi) {

		try {
			Map<String, Link> links = creaLink(mappaLink, mappaStanze);
			Map<String, Oggetto> oggetti = creaOggetti(mappaOggetti, links, mondo);
			Map<String, Entita> personaggi = creaPersonaggi(mappaPersonaggi, oggetti);
			Map<String, Room> stanze = creaStanze(links, oggetti, personaggi, mappaStanze);
			mondo.player.setRoom(stanze.get(mondo.startingRoom));
		} catch (ClassNotFoundException | NoSuchMethodException | SecurityException | InstantiationException
				| IllegalAccessException | IllegalArgumentException | InvocationTargetException | DirezioneNonConsentitaException e) {
			e.printStackTrace();
			throw new ErroreCreazioneException("Si è verificato un errore durante la creazione del mondo");
		}

	}

	/**
	 * Iterando sulle stanze da creare chiama il metodo "creaStanza"
	 * 
	 * @param links      Mappa dei links
	 * @param oggetti    Mappa degli oggetti
	 * @param personaggi Mappa dei personaggi
	 * @param stanze     Mappa delle stanze
	 * @return Mappa con chiave la Stringa che indentifica la stanza e la
	 *         corrispondente Room associata
	 * @throws DirezioneNonConsentitaException 
	 */
	private Map<String, Room> creaStanze(Map<String, Link> links, Map<String, Oggetto> oggetti,
			Map<String, Entita> personaggi, Map<String, List<String>> stanze) throws DirezioneNonConsentitaException {
		Map<String, Room> stanzeComplete = new HashMap<>();
		List<String> usati = new ArrayList<>();
		for (String s : stanze.keySet()) {
			if (stanzeComplete.containsKey(s))
				continue;
			creaStanza(s, stanze, links, oggetti, personaggi, stanzeComplete, usati);
		}
		return stanzeComplete;
	}

	/**
	 * Crea una stanza con i rispettivi oggetti, links e personaggi
	 * 
	 * @param s              Nome della nuova stanza
	 * @param stanze         Mappa delle stanze definite nel .game (con dati)
	 * @param links          Mappa dei link
	 * @param oggetti        Mappa degli oggetto
	 * @param personaggi     Mappa dei personaggi
	 * @param stanzeComplete Mappa che "raccoglie" le nuove istanze create
	 * @return La nuova stanza creata
	 * @throws DirezioneNonConsentitaException Se la stanza è connessa ad un'altra che non esiste
	 */
	private Room creaStanza(String s, Map<String, List<String>> stanze, Map<String, Link> links,
			Map<String, Oggetto> oggetti, Map<String, Entita> personaggi, Map<String, Room> stanzeComplete, List<String> used) throws DirezioneNonConsentitaException {
		s = s.strip();
		Room room = Room.getInstance(s);
		stanzeComplete.put(s, room);
		List<String> datiStanza = stanze.get(s);
		for (int i = 0; i < datiStanza.size(); i++) {
			String[] dati = eliminaTab(datiStanza.get(i));
			switch (dati[0]) {
			case "description" -> {
				room.addDescription(dati[1].strip());
			}
			case "objects" -> {
				String[] ogg = dati[1].split(",");
				for (String oggetto : ogg) {
					room.addElementi(oggetti.get(oggetto.strip()));
					if (used.contains(oggetto.strip()))
						throw new ErroreCreazioneException("Non puoi mettere " + oggetto.strip() + " in due stanze!");
					used.add(oggetto.strip());
				}
			}
			case "characters" -> {
				if (dati.length == 2) {
					String[] ents = dati[1].split(",");
					for (String pers : ents) {
						room.addElementi(personaggi.get(pers.strip()));
						if (used.contains(pers.strip()))
							throw new ErroreCreazioneException("Non puoi mettere " + pers.strip() + " in due stanze!");
						used.add(pers.strip());
					}
				}
			}
			case "links" -> {
				String[] direzioni = dati[1].split(",");
				for (String dir : direzioni) {
					switch (dir.charAt(0)) {
					case 'N' -> room.linkN(
							obtainConnection(dir.substring(2), links, s, stanze, stanzeComplete, oggetti, personaggi, used));
					case 'S' -> room.linkS(
							obtainConnection(dir.substring(2), links, s, stanze, stanzeComplete, oggetti, personaggi, used));
					case 'W', 'O' -> room.linkW(
							obtainConnection(dir.substring(2), links, s, stanze, stanzeComplete, oggetti, personaggi, used));
					case 'E' -> room.linkE(
							obtainConnection(dir.substring(2), links, s, stanze, stanzeComplete, oggetti, personaggi, used));
					case 'B' -> room.bonusB(
							obtainConnection(dir.substring(2), links, s, stanze, stanzeComplete, oggetti, personaggi, used));

					}
				}
			}

			}
		}
		return room;
	}

	/**
	 * Metodo che fornisce la tupla link/stanza collegata alla stanza che chiama il
	 * metodo stesso
	 * 
	 * @param link           Nome della stanza da collegare
	 * @param links          Mappa dei link
	 * @param stanze         Mappa delle stanze da creare
	 * @param stanzeComplete Mappa delle stanze complete
	 * @param oggetti        Mappa degli oggetti
	 * @param personaggi     Mappa dei personaggi
	 * @return Tupla con il collegamento
	 * @throws DirezioneNonConsentitaException Se il collegamento all'altra stanza è inesistente
	 * @throws ErroreCreazioneException se la stanza che si cerca di collegare non
	 *                                  esiste
	 */
	private Room.LinkTuple obtainConnection(String link, Map<String, Link> links, String s,
			Map<String, List<String>> stanze, Map<String, Room> stanzeComplete, Map<String, Oggetto> oggetti,
			Map<String, Entita> personaggi, List<String> used) throws DirezioneNonConsentitaException {
		Link l = links.get(link);
		if (l == null) {
			// Se non è un link vedo se mi trovo di fronte ad una stanza
			if (stanze.containsKey(link)) {
				// Vedo se esiste già
				if (stanzeComplete.containsKey(link))
					return new Room.LinkTuple(stanzeComplete.get(link), l);
				// Oppure se la posso creare in ricorsione
				else if (stanze.containsKey(link)) {
					Room r = creaStanza(link, stanze, links, oggetti, personaggi, stanzeComplete, used);
					return new Room.LinkTuple(r, l);
				}
			}
		} else
			return new Room.LinkTuple(l.getConnection(s), l);
		throw new ErroreCreazioneException("La connessione con la stanza " + link + " è inesistente");
	}

	/**
	 * Metodo che crea i personaggi
	 * 
	 * @param mappaPersonaggi Mappa dei personaggi con i loro dati
	 * @param oggetti         Mappa degli oggetti
	 * @return Mappa da string a personaggio con quel nome
	 * @throws ClassNotFoundException
	 * @throws NoSuchMethodException
	 * @throws SecurityException
	 * @throws InstantiationException
	 * @throws IllegalAccessException
	 * @throws IllegalArgumentException
	 * @throws InvocationTargetException
	 */
	private Map<String, Entita> creaPersonaggi(Map<String, List<String>> mappaPersonaggi, Map<String, Oggetto> oggetti)
			throws ClassNotFoundException, NoSuchMethodException, SecurityException, InstantiationException,
			IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		Map<String, Entita> personaggi = new HashMap<>();
		for (String s : mappaPersonaggi.keySet()) {
			creaPersonaggio(s, mappaPersonaggi, oggetti, personaggi);
		}
		return personaggi;

	}

	/**
	 * Metodo che crea il singolo personaggio
	 * 
	 * @param nomePersonaggio Nome del personaggio da creare
	 * @param mappaPersonaggi Mappa dei personaggi
	 * @param oggetti         Mappa degli oggetti
	 * @param personaggi      Mappa dei personaggi
	 * @return Entita creata
	 * @throws ClassNotFoundException
	 * @throws NoSuchMethodException
	 * @throws SecurityException
	 * @throws InstantiationException
	 * @throws IllegalAccessException
	 * @throws IllegalArgumentException
	 * @throws InvocationTargetException
	 */
	private Entita creaPersonaggio(String nomePersonaggio, Map<String, List<String>> mappaPersonaggi,
			Map<String, Oggetto> oggetti, Map<String, Entita> personaggi)
			throws ClassNotFoundException, NoSuchMethodException, SecurityException, InstantiationException,
			IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		List<String> dati = mappaPersonaggi.get(nomePersonaggio);
		Entita ent = null;
		if (!personaggi.containsKey(nomePersonaggio)) {
			String nomeClasse = dati.get(0);
			Class<?> classe = Class.forName("it.uniroma1.textadv.characters." + nomeClasse).asSubclass(Entita.class);
			if (dati.size() == 3) {
				// Devo gestire la particolarità di venditore e guardiano
				if (nomeClasse.equals("Venditore")) {
					Oggetto[] lista = new Oggetto[2];
					for (int i = 1; i < dati.size(); i++)
						lista[i - 1] = oggetti.get(dati.get(i));
					// Prendo il costruttore con i parametri adatti
					Constructor<?> costruttore = classe.getConstructor(String.class, Oggetto[].class);
					ent = (Entita) costruttore.newInstance(nomePersonaggio, lista);
					personaggi.put(nomePersonaggio, ent);
				} else {
					// Sto creando un guardiano
					Constructor<?> costruttore = classe.getConstructor(String.class, ElementoStanza.class, ElementoStanza.class);
					ElementoStanza e = cercaDato(dati.get(2), mappaPersonaggi, oggetti, personaggi);
					ElementoStanza t = cercaDato(dati.get(1), mappaPersonaggi, oggetti, personaggi);
					Guardiano g = (Guardiano) costruttore.newInstance(nomePersonaggio, e, t);
					ent = g;
					personaggi.put(nomePersonaggio, ent);

				}
			} else {
				Constructor<?> costruttore = classe.getConstructor(String.class);
				ent = (Entita) costruttore.newInstance(nomePersonaggio);
				personaggi.put(nomePersonaggio, ent);
			}
		}
		return ent;
	}

	private ElementoStanza cercaDato(String s, Map<String, List<String>> mappaPersonaggi,
			Map<String, Oggetto> oggetti, Map<String, Entita> personaggi ) throws ClassNotFoundException, NoSuchMethodException, SecurityException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		if (personaggi.containsKey(s))
			// Se esiste già
			return personaggi.get(s);
		else if (oggetti.containsKey(s))
			return oggetti.get(s);
		else if (mappaPersonaggi.containsKey(s))
			// Altrimenti lo creo in ricorsione
			return creaPersonaggio(s, mappaPersonaggi, oggetti, personaggi);
		throw new ErroreCreazioneException("L'oggetto " + s + "non esiste!");
	}
	
	/**
	 * Crea gli oggetti del mondo
	 * 
	 * @param mappaOggetti Mappa degli oggetti del mondo da creare
	 * @param links        Mappa dei link
	 * @param m            Mondo di gioco
	 * @return Mappa degli oggetti instanziati
	 * @throws ClassNotFoundException
	 * @throws NoSuchMethodException
	 * @throws SecurityException
	 * @throws InstantiationException
	 * @throws IllegalAccessException
	 * @throws IllegalArgumentException
	 * @throws InvocationTargetException
	 */
	private Map<String, Oggetto> creaOggetti(Map<String, List<String>> mappaOggetti, Map<String, Link> links, Mondo m)
			throws ClassNotFoundException, NoSuchMethodException, SecurityException, InstantiationException,
			IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		Map<String, Oggetto> oggetti = new HashMap<>();
		for (String s : mappaOggetti.keySet()) {
			creaOggetto(s, mappaOggetti, links, oggetti, m);
		}
		return oggetti;
	}

	/**
	 * Crea l'oggetto richiesto
	 * 
	 * @param nomeOggetto  Nome dell'oggetto da creare
	 * @param mappaOggetti Mappa degli oggetti da istanziare
	 * @param links        Mappa dei link istanziati
	 * @param oggetti      Mappa degli oggetti istanziati
	 * @param m            Mondo di gioco
	 * @return Oggetto istanziato
	 * @throws ClassNotFoundException
	 * @throws NoSuchMethodException
	 * @throws SecurityException
	 * @throws InstantiationException
	 * @throws IllegalAccessException
	 * @throws IllegalArgumentException
	 * @throws InvocationTargetException
	 */
	private Oggetto creaOggetto(String nomeOggetto, Map<String, List<String>> mappaOggetti, Map<String, Link> links,
			Map<String, Oggetto> oggetti, Mondo m)
			throws ClassNotFoundException, NoSuchMethodException, SecurityException, InstantiationException,
			IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		List<String> dati = mappaOggetti.get(nomeOggetto);
		Oggetto object = null;
		// Creo l'oggetto solo se ancora non è stato istanziato, altrimenti restituisco
		// quello già istanziato
		if (!oggetti.keySet().contains(nomeOggetto)) {
			Class<?> classe = Class.forName("it.uniroma1.textadv.oggetti." + dati.get(0)).asSubclass(Oggetto.class);
			if (dati.size() == 2) {
				// Se i dati hanno lunghezza due allora l'oggetto interagisce
				Constructor<?> costruttore = classe.getConstructor(String.class, Oggetto.class);
				String interazione = dati.get(1);
				if (links.containsKey(interazione)) {
					// Se l'interazione è un link
					Link link = links.get(interazione);
					object = (Oggetto) costruttore.newInstance(nomeOggetto, link);
					oggetti.put(nomeOggetto, object);
					link.setClosed(object);
				} else if (oggetti.containsKey(interazione)) {
					// Se è un oggetto
					Oggetto ogg = oggetti.get(interazione);
					object = (Oggetto) costruttore.newInstance(nomeOggetto, ogg);
					oggetti.put(nomeOggetto, object);
				} else if (mappaOggetti.containsKey(interazione)) {
					Oggetto obj = creaOggetto(dati.get(1), mappaOggetti, links, oggetti, m);
					Constructor<?> costr = classe.getConstructor(String.class, Oggetto.class);
					object = (Oggetto) costr.newInstance(nomeOggetto, obj);
					oggetti.put(nomeOggetto, object);
				} else {
					throw new ErroreCreazioneException("Non posso trovare l'oggetto " + interazione);
				}
			} else {
				// Se non interagisce
				Constructor<?> costruttore = classe.getConstructor(String.class);
				object = (Oggetto) costruttore.newInstance(nomeOggetto);
				oggetti.put(nomeOggetto, object);
				if (dati.get(0).equals("Tesoro"))
					if (m.oggettoVittoria == null)
						m.oggettoVittoria = (Tesoro) object;
					else
						throw new ErroreCreazioneException("Non possono esistere due tesori nel gioco!");
			}

		} else
			object = oggetti.get(nomeOggetto);
		return object;
	}

	/**
	 * Itera sui link da istanziare e li crea
	 * 
	 * @param mappaLink
	 * @param mappaStanze
	 * @return
	 * @throws ClassNotFoundException
	 * @throws NoSuchMethodException
	 * @throws SecurityException
	 * @throws InstantiationException
	 * @throws IllegalAccessException
	 * @throws IllegalArgumentException
	 * @throws InvocationTargetException
	 */
	private Map<String, Link> creaLink(Map<String, List<String>> mappaLink, Map<String, List<String>> mappaStanze)
			throws ClassNotFoundException, NoSuchMethodException, SecurityException, InstantiationException,
			IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		Map<String, Link> links = new HashMap<>();
		for (String s : mappaLink.keySet()) {
			List<String> dati = mappaLink.get(s);
			Class<?> classe = Class.forName("it.uniroma1.textadv.oggetti.links." + dati.get(0)).asSubclass(Link.class);
			// Controllo se le stanze sono valide
			if (mappaStanze.keySet().contains(dati.get(1)) && mappaStanze.keySet().contains(dati.get(2))) {
				Constructor<?> costruttore = classe.getConstructor(String.class, Room.class, Room.class);
				Room partenza = Room.getInstance(dati.get(1));
				Room destinazione = Room.getInstance(dati.get(2));
				Link link = (Link) costruttore.newInstance(s, partenza, destinazione);
				links.put(s, link);
			} else
				throw new ErroreCreazioneException("Le stanze collegate da questo link "+ s +" non esistono");
		}
		return links;
	}

	/**
	 * Legge tutti i link/oggetti fino ad incontrare la prossima sezione [....]
	 * 
	 * @param text BufferedReader del file
	 * @param listaCreati lista che tiene traccia degli oggetti già creati (non possono esistere due oggetti con lo stesso nome)
	 * @return Mappa da stringa ai dati degli oggetti/link
	 * @throws IOException
	 */
	private static Map<String, List<String>> addData(BufferedReader text, List<String> listaCreati) throws IOException {
		Map<String, List<String>> mappa = new HashMap<>();
		String riga = text.readLine();
		while (!(riga == null) && !(riga.isEmpty()) && !(riga.startsWith("[") && riga.endsWith("]"))) {
			ArrayList<String> values = new ArrayList<String>();
			String[] dati = eliminaTab(riga);
			if (listaCreati.contains(dati[0]))
				throw new ErroreCreazioneException("Non possono esistere due elementi con lo stesso nome nel file .game");
			listaCreati.add(dati[0]);
			mappa.put(dati[0], values);
			values.add(dati[1].split("//")[0].strip());
			if (dati.length >= 3)
				values.add(dati[2].split("//")[0].strip());
			if (dati.length == 4)
				values.add(dati[3]);
			riga = text.readLine();
		}
		return mappa;
	}

	@Override
	public String toString() {
		return worldName + " - " + description + " - " + startingRoom + " - " + player + " - ";
	}

}
