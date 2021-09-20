package it.uniroma1.textadv;

import java.io.BufferedReader;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import it.uniroma1.textadv.characters.Giocatore;
import it.uniroma1.textadv.oggetti.ImpossibileOttenereOggetto;
import it.uniroma1.textadv.rooms.ChiaveNecessariaExeption;
import it.uniroma1.textadv.rooms.DirezioneNonConsentitaException;
import it.uniroma1.textadv.rooms.ElementoInesistenteException;
import it.uniroma1.textadv.rooms.PagamentoNecessarioException;
import it.uniroma1.textadv.textEngine.MotoreTestuale;
import it.uniroma1.textadv.textEngine.verbs.Verbo;

/**
 * Classe che si occupa della gestione del gioco
 * 
 * @author matte
 *
 */
class Gioco { 

	/**
	 * Generica stringa di errore
	 */
	private static final String ERRORE = "Non ho riconosciuto il comando inserito";
	/**
	 * Stringa che indica la vittoria del gioco
	 */
	private static final String VITTORIA = "Bravo!! Hai vinto!!!";
	/**
	 * Generico errore mentre cerco di prendere qualcosa
	 */
	private static String ERROREGENERICO = "Si è verificato un errore mentre cercavi di prendere l'oggetto";
	/**
	 * Errore se provo ad andare in una direzione non consentita
	 */
	private static String DIREZIONENONCONSENTITA = "Non puoi andare in questa direzione";
	/**
	 * Errore se serve una chiave per aprire il link/box
	 */
	private static String CHIAVENECESSARIA = "Questo collegamento/box è chiuso";
	/**
	 * Errore se non trovo ciò con cui vuole interagire il giocatore
	 */
	private static String NON_TROVATO = "Non trovo ciò che stai cercando di usare";
	
	
	/**
	 * Metodo che gestisce i "turni di gioco"
	 * 
	 * @param mondoDiGioco Mondo su cui si sta giocando
	 */
	void play(Mondo mondoDiGioco) { 
		System.out.println(mondoDiGioco.getDescription());
		while (!vittoria(mondoDiGioco)) {
			List<String> comando = MotoreTestuale.leggiComando();
			System.out.println(exec(comando));
		}
		System.out.println(VITTORIA);
	}

	/**
	 * Metodo che gestisce i "turni di gioco" in maniera automatica sulla base di
	 * uno script .ff
	 * 
	 * @param mondoDiGioco Mondo su cui si sta giocando
	 * @param script       Script per la giocata fast forward
	 * @throws IOException Errore in caso di errore nell'apertura del file .ff
	 */
	void play(Mondo mondoDiGioco, Path script) throws IOException {
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
	 * 
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
				return (String) classe.getMethod("esegui", String.class, String.class).invoke(v, comando.get(1),
						comando.get(2));
		} catch (ClassNotFoundException | NoSuchMethodException | SecurityException | InstantiationException
				| IllegalAccessException | IllegalArgumentException e) {
			return ERRORE;
		} catch(InvocationTargetException e){
			try {
				throw e.getCause();
			} catch (ElementoInesistenteException e1) {
				return NON_TROVATO;
			} catch (PagamentoNecessarioException e3) {
				return e3.getMessage();
			} catch (DirezioneNonConsentitaException e1) {
				return DIREZIONENONCONSENTITA;
			} catch (ChiaveNecessariaExeption e1) {
				return CHIAVENECESSARIA;
			} catch (ImpossibileOttenereOggetto e1	) {
				return ERROREGENERICO;
			} catch (Throwable e1) {
				return ERROREGENERICO;
			}
			
		}
		return ERRORE;
	}

	/**
	 * Metodo che controlla se il giocatore ha vinto o meno
	 * 
	 * @param m Mondo di gioco
	 * @return True se il giocatore possiede l'oggetto vincente, false altrimenti
	 */
	private boolean vittoria(Mondo m) {
		return Giocatore.instanceOf().getInventario().values().stream().anyMatch(x -> x == m.getWinningObject());
	}

}
