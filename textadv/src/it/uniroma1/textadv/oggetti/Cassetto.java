package it.uniroma1.textadv.oggetti;

import it.uniroma1.textadv.ElementiStanza;
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
	public ElementiStanza getContenuto(String obj) throws OggettoInesistenteException, ImpossibileOttenereOggetto{
		if (!(bOpen))
			throw new ImpossibileOttenereOggetto();//TODO Eccezione perchè il camino è acceso
		if (super.interazione == null)
			throw new OggettoInesistenteException(); //TODO Lancia una eccezione perche non contiene l'elemento
		else if (obj.equals(super.interazione.getNome())) {
			ElementiStanza o = super.interazione;
			super.interazione = null;
			return o;
		} else
			throw new OggettoInesistenteException(); //TODO Lancia una eccezione perche non contiene l'elemento
	}
	

	@Override
	public void open() {
		bOpen = true;
		System.out.println("Il cassetto è ora aperto");
	}
	
	@Override
	public void open(Oggetto ogg) {
		System.out.println("Non hai bisogno di un oggetto per aprire " + super.getNome());
		open();
	}

	
}