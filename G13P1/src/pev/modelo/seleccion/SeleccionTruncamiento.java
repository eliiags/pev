package pev.modelo.seleccion;

import java.util.Arrays;

import pev.modelo.Cromosoma;

public class SeleccionTruncamiento implements Seleccion {

	private double trun;
	
	public SeleccionTruncamiento(double trun) {
		this.trun = trun;
	}
	
	
	@Override
	public Cromosoma[] seleccionar(Cromosoma[] poblacion) {
		
		Cromosoma[] nueva_poblacion = new Cromosoma[poblacion.length];
		
		int proporcion = (int) ((int) 1 / this.trun),
			tam = (int) poblacion.length / proporcion,
			pos = 0,
			i = 0; 
		
		// Separamos los mejores individuos
		Cromosoma[] mejores = this.separar(poblacion, tam);
		
		
		while (i < nueva_poblacion.length) {
			
			for (int j = 0; j < tam; j++) {
				if (pos < nueva_poblacion.length) {
					nueva_poblacion[pos] = mejores[j].hacerCopia();
					pos++;
				}
			}
			
			i += tam;
		
		}
		
		return nueva_poblacion;
		
		
	}
	
	
	
	private Cromosoma[] separar(Cromosoma[] poblacion, int tam) {
		
		// Mejores cromosomas
		Cromosoma[] mejores = new Cromosoma[tam];
		
		// Array donde se almacenaras las aptitudes para ordenarlas posteriormente
		double[] aptitudes = new double[poblacion.length];
		
		// Actualizamos el array de aptitudes con el fitness de cada individuo;
		for (int i = 0; i < aptitudes.length; i++) {
			aptitudes[i] = poblacion[i].getAptitud();
		}
		
		// Ordenador de mayor a menor
		Arrays.sort(aptitudes);

		if (poblacion[0].getMaximizar()) { // MAXIMIZAR
			for (int i = 0; i < mejores.length; i++) {
				for (int j = 0; j < poblacion.length; j++) {
					if (aptitudes[aptitudes.length - 1 - i] == poblacion[j].getAptitud()) {
						mejores[i] = poblacion[j].hacerCopia();
					}
				}
			}
		}
		else { // MINIMIZAR
			for (int i = 0; i < mejores.length; i++) {
				for (int j = 0; j < poblacion.length; j++) {
					if (aptitudes[i] == poblacion[j].getAptitud()) {
						mejores[i] = poblacion[j].hacerCopia();
					}
				}
			}
		}
		
		return mejores;
	}
	
}