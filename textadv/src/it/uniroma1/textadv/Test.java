package it.uniroma1.textadv;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.nio.file.Paths;
import java.util.Scanner;

/**
 * Classe di test del gioco
 * @author matte
 *
 */
public class Test {

	/**
	 * Metodo main del gioco
	 * @param args Args
	 * @throws ClassNotFoundException Se non trova la classe
	 * @throws NoSuchMethodException Se non trova il metodo
	 * @throws SecurityException Errore 
	 * @throws InstantiationException Erorre
	 * @throws IllegalAccessException Errore
	 * @throws IllegalArgumentException Errore
	 * @throws InvocationTargetException Errore
	 * @throws IOException Errore di lettura del file
	 */
	public static void main(String[] args) throws ClassNotFoundException, NoSuchMethodException, SecurityException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, IOException{
		Gioco g = new Gioco();
		System.out.println("Inserisci il nome del file .game che si deve trovare nella cartella " + System.getProperty("user.dir"));
		Scanner in = new Scanner(System.in);
		String fileName = in.nextLine();
		Mondo m = Mondo.fromFile(Paths.get(fileName));
		System.out.println("Vuoi fornire un file .ff ? (Y/N)");
		if( in.nextLine().equals("Y")) {
			System.out.println("Inserisci il nome del file .ff che si deve trovare nella cartella " + System.getProperty("user.dir"));
			g.play(m, Paths.get(in.nextLine()));
		}
		else
			g.play(m);

		

	}
	
}
