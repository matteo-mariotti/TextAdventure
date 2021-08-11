package it.uniroma1.textadv.characters;

public class Cane extends Animale {

	public Cane(String nome) {
		super(nome);
	}

	@Override
	public void accarezza() {
		System.out.println("Bau bau!!");
	}

}
