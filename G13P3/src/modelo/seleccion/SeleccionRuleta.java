package modelo.seleccion;

import java.util.ArrayList;

import modelo.Cromosoma;

public class SeleccionRuleta implements Seleccion {
	
	
	
	@Override
	public ArrayList<Cromosoma> seleccionar(ArrayList<Cromosoma> poblacion) {

		ArrayList<Cromosoma> nueva_poblacion = new ArrayList<Cromosoma>();
		
		double[] acumulada = new double[poblacion.size()];
		
		int pos_superviviente;

		double prob_seleccion;
		
		
		
		for (int i = 0; i < poblacion.size(); i++) {
			acumulada[i] = poblacion.get(i).getAcumulada();
		}
			
		
		// Recorremos la poblacion
		for (int i = 0; i < poblacion.size(); i++) {
			// Seleccionamos una probabilidad aleatoria
			prob_seleccion = Math.random();
			
			// Inicializamos la primera posicion
			pos_superviviente = 0;
			
			
			while (pos_superviviente < poblacion.size() && prob_seleccion > acumulada[pos_superviviente]) {
				pos_superviviente++;
			}
		
			
			if (pos_superviviente < poblacion.size()) {
				nueva_poblacion.add(poblacion.get(pos_superviviente).hacerCopia());
			}
		
		}
		
		
		return nueva_poblacion;
	}
	
}
