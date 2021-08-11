package it.uniroma1.textadv;

import java.io.BufferedReader;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import it.uniroma1.textadv.characters.Entita;
import it.uniroma1.textadv.characters.Giocatore;
import it.uniroma1.textadv.characters.Guardiano;
import it.uniroma1.textadv.oggetti.Chiave;
import it.uniroma1.textadv.oggetti.Oggetto;
import it.uniroma1.textadv.oggetti.OggettoCheInteragisce;
import it.uniroma1.textadv.oggetti.Tesoro;
import it.uniroma1.textadv.oggetti.links.Link;
import it.uniroma1.textadv.rooms.Room;

public class Mondo {

	Giocatore player = Giocatore.instanceOf();
	String worldName;
	String description;
	String startingRoom;
	Tesoro oggettoVittoria;
	Map<String, Room> listaStanze = new HashMap<>();

	private void setName(String nome) {
		worldName = nome;
	}

	private void setDesc(String desc) {
		description = desc;
	}

	private static String[] eliminaTab(String s) {
		return s.split("\t");
	}

	public Tesoro getWinningObject() {
		return oggettoVittoria;
	}

	public static Mondo fromFile(Path path) throws IOException, ClassNotFoundException, NoSuchMethodException, SecurityException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException
			 {
		Mondo m = new Mondo();
		// Creo un buffer per leggere il file
		BufferedReader text = Files.newBufferedReader(path);
		// Leggo la prima riga
		String riga = text.readLine();
		// Mi creo dei dizionari per tenere traccia di ogni elemento e dei suoi dati
		// Lo faccio in mondo da poter controllare l'esistenza, ad esempio, degli
		// oggetti delle stanze prima di crearli
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
				mappaStanze.putIfAbsent(info[1], values);
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
				m.player.setName(riga.split("\t")[0].strip());
				riga = text.readLine();
				if (!(riga == null)) {
					System.out.println("Più di un giocatore");
					// TODO SOLLEVA UNA ECCEZIONE PERCHè C'è PIù DI UN PLAYER
				}
			}
			case "characters" -> {
				mappaPersonaggi = addData(text);
				riga = text.readLine();

			}
			default -> riga = text.readLine();
			}
		}
		/*
		 * System.out.println(mappaStanze); System.out.println(mappaOggetti);
		 * System.out.println(mappaLink); System.out.println(mappaPersonaggi);
		 * System.out.println(m.player);
		 */

		// Inizio a creare gli oggetti del mondo
		m.initialize(m, mappaOggetti, mappaStanze, mappaLink, mappaPersonaggi, m);
		m.player.setRoom(m.listaStanze.get(m.startingRoom));
