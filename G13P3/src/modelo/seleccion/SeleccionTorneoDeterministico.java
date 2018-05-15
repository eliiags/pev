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
	public ArrayList<Cromosoma> seleccionar(ArrayList<Cromosoma> poblacion) {

		ArrayList<Cromosoma> nueva_poblacion = new ArrayList<Cromosoma>();
		
		int pos_mejor = 0;
		
		Random aleatorio = new Random(System.currentTimeMillis());
		
		
		for (int i = 0; i < poblacion.size(); i++){
			// Generamos las tres posiciones de los elementos a seleccionar
			int pos1 = aleatorio.nextInt(poblacion.size());
			int pos2 = aleatorio.nextInt(poblacion.size());
			int pos3 = aleatorio.nextInt(poblacion.size());

			pos_mejor = poblacion.get(pos1).getAptitud() <= poblacion.get(pos2).getAptitud() ? pos1 : pos2;
			pos_mejor = poblacion.get(pos3).getAptitud() <= poblacion.get(pos_mejor).getAptitud() ? pos3 : pos_mejor;
			
			// Annadir ese elemento a la nueva poblacion
			nueva_poblacion.add(poblacion.get(pos_mejor).hacerCopia());
		}
		
		return nueva_poblacion;
	}

}
