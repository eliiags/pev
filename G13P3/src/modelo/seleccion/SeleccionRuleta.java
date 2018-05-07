package modelo.seleccion;

import java.util.ArrayList;

import modelo.Cromosoma;

public class SeleccionRuleta implements Seleccion {
	
	
	
	@Override
	public void seleccionar(ArrayList<Cromosoma> poblacion) {

		ArrayList<Cromosoma> aux = new ArrayList<Cromosoma>();
		
		aux.addAll(poblacion);
		
		
		double[] acumulada = new double[aux.size()];
		
		int pos_superviviente;

		double prob_seleccion;
		
		
		
		for (int i = 0; i < aux.size(); i++) {
			acumulada[i] = aux.get(i).getAcumulada();
		}
			
		
		// Recorremos la poblacion
		for (int i = 0; i < aux.size(); i++) {
			// Seleccionamos una probabilidad aleatoria
			prob_seleccion = Math.random();
			
			// Inicializamos la primera posicion
			pos_superviviente = 0;
			

			
			while (pos_superviviente < aux.size() && prob_seleccion > acumulada[pos_superviviente]) {
				pos_superviviente++;
			}
		
			
			if (pos_superviviente < aux.size()) {
				poblacion.set(i, aux.get(pos_superviviente));
			}
		
		}
		
		
	}
	
}
