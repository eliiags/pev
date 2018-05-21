package modelo.seleccion;

import java.util.ArrayList;
import java.util.Arrays;

import modelo.Cromosoma;

public class SeleccionTruncamiento extends Seleccion {

	private double trun;
	
	
	public SeleccionTruncamiento(double trun) {
		this.trun = trun;
	}
	
	
	@Override
	public void seleccionar(ArrayList<Cromosoma> poblacion) {
		
		
		this.nueva_poblacion = new ArrayList<Cromosoma>();
		
		ArrayList<Cromosoma> mejores = new ArrayList<Cromosoma>();
		
		int proporcion = (int) ((int) 1 / this.trun);
		
		int tam = (int) poblacion.size() / proporcion;
		
		mejores = this.separar(poblacion, tam);
		
		int i = 0,
			pos = 0;
		
		
		while (i < poblacion.size()) {
			
			for (int j = 0; j < tam; j++) {
				if (pos < poblacion.size()) {
					this.nueva_poblacion.add(pos, mejores.get(j));
					pos++;
				}
			}
			
			i += tam;
		
		}
		
		
	}
	
	
	
	private ArrayList<Cromosoma> separar(ArrayList<Cromosoma> poblacion, int tam) {
		// Mejores cromosomas
		ArrayList<Cromosoma> mejores = new ArrayList<Cromosoma>();// Cromosoma[tam];

		// Array donde se almacenaras las aptitudes para ordenarlas posteriormente
		double[] aptitudes = new double[poblacion.size()];
		
		// Actualizamos el array de aptitudes con el fitness de cada individuo;
		for (int i = 0; i < aptitudes.length; i++) {
			aptitudes[i] = poblacion.get(i).getAptitud();
		}
		
		// Ordenador de mayor a menor
		Arrays.sort(aptitudes);
		
		int j;
		boolean encontrado;
		
		for (int i = 0; i < tam; i++) {
			encontrado = false;
			j = 0;
			while (!encontrado && j < poblacion.size()) {
				if (aptitudes[i] == poblacion.get(j).getAptitud()) {
					mejores.add(poblacion.get(j));
					encontrado = true;
				}
				j++;
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