package it.uniroma1.textadv.textEngine.verbs;

public abstract class Verbo {

	protected static String NON_TROVATO = "Ciò che stai cercando di usare non esiste!!";
	protected static String NOT_SPECIFIED = "Devi specificare come usare il verbo";

	public String esegui() {
		return NOT_SPECIFIED;
	}
	
}
