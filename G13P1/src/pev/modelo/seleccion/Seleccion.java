package pev.modelo.seleccion;

import pev.modelo.Cromosoma;

public interface Seleccion {

	public abstract Cromosoma[] seleccionar(Cromosoma[] poblacion);

}
