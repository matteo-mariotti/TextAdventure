package it.uniroma1.textadv.oggetti;

public class Pozzo extends Oggetto {
	
	public Pozzo(String nome) {
		super(nome);
	}

	public void riempi(Secchio sec) {
		sec.fill();
		System.out.println("" + sec + "è stato riempito");
	}
	
}
