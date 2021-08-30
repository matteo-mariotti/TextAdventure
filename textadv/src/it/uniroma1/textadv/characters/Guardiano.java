package it.uniroma1.textadv.characters;

import it.uniroma1.textadv.ElementoStanza;
import it.uniroma1.textadv.oggetti.Tesoro;
import it.uniroma1.textadv.rooms.PagamentoNecessarioException;
import it.uniroma1.textadv.textEngine.verbs.Prendi;

/**
 * Classe che modella il Guardiano del tesoro della storia
 * @author matte
 *
 */
public class Guardiano extends Personaggio implements Payable{
	
	/**
	 * Tesoro che viene protetto dal guardiano
	 */
	private Tesoro tesoroSegreto;
	/**
	 * Distrazione che permette di prendere il tesoro
	 */
	private Entita distrazione;
	/**
	 * Stato del guardiano (distratto o meno)
	 */
	private boolean distracted = false;
	/**
	 * Messaggio del guardiano
	 */
	private final static String MESSAGGIO = "Io sono il protettore del tesoro, non sarà così semplice!!!!!";
	/**
	 * Costruttore del guardiano
	 * @param nome Nome del Guardiano
	 * @param distrazione 
	 * @param tesoro
	 */
	public Guardiano(String nome, Entita distrazione, Tesoro tesoro) {
		super(nome);
		this.distrazione = distrazione;
		super.addOggetto(tesoro);
		tesoro.setOwner(this);
	}
	
	/**
	 * Permette di prendere il tesoro
	 * @return Fornisce il tesoro se il guardiano è stato distratto precedentemente
	 * @throws PagamentoNecessarioException 
	 */
	public String getTesoro() throws PagamentoNecessarioException {
		if (distracted)
			return new Prendi().esegui(tesoroSegreto.getNome());
		throw new PagamentoNecessarioException(this,MESSAGGIO);
	}
	
	@Override
	public String pagamento(String s) throws PagamentoNecessarioException{
		ElementoStanza e = Giocatore.instanceOf().getInventario().get(s);
		if (e == distrazione) {
			Giocatore.instanceOf().getInventario().remove(e.getNome());
			for (String s1 : super.getInventario().keySet())
				super.getInventario().get(s1).setOwner(null);
			super.getInventario().clear();
			return "Zzzz....";
		}
		throw new PagamentoNecessarioException(this);
	}
	
}
