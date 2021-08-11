package it.uniroma1.textadv.rooms;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import it.uniroma1.textadv.characters.Entita;
import it.uniroma1.textadv.oggetti.Oggetto;
import it.uniroma1.textadv.oggetti.links.Link;

public class RoomOldVers {

	private static Map<String, RoomOldVers> listaStanze = new HashMap<>();
	
	private String nome;
	private String description;
	private Map<String, Entita> personaggi = new HashMap<>();
	private Map<String, Oggetto> oggetti= new HashMap<>();
	private RoomOldVers linkN;
	private RoomOldVers linkS;
	private RoomOldVers linkW;
	private RoomOldVers linkE;
	
	RoomOldVers(String nome, String desc, RoomOldVers linkN, RoomOldVers linkS, RoomOldVers linkW, RoomOldVers linkE, Map<String, Entita> pers, Map<String, Oggetto> oggetti) {
		this.nome = nome;
		description = desc;
		this.linkN = linkN;
		this.linkS = linkS;
		this.linkE = linkE;
		this.linkW = linkW;
		personaggi = pers;
		this.oggetti = oggetti;
	}
	
	private RoomOldVers(String nome) {
		this.nome = nome;
	}
	
	public RoomOldVers getLink(String s) {
		if (linkN!=null && linkN.getNome().equals(s))
			return linkN;
		if (linkS!=null && linkS.getNome().equals(s))
			return linkS;
		if (linkE!=null && linkE.getNome().equals(s))
			return linkE;
		if (linkW!=null && linkW.getNome().equals(s))
			return linkW;
		return null;
	}
	
	public static RoomOldVers getInstance(String nome) {
		if (listaStanze.containsKey(nome))
			return listaStanze.get(nome);
		else {
			RoomOldVers r = new RoomOldVers(nome);
			listaStanze.put(nome, r);
			return r;
		}
	}
	
	public String getNome() {
		return nome;
	}
	
	public void describe() {
		System.out.println(description);
	}
	
	public Map<String, Oggetto> listaOggetti(){
		return oggetti;
	}
	
	public Map<String, Entita> listaEntita(){
		return personaggi;
	}
	
	public String toString() {
		return "Nome: " + nome + " -descrizione " + description + linkE + linkS;
	}
}
