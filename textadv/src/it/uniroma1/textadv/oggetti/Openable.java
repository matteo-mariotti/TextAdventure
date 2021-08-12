package it.uniroma1.textadv.oggetti;

public interface Openable {
	
	void open();
	
	void open(Oggetto ogg);
	
	boolean unlock(Oggetto ogg);
		
}
