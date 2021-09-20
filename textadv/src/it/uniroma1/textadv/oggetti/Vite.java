package it.uniroma1.textadv.oggetti;

/**
 * Classe che modella una vite
 * @author matte
 *
 */
public class Vite extends OggettoCheInteragisce implements Unscrewable{ // NO_UCD (unused code)
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
			if (super.interazione instanceof Oggetto) {
				((Oggetto)super.interazione).setCompApertura(new ApriSenzaChiave());
				return "Vite svitata";}
			else 
				return "Non puoi aprire " + super.interazione;
	}

	
	
}
