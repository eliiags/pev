package pev.modelo.seleccion;

import pev.modelo.Cromosoma;

public class SeleccionEstocastico implements Seleccion {

	@Override
	public Cromosoma[] seleccionar(Cromosoma[] poblacion) {
		
		Cromosoma[] nueva_poblacion = new Cromosoma[poblacion.length];

		double[] acumulada = new double[poblacion.length];
		
		int pos_elegida   = 0;
		
		double valor_a_sumar = (double) 1 / (double) poblacion.length,
			   fitness_total = 0.0,
			   marca = 0.0;
		
		
		if (poblacion[0].getMaximizar()) {
			for (int i = 0; i < poblacion.length; i++) {
				acumulada[i] = poblacion[i].getPuntuacionAcumulada();
			}
		} 
		else {

			double max = 0.0;
			
			for (int i = 0; i < poblacion.length; i++) { 
				if (poblacion[i].getAptitud() > max) {
					max = poblacion[i].getAptitud();
				}
			}
			
			for (int i = 0; i < poblacion.length; i++) { 
				fitness_total += max - poblacion[i].getAptitud();
			}
			
			acumulada[0] = ( max - poblacion[0].getAptitud()) / fitness_total; 
			
			for (int i = 1; i < poblacion.length; i++) {
				acumulada[i] = acumulada[i-1] + ( max - 
						poblacion[i].getAptitud()) / fitness_total;
			}
			
			
		}

		
		for (int i = 0; i < nueva_poblacion.length; i++) {
			
			if (pos_elegida == 0) {
				i = 0;
			}
			
			// Inicializamos la primera posicion	
			pos_elegida = 0;
			marca += valor_a_sumar;

			while(pos_elegida < nueva_poblacion.length 
					&& marca > acumulada[pos_elegida]) {
				pos_elegida++;
			}
		
			if (pos_elegida < poblacion.length && pos_elegida > 0) {
				nueva_poblacion[i] = poblacion[pos_elegida].hacerCopia();
			}
			else if(pos_elegida == poblacion.length){
				nueva_poblacion[i] = poblacion[pos_elegida-1].hacerCopia();
			}
		
		}
		
		return nueva_poblacion;
	}

}
