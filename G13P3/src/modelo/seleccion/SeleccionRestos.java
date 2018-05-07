package modelo.seleccion;

import java.util.ArrayList;

import modelo.Cromosoma;

public class SeleccionRestos implements Seleccion{

	private int k;
	
	private static final double N = 2;
	

	@Override
	public void seleccionar(ArrayList<Cromosoma> poblacion) {
		
		ArrayList<Cromosoma> aux = new ArrayList<Cromosoma>();
		
		aux.addAll(poblacion);

		poblacion.removeAll(poblacion);
		
		
		double[] acumulada = new double[aux.size()];
		
		double prob_seleccion;
		
		int pos;

		this.k = (int) (aux.size() / N);


		for (int i = 0; i < aux.size(); i++) {
			acumulada[i] = aux.get(i).getAcumulada();
		}
			
		// Recorremos el array de pobacion
		for (int i = 0; i < aux.size(); i++) {
			// Seleccionamos una probabilidad aleatoria
			prob_seleccion = Math.random();
			// Inicializamos la primera posicion
			pos = 0;
			

			while (pos < aux.size() && prob_seleccion > acumulada[pos]) {
				pos++;
			}
			
			for (int j = 0; j < (int) (prob_seleccion * this.k); j++) {	
				poblacion.add(aux.get(pos));
				
				if (poblacion.size() == aux.size()) {
					return;
				}
			}
		
		}
		
		
		
		Seleccion s = new SeleccionTorneoProbabilistico();
		
		ArrayList<Cromosoma> p = new ArrayList<Cromosoma>();
		
		for (int k = poblacion.size(); k < aux.size(); k++) {
			p.add(aux.get(k));
		}
		
		s.seleccionar(p);
		
		for (int j = 0; j < p.size(); j++) {
			poblacion.add(p.get(j));
		}
		
		
		
	}

}
