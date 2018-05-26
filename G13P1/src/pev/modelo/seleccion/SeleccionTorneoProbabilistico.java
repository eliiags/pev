package pev.modelo.seleccion;

import java.util.Random;

import pev.modelo.Cromosoma;

public class SeleccionTorneoProbabilistico implements Seleccion{

	private double p = 0.5;
	
	@Override
	public Cromosoma[] seleccionar(Cromosoma[] poblacion) {
		
		Cromosoma[] nueva_poblacion = new Cromosoma[poblacion.length];
		
		int pos_elegido = 0;

		Random aleatorio = new Random(System.currentTimeMillis());


		for (int i = 0; i < poblacion.length; i++) {
			// Generamos las tres posiciones de los elementos a seleccionar
			int pos1 = aleatorio.nextInt(poblacion.length);
			int pos2 = aleatorio.nextInt(poblacion.length);

			// Generar un num random entre 0 y 1
			// Si es mayor que p se elige el mejor sino el peor
			double num = Math.random();
			
			
			if (num >= p) {
				pos_elegido = poblacion[pos1].getAptitud() <= poblacion[pos2].getAptitud() ? pos1 : pos2;
			}
			else {
				pos_elegido = poblacion[pos1].getAptitud() <= poblacion[pos2].getAptitud() ? pos2 : pos1;
			}
			
			// Annadir ese elemento a la nueva poblacion
			nueva_poblacion[i] = poblacion[pos_elegido].hacerCopia();
		}
		
		return nueva_poblacion;
	}
	
}
