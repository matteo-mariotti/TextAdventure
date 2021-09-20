package it.uniroma1.textadv.characters;
/**
 * Classe che rappresenta il miagolio
 * @author matte
 *
 */
class Miagola implements VersoAnimale, ComportamentoParla{

	/**
	 * Verso del gatto
	 */
	private static String VERSO = "Miaooooooo!!";
	
	
	@Override
	public String verso() {
		return VERSO;
	}


	@Override
	public String speak() {
		return VERSO;
	}
	
	

}
