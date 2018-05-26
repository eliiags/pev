package pev.modelo.seleccion;

import java.util.Random;

import pev.modelo.Cromosoma;

public class SeleccionTorneoDeterministico implements Seleccion {

	@Override
	public Cromosoma[] seleccionar(Cromosoma[] poblacion) {
		// TODO Auto-generated method stub
		Cromosoma[] nueva_poblacion = new Cromosoma[poblacion.length];
		int pos_mejor = 0;
		Random aleatorio = new Random(System.currentTimeMillis());
		
		
		for(int i = 0; i < poblacion.length; i++){
			// Generamos las tres posiciones de los elementos a seleccionar
			int pos1 = aleatorio.nextInt(poblacion.length);
			int pos2 = aleatorio.nextInt(poblacion.length);;
			int pos3 = aleatorio.nextInt(poblacion.length);

			if (poblacion[i].getMaximizar()) {
				pos_mejor = poblacion[pos1].getAptitud() >= poblacion[pos2].getAptitud() ? pos1 : pos2;
				pos_mejor = poblacion[pos3].getAptitud() >= poblacion[pos_mejor].getAptitud() ? pos3 : pos_mejor;
			}
			else {
				pos_mejor = poblacion[pos1].getAptitud() <= poblacion[pos2].getAptitud() ? pos1 : pos2;
				pos_mejor = poblacion[pos3].getAptitud() <= poblacion[pos_mejor].getAptitud() ? pos3 : pos_mejor;
			}

			// Annadir ese elemento a la nueva poblacion
			nueva_poblacion[i] = poblacion[pos_mejor].hacerCopia();
		}	
		
		return nueva_poblacion;
	}

}