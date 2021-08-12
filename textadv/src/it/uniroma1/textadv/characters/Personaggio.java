package it.uniroma1.textadv.characters;

import java.util.HashMap;
import java.util.Map;

import it.uniroma1.textadv.ElementiStanza;

public abstract class Personaggio extends Entita{

	private Map<String, ElementiStanza> inventario = new HashMap<>();
	
	public Personaggio(String nome) {
		super(nome);
	}
	
	public Map<String, ElementiStanza> getInventario(){
		return inventario;
	}
	
	public void addOggetto(ElementiStanza ogg) {
		inventario.put(ogg.getNome(), ogg);
	}
	
	}
