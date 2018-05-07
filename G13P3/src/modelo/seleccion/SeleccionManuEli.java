package modelo.seleccion;

import java.util.ArrayList;

import modelo.Cromosoma;

public class SeleccionManuEli implements Seleccion{

	
	@Override
	public void seleccionar(ArrayList<Cromosoma> poblacion) {

		ArrayList<Cromosoma> aux = new ArrayList<Cromosoma>();
		
		aux.addAll(poblacion);
		
		poblacion.removeAll(poblacion);
		
		
		double prob_seleccion;
		
		int pos_superviviente, 
			i = 0;
		
		
		while (i < aux.size()){
			
			prob_seleccion = Math.random();
			
			// Inicializamos la primera posicion
			pos_superviviente = 0;

			while (pos_superviviente < aux.size() && prob_seleccion > aux.get(pos_superviviente).getAcumulada()) {
				pos_superviviente++;
			}
			
			int num_elems = (int) (aux.get(pos_superviviente).getRelativa() * aux.size());
			num_elems++;
			
			
			for (int j = 0; j < num_elems; j++) {
				if (pos_superviviente < aux.size() && poblacion.size() < aux.size()) {
					poblacion.add(aux.get(pos_superviviente));
				}
			}
			
			i += num_elems;
		}
		
	}

}
