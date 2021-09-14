package it.uniroma1.textadv.oggetti;

public class Letto extends Box implements Openable{

	public Letto(String nome, Oggetto inter) {
		super(nome, inter);
	}

	public Letto(String nome) {
		super(nome, null);
	}
	
	@Override
	public String open() {
		super.setStatus(true);
		return "Il letto è ora aperto, puoi vedere cosa c'è nel cassone sotto";
	}

	@Override
	public boolean unlock(OggettoCheInteragisce ogg) {
		return true;
	}
}
