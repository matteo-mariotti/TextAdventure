package it.uniroma1.textadv.characters;
/**
 * Interfaccia che rappresenta come parla una entit� (Strategy pattern)
 * @author matte
 *
 */
@FunctionalInterface 
interface ComportamentoParla {
	
	/**
	 * Metodo parla
	 * @return Verso/messaggio
	 */
	String speak();
}
