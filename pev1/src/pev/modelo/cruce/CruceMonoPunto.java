package pev.modelo.cruce;

import pev.modelo.Cromosoma;

public class CruceMonoPunto extends Cruce {


	public CruceMonoPunto() {
	
	}
	

	
	@Override
	public void reproduccion(Cromosoma[] poblacion, double prob_cruce) {
		
		this.nueva_poblacion = new Cromosoma[poblacion.length];
		
		for (int i = 0; i < this.nueva_poblacion.length; i++) {
			this.nueva_poblacion[i] = poblacion[i].hacerCopia();
		}
		
		// Seleccionados para reproducir
		int[] seleccionado_cruce = new int[poblacion.length];
		
		// Contador de seleccionados
		int cont_seleccionado = 0;
		
		double prob;
		
		int punto_cruce = 0;
		
		
		
		// Se eligen los individuos a cruzar
		for (int i = 0; i < seleccionado_cruce.length; i++) {
			// Se generan números aleatorios entre 0 y 1
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
			punto_cruce = generaEnteroAleatorio(this.nueva_poblacion[0].getGenes().length);

			Cromosoma[] hijos = cruce (this.nueva_poblacion[seleccionado_cruce[i]], 
					this.nueva_poblacion[seleccionado_cruce[i+1]], punto_cruce);

			// Los nuevos individuos sustituyen a sus progenitores
			this.nueva_poblacion[seleccionado_cruce[i]]   = hijos[0];
			this.nueva_poblacion[seleccionado_cruce[i+1]] = hijos[1];

			this.nueva_poblacion[seleccionado_cruce[i]].calcularFenotipo();
			this.nueva_poblacion[seleccionado_cruce[i]].funcionFitness();
			
			this.nueva_poblacion[seleccionado_cruce[i+1]].calcularFenotipo();
			this.nueva_poblacion[seleccionado_cruce[i+1]].funcionFitness();
			
		}
			
		
		
	}
	
	
	public Cromosoma[] cruce(Cromosoma padre1, Cromosoma padre2, int punto_cruce) {
		
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
	
	
	/**
	 * Genera un numero entero aleatorio entre 0 y max 
	 * @param max
	 * @return
	 */
	public int generaEnteroAleatorio(int max) {
		return ((int) (Math.random() * (max - 1))) + 1;
	}




	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
//	/**
//	 * 
//	 * @param padre1
//	 * @param padre2
//	 * @param hijo1
//	 * @param hijo2
//	 * @param punto_cruce
//	 */
//	public Cromosoma[] cruce (Cromosoma padre1, Cromosoma padre2, int punto_cruce){
//		
//		Cromosoma[] hijos = new Cromosoma[2];
//		
//		Cromosoma hijo1 = padre1.hacerCopia();
//		Cromosoma hijo2 = padre2.hacerCopia();
//		
//		
//		
//		
//		for (int i = 0; i < hijo1.getGenes().length; i++) {
//			for (int j = punto_cruce; j < hijo1.getGenes()[i].getLongitudGen(); j++) {
//				hijo1.getGenes()[i].setAleloBin(j, padre2.getGenes()[i].getAleloBin(j));
//			}
//		}
//		
//		for (int i = 0; i < hijo2.getGenes().length; i++) {
//			for (int j = punto_cruce; j < hijo2.getGenes()[i].getLongitudGen(); j++) {
//				hijo2.getGenes()[i].setAleloBin(j, padre1.getGenes()[i].getAleloBin(j));
//			}
//		}
//		
////		System.out.println("     ------------");
////		System.out.println("     Padre1: " + padre1.toString());
////		System.out.println("     Padre2: " + padre2.toString());
////		System.out.println("     Hijo1:  " + hijo1.toString());
////		System.out.println("     Hijo2:  " + hijo2.toString());
////		System.out.println("     ------------");
////		System.out.println("     Punto de cruce: " + punto_cruce);
//		
//		hijos[0] = hijo1;
//		hijos[1] = hijo2;
//		
//		return hijos;
//		
//	}
//	
	
	
	
	
//	@Override
//	public void reproduccion (Cromosoma[] poblacion, double prob_cruce) {
//		
//		if (cruce_gen) {
//			
//		}
//		else {
//			
//		}
//		
//		
//		// Longitud cromosoma
//		this.longitud_cromosoma = poblacion[0].getLongitudCromosoma();
//		
//		this.nueva_poblacion = new Cromosoma[poblacion.length];
//		
//		for (int i = 0; i < this.nueva_poblacion.length; i++) {
//			this.nueva_poblacion[i] = poblacion[i].hacerCopia();
//		}
//		
//		// Seleccionados para reproducir
//		int[] seleccionado_cruce = new int[poblacion.length];
//		
//		// Contador de seleccionados
//		int cont_seleccionado = 0;
//		
//		int punto_cruce;
//		
//		double prob;
//
//		
//		
//		// Se eligen los individuos a cruzar
//		for (int i = 0; i < seleccionado_cruce.length; i++) {
//			// Se generan números aleatorios entre 0 y 1
//			prob = Math.random();
//			// Se eligen los individuos de las posiciones i si  prob < prob_cruce
//			if (prob < prob_cruce) {
//				seleccionado_cruce[cont_seleccionado] = i;
//				cont_seleccionado++;
//			}	
//		}
//		
//		
//		// Hacemos el numero de seleccionados par
//		if ((cont_seleccionado % 2) == 1) {
//			cont_seleccionado--;
//		}
//		
//
//		// Se cruzan
//		for (int i = 0; i < cont_seleccionado; i+=2) {
//			// Se cruzan los individuos elegidos en un punto al azar
//			punto_cruce = generaEnteroAleatorio(this.longitud_cromosoma);
//
//			Cromosoma[] hijos = cruce (this.nueva_poblacion[seleccionado_cruce[i]], 
//					this.nueva_poblacion[seleccionado_cruce[i+1]], punto_cruce);
//			
//					
//			// Los nuevos individuos sustituyen a sus progenitores
//			this.nueva_poblacion[seleccionado_cruce[i]]   = hijos[0];
//			this.nueva_poblacion[seleccionado_cruce[i+1]] = hijos[1];
//
//			this.nueva_poblacion[seleccionado_cruce[i]].calcularFenotipo();
//			this.nueva_poblacion[seleccionado_cruce[i]].funcionFitness();
//			
//			this.nueva_poblacion[seleccionado_cruce[i+1]].calcularFenotipo();
//			this.nueva_poblacion[seleccionado_cruce[i+1]].funcionFitness();
//		}
//		
//	}
//
//	
//	/**
//	 * Genera un numero entero aleatorio entre 0 y max 
//	 * @param max
//	 * @return
//	 */
//	public int generaEnteroAleatorio(int max) {
//		return ((int) (Math.random() * (max - 1))) + 1;
//	}	
//	
//	
//	
//	
//	
//	
//	
//	
//	
//	/**
//	 * 
//	 * @param padre1
//	 * @param padre2
//	 * @param hijo1
//	 * @param hijo2
//	 * @param punto_cruce
//	 */
//	public Cromosoma[] cruce (Cromosoma padre1, Cromosoma padre2, int punto_cruce){
//		
//		Cromosoma[] hijos = new Cromosoma[2];
//		
//		Cromosoma hijo1 = padre1.hacerCopia();
//		Cromosoma hijo2 = padre2.hacerCopia();
//		
//		
//		for (int i = 0; i < hijo1.getGenes().length; i++) {
//			for (int j = punto_cruce; j < hijo1.getGenes()[i].getLongitudGen(); j++) {
//				hijo1.getGenes()[i].setAleloBin(j, padre2.getGenes()[i].getAleloBin(j));
//			}
//		}
//		
//		for (int i = 0; i < hijo2.getGenes().length; i++) {
//			for (int j = punto_cruce; j < hijo2.getGenes()[i].getLongitudGen(); j++) {
//				hijo2.getGenes()[i].setAleloBin(j, padre1.getGenes()[i].getAleloBin(j));
//			}
//		}
//		
////		System.out.println("     ------------");
////		System.out.println("     Padre1: " + padre1.toString());
////		System.out.println("     Padre2: " + padre2.toString());
////		System.out.println("     Hijo1:  " + hijo1.toString());
////		System.out.println("     Hijo2:  " + hijo2.toString());
////		System.out.println("     ------------");
////		System.out.println("     Punto de cruce: " + punto_cruce);
//		
//		hijos[0] = hijo1;
//		hijos[1] = hijo2;
//		
//		return hijos;
//		
//	}
	
	
	
}
