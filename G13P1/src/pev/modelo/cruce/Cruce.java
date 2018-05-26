package pev.modelo.cruce;

import pev.modelo.Cromosoma;

public interface Cruce {

	public abstract Cromosoma[] reproduccion(Cromosoma[] poblacion, double prob_cruce);
	
}
