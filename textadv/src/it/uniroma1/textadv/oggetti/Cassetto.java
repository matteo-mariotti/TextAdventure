package it.uniroma1.textadv.oggetti;

import it.uniroma1.textadv.ElementiStanza;
import it.uniroma1.textadv.rooms.ChiaveNecessariaExeption;
import it.uniroma1.textadv.rooms.ElementoInesistenteException;
import it.uniroma1.textadv.textEngine.OggettoInesistenteException;

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
	public ElementiStanza getContenuto(String obj) throws  ChiaveNecessariaExeption, ImpossibileOttenereOggetto{
		if (!(bOpen))
			throw new ChiaveNecessariaExeption();//TODO Eccezione perchè il cassetto è chiuso
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
	public String open() {
		bOpen = true;
		return "Il cassetto è ora aperto";
	}
	
	/*@Override
	public String open(Oggetto ogg) {
		open();
		return "Non hai bisogno di un oggetto per aprire " + super.getNome();
	}*/

	
}