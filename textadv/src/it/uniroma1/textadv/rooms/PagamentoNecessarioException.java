package it.uniroma1.textadv.rooms;

import it.uniroma1.textadv.characters.Entita;

public class PagamentoNecessarioException extends Exception {

	
	public PagamentoNecessarioException(Entita e, String s) {
		super(s);
	}
	
	public PagamentoNecessarioException(Entita e) {
		super("Devi prima pagare" + e.getNome() + "!!");
	}
}

