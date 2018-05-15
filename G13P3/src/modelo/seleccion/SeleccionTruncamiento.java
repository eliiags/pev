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
	public ArrayList<Cromosoma> seleccionar(ArrayList<Cromosoma> poblacion) {
		
		ArrayList<Cromosoma> nueva_poblacion = new ArrayList<Cromosoma>();
		
		int proporcion = (int) ((int) 1 / this.trun),
			tam = (int) poblacion.size() / proporcion,
			pos = 0,
			i   = 0;
		
		Cromosoma[] mejores = separar(poblacion, tam);
		
		
		while (tam > 0 && i < poblacion.size()) {
			
			for (int j = 0; j < tam; j++) {
				if (pos < poblacion.size()) {
					nueva_poblacion.add(mejores[j].hacerCopia());
					pos++;
				}
			}
			
			i += tam;
		
		}
		
		return nueva_poblacion;
		
	}
	
	
	
	private Cromosoma[] separar(ArrayList<Cromosoma> poblacion, int tam) {
		// Mejores cromosomas
		Cromosoma[] mejores = new Cromosoma[tam];

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
					mejores[i] = poblacion.get(j);
					encontrado = true;
				}
				j++;
			}
		}
		
		return mejores;
		
	}
	
	
	

}
