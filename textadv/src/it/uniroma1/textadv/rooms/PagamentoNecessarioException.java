package it.uniroma1.textadv.rooms;

import it.uniroma1.textadv.characters.Entita;

public class PagamentoNecessarioException extends Exception {

	private Entita owner;
	
	public PagamentoNecessarioException(Entita e) {
		owner = e;
	}
	
	public String getNomeOwner() {
		return owner.getNome();
	}
	
}

