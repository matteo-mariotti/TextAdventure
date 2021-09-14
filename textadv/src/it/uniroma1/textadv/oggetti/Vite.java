package it.uniroma1.textadv.oggetti;

import it.uniroma1.textadv.rooms.ChiaveNecessariaExeption;

/**
 * Classe che modella una vite
 * @author matte
 *
 */
public class Vite extends OggettoCheInteragisce implements Unscrewable{
/**
 * Costruttore della classe 
 * @param nome Nome dell'oggetto
 * @param inter Oggetto con cui interagisce
 */
	public Vite(String nome, Oggetto inter) {
		super(nome, inter);
	}

	
	@Override
	public String svita() {
		try {
			((Openable) super.interazione).unlock(this);
			return "Vite svitata";
		} catch (ChiaveNecessariaExeption e) {
			return "Non è stato possibile aprire " + super.interazione;
		}
	}

	
	
}
