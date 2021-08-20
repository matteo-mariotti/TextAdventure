package it.uniroma1.textadv.oggetti;

import it.uniroma1.textadv.ElementiStanza;

public class Secchio extends  Oggetto implements Usable {
	
	boolean pieno = false;
	
	public Secchio(String nome) {
		super(nome);
	}
	
	public void fill() {
		pieno = true;
		}
	
	public boolean filled() {
		return pieno;
	}
	@Override
	public String use() {
		return "Devi indicare su cosa usare il secchio";
	}

	public void empty() {
		pieno = false;
	}
	
	@Override
	public String use(ElementiStanza e) {
		if (e instanceof Pozzo)
			return ((Pozzo) e).riempi(this);
		if (e instanceof Camino)
			return ((Camino) e).spegni(this);
		return "Non puoi usare il secchio su " + e.getNome();
	}

}
