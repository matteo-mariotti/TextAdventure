package it.uniroma1.textadv.oggetti;

import it.uniroma1.textadv.ElementiStanza;
import it.uniroma1.textadv.textEngine.OggettoInesistenteException;

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
	public ElementiStanza getContenuto(String obj) throws OggettoInesistenteException {
		if (!(bOpen))
			return null; //TODO Eccezione perchè l'armadio è chiusa
		if (super.interazione == null)
			throw new OggettoInesistenteException(); //TODO Lancia una eccezione perche non contiene l'elemento

		if (obj.equals(super.interazione.getNome())) {
			ElementiStanza o = super.interazione;
			super.interazione = null;
			return o;
		} else
			throw new OggettoInesistenteException(); //TODO Lancia una eccezione perche non contiene l'elemento
	}

	@Override
	public boolean unlock(Oggetto ogg) {
		return true;
	}
	
}
