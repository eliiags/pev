package modelo.mutacion;

import java.util.ArrayList;
import java.util.Random;

import modelo.Cromosoma;

public class MutacionPermutacion implements Mutacion{

	@Override
	public void muta(ArrayList<Cromosoma> poblacion, double prob_mutacion) {
		
		Random random = new Random(System.currentTimeMillis());
		
		for (Cromosoma crm : poblacion) {
			
			double prob = random.nextDouble();

			if (prob < prob_mutacion) {
				
				int nodo = crm.getNumNodos(1);
				
				if (nodo != 0) {
					// Generamos un numero aleatorio entre 0 y numNodos
					nodo = random.nextInt(nodo);
					// Muta
					crm.muta(nodo, 3);
				}
				
			}

		}
		
	}

}
