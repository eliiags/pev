package modelo.seleccion;

import java.util.ArrayList;

import modelo.Cromosoma;

public class SeleccionManuEli extends Seleccion{

	public SeleccionManuEli() {

	}
	
	
	@Override
	public void seleccionar(ArrayList<Cromosoma> poblacion) {
		// Los que seran seleccionados para sobrevivir
		this.nueva_poblacion = new ArrayList<Cromosoma>();
		
		double prob_seleccion;
		int pos_superviviente, 
			i = 0;
		
		while (i < poblacion.size()){
			
			prob_seleccion = Math.random();
			
			// Inicializamos la primera posicion
			pos_superviviente = 0;

			while (pos_superviviente < poblacion.size()
					&& prob_seleccion > poblacion.get(pos_superviviente).getPuntuacionAcumulada()) {
				pos_superviviente++;
			}
			
			int num_elems = (int) (poblacion.get(pos_superviviente).getPuntuacionRelativa() * poblacion.size());
			num_elems++;
			
			for (int j = 0; j < num_elems; j++) {
				if (pos_superviviente < poblacion.size() && nueva_poblacion.size() < poblacion.size()) {
					this.nueva_poblacion.add(poblacion.get(pos_superviviente));
				}
			}
			
			i += num_elems;
		}
		
	}

}
