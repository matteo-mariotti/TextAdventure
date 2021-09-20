package it.uniroma1.textadv.oggetti;

import it.uniroma1.textadv.rooms.ChiaveNecessariaExeption;
/**
 * Classe che rappresenta tutti gli oggetti che si aprono con una chiave
 * @author matte
 *
 */
public class ApriConChiave implements ComportamentoApertura{

	@Override
	public String open(OggettoCheInteragisce c, Oggetto o) throws ChiaveNecessariaExeption {
		if (c!= null && c.getOggInter() == o) {
			o.setCompApertura(new GiaAperto());
			return o.getNome() + " è stato aperto";
		}
		throw new ChiaveNecessariaExeption();
	}
	
	

}
