package modelo.seleccion;

import java.util.ArrayList;

import modelo.Cromosoma;

public class SeleccionManuEli implements Seleccion{

	
	@Override
	public ArrayList<Cromosoma> seleccionar(ArrayList<Cromosoma> poblacion) {

		ArrayList<Cromosoma> nueva_poblacion = new ArrayList<Cromosoma>();
		
		double[] acumulada = new double[poblacion.size()];
		double[] relativa  = new double[poblacion.size()];
		
		double prob_seleccion;
		
		int pos_superviviente, 
			j = 0;
		
		double max = 0.0, 
			   fitness_total = 0.0;
		
		for (int i = 0; i < poblacion.size(); i++) { 
			if (poblacion.get(i).getAptitud() > max) {
				max = poblacion.get(i).getAptitud();
			}
		}
			
		for (int i = 0; i < poblacion.size(); i++) { 
			fitness_total += max - poblacion.get(i).getAptitud();
		}
		
		acumulada[0] = (max - poblacion.get(0).getAptitud()) / fitness_total; 
		relativa[0]  = (max - poblacion.get(0).getAptitud()) / fitness_total;
		
		for (int i = 1; i < poblacion.size(); i++) {
			acumulada[i] = acumulada[i-1] + (max - poblacion.get(i).getAptitud()) / fitness_total;
			relativa[i]  = (max - poblacion.get(i).getAptitud()) / fitness_total;
		}
		
		
		while (j < poblacion.size()){
			
			prob_seleccion = Math.random();
			
			// Inicializamos la primera posicion
			pos_superviviente = 0;

			while (pos_superviviente < poblacion.size() && prob_seleccion > acumulada[pos_superviviente]) {
				pos_superviviente++;
			}
			
			int num_elems = (int) (relativa[pos_superviviente] * poblacion.size());
			num_elems++;
			
			
			for (int k = 0; k < num_elems; k++) {
				if (pos_superviviente < poblacion.size() && nueva_poblacion.size() < poblacion.size()) {
					nueva_poblacion.add(poblacion.get(pos_superviviente).hacerCopia());
				}
			}
			
			j += num_elems;
		}
		
		return nueva_poblacion;
		
	}

}
