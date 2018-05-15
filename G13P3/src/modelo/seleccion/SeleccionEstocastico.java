package modelo.seleccion;

import java.util.ArrayList;

import modelo.Cromosoma;

public class SeleccionEstocastico implements Seleccion {


	@Override
	public ArrayList<Cromosoma> seleccionar(ArrayList<Cromosoma> poblacion) {

		ArrayList<Cromosoma> nueva_poblacion = new ArrayList<Cromosoma>();
		
		double[] acumulada = new double[poblacion.size()];
		
		int pos_elegida = 0;
		
		double valor_a_sumar = (double) 1 / (double) poblacion.size(),
			   marca 		 = 0.0;
		

		for (int i = 0; i < poblacion.size(); i++) {
			acumulada[i] = poblacion.get(i).getAcumulada();
		}

		
		for (int i = 0; i < poblacion.size(); i++) {
			
			if (pos_elegida == 0) {
				i = 0;
			}
			
			// Inicializamos la primera posicion	
			pos_elegida = 0;
			marca += valor_a_sumar;

			
			while (pos_elegida < poblacion.size() && marca > acumulada[pos_elegida]) {
				pos_elegida++;
			}
		
			if (pos_elegida < poblacion.size() && pos_elegida > 0) {
				nueva_poblacion.add(poblacion.get(pos_elegida).hacerCopia());
			}
			else if(pos_elegida == poblacion.size()){
				nueva_poblacion.add(poblacion.get(pos_elegida - 1).hacerCopia());
			}
			
		}
		
		return nueva_poblacion;
	
	}
	
	
}
