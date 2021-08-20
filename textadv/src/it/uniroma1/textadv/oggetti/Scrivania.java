package it.uniroma1.textadv.oggetti;

import it.uniroma1.textadv.ElementiStanza;
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
		return "La scrivania � ora aperta";
	}
	
	public String toString() {
		return bOpen
				? super.interazione == null ? "La scrivania � vuota" : "La scrivania contiene: " + super.interazione
				: "Questa � una scrivania";
	}

	@Override
	public ElementiStanza getContenuto(String obj) throws ImpossibileOttenereOggetto, ChiaveNecessariaExeption{
		if (!(bOpen))
			throw new ChiaveNecessariaExeption(); //TODO Eccezione perch� la scrivania � chiusa
		if (super.interazione == null)
			throw new ImpossibileOttenereOggetto(); //TODO Lancia una eccezione perche non contiene l'elemento
		else if (obj.equals(super.interazione.getNome())) {
			ElementiStanza o = super.interazione;
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
