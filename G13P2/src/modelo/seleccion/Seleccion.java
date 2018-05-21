package modelo.seleccion;

import java.util.ArrayList;

import modelo.Cromosoma;

public abstract class Seleccion {

	protected ArrayList<Cromosoma> nueva_poblacion;
	
	public abstract void seleccionar(ArrayList<Cromosoma> poblacion);
	
	public ArrayList<Cromosoma> getNuevaPoblacion() {
		return this.nueva_poblacion;
	}
	
}
