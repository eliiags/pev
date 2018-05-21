package modelo.mutacion;

import java.util.ArrayList;

import modelo.Cromosoma;

public abstract class Mutacion {

//	protected ArrayList<Cromosoma> nueva_poblacion;
	
	public abstract void muta(ArrayList<Cromosoma> poblacion, double prob_mutacion);

	
//	public ArrayList<Cromosoma> getNuevaPoblacion() {
//		return this.nueva_poblacion;
//	}
	
	
	
}
