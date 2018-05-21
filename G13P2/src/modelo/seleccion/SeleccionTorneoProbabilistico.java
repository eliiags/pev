package modelo.seleccion;

import java.util.ArrayList;
import java.util.Random;

import modelo.Cromosoma;

public class SeleccionTorneoProbabilistico extends Seleccion {

	private double p = 0.5;
	
	public SeleccionTorneoProbabilistico() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void seleccionar(ArrayList<Cromosoma> poblacion) {
		
		this.nueva_poblacion = new ArrayList<Cromosoma>();

		int pos_elegido = 0;

		Random aleatorio = new Random(System.currentTimeMillis());

		for (int i = 0; i < poblacion.size(); i++) {
			// generamos las tres posiciones de los elementos a seleccionar
			int pos1 = aleatorio.nextInt(poblacion.size());
			int pos2 = aleatorio.nextInt(poblacion.size());

			//generar un num random entre 0 y 1
			//si es mayor que p se elige el mejor sino el peor
			double num = Math.random();
			
			if (num >= p) {
				pos_elegido = poblacion.get(pos1).getAptitud() <= poblacion.get(pos2).getAptitud() ? pos1 : pos2;
			}
			else {
				pos_elegido = poblacion.get(pos1).getAptitud() <= poblacion.get(pos2).getAptitud() ? pos2 : pos1;
			}
			
			// Annadir ese elemento a la nueva poblacion
			this.nueva_poblacion.add(poblacion.get(pos_elegido));
		}
	}

}