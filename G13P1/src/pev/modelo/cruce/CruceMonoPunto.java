package pev.modelo.cruce;

import java.util.Random;

import pev.modelo.Cromosoma;

public class CruceMonoPunto implements Cruce {
	
	@Override
	public Cromosoma[] reproduccion(Cromosoma[] poblacion, double prob_cruce) {
		
		if (poblacion[0].getGenes().length == 1) {
			return poblacion;
		}
		
		Random random = new Random();
		
		// Seleccionados para reproducir
		int[] seleccionado_cruce = new int[poblacion.length];
		
		// Contador de seleccionados
		int cont_seleccionado = 0;
		
		double prob;
		
		int punto_cruce = 0;
		
		
		// Se eligen los individuos a cruzar
		for (int i = 0; i < seleccionado_cruce.length; i++) {
			// Se generan numeros aleatorios entre 0 y 1
			prob = Math.random();
			// Se eligen los individuos de las posiciones i si  prob < prob_cruce
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
		for (int i = 0; i < cont_seleccionado; i+=2) {
			// Se cruzan los individuos elegidos en un punto al azar (numero de genes)
			punto_cruce = random.nextInt(poblacion[0].getGenes().length);

			Cromosoma[] hijos = cruce (poblacion[seleccionado_cruce[i]], 
					poblacion[seleccionado_cruce[i+1]], punto_cruce);

			// Los nuevos individuos sustituyen a sus progenitores
			poblacion[seleccionado_cruce[i]]   = hijos[0].hacerCopia();
			poblacion[seleccionado_cruce[i+1]] = hijos[1].hacerCopia();

			poblacion[seleccionado_cruce[i]].setModificado(true);;
			poblacion[seleccionado_cruce[i+1]].setModificado(true);;
			
		}
			
		return poblacion;
		
	}
	
	
	private Cromosoma[] cruce(Cromosoma padre1, Cromosoma padre2, int punto_cruce) {
		
		Cromosoma[] hijos = new Cromosoma[2];
		
		Cromosoma hijo1 = padre1.hacerCopia();
		Cromosoma hijo2 = padre2.hacerCopia();
		
		hijo1.cambiaGenes(padre2.getGenes(), punto_cruce, hijo1.getGenes().length);
		hijo2.cambiaGenes(padre1.getGenes(), punto_cruce, hijo2.getGenes().length);
		
			
//		System.out.println("     ------------");
//		System.out.println("     Padre1: " + padre1.toString());
//		System.out.println("     Padre2: " + padre2.toString());
//		System.out.println("     Hijo1:  " + hijo1.toString());
//		System.out.println("     Hijo2:  " + hijo2.toString());
//		System.out.println("     ------------");
//		System.out.println("     Punto de cruce: " + punto_cruce);

		
		hijos[0] = hijo1;
		hijos[1] = hijo2;
		
		return hijos;
	}

}