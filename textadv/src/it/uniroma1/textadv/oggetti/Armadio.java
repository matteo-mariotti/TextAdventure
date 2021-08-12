package it.uniroma1.textadv.oggetti;

import it.uniroma1.textadv.ElementiStanza;

public class Armadio extends OggettoCheInteragisce implements Openable, Box{
	
	private boolean bOpen = false;

	public Armadio(String nome, Oggetto inter) {
		super(nome, inter);
	}
	
	public Armadio(String nome) {
		super(nome);
	}
	
	
	@Override
	public void open() {
		System.out.println("Ti serve un oggetto per aprire " + super.getNome());
	}
	
	@Override
	public void open(Oggetto ogg) {
		if (ogg instanceof Tronchesi) {
			bOpen = true;
			System.out.println("" + super.getNome() + " è ora aperto");
		}
	}


	public String toString() {
		return bOpen
				? super.interazione == null ? "L'armadio è vuoto" : "L'armadio contiene: " + super.interazione
				: "Questo è un armadio";
	}
	
	@Override
	public ElementiStanza getContenuto(String obj) {
		if (!(bOpen))
			return null; //TODO Eccezione perchè l'armadio è chiusa
		if (obj.equals(super.interazione.getNome())) {
			ElementiStanza o = super.interazione;
			super.interazione = null;
			return o;
		} else
			return null; //TODO Lancia una eccezione perche non contiene l'elemento
	}

	@Override
	public boolean unlock(Oggetto ogg) {
		return true;
	}
	
}
