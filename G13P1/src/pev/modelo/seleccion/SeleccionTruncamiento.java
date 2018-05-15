package pev.modelo.seleccion;

import java.util.Arrays;

import pev.modelo.Cromosoma;

public class SeleccionTruncamiento extends Seleccion {

	private double trun;
	
	public SeleccionTruncamiento(double trun) {
		this.trun = trun;
	}
	
	
	@Override
	public void seleccionar(Cromosoma[] poblacion) {
		
		this.nueva_poblacion = new Cromosoma[poblacion.length];
		
		int proporcion = (int) ((int) 1 / this.trun),
			tam = (int) poblacion.length / proporcion,
			pos = 0,
			i = 0; 
		
		// Separamos los mejores individuos
		Cromosoma[] mejores = this.separar(poblacion, tam);
		
		
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
		
		Arrays.sort(poblacion);
		
		// Mejores cromosomas
		Cromosoma[] mejores = new Cromosoma[tam];

		// Si es una funcion de maximizar
		if (poblacion[0].getMaximizar()){
			for (int i = 0; i < mejores.length; i++) {
				mejores[i] = poblacion[poblacion.length - 1 - i].hacerCopia();
			}
		}
		// Si es una funcion de maximizar
		else {
			for (int i = 0; i < mejores.length; i++) {
				mejores[i] = poblacion[i].hacerCopia();
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