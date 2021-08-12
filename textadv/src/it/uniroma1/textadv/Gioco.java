package it.uniroma1.textadv;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;
import java.util.Map;

import it.uniroma1.textadv.characters.Giocatore;
import it.uniroma1.textadv.textEngine.MotoreTestuale;
import it.uniroma1.textadv.textEngine.verbs.Verbo;

public class Gioco {

	public void play(Mondo mondoDiGioco) throws ClassNotFoundException, NoSuchMethodException, SecurityException,
			InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		while (!vittoria(mondoDiGioco)) {
			List<String> comando = MotoreTestuale.leggiComando();
			System.out.println(comando);
			Class<?> classe = Class.forName("it.uniroma1.textadv.textEngine.verbs." + comando.get(0));
			Constructor<?> costruttore = classe.getConstructor();
			Verbo v = (Verbo) costruttore.newInstance();
			Method m;
			if (comando.size() == 1) {
				m = classe.getMethod("esegui");
				m.invoke(v);
			} else if (comando.size() == 2) {
				m = classe.getMethod("esegui", String.class);
				m.invoke(v, comando.get(1));
			} else if (comando.size() == 3) {
				// Trovo il primo parametro
				System.out.println("Comando con tre parametri");
				m = classe.getMethod("esegui", String.class, String.class);
				m.invoke(v, comando.get(1), comando.get(2));
			}

		}
	}
/*
	private class TuplaDati{
		
		Object o;
		Class<?> classe;
		
		public TuplaDati(Object o, Class<?> classe) {
			this.o = o;
			this.classe = classe;
		}
		
		public Class<?> getClasse(){
			return classe;
		}
		
		public Object getElemento() {
			return o;
		}
		
	}
	
	private TuplaDati getArg(String instr) {
		Oggetto ogg = Giocatore.instanceOf().getStanza().listaOggetti().get(instr.strip());
		if (ogg == null) {
			Entita e = Giocatore.instanceOf().getStanza().listaEntita().get(instr.strip());
			if (e == null) {
				Room l = Giocatore.instanceOf().getStanza().getLink(instr);
				if (l != null)
					return new TuplaDati(l, Room.class);
				else
					return null; //Devo lanciare una eccezione
			}else
				return new TuplaDati(e, Entita.class);
		}
		return new TuplaDati(ogg, Oggetto.class);
	}
	*/
	/*private Class<?> obtainClass(String s) {
		
	}*/
	
	private boolean vittoria(Mondo m) {
		Map<String, ElementiStanza> lista = Giocatore.instanceOf().getInventario();
		return lista.entrySet().stream().anyMatch(x -> x == m.getWinningObject());
	}

}
