package it.uniroma1.textadv.characters;
/**
 * Classe che rappresenta l'abbaiare
 * @author matte
 *
 */
class Abbaia implements VersoAnimale, ComportamentoParla {

	/**
	 * Verso del cane
	 */
	private static final String VERSO = "Bau bau";
	
	@Override
	public String verso() {
		return VERSO;
	}

	@Override
	public String speak() {
		return VERSO;
	}

	
	
}

