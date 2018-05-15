package modelo.seleccion;

import java.util.ArrayList;
import java.util.Random;

import modelo.Cromosoma;

public class SeleccionTorneoProbabilistico implements Seleccion {

	private double p = 0.5;
	

	@Override
	public ArrayList<Cromosoma> seleccionar(ArrayList<Cromosoma> poblacion) {
		
		ArrayList<Cromosoma> nueva_poblacion = new ArrayList<Cromosoma>();
		
		int pos_elegido = 0;

		Random aleatorio = new Random(System.currentTimeMillis());


		for (int i = 0; i < poblacion.size(); i++) {
			// Generamos las tres posiciones de los elementos a seleccionar
			int pos1 = aleatorio.nextInt(poblacion.size());
			int pos2 = aleatorio.nextInt(poblacion.size());

			// Generar un num random entre 0 y 1
			// Si es mayor que p se elige el mejor sino el peor
			double num = Math.random();
			
			
			if (num >= p) {
				pos_elegido = poblacion.get(pos1).getAptitud() <= poblacion.get(pos2).getAptitud() ? pos1 : pos2;
			}
			else {
				pos_elegido = poblacion.get(pos1).getAptitud() <= poblacion.get(pos2).getAptitud() ? pos2 : pos1;
			}
			
			// Annadir ese elemento a la nueva poblacion
			nueva_poblacion.add(poblacion.get(pos_elegido).hacerCopia());
		}
		
		return nueva_poblacion;
	}

}