package it.uniroma1.textadv.oggetti;

import it.uniroma1.textadv.ElementoStanza;
import it.uniroma1.textadv.rooms.ChiaveNecessariaExeption;
import it.uniroma1.textadv.textEngine.OggettoInesistenteException;

public class Scrivania extends OggettoCheInteragisce implements Openable, Box {

	private boolean bOpen = false;

	public Scrivania(String nome, Oggetto inter) {
		super(nome, inter);
	}

	public Scrivania(String nome) {
		super(nome);
	}

	@Override
	public String open() {
		bOpen = true;
		return "La scrivania è ora aperta";
	}
	
	public String toString() {
		return bOpen
				? super.interazione == null ? "La scrivania è vuota" : "La scrivania contiene: " + super.interazione
				: "Questa è una scrivania";
	}

	@Override
	public ElementoStanza getContenuto(String obj) throws ImpossibileOttenereOggetto, ChiaveNecessariaExeption{
		if (!(bOpen))
			throw new ChiaveNecessariaExeption(); //TODO Eccezione perchè la scrivania è chiusa
		if (super.interazione == null)
			throw new ImpossibileOttenereOggetto(); //TODO Lancia una eccezione perche non contiene l'elemento
		else if (obj.equals(super.interazione.getNome())) {
			ElementoStanza o = super.interazione;
			super.interazione = null;
			return o;
		} else
			throw new ImpossibileOttenereOggetto(); //TODO Lancia una eccezione perche non contiene l'elemento
	}

	@Override
	public boolean unlock(Oggetto ogg) {
		return true;
	}

}
