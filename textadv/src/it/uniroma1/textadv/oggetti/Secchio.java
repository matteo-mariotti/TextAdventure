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
	public void use() {
		System.out.println("Devi indicare su cosa usare il secchio");
	}

	@Override
	public void use(ElementiStanza e) {
		if (e instanceof Pozzo)
			((Pozzo) e).riempi(this);
		if (e instanceof Camino)
			((Camino) e).spegni(this);
	}

}
