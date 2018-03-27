package pev.modelo.seleccion;

import java.util.Random;

import pev.modelo.Cromosoma;

public class TorneoDeterministicoSeleccion extends Seleccion {


	public TorneoDeterministicoSeleccion() {

	}
	
	
	/**
	 * Cada elemento de la muestra se toma eligiendo el mejor
	 * de los individuos de un conjunto de z elementos(2 ó 3)
	 * tomados al azar de la población base.
	 * El proceso se repite k veces hasta completar la muestra.
	 */
	
	
	@Override
	public void seleccionar(Cromosoma[] poblacion) {
		// TODO Auto-generated method stub
		this.nueva_poblacion = new Cromosoma[poblacion.length];
		int pos_mejor = 0;
		Random aleatorio = new Random(System.currentTimeMillis());
		
		
		for(int i = 0; i < poblacion.length; i++){
			//generamos las tres posiciones de los elementos a seleccionar
			int pos1 = aleatorio.nextInt(poblacion.length);
			int pos2 = aleatorio.nextInt(poblacion.length);;

			if(poblacion[i].getMaximizar()) {
				pos_mejor = poblacion[pos1].getAptitud() >= poblacion[pos2].getAptitud() ? pos1 : pos2;
			}
			else {
				pos_mejor = poblacion[pos1].getAptitud() <= poblacion[pos2].getAptitud() ? pos1 : pos2;
			}

			
			// Añadir ese elemento a la nueva poblacion
			this.nueva_poblacion[i] = poblacion[pos_mejor];
		}	
	}

}
