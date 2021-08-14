package it.uniroma1.textadv.oggetti;

import it.uniroma1.textadv.ElementiStanza;
import it.uniroma1.textadv.textEngine.OggettoInesistenteException;

public class Camino extends OggettoCheInteragisce implements Box{

	boolean acceso = true;

	public Camino(String nome, Oggetto inter) {
		super(nome, inter);
	}

	public Camino(String nome) {
		super(nome);
	}

	public String toString() {
		return "Il camino � " + (acceso ? "acceso" : "spento") + " e contiene: " + super.interazione;
	}

	public void spegni(Secchio sec) {
		if (sec.filled()) {
			acceso = false;
			sec.empty();
			System.out.println("Camino spento");
		} else {
			System.out.println("Il secchio era vuoto!!!");
		}

	}
	
	@Override
	public ElementiStanza getContenuto(String obj) throws OggettoInesistenteException, ImpossibileOttenereOggetto{
		if (acceso)
			throw new ImpossibileOttenereOggetto();//TODO Eccezione perch� il camino � acceso
		if (super.interazione == null)
			throw new OggettoInesistenteException(); //TODO Lancia una eccezione perche non contiene l'elemento
		else if (obj.equals(super.interazione.getNome())) {
			ElementiStanza o = super.interazione;
			super.interazione = null;
			return o;
		} else
			throw new OggettoInesistenteException(); //TODO Lancia una eccezione perche non contiene l'elemento
	}

}
