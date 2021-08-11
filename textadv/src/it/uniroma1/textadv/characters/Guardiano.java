package it.uniroma1.textadv.characters;

import java.util.List;

import it.uniroma1.textadv.oggetti.Oggetto;
import it.uniroma1.textadv.oggetti.Tesoro;

public class Guardiano extends Personaggio{
	
	private Oggetto tesoroSegreto;
	private Entita distrazione;
	private boolean distracted = false;
		
	public Guardiano(String nome, Entita gattinoCalmante, Tesoro tesoro) {
		super(nome);
		distrazione = gattinoCalmante;
		tesoroSegreto = tesoro;
	}
	
	public Guardiano(String nome) {
		super(nome);
	}
	
	public void get(Gatto gattino) {
		if (gattino == distrazione)
			distracted = true;
	}
	
	public Oggetto getTesoro() {
		if (distracted)
			return tesoroSegreto;
		else
			System.out.println("Io sono il protettore del tesoro, non sarà così semplice!!!!!");
		return null;
	}
	
	public void addArguments(Entita ent, Oggetto tes) {
		tesoroSegreto = tes;
		distrazione = ent;
	}
	
}
