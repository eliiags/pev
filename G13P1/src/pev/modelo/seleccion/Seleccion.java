package pev.modelo.seleccion;

import pev.modelo.Cromosoma;

public abstract class Seleccion {

	protected Cromosoma[] nueva_poblacion;
	
	public abstract void seleccionar(Cromosoma[] poblacion);
	
	public Cromosoma[] getNuevaPoblacion() {
		return this.nueva_poblacion;
	}
	
}
