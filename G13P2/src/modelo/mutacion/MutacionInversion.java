package modelo.mutacion;

import java.util.ArrayList;

import modelo.Cromosoma;

public class MutacionInversion implements Mutacion {

	private int N;


	@Override
	public void muta(ArrayList<Cromosoma> poblacion, double prob_mutacion) {
	
		this.N = poblacion.get(0).getGenes().get(0).getLongitud();
		
		int pos1, 
			pos2, 
			aux;
		double prob = 0.0;
		
		for (int i = 0; i < poblacion.size(); i++) {
			
			prob = Math.random();
			
			if (prob < prob_mutacion) {
				// preparar mutacion
				pos1 = ((int) (Math.random() * (this.N)));
				pos2 = ((int) (Math.random() * (this.N)));
				
				if (pos1 != pos2) {
					if (pos1 > pos2) {
						// Swap
						aux = pos2;
						pos2 = pos1;
						pos1 = aux;
					}
					
					// Mutamos
	                int contador = 0;
	                for (int j = pos1; j < (pos2 + pos1) / 2; j++){
	                    char letra1 = (char) poblacion.get(i).getGenes().get(0).getAlelo(j);
	                    char letra2 = (char) poblacion.get(i).getGenes().get(0).getAlelo(pos2-contador);
	                    
	                    poblacion.get(i).getGenes().get(0).setAlelo(letra2, j);
	                    poblacion.get(i).getGenes().get(0).setAlelo(letra1, pos2 - contador);
	                    
	                    contador++;
	                }
					
	                poblacion.get(i).setModificado(true);
				}
			}
		}
	}

}
