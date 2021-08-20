package it.uniroma1.textadv.characters;

import it.uniroma1.textadv.ElementiStanza;
import it.uniroma1.textadv.oggetti.Oggetto;
import it.uniroma1.textadv.oggetti.Soldi;
import it.uniroma1.textadv.rooms.PagamentoNecessarioException;
import it.uniroma1.textadv.textEngine.OggettoInesistenteException;
import it.uniroma1.textadv.textEngine.verbs.Prendi;

public class Venditore extends Personaggio implements Payable{


	public Venditore(String nome, Oggetto... oggettiInVendita ) {
		super(nome);
		for (Oggetto o : oggettiInVendita) {
			o.setOwner(this);
			super.addOggetto(o);
		}
	}

	public Venditore(String nome) {
		super(nome);
	}

	@Override
	public String pagamento(String s) throws PagamentoNecessarioException{
		ElementiStanza e = Giocatore.instanceOf().getInventario().get(s);
		StringBuffer sb = new StringBuffer();
		if (e instanceof Soldi) {
			Giocatore.instanceOf().getInventario().remove(e.getNome());
			for (String s1 : super.getInventario().keySet()) {
				super.getInventario().get(s1).setOwner(null);
				sb.append(new Prendi().esegui(s1));
			}
			super.getInventario().clear();
			return sb.toString();
		}
		else
			throw new PagamentoNecessarioException(this);
	}
	
}
