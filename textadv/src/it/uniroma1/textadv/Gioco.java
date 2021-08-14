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
			Class<?> classe = Class.forName("it.uniroma1.textadv.textEngine.verbs." + comando.get(0)).asSubclass(Verbo.class);
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
				m = classe.getMethod("esegui", String.class, String.class);
				m.invoke(v, comando.get(1), comando.get(2));
			}
		}
		System.out.println("Bravo!! Hai vinto!!!");
	}
	
	private boolean vittoria(Mondo m) {
		Map<String, ElementiStanza> lista = Giocatore.instanceOf().getInventario();
		return lista.entrySet().stream().anyMatch(x -> x == m.getWinningObject());
	}

}
