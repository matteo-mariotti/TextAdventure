package it.uniroma1.textadv;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.nio.file.Paths;
import java.util.Arrays;

import it.uniroma1.textadv.textEngine.Direzione;

public class Test {

	public static void main(String[] args) throws ClassNotFoundException, NoSuchMethodException, SecurityException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, IOException{
		Gioco g = new Gioco();
		Mondo m = Mondo.fromFile(Paths.get("minizak.game"));
		g.play(m, Paths.get("minizak.ff"));
		

	}
	
}
