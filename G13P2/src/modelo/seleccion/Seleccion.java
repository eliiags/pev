package modelo.seleccion;

import java.util.ArrayList;

import modelo.Cromosoma;

public interface Seleccion {

	public ArrayList<Cromosoma> seleccionar(ArrayList<Cromosoma> poblacion);
	
}
