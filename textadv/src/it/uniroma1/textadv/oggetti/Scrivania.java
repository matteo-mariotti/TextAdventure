package it.uniroma1.textadv.oggetti;

import it.uniroma1.textadv.ElementiStanza;

public class Scrivania extends OggettoCheInteragisce implements Openable, Box {

	private boolean bOpen = false;

	public Scrivania(String nome, Oggetto inter) {
		super(nome, inter);
	}

	public Scrivania(String nome) {
		super(nome);
	}

	@Override
	public void open() {
		bOpen = true;
		System.out.println("La scrivania è ora aperta");
	}
	
	@Override
	public void open(Oggetto ogg) {
		System.out.println("Non hai bisogno di un oggetto per aprire " + super.getNome());
		open();
	}

	public String toString() {
		return bOpen
				? super.interazione == null ? "La scrivania è vuota" : "La scrivania contiene: " + super.interazione
				: "Questa è una scrivania";
	}

	@Override
	public ElementiStanza getContenuto(String obj) {
		if (!(bOpen))
			return null; //TODO Eccezione perchè la scrivania è chiusa
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
