package it.uniroma1.textadv.characters;

import it.uniroma1.textadv.ElementoStanza;
import it.uniroma1.textadv.oggetti.ImpossibileOttenereOggetto;
import it.uniroma1.textadv.rooms.ChiaveNecessariaExeption;
import it.uniroma1.textadv.rooms.DirezioneNonConsentitaException;
import it.uniroma1.textadv.rooms.ElementoInesistenteException;
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
	private ElementoStanza tesoroSegreto;
	/**
	 * Distrazione che permette di prendere il tesoro
	 */
	private ElementoStanza distrazione;
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
	 * @param distrazione Entita che distrae il guardiano
	 * @param tesoro Tesoro protetto dal guardiano
	 */
	public Guardiano(String nome, ElementoStanza distrazione, ElementoStanza tesoro) {
		super(nome);
		this.distrazione = distrazione;
		super.addOggetto(tesoro);
		tesoro.setOwner(this);
	}
	
	/**
	 * Permette di prendere il tesoro
	 * @return Fornisce il tesoro se il guardiano è stato distratto precedentemente
	 * @throws PagamentoNecessarioException Se non ha ancora pagato il guardiano
	 * @throws DirezioneNonConsentitaException Se il guardiano protegge una stanza/link che non posso prendere
	 * @throws ElementoInesistenteException Se non trovo l'elemento che possiede il guardiano
	 * @throws ChiaveNecessariaExeption  Se serve aprire un link/box prima di prendere cosa protegge il guardiano
	 * @throws ImpossibileOttenereOggetto Se non riesco ad ottenere l'oggetto
	 */
	public String getTesoro() throws PagamentoNecessarioException, ImpossibileOttenereOggetto, ChiaveNecessariaExeption, ElementoInesistenteException, DirezioneNonConsentitaException {
		if (distracted)
			return new Prendi().esegui(tesoroSegreto.getNome());
		throw new PagamentoNecessarioException(getMsg());
	}
	
	private String getMsg() {
		return MESSAGGIO;
	}
	
	@Override
	public String pagamento(String s) throws PagamentoNecessarioException{
		ElementoStanza e = Giocatore.instanceOf().getInventario().get(s);
		if (e == distrazione) {
			Giocatore.instanceOf().getInventario().remove(e.getNome());
			for (String s1 : super.getInventario().keySet())
				super.getInventario().get(s1).setOwner(null);
			super.getInventario().clear();
			return "Zzz zzz....";
		}
		throw new PagamentoNecessarioException(this);
	}
	
}
