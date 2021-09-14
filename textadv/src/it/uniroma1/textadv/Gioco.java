package it.uniroma1.textadv;

import java.io.BufferedReader;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import it.uniroma1.textadv.characters.Giocatore;
import it.uniroma1.textadv.textEngine.MotoreTestuale;
import it.uniroma1.textadv.textEngine.verbs.Verbo;
/**
 * Classe che si occupa della gestione del gioco
 * @author matte
 *
 */
public class Gioco {

	/**
	 * Generica stringa di errore
	 */
	public static final String ERRORE = "Non ho riconosciuto il comando inserito";
	/**
	 * Stringa che indica la vittoria del gioco
	 */
	public static final String VITTORIA = "Bravo!! Hai vinto!!!";

	/**
	 * Metodo che gestisce i "turni di gioco"
	 * @param mondoDiGioco Mondo su cui si sta giocando
	 */
	public void play(Mondo mondoDiGioco){
		System.out.println(mondoDiGioco.description);
		while (!vittoria(mondoDiGioco)) {
			List<String> comando =	MotoreTestuale.leggiComando();
			System.out.println(exec(comando));
		}
		System.out.println(VITTORIA);
	}
	
	/**
	 * Metodo che gestisce i "turni di gioco" in maniera automatica sulla base di uno script .ff
	 * @param mondoDiGioco Mondo su cui si sta giocando
	 * @param script Script per la giocata fast forward
	 * @throws IOException Errore in caso di errore nell'apertura del file .ff
	 */
	public void play(Mondo mondoDiGioco, Path script) throws IOException{
		BufferedReader text = Files.newBufferedReader(script);
		String riga;
		while (!vittoria(mondoDiGioco)) {
			riga = text.readLine().split("//")[0].trim();
			System.out.println("Input: " + riga);
			List<String> comando = MotoreTestuale.parser(riga);
			System.out.println(exec(comando));
		}
		System.out.println(VITTORIA);
	}

	/**
	 * Metodo che, preso l'input, si occupa di interpretarlo correttamente
	 * @param comando Comando diviso in parti (es. Verbo, arg1, arg2)
	 * @return Stringa con il risultato
	 */
	private String exec(List<String> comando) {
		Class<?> classe;
		try {
			classe = Class.forName("it.uniroma1.textadv.textEngine.verbs." + comando.get(0)).asSubclass(Verbo.class);
			Constructor<?> costruttore = classe.getConstructor();
			Verbo v = (Verbo) costruttore.newInstance();
			if (comando.size() == 1) 
				return (String) classe.getMethod("esegui").invoke(v);
			else if (comando.size() == 2)
				return (String) classe.getMethod("esegui", String.class).invoke(v, comando.get(1));
			else if (comando.size() == 3)
				return (String) classe.getMethod("esegui", String.class, String.class).invoke(v, comando.get(1), comando.get(2));
		} catch (ClassNotFoundException | NoSuchMethodException | SecurityException | InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
			return ERRORE;
		}
		return ERRORE;
	}

	/**
	 * Metodo che controlla se il giocatore ha vinto o meno
	 * @param m Mondo di gioco
	 * @return True se il giocatore possiede l'oggetto vincente, false altrimenti
	 */
	private boolean vittoria(Mondo m) {
		return Giocatore.instanceOf().getInventario().values().stream().anyMatch(x -> x == m.getWinningObject());
	}

}
