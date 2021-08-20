package it.uniroma1.textadv.oggetti;

public class Pozzo extends Oggetto {
	
	public Pozzo(String nome) {
		super(nome);
	}

	public String riempi(Secchio sec) {
		sec.fill();
		return "" + sec + "� stato riempito";
	}
	
}
