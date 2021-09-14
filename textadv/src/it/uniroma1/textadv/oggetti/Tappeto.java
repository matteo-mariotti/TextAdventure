package it.uniroma1.textadv.oggetti;


public class Tappeto extends Box implements Liftable{

	public Tappeto(String nome, Oggetto inter) {
		super(nome, inter);
	}

	@Override
	public String alza() {
		super.setStatus(true);
		if (super.getInterazione())
			return "Sotto il tappeto c'è " + super.interazione;
		return "Sotto il tappeto non c'è nulla";
	}



}

