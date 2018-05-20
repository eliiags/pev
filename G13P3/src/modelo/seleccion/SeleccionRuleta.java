package modelo.seleccion;

import java.util.ArrayList;

import modelo.Cromosoma;

public class SeleccionRuleta implements Seleccion {
	
	
	
	@Override
	public ArrayList<Cromosoma> seleccionar(ArrayList<Cromosoma> poblacion) {

		// Los que seran seleccionados para sobrevivir
		ArrayList<Cromosoma> nueva_poblacion = new ArrayList<Cromosoma>();
		
		double[] acumulada = new double[poblacion.size()];
		
		double fitness_total = 0.0;
		int pos_superviviente;

		double prob_seleccion;
		
		
		double max = 0.0;
			
		for (int i = 0; i < poblacion.size(); i++) { 
			if (poblacion.get(i).getAptitud() > max) {
				max = poblacion.get(i).getAptitud();
			}
		}
		
		for (int i = 0; i < poblacion.size(); i++) { 
			fitness_total += max - poblacion.get(i).getAptitud();
		}
			
		acumulada[0] = ( max - poblacion.get(0).getAptitud()) / fitness_total; 
			
		for (int i = 1; i < poblacion.size(); i++) {
			acumulada[i] = acumulada[i-1] + ( max -	poblacion.get(i).getAptitud()) / fitness_total;
		}
			

		// Recorremos el array de pobacion
		for (int i = 0; i < poblacion.size(); i++) {
			// Seleccionamos una probabilidad aleatoria
			prob_seleccion = Math.random();
			// Inicializamos la primera posicion
			pos_superviviente = 0;
			

			while(pos_superviviente < poblacion.size() && prob_seleccion > acumulada[pos_superviviente]) {
				pos_superviviente++;
			}
		
			if (pos_superviviente < poblacion.size()) {
				nueva_poblacion.add(poblacion.get(pos_superviviente).hacerCopia());
			}
		
		}
		
		return nueva_poblacion;
	}
	
}
