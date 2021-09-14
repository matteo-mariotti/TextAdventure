package it.uniroma1.textadv.oggetti.links;

import it.uniroma1.textadv.oggetti.Oggetto;
import it.uniroma1.textadv.oggetti.OggettoCheInteragisce;
import it.uniroma1.textadv.oggetti.Openable;
import it.uniroma1.textadv.rooms.ChiaveNecessariaExeption;
import it.uniroma1.textadv.rooms.DirezioneNonConsentitaException;
import it.uniroma1.textadv.rooms.Room;

/**
 * Classe che rappresenta un generico collegamento
 * 
 * @author matte
 *
 */
public abstract class Link extends Oggetto implements Openable {

	/**
	 * Stanza di partenza
	 */
	Room stanza1;
	/**
	 * Stanza destinazione
	 */
	Room stanza2;
	/**
	 * Flag per indicare se il collegamento è aperto o chiuso
	 */
	boolean bChiuso = false;
	/**
	 * Oggetto con cui si sblocca il collegamento
	 */
	Oggetto chiave;

	/**
	 * Costruttore della classe
	 * 
	 * @param nome               Nome del collegamento
	 * @param stanzaPartenza     Stanza di partenza
	 * @param stanzaDestinazione Stanza destinazione
	 */
	public Link(String nome, Room stanzaPartenza, Room stanzaDestinazione) {
		super(nome);
		stanza1 = stanzaPartenza;
		stanza2 = stanzaDestinazione;
	}

	/**
	 * Data una delle due stanze connesse fornisce il riferimento all'altra
	 * 
	 * @param startingRoom Stanza da cui si parte
	 * @return Stanza destinazione
	 * @throws DirezioneNonConsentitaException Se la Stringa fornita non è nessuna
	 *                                         delle due stanze connesse
	 */
	public Room getConnection(String startingRoom) throws DirezioneNonConsentitaException {
		if (stanza1.getNome().equals(startingRoom))
			return stanza2;
		else if (stanza2.getNome().equals(startingRoom))
			return stanza1;
		throw new DirezioneNonConsentitaException();
	}

	/**
	 * Metodo che permette di sapere se il link è aperto o chiuso
	 * 
	 * @return True se chiuso, False se aperto
	 */
	public boolean status() {
		return bChiuso;
	}

	/**
	 * Permette di chiudere il link con un oggetto chiave
	 * 
	 * @param chiave Oggetto che blocca il link
	 */
	public void setClosed(Oggetto chiave) {
		lock();
		this.chiave = chiave;
	}

	@Override
	public String toString() {
		return super.nome;
	}

	@Override
	public String open() {
		if (chiave == null) {
			bChiuso = false;
			return "" + getNome() + " è stato aperto/era già aperto";
		} else {
			return "" + getNome() + " è ancora chiuso";
		}
	}

	/**
	 * Permette di chiudere il link
	 */
	public void lock() {
		bChiuso = true;
	}

	@Override
	public boolean unlock(OggettoCheInteragisce chiave) throws ChiaveNecessariaExeption {
		if (this.chiave == chiave) {
			this.chiave = null;
			bChiuso = false;
			return true;
		}
		throw new ChiaveNecessariaExeption();
	}

}
