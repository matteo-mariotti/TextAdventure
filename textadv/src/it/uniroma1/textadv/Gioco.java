package it.uniroma1.textadv;

import java.io.BufferedReader;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Map;
import java.util.function.Supplier;

import it.uniroma1.textadv.characters.Giocatore;
import it.uniroma1.textadv.textEngine.MotoreTestuale;
import it.uniroma1.textadv.textEngine.verbs.Verbo;

public class Gioco {

	public static String ERRORE = "Non ho riconosciuto il comando inserito";
	

	
	public void play(Mondo mondoDiGioco) throws ClassNotFoundException, NoSuchMethodException, SecurityException,
			InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		while (!vittoria(mondoDiGioco)) {
			List<String> comando =	MotoreTestuale.leggiComando();
			System.out.println(exec(comando));
		}
		System.out.println("Bravo!! Hai vinto!!!");
	}
	
	public void play(Mondo mondoDiGioco, Path script)
			throws ClassNotFoundException, NoSuchMethodException, SecurityException, InstantiationException,
			IllegalAccessException, IllegalArgumentException, InvocationTargetException, IOException {
		BufferedReader text = Files.newBufferedReader(script);
		String riga;
		while (!vittoria(mondoDiGioco)) {
			riga = text.readLine().split("//")[0].trim();
			System.out.println("Input: " + riga);
			List<String> comando = MotoreTestuale.parser(riga);
			System.out.println(exec(comando));
		}
		System.out.println("Bravo!! Hai vinto!!!");
	}

	private String exec(List<String> comando) throws NoSuchMethodException, SecurityException, InstantiationException,
			IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		Class<?> classe;
		Method m;
		String res;
		try {
			classe = Class.forName("it.uniroma1.textadv.textEngine.verbs." + comando.get(0)).asSubclass(Verbo.class);
			Constructor<?> costruttore = classe.getConstructor();
			Verbo v = (Verbo) costruttore.newInstance();
			if (comando.size() == 1) {
				m = classe.getMethod("esegui");
				return (String) m.invoke(v);
			} else if (comando.size() == 2) {
				m = classe.getMethod("esegui", String.class);
				return (String) m.invoke(v, comando.get(1));
			} else if (comando.size() == 3) {
				m = classe.getMethod("esegui", String.class, String.class);
				return (String) m.invoke(v, comando.get(1), comando.get(2));
			}
		} catch (ClassNotFoundException e) {
			return ERRORE;
		}
		return ERRORE;
	}

	private boolean vittoria(Mondo m) {
		return Giocatore.instanceOf().getInventario().values().stream().anyMatch(x -> x == m.getWinningObject());
	}

}
