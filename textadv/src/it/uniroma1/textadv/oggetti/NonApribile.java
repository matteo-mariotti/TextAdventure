package it.uniroma1.textadv.oggetti;
/**
 * Classe che rappresenta il comportamento degli oggetti già aperti
 * @author matte
 *
 */
class NonApribile implements ComportamentoApertura{

	@Override
	public String open(OggettoCheInteragisce c, Oggetto o) {
		return "Non puoi aprire " + o.getNome();
	}

}
