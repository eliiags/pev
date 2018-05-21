package modelo.mutacion;

import java.util.ArrayList;

import modelo.Cromosoma;

public class MutacionInsercion extends Mutacion {

	
	private int num_cambios = 1;

	public MutacionInsercion() {

	}

	@Override
	public void muta(ArrayList<Cromosoma> poblacion, double prob_mutacion) {
	
		boolean mutado;
		double probabilidad = 0.0;
		int pos_elemento, 
			pos;
		
		
		for (int i = 0; i < poblacion.size(); i++) {
			
			mutado = false;
			probabilidad = Math.random();
				
			if (probabilidad < prob_mutacion) {
			
				for (int k = 0; k < this.num_cambios; k++) {
					
					// Sacamos un numero aleatorio
					pos_elemento = ((int) (Math.random() * (poblacion.get(i).getLongitudCromosoma())));
					pos = ((int) (Math.random() * (pos_elemento)));
					
					
					if(pos_elemento > pos) {
						char caracter = (char) poblacion.get(i).getGenes().get(0).getAlelo(pos_elemento);
						
						for (int j = pos_elemento; j > pos; j--) {
							poblacion.get(i).getGenes().get(0).
								setAlelo(poblacion.get(i).getGenes().get(0).getAlelo(j-1), 
										j);
						}
						
						poblacion.get(i).getGenes().get(0).setAlelo(caracter, pos);
						
						mutado = true;
					}
				}
			
			}
			
			if (mutado) {
				poblacion.get(i).funcionFitness();
			}

		}


	}

}
