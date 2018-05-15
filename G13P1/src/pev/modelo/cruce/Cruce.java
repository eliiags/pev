package pev.modelo.cruce;

import pev.modelo.Cromosoma;

public abstract class Cruce {

	protected int longitud_cromosoma;
	
	protected Cromosoma[] nueva_poblacion;
	
	public abstract void reproduccion(Cromosoma[] poblacion, double prob_cruce);
	
	public Cromosoma[] getNuevaPoblacion() {
		return this.nueva_poblacion;
	}
	
}
