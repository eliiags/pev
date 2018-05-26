package modelo.cruce;

import java.util.ArrayList;

import modelo.Cromosoma;

public interface Cruce {

	public void reproduccion(ArrayList<Cromosoma> poblacion, double prob_cruce);
	
}
