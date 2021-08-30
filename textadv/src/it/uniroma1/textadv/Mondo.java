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
import it.uniroma1.textadv.oggetti.OggettoCheInteragisce;
import it.uniroma1.textadv.oggetti.Tesoro;
import it.uniroma1.textadv.oggetti.links.Link;
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

	public static Mondo fromFile(String s) throws IOException {
		return fromFile(Paths.get(s));
	}

	public static Mondo fromFile(Path path) throws IOException {
		Mondo m = new Mondo();
		// Creo un buffer per leggere il file
		BufferedReader text = Files.newBufferedReader(path);
		// Leggo la prima riga
		String riga = text.readLine();
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
					throw new ErroreCreazioneException();
				mappaStanze.put(info[1], values);
				riga = text.readLine();
				while (!(riga.isEmpty()) && !(riga.startsWith("[") && riga.endsWith("]"))) {
					values.add(riga);
					riga = text.readLine();
				}
			}
			case "links" -> {
				mappaLink = addData(text);
				riga = text.readLine();
			}
			case "objects" -> {
				mappaOggetti = addData(text);
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
				mappaPersonaggi = addData(text);
				riga = text.readLine();

			}
			default -> riga = text.readLine();
			}
		}
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
				| IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
			throw new ErroreCreazioneException();
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
	 */
	private Map<String, Room> creaStanze(Map<String, Link> links, Map<String, Oggetto> oggetti,
			Map<String, Entita> personaggi, Map<String, List<String>> stanze) {
		Map<String, Room> stanzeComplete = new HashMap<>();
		for (String s : stanze.keySet()) {
			if (stanzeComplete.containsKey(s))
				continue;
			creaStanza(s, stanze, links, oggetti, personaggi, stanzeComplete);
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
	 */
	private Room creaStanza(String s, Map<String, List<String>> stanze, Map<String, Link> links,
			Map<String, Oggetto> oggetti, Map<String, Entita> personaggi, Map<String, Room> stanzeComplete) {
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
				}
			}
			case "characters" -> {
				if (dati.length == 2) {
					String[] ents = dati[1].split(",");
					for (String pers : ents) {
						room.addElementi(personaggi.get(pers.strip()));
					}
				}
			}
			case "links" -> {
				String[] direzioni = dati[1].split(",");
				for (String dir : direzioni) {
					switch (dir.charAt(0)) {
					case 'N' -> room.linkN(
							obtainConnection(dir.substring(2), links, s, stanze, stanzeComplete, oggetti, personaggi));
					case 'S' -> room.linkS(
							obtainConnection(dir.substring(2), links, s, stanze, stanzeComplete, oggetti, personaggi));
					case 'W', 'O' -> room.linkW(
							obtainConnection(dir.substring(2), links, s, stanze, stanzeComplete, oggetti, personaggi));
					case 'E' -> room.linkE(
							obtainConnection(dir.substring(2), links, s, stanze, stanzeComplete, oggetti, personaggi));

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
	 * @throws ErroreCreazioneException se la stanza che si cerca di collegare non
	 *                                  esiste
	 */
	private Room.LinkTuple obtainConnection(String link, Map<String, Link> links, String s,
			Map<String, List<String>> stanze, Map<String, Room> stanzeComplete, Map<String, Oggetto> oggetti,
			Map<String, Entita> personaggi) {
		Link l = links.get(link);
		if (l == null) {
			// Se non è un link vedo se mi trovo di fronte ad una stanza
			if (stanze.containsKey(link)) {
				// Vedo se esiste già
				if (stanzeComplete.containsKey(link))
					return new Room.LinkTuple(stanzeComplete.get(link), l);
				// Oppure se la posso creare in ricorsione
				else if (stanze.containsKey(link)) {
					Room r = creaStanza(link, stanze, links, oggetti, personaggi, stanzeComplete);
					return new Room.LinkTuple(r, l);
				}
			}
		} else
			return new Room.LinkTuple(l.getConnection(s), l);
		throw new ErroreCreazioneException();
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
					Constructor<?> costruttore = classe.getConstructor(String.class, Entita.class, Tesoro.class);
					Entita e = null;
					String arg2 = dati.get(2);
					if (personaggi.containsKey(arg2))
						// Se esiste già
						e = personaggi.get(arg2);
					else
						// Altrimenti lo creo in ricorsione
						e = creaPersonaggio(arg2, mappaPersonaggi, oggetti, personaggi);
					Tesoro t = (Tesoro) oggetti.get(dati.get(1));
					if (t == null)
						throw new ErroreCreazioneException();
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
					Constructor<?> costr = classe.getConstructor(String.class);
					OggettoCheInteragisce objInter = (OggettoCheInteragisce) costr.newInstance(nomeOggetto);
					object = objInter;
					// Devo prima inserire l'oggetto per evitare loop nella ricorsione
					oggetti.put(nomeOggetto, objInter);
					Oggetto obj = creaOggetto(dati.get(1), mappaOggetti, links, oggetti, m);
					objInter.addInteraction(obj);
				} else {
					throw new ErroreCreazioneException();
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
						throw new ErroreCreazioneException();
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
				throw new ErroreCreazioneException();
		}
		return links;
	}

	/**
	 * Legge tutti i link/oggetti fino ad incontrare la prossima sezione [....]
	 * 
	 * @param text BufferedReader del file
	 * @return Mappa da stringa ai dati degli oggetti/link
	 * @throws IOException
	 */
	private static Map<String, List<String>> addData(BufferedReader text) throws IOException {
		Map<String, List<String>> mappa = new HashMap<>();
		String riga = text.readLine();
		while (!(riga == null) && !(riga.isEmpty()) && !(riga.startsWith("[") && riga.endsWith("]"))) {
			ArrayList<String> values = new ArrayList<String>();
			String[] dati = eliminaTab(riga);
			if (mappa.containsKey(dati[0]))
				throw new ErroreCreazioneException();
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
