package pev.modelo.seleccion;

import pev.modelo.Cromosoma;

public class SeleccionRuleta extends Seleccion {
	
	
	public SeleccionRuleta() {
		
	}
		
	
	@Override
	public void seleccionar(Cromosoma[] poblacion) {

		// Los que seran seleccionados para sobrevivir
		this.nueva_poblacion = new Cromosoma[poblacion.length];
		
		double[] acumulada = new double[poblacion.length];
		
		double fitness_total = 0.0;
		int pos_superviviente;

		double prob_seleccion;
		
		
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
				acumulada[i] = acumulada[i-1] + 
						( max -	poblacion[i].getAptitud()) / fitness_total;
			}
			
		}
		
		
		// Recorremos el array de pobacion
		for (int i = 0; i < this.nueva_poblacion.length; i++) {
			// Seleccionamos una probabilidad aleatoria
			prob_seleccion = Math.random();
			// Inicializamos la primera posicion
			pos_superviviente = 0;
			

			while(pos_superviviente < this.nueva_poblacion.length 
					&& prob_seleccion > acumulada[pos_superviviente]) {
				pos_superviviente++;
			}
		
			if (pos_superviviente < poblacion.length) {
				this.nueva_poblacion[i] = poblacion[pos_superviviente].hacerCopia();
			}
		
		}
		
		
	}
	
}
