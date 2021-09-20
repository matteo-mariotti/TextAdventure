package it.uniroma1.textadv.textEngine;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

/**
 * Motore testuale che si occupa di interpretare i comandi inseriti dal giocatore
 * @author matte
 *
 */
public class MotoreTestuale {

	/**
	 * Permette di lettere da tastiera il comando dell'utente
	 * @return Comando diviso dall'interprete
	 */
	public static List<String> leggiComando(){
		String s;
		do {
		@SuppressWarnings("resource")
		Scanner in = new Scanner(System.in);
		s = in.nextLine();
		}while(s.isEmpty());
		return parser(s);
	}

	/**
	 * Permette di dividere secondo l'interprete la stringa in input
	 * @param s Stringa da interpretare
	 * @return Lista con la stringa divisa
	 */
	public static List<String> parser(String s){
		s = s.replaceAll(" il | lo | la | i | gli | le ", " ");
		String[] comando = s.split(" con | su | a | da | nella | nel | in ");
		String[] info = comando[0].split(" ");
		String verbo = info[0].substring(0, 1).toUpperCase() + info[0].substring(1);
		ArrayList<String> parsed = new ArrayList<String>();
		parsed.addAll(List.of(verbo));
		if (info.length > 1) {
			//es. VAI N sarebbe info = vai, N
			comando[0] = comando[0].replace(info[0] + " ", "");
			parsed.addAll(List.of(comando));
		} else if (comando.length > 1)
			parsed.addAll(List.of(Arrays.copyOfRange(comando, 1, comando.length)));
		return parsed;
	}
}
