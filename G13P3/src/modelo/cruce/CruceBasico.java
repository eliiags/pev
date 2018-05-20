package modelo.cruce;

import java.util.ArrayList;
//import java.util.Random;

import modelo.Cromosoma;
import modelo.Nodo;
import modelo.algoritmo.Datos;


public class CruceBasico implements Cruce {

	@Override
	public void reproduccion(ArrayList<Cromosoma> poblacion, double prob_cruce) {
		
		// Seleccionados para reproducir
		int[] seleccionado_cruce = new int[poblacion.size()];
		int cont_seleccionado = 0;

		double prob;

		// Se eligen los individuos a cruzar
		for (int i = 0; i < seleccionado_cruce.length; i++) {
			// Se generan números aleatorios entre 0 y 1
			prob = Math.random();
			// Se eligen los individuos de las posiciones i si prob < prob_cruce
			if (prob < prob_cruce) {
				seleccionado_cruce[cont_seleccionado] = i;
				cont_seleccionado++;
			}
		}

		// Hacemos el numero de seleccionados par
		if ((cont_seleccionado % 2) == 1) {
			cont_seleccionado--;
		}
		
		// Se cruzan
		for (int i = 0; i < cont_seleccionado; i += 2) {
		
//			Random random = new Random(System.currentTimeMillis());
			
//			System.out.println("Padre 1: " + poblacion.get(seleccionado_cruce[i]).toString());
//			System.out.println("Padre 2: " + poblacion.get(seleccionado_cruce[i+1]).toString());
			
			int aleatorio1 = -1; 
			int aleatorio2 = -1;
			
			if ((poblacion.get(seleccionado_cruce[i]).getNumNodos(2) - 1) > 0) {
//				aleatorio1 = random.nextInt((poblacion.get(seleccionado_cruce[i]).getNumNodos(2) - 1));
				aleatorio1 = Datos.nextInt((poblacion.get(seleccionado_cruce[i]).getNumNodos(2) - 1));
			} 
	
			if ((poblacion.get(seleccionado_cruce[i+1]).getNumNodos(2) - 1) > 0) {
//				aleatorio2 = random.nextInt((poblacion.get(seleccionado_cruce[i+1]).getNumNodos(2) - 1));
				aleatorio2 = Datos.nextInt((poblacion.get(seleccionado_cruce[i+1]).getNumNodos(2) - 1));
			} 
			
//			System.out.println(aleatorio1);
//			System.out.println(aleatorio2);
			
			Nodo hijo1 = poblacion.get(seleccionado_cruce[i]).getNodo(aleatorio1 + 1).hacerCopia();
			Nodo hijo2 = poblacion.get(seleccionado_cruce[i+1]).getNodo(aleatorio2 + 1).hacerCopia();
			
//			System.out.println("Parte padre 1: " + hijo1.toString());
//			System.out.println("Parte padre 2: " + hijo2.toString());
			
			poblacion.get(seleccionado_cruce[i]).setNodo(aleatorio1 + 1, hijo2);
			poblacion.get(seleccionado_cruce[i+1]).setNodo(aleatorio2 + 1, hijo1);
			
//			System.out.println("Hijo 1: " + poblacion.get(seleccionado_cruce[i]).toString());
//			System.out.println("Hijo 2: " + poblacion.get(seleccionado_cruce[i+1]).toString());
			
			poblacion.get(seleccionado_cruce[i]).hacerPoda();
			poblacion.get(seleccionado_cruce[i+1]).hacerPoda();
			
//			System.out.println("Poda 1: " + poblacion.get(seleccionado_cruce[i]).toString());
//			System.out.println("Poda 2: " + poblacion.get(seleccionado_cruce[i+1]).toString());
//			
//			System.out.println("----");
		}
		
		
	}
	
	
}