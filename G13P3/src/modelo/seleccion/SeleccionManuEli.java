package modelo.seleccion;

import java.util.ArrayList;

import modelo.Cromosoma;

public class SeleccionManuEli implements Seleccion{

	
	@Override
	public ArrayList<Cromosoma> seleccionar(ArrayList<Cromosoma> poblacion) {

		ArrayList<Cromosoma> nueva_poblacion = new ArrayList<Cromosoma>();
		
		
		double prob_seleccion;
		
		int pos_superviviente, 
			i = 0;
		
		
		while (i < poblacion.size()){
			
			prob_seleccion = Math.random();
			
			// Inicializamos la primera posicion
			pos_superviviente = 0;

			while (pos_superviviente < poblacion.size() && prob_seleccion > poblacion.get(pos_superviviente).getAcumulada()) {
				pos_superviviente++;
			}
			
			int num_elems = (int) (poblacion.get(pos_superviviente).getRelativa() * poblacion.size());
			num_elems++;
			
			
			for (int j = 0; j < num_elems; j++) {
				if (pos_superviviente < poblacion.size() && nueva_poblacion.size() < poblacion.size()) {
					nueva_poblacion.add(poblacion.get(pos_superviviente).hacerCopia());
				}
			}
			
			i += num_elems;
		}
		
		return nueva_poblacion;
		
	}

}
