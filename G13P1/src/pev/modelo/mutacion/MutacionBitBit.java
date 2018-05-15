package pev.modelo.mutacion;

import pev.modelo.Cromosoma;

public class MutacionBitBit extends Mutacion {

	
	public MutacionBitBit() {

	}
	
	@Override
	public void muta(Cromosoma[] poblacion, double prob_mutacion) {

		this.nueva_poblacion = new Cromosoma[poblacion.length];
		
		for (int i = 0; i < poblacion.length; i++) {
			this.nueva_poblacion[i] = poblacion[i].hacerCopia();
		}
		
		
		boolean mutado;
		
		double probabilidad;

		
		for (int i = 0; i < this.nueva_poblacion.length; i++) {
			mutado = false;
			for (int j = 0; j < this.nueva_poblacion[i].getGenes().length; j++) {
				for (int k = 0; k < this.nueva_poblacion[i].getGenes()[j].getLongitudGen(); k++) {
					// Generamos un numero aleatorio entre 0 y 1.
					probabilidad = Math.random();
					if (probabilidad < prob_mutacion) {
						this.nueva_poblacion[i].getGenes()[j].muta(k);
						mutado = true;
					}
				} 
			}
			
			
			if (mutado) {
				this.nueva_poblacion[i].calcularFenotipo();
				this.nueva_poblacion[i].funcionFitness();
			}
		}
		
	}

}
