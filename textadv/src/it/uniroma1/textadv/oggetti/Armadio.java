package it.uniroma1.textadv.oggetti;

import it.uniroma1.textadv.ElementoStanza;
import it.uniroma1.textadv.rooms.ChiaveNecessariaExeption;

/**
 * Classe che modella l'oggetto armadio
 * @author matte
 *
 */
public class Armadio extends OggettoCheInteragisce implements Openable, Box {

	private boolean bOpen = false;

	public Armadio(String nome, Oggetto inter) {
		super(nome, inter);
	}

	public Armadio(String nome) {
		super(nome);
	}

	@Override
	public String open() {
		if (bOpen)
			return "" + super.getNome() + " è ora aperto";
		return "Hai bisogno delle tronchesi per aprire l'armadio";
	}

	public String toString() {
		return bOpen ? super.interazione == null ? "L'armadio è vuoto" : "L'armadio contiene: " + super.interazione
				: "Questo è un armadio";
	}

	@Override
	public ElementoStanza getContenuto(String obj) throws ImpossibileOttenereOggetto, ChiaveNecessariaExeption {
		if (!(bOpen))
			throw new ChiaveNecessariaExeption();
		if (super.interazione == null)
			throw new ImpossibileOttenereOggetto(); // TODO Lancia una eccezione perche non contiene l'elemento

		if (obj.equals(super.interazione.getNome())) {
			ElementoStanza o = super.interazione;
			super.interazione = null;
			return o;
		} else
			throw new ImpossibileOttenereOggetto(); // TODO Lancia una eccezione perche non contiene l'elemento
	}

	@Override
	public boolean unlock(Oggetto ogg) throws ChiaveNecessariaExeption {
		if (ogg instanceof Tronchesi) {
			bOpen = true;
			return true;
		}
		throw new ChiaveNecessariaExeption();
	}

}
