package it.uniroma1.textadv.oggetti;

import it.uniroma1.textadv.rooms.ChiaveNecessariaExeption;
/**
 * Classe che rappresenta tutti gli oggetti che si aprono con una chiave
 * @author matte
 *
 */
public class ApriSenzaChiave implements ComportamentoApertura {

	@Override
	public String open(OggettoCheInteragisce c, Oggetto o) throws ChiaveNecessariaExeption {
		o.setCompApertura(new GiaAperto());
		return o.getNome() + " è stato aperto";
		}
	
	

}
