package modelo.cruce;

import java.util.ArrayList;
import java.util.Random;

import modelo.Cromosoma;
import modelo.Nodo;


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
		
			Random random = new Random(System.currentTimeMillis());
			
			int aleatorio1 = random.nextInt(poblacion.get(seleccionado_cruce[i]).getNumNodos(0) - 1);
			int aleatorio2 = random.nextInt(poblacion.get(seleccionado_cruce[i+1]).getNumNodos(0) - 1);
			
			Nodo hijo1 = poblacion.get(seleccionado_cruce[i]).getNodo(aleatorio1 + 1).hacerCopia();
			Nodo hijo2 = poblacion.get(seleccionado_cruce[i+1]).getNodo(aleatorio2 + 1).hacerCopia();
			
			poblacion.get(seleccionado_cruce[i]).setNodo(aleatorio1 + 1, hijo2);
			poblacion.get(seleccionado_cruce[i+1]).setNodo(aleatorio2 + 1, hijo1);
			
		}
		
	}
	
}