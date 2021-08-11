package it.uniroma1.textadv.characters;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import it.uniroma1.textadv.oggetti.Oggetto;

public class Venditore extends Personaggio {

	private List<Oggetto> esposizione;

	public Venditore(String nome, Oggetto... oggettiInVendita ) {
		super(nome);
		esposizione = new ArrayList<Oggetto>(Arrays.asList(oggettiInVendita));
	}

	public Venditore(String nome) {
		super(nome);
	}

}
