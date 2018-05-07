package modelo.seleccion;

import java.util.ArrayList;

import modelo.Cromosoma;

public class SeleccionEstocastico implements Seleccion {


	@Override
	public void seleccionar(ArrayList<Cromosoma> poblacion) {

		ArrayList<Cromosoma> aux = new ArrayList<Cromosoma>();
		
		aux.addAll(poblacion);
		
		double[] acumulada = new double[aux.size()];
		
		int pos_elegida = 0;
		
		double valor_a_sumar = (double) 1 / (double) aux.size();
		
		double marca = 0.0;
		

		for (int i = 0; i < aux.size(); i++) {
			acumulada[i] = aux.get(i).getAcumulada();
		}

		
		for (int i = 0; i < aux.size(); i++) {
			
			if (pos_elegida == 0) {
				i = 0;
			}
			
			// Inicializamos la primera posicion	
			pos_elegida = 0;
			marca += valor_a_sumar;

			
			while (pos_elegida < aux.size() && marca > acumulada[pos_elegida]) {
				pos_elegida++;
			}
		
			if (pos_elegida < aux.size() && pos_elegida > 0) {
				poblacion.set(i, aux.get(pos_elegida));
			}
			else if(pos_elegida == aux.size()){
				poblacion.set(i, aux.get(pos_elegida - 1));
			}
		
			
		}
	
	}
	
	
}
