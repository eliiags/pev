package modelo.mutacion;

import java.util.ArrayList;
import java.util.HashSet;

import modelo.Cromosoma;

public class MutacionIntercambio implements Mutacion {

	private int N;
	

	@Override
	public void muta(ArrayList<Cromosoma> poblacion, double prob_mutacion) {

		this.N = poblacion.get(0).getGenes().get(0).getLongitud();
		
		double probabilidad = 0.0;

		int pos1, 
			pos2;
		
		HashSet<Integer> posibles;
    	ArrayList<Integer> pos;

		
		// Para cada individuo
		for (int i = 0; i < poblacion.size(); i++) {
			probabilidad = Math.random();
			
			if (probabilidad < prob_mutacion) {
				
				// Borramos los posibles para este individuo
				pos = new ArrayList<Integer>();
				posibles = new HashSet<Integer>();
				
				// Escoger N elementos al azar 
		        while (posibles.size() < 2) {
		        	posibles.add(((int) (Math.random() * (this.N))));
		        }

		        // Metemos en un arraylist las posiciones posibles
		        pos = new ArrayList<Integer>(posibles);
				
				// para el cromosoma i de la nueva poblacion
				pos1 = pos.get(0);
				pos2 = pos.get(1);
				
//				System.out.println("Valores: " + pos);
				
				char aux = (char) poblacion.get(i).getGenes().get(0).getAlelo(pos1);
//				Cromosoma crm = poblacion.get(i).hacerCopia();
				// Intercambiar pos1 con pos2
				poblacion.get(i).
					getGenes().get(0).
					setAlelo(poblacion.get(i).getGenes().get(0).getAlelo(pos2), pos1);
//				System.out.println("Primer cambio: " + poblacion.get(i).toString());
				poblacion.get(i).
					getGenes().get(0).
					setAlelo(aux, pos2);
//				System.out.println("Segundo cambio: " + poblacion.get(i).toString());
				
				poblacion.get(i).setModificado(true);

			}

		}


	}

}
