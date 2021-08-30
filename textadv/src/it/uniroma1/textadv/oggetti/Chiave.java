package it.uniroma1.textadv.oggetti;

import it.uniroma1.textadv.ElementoStanza;
import it.uniroma1.textadv.rooms.ChiaveNecessariaExeption;

public class Chiave extends OggettoCheInteragisce implements Usable {

	public Chiave(String nome, Oggetto inter) {
		super(nome, inter);
	}

	public Chiave(String nome) {
		super(nome);
	}

	@Override
	public String use() {
		return "Devi indicare su cosa usare la chiave";
	}

	@Override
	public String use(ElementoStanza e) {
		if (e instanceof Openable) {
			try {
				((Openable) e).unlock(this);
				return ((Openable) e).open();	
			} catch (ChiaveNecessariaExeption e1) {
				return "Non hai fornito la chiave corretta!";
			}
		}
		return "Non puoi aprire " + e.getNome();

	}

}
