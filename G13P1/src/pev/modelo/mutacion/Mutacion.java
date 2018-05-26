package pev.modelo.mutacion;

import pev.modelo.Cromosoma;

public interface Mutacion {

	public abstract Cromosoma[] muta(Cromosoma[] poblacion, double prob_mutacion);
	
}
