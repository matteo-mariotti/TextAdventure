package it.uniroma1.textadv.characters;

public class Gatto extends Animale{

	public Gatto(String nome) {
		super(nome);
	}
	
	@Override
	public void accarezza() {
		System.out.println("Miaooooooo!!");
	}
	
}
