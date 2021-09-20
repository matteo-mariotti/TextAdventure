package it.uniroma1.textadv.characters;
/**
 * Classe che rapprenta la parlata di una entita
 * @author matte
 *
 */
class Parla implements ComportamentoParla{

	private String nome;
	
	/**
	 * Metodo che permette di parlare con l'entita
	 * @param e Nome dell'entita
	 */
	Parla(String e) {
		nome = e;
	}
	
	@Override
	public String speak() {
		return "Ciao, io sono " + nome;
	}
	
	

}
