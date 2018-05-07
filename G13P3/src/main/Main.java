package main;

import modelo.algoritmo.AlgoritmoEvolutivo;
import modelo.algoritmo.Datos;

public class Main {

	public static void main(String[] args) {
		
		new Datos();
		
		AlgoritmoEvolutivo AE = new AlgoritmoEvolutivo();
		
		AE.ejecuta();
		
		System.out.println("Hechito");
		
	}

}
