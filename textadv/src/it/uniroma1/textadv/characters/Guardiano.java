package it.uniroma1.textadv.characters;

import java.util.List;

import it.uniroma1.textadv.ElementiStanza;
import it.uniroma1.textadv.oggetti.Oggetto;
import it.uniroma1.textadv.oggetti.Soldi;
import it.uniroma1.textadv.oggetti.Tesoro;
import it.uniroma1.textadv.rooms.PagamentoNecessarioException;
import it.uniroma1.textadv.textEngine.OggettoInesistenteException;
import it.uniroma1.textadv.textEngine.verbs.Prendi;

public class Guardiano extends Personaggio implements Payable{
	
	private Oggetto tesoroSegreto;
	private Entita distrazione;
	private boolean distracted = false;
		
	public Guardiano(String nome, Entita gattinoCalmante, Tesoro tesoro) {
		super(nome);
		distrazione = gattinoCalmante;
		tesoroSegreto = tesoro;
		tesoro.setOwner(this);
	}
	
	public Guardiano(String nome) {
		super(nome);
	}
	
	public void get(Gatto gattino) {
		if (gattino == distrazione)
			distracted = true;
	}
	
	public Oggetto getTesoro() {
		if (distracted)
			return tesoroSegreto;
		else
			System.out.println("Io sono il protettore del tesoro, non sarà così semplice!!!!!");
		return null;
	}
	
	public void addArguments(Entita ent, Oggetto tes) {
		tesoroSegreto = tes;
		distrazione = ent;
	}
	
	public void pagamento(String s) throws OggettoInesistenteException, PagamentoNecessarioException{
		ElementiStanza e = Giocatore.instanceOf().getInventario().get(s);
		if (e == distrazione) {
			Giocatore.instanceOf().getInventario().remove(e.getNome());
			for (String s1 : super.getInventario().keySet()) {
				super.getInventario().get(s1).setOwner(null);
			}
			super.getInventario().clear();
		}
	}
	
}
