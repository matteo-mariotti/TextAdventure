package it.uniroma1.textadv.oggetti;

import it.uniroma1.textadv.rooms.ChiaveNecessariaExeption;

public class Vite extends OggettoCheInteragisce implements Unscrewable{

	public Vite(String nome, Oggetto inter) {
		super(nome, inter);
	}
	
	public Vite(String nome) {
		super(nome);
	}
	
	@Override
	public String svita() {
		try {
			((Openable) super.interazione).unlock(this);
			return "Vite svitata";
		} catch (ChiaveNecessariaExeption e) {
			return "Non è stato possibile aprire " + super.interazione;
		}
	}

	
	
}
