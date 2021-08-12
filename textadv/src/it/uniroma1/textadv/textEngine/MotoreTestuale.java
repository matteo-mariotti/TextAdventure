package it.uniroma1.textadv.textEngine;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class MotoreTestuale {

	public static List<String> leggiComando(){
		Scanner in = new Scanner(System.in);
		return parser(in.nextLine());
	}

	private static List<String> parser(String s){
		s = s.replaceAll(" il | lo | la | i | gli | le | in ", " ");
		String[] comando = s.split(" con | su | a | da | nella ");
		String[] info = comando[0].split(" ");
		String verbo = info[0].substring(0, 1).toUpperCase() + info[0].substring(1);
		ArrayList<String> parsed = new ArrayList<String>();
		parsed.addAll(List.of(verbo));
		if (info.length > 1) {
			comando[0] = comando[0].replace(info[0] + " ", "");
			parsed.addAll(List.of(comando));
		} else if (comando.length > 1)
			parsed.addAll(List.of(Arrays.copyOfRange(comando, 1, comando.length)));
		return parsed;
	}
}
