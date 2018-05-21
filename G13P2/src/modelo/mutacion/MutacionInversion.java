package modelo.mutacion;

import java.util.ArrayList;

import modelo.Cromosoma;

public class MutacionInversion extends Mutacion {

	private int N;

	public MutacionInversion() {

	}

	@Override
	public void muta(ArrayList<Cromosoma> poblacion, double prob_mutacion) {
	
		this.N = poblacion.get(0).getLongitudCromosoma();
		
		boolean mutado = false;
		int pos1, 
			pos2, 
			aux;
		double prob = 0.0;
		
		for (int i = 0; i < poblacion.size(); i++) {
			
			mutado = false;
//			contador = 0;
			prob = Math.random();
			
			if (prob < prob_mutacion) {
				// preparar mutacion
				pos1 = ((int) (Math.random() * (this.N)));
				pos2 = ((int) (Math.random() * (this.N)));
				
				
				System.out.println("posiciones: " + pos1 + " " + pos2);
				if (pos1 != pos2) {
					if (pos1 > pos2) {
						// Swap
						aux = pos2;
						pos2 = pos1;
						pos1 = aux;
					}
					
					// Mutamos
	                int contador = 0;
	                for (int j = pos1; j < /*pos2 + */(pos2 + pos1)/2; j++){
	                    char letra1 = (char) poblacion.get(i).getGenes().get(0).getAlelo(j);
	                    char letra2 = (char) poblacion.get(i).getGenes().get(0).getAlelo(pos2-contador);
	                    
	                    poblacion.get(i).getGenes().get(0).setAlelo(letra2, j);
	                    poblacion.get(i).getGenes().get(0).setAlelo(letra1, pos2 - contador);
	                    System.out.println("ind: " + poblacion.get(i).toString());
	                    
	                    contador++;
	                }
					
					
//					char elemento = (char) poblacion.get(i).getGenes().get(0).getAlelo(pos2);
//					for (int j = pos2-1; j >= pos1; j--) {
//						poblacion.get(i).getGenes().get(0).
//							setAlelo(poblacion.get(i).getGenes().get(0).getAlelo(j), j+1);
//					}
					
					mutado = true;
				}
			}
			
			if (mutado) {
				poblacion.get(i).funcionFitness();
			}
		}
	}

}
