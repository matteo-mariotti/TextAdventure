package it.uniroma1.textadv.oggetti;

import it.uniroma1.textadv.ElementiStanza;
import it.uniroma1.textadv.rooms.ChiaveNecessariaExeption;
import it.uniroma1.textadv.rooms.ElementoInesistenteException;
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
		return "Il camino è " + (acceso ? "acceso" : "spento") + " e contiene: " + super.interazione;
	}

	public String spegni(Secchio sec) {
		if (sec.filled()) {
			acceso = false;
			sec.empty();
			return "Camino spento";
		} else {
			return "Il secchio era vuoto!!!";
		}

	}
	
	@Override
	public ElementiStanza getContenuto(String obj) throws ImpossibileOttenereOggetto, ChiaveNecessariaExeption{
		if (acceso)
			throw new ChiaveNecessariaExeption();//TODO Eccezione perchè il camino è acceso
		if (super.interazione == null)
			throw new ImpossibileOttenereOggetto(); //TODO Lancia una eccezione perche non contiene l'elemento
		else if (obj.equals(super.interazione.getNome())) {
			ElementiStanza o = super.interazione;
			super.interazione = null;
			return o;
		} else
			throw new ImpossibileOttenereOggetto(); //TODO Lancia una eccezione perche non contiene l'elemento
	}

}
