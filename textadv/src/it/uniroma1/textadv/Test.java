package it.uniroma1.textadv;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.Scanner;

/**
 * Classe di test del gioco
 * 
 * @author matte
 *
 */
public class Test {

	/**
	 * Metodo main del gioco
	 * @param args Args
	 */
	public static void main(String[] args) {
		Gioco g = new Gioco();
		String input;
		Scanner in = null;
		Mondo m;
		try {
		do {
			System.out.println("Inserisci il nome del file .game che si deve trovare nella cartella "
					+ System.getProperty("user.dir"));
			in = new Scanner(System.in);
			input = in.nextLine();
		} while (!(input.endsWith(".game")));
			m = Mondo.fromFile(Paths.get(input));

		do {
			System.out.println("Vuoi fornire un file .ff ? (Y/N)");
			input = in.nextLine();
		} while (!(input.equals("Y")) && !(input.equals("N")));
		if (input.equals("Y")) {
			do {
				System.out.println("Inserisci il nome del file .ff che si deve trovare nella cartella "
						+ System.getProperty("user.dir"));
				input = in.nextLine();
			} while (!(input.endsWith(".ff")));
			g.play(m, Paths.get(input));
		}

		else
			g.play(m);
		} catch (IOException e) {
			System.out.println("File non trovato");
		} finally {
			in.close();
		}
	}

}
