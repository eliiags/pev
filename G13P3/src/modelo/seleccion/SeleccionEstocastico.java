package modelo.seleccion;

import java.util.ArrayList;

import modelo.Cromosoma;

public class SeleccionEstocastico implements Seleccion {


	@Override
	public ArrayList<Cromosoma> seleccionar(ArrayList<Cromosoma> poblacion) {

		ArrayList<Cromosoma> nueva_poblacion = new ArrayList<Cromosoma>();
		
		double[] acumulada = new double[poblacion.size()];
		
		int fitness_total = 0,
			pos_elegida   = 0;
		
		double valor_a_sumar = (double) 1 / (double) poblacion.size();
		
		double marca = 0.0;
		
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
			acumulada[i] = acumulada[i-1] + ( max - poblacion.get(i).getAptitud()) / fitness_total;
		}
		
			
		for (int i = 0; i < poblacion.size(); i++) {
			
			// Inicializamos la primera posicion	
			pos_elegida = 0;
			marca += valor_a_sumar;

			while (pos_elegida < poblacion.size() && marca > acumulada[pos_elegida]) {
				pos_elegida++;
			}
		
			if (pos_elegida < poblacion.size()) {
				nueva_poblacion.add(poblacion.get(pos_elegida).hacerCopia());
			}
			else if(pos_elegida == poblacion.size()){
				nueva_poblacion.add(poblacion.get(pos_elegida - 1).hacerCopia());
			}
		
			
		}
	
		return nueva_poblacion;
	
	}
}
