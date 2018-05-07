package modelo.seleccion;

import java.util.ArrayList;
import java.util.Random;

import modelo.Cromosoma;

public class SeleccionTorneoDeterministico implements Seleccion {
	
	
	/**
	 * Cada elemento de la muestra se toma eligiendo el mejor
	 * de los individuos de un conjunto de z elementos(2 ó 3)
	 * tomados al azar de la población base.
	 * El proceso se repite k veces hasta completar la muestra.
	 */

	
	@Override
	public void seleccionar(ArrayList<Cromosoma> poblacion) {

		ArrayList<Cromosoma> aux = new ArrayList<Cromosoma>();
		
		aux.addAll(poblacion);
		

		int pos_mejor = 0;
		
		Random aleatorio = new Random(System.currentTimeMillis());
		
		
		for(int i = 0; i < aux.size(); i++){
			// Generamos las tres posiciones de los elementos a seleccionar
			int pos1 = aleatorio.nextInt(aux.size());
			int pos2 = aleatorio.nextInt(aux.size());
			int pos3 = aleatorio.nextInt(aux.size());

			pos_mejor = aux.get(pos1).getAptitud() <= aux.get(pos2).getAptitud() ? pos1 : pos2;
			pos_mejor = aux.get(pos3).getAptitud() <= aux.get(pos_mejor).getAptitud() ? pos3 : pos_mejor;
			
			// Annadir ese elemento a la nueva poblacion
			poblacion.set(i, aux.get(pos_mejor));
		}	
	}

}
