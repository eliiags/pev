package modelo.mutacion;

import java.util.ArrayList;
import java.util.Random;

import modelo.Cromosoma;

public class MutacionArbol implements Mutacion{

	@Override
	public void muta(ArrayList<Cromosoma> poblacion, double prob_mutacion) {
		
		Random random = new Random(System.currentTimeMillis());
		
		for (Cromosoma crm : poblacion) {
			
			double prob = random.nextDouble();

			if (prob < prob_mutacion) {
				
				// Generamos un numero aleatorio entre 0 y numNodos
				int nodo = random.nextInt(crm.getNumNodos(2));
				
				// Muta
				crm.muta(nodo, 2);
				
			}

		}

		
	}

}
