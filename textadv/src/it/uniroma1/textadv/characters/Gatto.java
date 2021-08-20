package it.uniroma1.textadv.characters;

public class Gatto extends Animale{

	private static String VERSO = "Miaooooooo!!";

	
	public Gatto(String nome) {
		super(nome);
	}
	
	@Override
	public String accarezza() {
		return VERSO;
	}
	
}
