package modelo.mutacion;

import java.util.ArrayList;

import modelo.Cromosoma;

public class MutacionManuEli implements Mutacion {

	private int N;
	
	
	@Override
	public void muta(ArrayList<Cromosoma> poblacion, double prob_mutacion) {

		ArrayList<Character> aux = new ArrayList<Character>();
		
		this.N = poblacion.get(0).getGenes().get(0).getLongitud();
    	
    	double probabilidad;
    	
    	int pos,
    		rand = 0;

    	
    	// Para cada individuo
		for (int i = 0; i < poblacion.size(); i++) {
			
			probabilidad = Math.random();
				
			// Comprobamos la probabilidad de mutar
			if (probabilidad < prob_mutacion) {

				// Seleccionamos una posicion aleatoria
				pos = ((int) (Math.random() * (this.N)));

				// Sacamos los valores de
				for (int j = pos; j < this.N; j++) {
					// Guardamos el valor de la posicion
					aux.add((char) poblacion.get(i).getGenes().get(0).getAlelo(j));
				}
				
				for (int j = pos; j < this.N; j++) {
					rand = ((int) (Math.random() * (aux.size())));
					poblacion.get(i).getGenes().get(0).setAlelo((char) aux.get(rand), j);
					aux.remove(rand);
				}
				
				poblacion.get(i).setModificado(true);
			}
			
		}
		
	}

}
