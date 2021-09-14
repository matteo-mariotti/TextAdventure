package it.uniroma1.textadv.oggetti;

public class Cassa extends Box implements Openable{
	
	public Cassa(String nome, Oggetto inter) {
		super(nome, inter);
	}
	
	@Override
	public String open() {
		super.setStatus(true);
		return "La cassa è ora aperta";
	}

	@Override
	public boolean unlock(OggettoCheInteragisce ogg) {
		return true;
	}


}
