package it.uniroma1.textadv;

import it.uniroma1.textadv.characters.Entita;

/**
 * Classe astratta che rappresenta un generico elemento della stanza di gioco (link/oggetto)
 * @author matte
 *
 */
public abstract class ElementoStanza {

	/**
	 * Nome dell'elemento della stanza
	 */
	protected String nome;
	/**
	 * Proprietario dell'elemento, se null è accessibile al Giocatore
	 */
	private Entita owner;

	/**
	 * Costruttore dell'elemento
	 * @param nome Nome dell'elemento
	 */
	public ElementoStanza(String nome) {
		this.nome = nome;
	}
	
	/**
	 * Getter del nome
	 * @return Nome dell'elemento
	 */
	public String getNome() {
		return nome;
	}
	
	/**
	 * Setter del nome dell'elemento
	 * @param nome Nome da impostare
	 */
	public void setName(String nome) {
		this.nome = nome;
	}
	
	@Override
	public String toString() {
		return nome; 
	}
	
	/**
	 * Metodo che descrive l'elemento
	 * @return Descrizione
	 */
	public String describe() {
		return toString();
	}
	
	/**
	 * Metodo per impostare il proprietario dell'elemento
	 * @param owner Nuovo proprietario
	 */
	public void setOwner(Entita owner) {
		this.owner = owner;
	}
	
	/**
	 * Getter del proprietario
	 * @return Proprietario dell'oggetto
	 */
	public Entita getOwner() {
		return owner;
	}
}

