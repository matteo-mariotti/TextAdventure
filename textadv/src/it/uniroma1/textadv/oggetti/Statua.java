package it.uniroma1.textadv.oggetti;

import it.uniroma1.textadv.characters.Giocatore;
import it.uniroma1.textadv.oggetti.links.Link;
import it.uniroma1.textadv.rooms.DirezioneNonConsentitaException;
import it.uniroma1.textadv.rooms.Room;
/**
 * Classe che modella una statua
 * @author matte
 *
 */
public class Statua extends OggettoCheInteragisce implements Breakable{

	/**
	 * Flag per indicare se il salvadanaio è rotto oppure no
	 */
	private boolean bBroken = false;
	
	/**
	 * Costruttore della classe Salvadanaio
	 * @param nome Nome del salvadanaio
	 * @param inter Oggetto contenuto nel salvadanaio
	 */
	public Statua(String nome, Oggetto inter) {
		super(nome, inter);
	}
	

	@Override
	public String rompi(Oggetto ogg) {
		if(bBroken){
			return "La statua è gia rotta";
		}
		else if (ogg instanceof Martello) {
			bBroken = true;
			if (super.interazione instanceof Link)
			{
				try {
					Link l = (Link) super.interazione;
					l.setCompApertura(new GiaAperto());
					Giocatore.instanceOf().getStanza().bonusB(new Room.LinkTuple(l.getConnection(Giocatore.instanceOf().getStanza().getNome()), l));
				} catch (DirezioneNonConsentitaException e) {
					e.printStackTrace();
				}
			}
			else
				Giocatore.instanceOf().getStanza().addElementi(super.interazione);	
			return "Crash!! Hai rotto la statua";
		}
		return "Hai bisogno di un martello per rompere la statua";
	}
	
	@Override
	public String describe() {
		return bBroken ? "La statua è rotta" : "Questa è una statua";
	}

	@Override
	public String rompi() {
		return bBroken ? "La statua è già rotta" : "Per rompere la statua hai bisogno di un oggetto adatto";
	}
	
}
