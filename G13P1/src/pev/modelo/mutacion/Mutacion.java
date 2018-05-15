package pev.modelo.mutacion;

import pev.modelo.Cromosoma;

public abstract class Mutacion {

	protected Cromosoma[] nueva_poblacion;
	
	public abstract void muta(Cromosoma[] poblacion, double prob_mutacion);

	
	public Cromosoma[] getNuevaPoblacion() {
		return this.nueva_poblacion;
	}
	
	
	
}
