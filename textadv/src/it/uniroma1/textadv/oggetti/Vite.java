package it.uniroma1.textadv.oggetti;


public class Vite extends OggettoCheInteragisce implements Unscrewable{

	public Vite(String nome, Oggetto inter) {
		super(nome, inter);
	}
	
	public Vite(String nome) {
		super(nome);
	}
	
	@Override
	public void svita() {
		((Openable) super.interazione).unlock(this);
		System.out.println("Vite svitata");
	}

	
	
}
