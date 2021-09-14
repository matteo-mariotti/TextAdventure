package it.uniroma1.textadv.oggetti;

import it.uniroma1.textadv.rooms.ChiaveNecessariaExeption;

/**
 * Classe che modella l'oggetto armadio
 * 
 * @author matte
 *
 */
public class Armadio extends Box implements Openable {

	/**
	 * Costruttore dell'Armadio
	 * @param nome Nome dell'armadio
	 * @param inter Oggetto contenuto
	 */
	public Armadio(String nome, Oggetto inter) {
		super(nome, inter);
	}


	@Override
	public String open() {
		if (super.getStatus())
			return "" + super.getNome() + " è ora aperto";
		return "Hai bisogno di un oggetto adatto per aprire" + super.getNome();
	}

	@Override
	public boolean unlock(OggettoCheInteragisce ogg) throws ChiaveNecessariaExeption {
		if (ogg != null && ogg.getOggInter() == this) {
			super.setStatus(true);
			return true;
		}
		throw new ChiaveNecessariaExeption();
	}

}
