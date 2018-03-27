package pev.modelo.seleccion;

import java.util.Arrays;

import pev.modelo.Cromosoma;

public class TruncamientoSeleccion extends Seleccion {

	private double trun;
	
	public TruncamientoSeleccion(double trun) {
		this.trun = trun;
	}
	
	@Override
	public void seleccionar(Cromosoma[] poblacion) {
		
		
		this.nueva_poblacion = new Cromosoma[poblacion.length];
		
		Cromosoma[] mejores;
		
		int proporcion = (int) ((int) 1 / this.trun);
		
		int tam = (int) poblacion.length / proporcion;
		
		mejores = this.separar(poblacion, tam);
		
		int i = 0,
			pos = 0;
		
		
		while (i < this.nueva_poblacion.length) {
			
			for (int j = 0; j < tam; j++) {
				if (pos < this.nueva_poblacion.length) {
					this.nueva_poblacion[pos] = mejores[j];
					pos++;
				}
			}
			
			i += tam;
		
		}
		
		
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
		
		// Si es una funcion de maximizar
		if (poblacion[0].getMaximizar()){
			// MAXIMIZAR
			for (int i = 0; i < mejores.length; i++) {
				for (int j = 0; j < poblacion.length; j++) {
					if (aptitudes[aptitudes.length - 1 - i] == poblacion[j].getAptitud()) {
						mejores[i] = poblacion[j].hacerCopia();
					}
				}
			}
		}
		// Si es una funcion de maximizar
		else {
			// MINIMIZAR
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

//System.out.println("*************************************");
//for (int i = 0; i < aux_poblacion.length; i++) {
//	System.out.println(aux_poblacion[i].getAptitud());
//}
//System.out.println("*************************************");