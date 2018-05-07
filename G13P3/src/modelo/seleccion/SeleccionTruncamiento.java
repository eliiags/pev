package modelo.seleccion;

import java.util.ArrayList;
import java.util.Arrays;

import modelo.Cromosoma;

public class SeleccionTruncamiento implements Seleccion {

	private double trun;
	
	public SeleccionTruncamiento(double trun) {
		this.trun = trun;
	}
	
	
	@Override
	public void seleccionar(ArrayList<Cromosoma> poblacion) {
		
		ArrayList<Cromosoma> aux = new ArrayList<Cromosoma>();
		
		aux.addAll(poblacion);
		
		int proporcion = (int) ((int) 1 / this.trun),
			tam = (int) aux.size() / proporcion,
			pos = 0,
			i = 0;
		
		Cromosoma[] mejores = separar(aux, tam);
		
		
		while (tam > 0 && i < aux.size()) {
			
			for (int j = 0; j < tam; j++) {
				if (pos < aux.size()) {
					poblacion.set(pos, mejores[j]);
					pos++;
				}
			}
			
			i += tam;
		
		}
		
		
	}
	
	
	
	private Cromosoma[] separar(ArrayList<Cromosoma> aux, int tam) {
		// Mejores cromosomas
		Cromosoma[] mejores = new Cromosoma[tam];

		// Array donde se almacenaras las aptitudes para ordenarlas posteriormente
		double[] aptitudes = new double[aux.size()];
		
		
		// Actualizamos el array de aptitudes con el fitness de cada individuo;
		for (int i = 0; i < aptitudes.length; i++) {
			aptitudes[i] = aux.get(i).getAptitud();
		}
		
		
		// Ordenador de mayor a menor
		Arrays.sort(aptitudes);
		
		
		int j;
		boolean encontrado;
		
		for (int i = 0; i < tam; i++) {
			encontrado = false;
			j = 0;
			while (!encontrado && j < aux.size()) {
				if (aptitudes[i] == aux.get(j).getAptitud()) {
					mejores[i] = aux.get(j);
					encontrado = true;
				}
				j++;
			}
		}
		
		return mejores;
		
	}
	
	
	

}