//		System.out.println(m.player.getStanza());
		for (String s : m.listaStanze.keySet())
		{
			System.out.println(m.listaStanze.get(s));
		}
		return m;
	}

	private void initialize(Mondo mondo, Map<String, List<String>> mappaOggetti, Map<String, List<String>> mappaStanze,
			Map<String, List<String>> mappaLink, Map<String, List<String>> mappaPersonaggi, Mondo m) throws ClassNotFoundException, NoSuchMethodException, SecurityException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException
			 {

		Map<String, Link> links = creaLink(mappaLink, mappaStanze); // Creo i link
		// System.out.println(links);
		Map<String, Oggetto> oggetti = creaOggetti(mappaOggetti, links, m); // Creo gli oggetti
		// System.out.println(oggetti);
		Map<String, Entita> personaggi = creaPersonaggi(mappaPersonaggi, oggetti);
		// System.out.println(personaggi);
		Map<String, Room> stanze = creaStanze(links, oggetti, personaggi, mappaStanze);
		// System.out.println(stanze);

		m.listaStanze = stanze;
	}

	private Map<String, Room> creaStanze(Map<String, Link> links, Map<String, Oggetto> oggetti,
			Map<String, Entita> personaggi, Map<String, List<String>> stanze){
		Map<String, Room> stanzeComplete = new HashMap<>();
		for (String s : stanze.keySet()) {
			if (stanzeComplete.containsKey(s))
				continue;
			creaStanza(s, stanze, links, oggetti, personaggi, stanzeComplete);
		}
		return stanzeComplete;
	}
	
	private Room creaStanza(String s, Map<String, List<String>> stanze, Map<String, Link> links, Map<String, Oggetto> oggetti,
			Map<String, Entita> personaggi, Map<String, Room> stanzeComplete) {
		Room room = Room.getInstance(s);
		stanzeComplete.put(s, room);
		for (int i = 0; i < stanze.get(s).size(); i++) {
			String[] dati = stanze.get(s).get(i).split("\t");
			switch (dati[0]) {
			case "description" -> {
				room.addDescription(dati[1]);
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
						room.addElementi(personaggi.get(pers));
					}
				}
			}
			case "links" -> {
				String[] direzioni = dati[1].split(",");
				for (String dir : direzioni) {
					switch (dir.charAt(0)) {
					case 'N' -> room.linkN(obtainConnection(dir.substring(2), links, s, stanze, stanzeComplete, oggetti, personaggi));
					case 'S' -> room.linkS(obtainConnection(dir.substring(2), links, s, stanze, stanzeComplete, oggetti, personaggi));
					case 'W', 'O' -> room.linkW(obtainConnection(dir.substring(2), links, s, stanze, stanzeComplete, oggetti, personaggi));
					case 'E' -> room.linkE(obtainConnection(dir.substring(2), links, s, stanze, stanzeComplete, oggetti, personaggi));

					}
				}
			}

			}
		}
		return room;
	}

	private Room.LinkTuple obtainConnection(String link, Map<String, Link> links, String s, Map<String, List<String>> stanze, Map<String, Room> stanzeComplete, Map<String, Oggetto> oggetti,
			Map<String, Entita> personaggi) {
		Link l = links.get(link);
		if (l == null) {
			//Se non è un link vedo se mi trovo di fronte ad una stanza
			if (stanze.containsKey(link)) {
				//Vedo se esiste già
				if (stanzeComplete.containsKey(link))
					return new Room.LinkTuple(stanzeComplete.get(link), l);
				else {
					Room r = creaStanza(link, stanze, links, oggetti, personaggi, stanzeComplete);
					return new Room.LinkTuple(r, l);
				}
			}
		}else
			return new Room.LinkTuple(l.getConnection(s), l);
		return null; //TODO Devo lanciare una eccezione consona all'errore (non è ne un link ne una stanza)
	}
	
	private Map<String, Entita> creaPersonaggi(Map<String, List<String>> mappaPersonaggi, Map<String, Oggetto> oggetti)
			throws ClassNotFoundException, NoSuchMethodException, SecurityException, InstantiationException,
			IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		Map<String, Entita> personaggi = new HashMap<>();
		for (String s : mappaPersonaggi.keySet()) {
			creaPersonaggio(s, mappaPersonaggi, oggetti, personaggi);
		}
		return personaggi;

	}

	private Entita creaPersonaggio(String nomePersonaggio, Map<String, List<String>> mappaPersonaggi,
			Map<String, Oggetto> oggetti, Map<String, Entita> personaggi)
			throws ClassNotFoundException, NoSuchMethodException, SecurityException, InstantiationException,
			IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		List<String> dati = mappaPersonaggi.get(nomePersonaggio);
		Entita ent = null;
		if (!personaggi.containsKey(nomePersonaggio)) {
			Class<?> classe = Class.forName("it.uniroma1.textadv.characters." + dati.get(0));
			if (dati.size() == 3) {
				if (dati.get(0).equals("Venditore")) {
					Oggetto[] lista = new Oggetto[2];
					for (int i = 1; i < dati.size(); i++) {
						lista[i - 1] = oggetti.get(dati.get(i));
					}
					Constructor<?> costruttore = classe.getConstructor(String.class, Oggetto[].class);
					ent = (Entita) costruttore.newInstance(nomePersonaggio, lista);
					personaggi.put(nomePersonaggio, ent);
				} else {
					Constructor<?> costruttore = classe.getConstructor(String.class);
					Entita e = null;
					Guardiano g;
					g = (Guardiano) costruttore.newInstance(nomePersonaggio);
					ent = g;
					personaggi.put(nomePersonaggio, ent);
					if (personaggi.containsKey(dati.get(2))) {
						e = personaggi.get(dati.get(2));
					} else {
						e = creaPersonaggio(dati.get(2), mappaPersonaggi, oggetti, personaggi);
					}
					g.addArguments(e, oggetti.get(dati.get(1)));

				}
			} else {
				Constructor<?> costruttore = classe.getConstructor(String.class);
				ent = (Entita) costruttore.newInstance(nomePersonaggio);
				personaggi.put(nomePersonaggio, ent);
			}
		}
		return ent;
	}

	private Map<String, Oggetto> creaOggetti(Map<String, List<String>> mappaOggetti, Map<String, Link> links, Mondo m)
			throws ClassNotFoundException, NoSuchMethodException, SecurityException, InstantiationException,
			IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		Map<String, Oggetto> oggetti = new HashMap<>();
		for (String s : mappaOggetti.keySet()) {
			creaOggetto(s, mappaOggetti, links, oggetti, m);
		}
		return oggetti;
	}

	private Oggetto creaOggetto(String nomeOggetto, Map<String, List<String>> mappaOggetti, Map<String, Link> links,
			Map<String, Oggetto> oggetti, Mondo m)
			throws ClassNotFoundException, NoSuchMethodException, SecurityException, InstantiationException,
			IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		List<String> dati = mappaOggetti.get(nomeOggetto);
		Oggetto object = null;
		if (!oggetti.keySet().contains(nomeOggetto)) {
			Class<?> classe = Class.forName("it.uniroma1.textadv.oggetti." + dati.get(0));
			if (dati.size() == 2) {
				Constructor<?> costruttore = classe.getConstructor(String.class, Oggetto.class);
				if (links.containsKey(dati.get(1))) {
					Link link = links.get(dati.get(1));
					object = (Oggetto) costruttore.newInstance(nomeOggetto, link);
					oggetti.put(nomeOggetto, object);
					link.setClosed(object);
				} else if (oggetti.containsKey(dati.get(1))) {
					Oggetto ogg = oggetti.get(dati.get(1));
					object = (Oggetto) costruttore.newInstance(nomeOggetto, ogg);
					oggetti.put(nomeOggetto, object);
				} else if (mappaOggetti.containsKey(dati.get(1))) {
					Constructor<?> costr = classe.getConstructor(String.class);
					OggettoCheInteragisce objInter = (OggettoCheInteragisce) costr.newInstance(nomeOggetto);
					object = objInter;
					oggetti.put(nomeOggetto, objInter);
					Oggetto obj = creaOggetto(dati.get(1), mappaOggetti, links, oggetti, m);
					objInter.addInteraction(obj);
				} else {
					// TODO Genera una eccezione perchè l'elemento non esiste
				}
			} else {
				Constructor<?> costruttore = classe.getConstructor(String.class);
				object = (Oggetto) costruttore.newInstance(nomeOggetto);
				oggetti.put(nomeOggetto, object);
				if (dati.get(0).equals("Tesoro"))
					// TODO Eccezione se esiste già un oggetto vincente
					m.oggettoVittoria = (Tesoro) object;
			}

		} else {
			return oggetti.get(nomeOggetto);
		}
		return object;
	}

	private Map<String, Link> creaLink(Map<String, List<String>> mappaLink, Map<String, List<String>> mappaStanze)
			throws ClassNotFoundException, NoSuchMethodException, SecurityException, InstantiationException,
			IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		Map<String, Link> links = new HashMap<>();
		for (String s : mappaLink.keySet()) {
			List<String> dati = mappaLink.get(s);
			Class<?> classe = Class.forName("it.uniroma1.textadv.links." + dati.get(0));
			// Controllo se le stanze sono valide
			if (mappaStanze.keySet().contains(dati.get(1)) && mappaStanze.keySet().contains(dati.get(2))) {
				Constructor<?> costruttore = classe.getConstructor(String.class, Room.class, Room.class);
				Room partenza = Room.getInstance(dati.get(1));
				Room destinazione = Room.getInstance(dati.get(2));
				Link link = (Link) costruttore.newInstance(s, partenza, destinazione);
				links.put(s, link);
			} else
				// TODO SOLLEVA UNA ECCEZIONE SE LA STANZA NON ESISTE
				System.out.println("Stanza non presente");
		}
		return links;
	}

	private static Map<String, List<String>> addData(BufferedReader text) throws IOException {
		Map<String, List<String>> mappa = new HashMap<>();
		String riga = text.readLine();
		while (!(riga == null) && !(riga.isEmpty()) && !(riga.startsWith("[") && riga.endsWith("]"))) {
			ArrayList<String> values = new ArrayList<String>();
			String[] dati = eliminaTab(riga);
			mappa.putIfAbsent(dati[0], values);
			values.add(dati[1].split("//")[0].strip());
			if (dati.length >= 3)
				values.add(dati[2].split("//")[0].strip());
			if (dati.length == 4)
				values.add(dati[3]);
			riga = text.readLine();
		}
		return mappa;
	}

	public String toString() {
		return worldName + " - " + description + " - " + startingRoom + " - " + player + " - " + listaStanze;
	}

}
