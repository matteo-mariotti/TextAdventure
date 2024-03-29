package it.uniroma1.textadv.characters;

import it.uniroma1.textadv.ElementoStanza;
import it.uniroma1.textadv.oggetti.Oggetto;
import it.uniroma1.textadv.oggetti.Soldi;
import it.uniroma1.textadv.rooms.PagamentoNecessarioException;

/**
 * Classe che modella il venditore del gioco
 * 
 * @author matte
 *
 */
public class Venditore extends Personaggio implements Payable {

	/**
	 * Costruttore del Venditore
	 * 
	 * @param nome             Nome del venditore
	 * @param oggettiInVendita Array degli oggetti in vendita
	 */
	public Venditore(String nome, Oggetto... oggettiInVendita) {
		super(nome);
		for (Oggetto o : oggettiInVendita) {
			o.setOwner(this);
			super.addOggetto(o);
		}
	}

	@Override
	public String pagamento(String s) throws PagamentoNecessarioException {
		ElementoStanza e = Giocatore.instanceOf().getInventario().get(s);
		StringBuffer sb = new StringBuffer();
		Giocatore g = Giocatore.instanceOf();
		if (e instanceof Soldi) {
			g.getInventario().remove(e.getNome());
			for (String s1 : super.getInventario().keySet()) {
				ElementoStanza elem = super.getInventario().get(s1);
				elem.setOwner(null);
				g.getStanza().remove(elem);
				sb.append(g.addOggetto(elem) + "\n");
			}
			super.getInventario().clear();
			return sb.toString();
		} else
			throw new PagamentoNecessarioException(this);
	}

}
