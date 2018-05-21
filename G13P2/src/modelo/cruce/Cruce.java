package modelo.cruce;

import java.util.ArrayList;

import modelo.Cromosoma;

public abstract class Cruce {

	protected int longitud_cromosoma;
	
	protected int N;
	
//	protected ArrayList<Cromosoma> nueva_poblacion;
	
	public abstract void reproduccion(ArrayList<Cromosoma> poblacion, double prob_cruce);
	
//	public ArrayList<Cromosoma> getNuevaPoblacion() {
//		return this.nueva_poblacion;
//	}
	
}
