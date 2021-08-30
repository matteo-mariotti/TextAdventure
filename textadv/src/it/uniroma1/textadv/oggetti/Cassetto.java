package it.uniroma1.textadv.oggetti;

import it.uniroma1.textadv.ElementoStanza;
import it.uniroma1.textadv.rooms.ChiaveNecessariaExeption;

public class Cassetto extends OggettoCheInteragisce implements Openable, Box{

	private boolean bOpen = false;
	
	public Cassetto(String nome, Oggetto inter) {
		super(nome, inter);
	}
	
	public Cassetto(String nome) {
		super(nome);
	}

	@Override
	public boolean unlock(Oggetto ogg) {
		return true;
	}
	

	@Override
	public ElementoStanza getContenuto(String obj) throws  ChiaveNecessariaExeption, ImpossibileOttenereOggetto{
		if (!(bOpen))
			throw new ChiaveNecessariaExeption();//TODO Eccezione perchè il cassetto è chiuso
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
	public String open() {
		bOpen = true;
		return "Il cassetto è ora aperto";
	}

	
}