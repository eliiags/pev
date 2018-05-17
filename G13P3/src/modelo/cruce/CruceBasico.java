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
			
			int aleatorio1 = random.nextInt(poblacion.get(seleccionado_cruce[i]).getNumNodos() - 1);
			int aleatorio2 = random.nextInt(poblacion.get(seleccionado_cruce[i+1]).getNumNodos() - 1);
			
			Nodo hijo1 = poblacion.get(seleccionado_cruce[i]).getNodo(aleatorio1 + 1).hacerCopia();
			Nodo hijo2 = poblacion.get(seleccionado_cruce[i+1]).getNodo(aleatorio2 + 1).hacerCopia();
			
			poblacion.get(seleccionado_cruce[i]).setNodo(aleatorio1 + 1, hijo2);
			poblacion.get(seleccionado_cruce[i+1]).setNodo(aleatorio2 + 1, hijo1);
			
		}
		
	}
	
}


//package modelo.cruce;
//
//import java.util.ArrayList;
//import java.util.Arrays;
//
//import modelo.Cromosoma;
//import modelo.Nodo;
//
//public class CruceBasico implements Cruce {
//
//	@Override
//	public void reproduccion(ArrayList<Cromosoma> poblacion, double prob_cruce) {
//
//		// Seleccionados para reproducir
//		int[] seleccionado_cruce = new int[poblacion.size()];
//		int cont_seleccionado = 0, punto_cruce = 0;
//
//		double prob;
//
//		// Se eligen los individuos a cruzar
//		for (int i = 0; i < seleccionado_cruce.length; i++) {
//			// Se generan números aleatorios entre 0 y 1
//			prob = Math.random();
//			// Se eligen los individuos de las posiciones i si prob < prob_cruce
//			if (prob < prob_cruce) {
//				seleccionado_cruce[cont_seleccionado] = i;
//				cont_seleccionado++;
//			}
//		}
//
//		// Hacemos el numero de seleccionados par
//		if ((cont_seleccionado % 2) == 1) {
//			cont_seleccionado--;
//		}
//
//		Arrays.
//		
//		// se cruzan
//		for (int i = 0; i < cont_seleccionado; i += 2) {
//			// ver cantidad de nodos que tienen
//			int numNodosElem1 = poblacion.get(seleccionado_cruce[i]).numNodos();
//			int numNodosElem2 = poblacion.get(seleccionado_cruce[i + 1])
//					.numNodos();
//
//			// seleccionar un nodo aleatorio
//			int nodo_elegido_elem1 = (int) (Math.random() * (numNodosElem1));
//			int nodo_elegido_elem2 = (int) (Math.random() * (numNodosElem2));
//
//			Nodo nodo1 = poblacion.get(seleccionado_cruce[i]).encuentraNodo(
//					nodo_elegido_elem1);
//			Nodo nodo2 = poblacion.get(seleccionado_cruce[i + 1])
//					.encuentraNodo(nodo_elegido_elem2);
//
//			// ///////////////////////////
//			System.out.println(poblacion.get(seleccionado_cruce[i]));
//			System.out.println(nodo1.toString());
//			System.out.println(poblacion.get(seleccionado_cruce[i + 1]));
//			System.out.println(nodo2.toString());
//			// /////////////////////////////
//			Nodo nodoAux = nodo1.hacerCopia();
//			if (nodo2.getOperacion().equals("sqrt")
//					|| nodo2.getOperacion().equals("log")) {
//				nodo1 = new FuncionUnaria();
//				nodo1 = nodo2.hacerCopia();
//			} else {
//				nodo1 = new FuncionBinaria();
//				nodo1 = nodo2.hacerCopia();
//			}
//			if (nodoAux.getOperacion().equals("sqrt")
//					|| nodoAux.getOperacion().equals("log")) {
//				nodo2 = new FuncionUnaria();
//				nodo2 = nodoAux.hacerCopia();
//			} else {
//				nodo2 = new FuncionBinaria();
//				nodo2 = nodoAux.hacerCopia();
//			}
//
//			// ///////////////////////////
//			System.out.println("nodo 1");
//			System.out.println(nodo1.toString());
//			System.out.println("nodo 2");
//			System.out.println(nodo2.toString());
//			// /////////////////////////////
//			
//			//primer individuo meter en ind1 en pos nodo_elegido_elem1 el nodo1
//			poblacion.get(seleccionado_cruce[i]).setNodo(nodo_elegido_elem1, nodo1);
//			System.out.println("---------------------------------------");
//			System.out.println(poblacion.get(seleccionado_cruce[i]).toString());
//			System.out.println("---------------------------------------");
//			
//		}
//		
//	}
//}
