package modelo.seleccion;

import java.util.ArrayList;

import modelo.Cromosoma;

public class SeleccionRestos implements Seleccion{

	private int k;
	
	private static final double N = 2;
	

	@Override
	public ArrayList<Cromosoma> seleccionar(ArrayList<Cromosoma> poblacion) {
		
		ArrayList<Cromosoma> nueva_poblacion = new ArrayList<Cromosoma>();
		
		double[] acumulada = new double[poblacion.size()];
		
		double prob_seleccion;
		
		int pos;

		this.k = (int) (poblacion.size() / N);


		for (int i = 0; i < poblacion.size(); i++) {
			acumulada[i] = poblacion.get(i).getAcumulada();
		}
			
		// Recorremos el array de pobacion
		for (int i = 0; i < poblacion.size(); i++) {
			// Seleccionamos una probabilidad aleatoria
			prob_seleccion = Math.random();
			// Inicializamos la primera posicion
			pos = 0;
			

			while (pos < poblacion.size() && prob_seleccion > acumulada[pos]) {
				pos++;
			}
			
			for (int j = 0; j < (int) (prob_seleccion * this.k); j++) {	
				nueva_poblacion.add(poblacion.get(pos).hacerCopia());
				
				if (nueva_poblacion.size() == poblacion.size()) {
					return nueva_poblacion;
				}
			}
		
		}
		
		
		
		Seleccion s = new SeleccionTorneoProbabilistico();
		
		ArrayList<Cromosoma> p = new ArrayList<Cromosoma>();
		
		for (int k = nueva_poblacion.size(); k < poblacion.size(); k++) {
			p.add(poblacion.get(k).hacerCopia());
		}
		
		p = s.seleccionar(p);
		
		
		for (int j = 0; j < p.size(); j++) {
			nueva_poblacion.add(p.get(j).hacerCopia());
		}
		
		return nueva_poblacion;
		
	}

}
