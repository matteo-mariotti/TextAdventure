package it.uniroma1.textadv.oggetti;
/**
 * Classe che rappresenta il comportamento degli oggetti gi� aperti
 * @author matte
 *
 */
public class GiaAperto implements ComportamentoApertura{

	@Override
	public String open(OggettoCheInteragisce c, Oggetto o) {
		return o.getNome() + " � gia aperto";
	}

}
