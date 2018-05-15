package modelo.mutacion;

import java.util.ArrayList;

import modelo.Cromosoma;

public interface Mutacion {

	public void muta(ArrayList<Cromosoma> poblacion, double prob_mutacion);
	
}
