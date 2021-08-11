package it.uniroma1.textadv.characters;

import java.util.ArrayList;
import java.util.List;

import it.uniroma1.textadv.ElementiStanza;

public abstract class Personaggio extends Entita{

	private List<ElementiStanza> inventario = new ArrayList<>();
	
	public Personaggio(String nome) {
		super(nome);
	}
	
	public List<ElementiStanza> getInventario(){
		return inventario;
	}
	
	public void addOggetto(ElementiStanza ogg) {
		inventario.add(ogg);
	}
	
	}
