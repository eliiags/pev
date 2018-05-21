package modelo.seleccion;

import java.util.ArrayList;

import modelo.Cromosoma;

public class SeleccionRestos extends Seleccion{

	private int k;
	
	private static final double N = 2;
	
	public SeleccionRestos() {

	}
	
	
	@Override
	public void seleccionar(ArrayList<Cromosoma> poblacion) {
		
		double[] acumulada = new double[poblacion.size()];
		double prob_seleccion;
		
		int pos;
		
		this.nueva_poblacion = new ArrayList<Cromosoma>();
		
		this.k = (int) (poblacion.size() / N);


		for (int i = 0; i < poblacion.size(); i++) {
			acumulada[i] = poblacion.get(i).getPuntuacionAcumulada();
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
				this.nueva_poblacion.add(poblacion.get(pos));
				
				if (this.nueva_poblacion.size() == poblacion.size()) {
					return;
				}
			}
		
		}
		
		
		
		Seleccion s = new SeleccionTorneoProbabilistico();
		
		ArrayList<Cromosoma> p = new ArrayList<Cromosoma>();
		
		for (int k = this.nueva_poblacion.size(); k < poblacion.size(); k++) {
			p.add(poblacion.get(k));
		}
		
		s.seleccionar(p);
		
		p = s.getNuevaPoblacion();
		
		for (int j = 0; j < p.size(); j++) {
			this.nueva_poblacion.add(p.get(j));
		}
		
		
		
	}

}
