package pev.modelo.mutacion;

import pev.modelo.Cromosoma;

public class MutacionBitBit implements Mutacion {

	@Override
	public Cromosoma[] muta(Cromosoma[] poblacion, double prob_mutacion) {

		double probabilidad;

		for (int i = 0; i < poblacion.length; i++) {
			for (int j = 0; j < poblacion[i].getGenes().length; j++) {
				for (int k = 0; k < poblacion[i].getGenes()[j].getLongitudGen(); k++) {
					// Generamos un numero aleatorio entre 0 y 1.
					probabilidad = Math.random();
					if (probabilidad < prob_mutacion) {
						poblacion[i].getGenes()[j].muta(k);
						poblacion[i].setModificado(true);
					}
				} 
			}
			
		}
		
		return poblacion;
	}

}
