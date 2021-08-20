package it.uniroma1.textadv.characters;

public class Cane extends Animale {

	private static String VERSO = "Bau bau";
	
	public Cane(String nome) {
		super(nome);
	}

	@Override
	public String accarezza() {
		return VERSO;
	}

}
