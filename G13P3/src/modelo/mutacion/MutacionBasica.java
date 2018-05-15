package modelo.mutacion;

import java.util.ArrayList;
import java.util.Random;

import modelo.Cromosoma;

public class MutacionBasica implements Mutacion{

	@Override
	public void muta(ArrayList<Cromosoma> poblacion, double prob_mutacion) {
		
		Random random = new Random(System.currentTimeMillis());
		double prob = random.nextDouble();
		
		for (Cromosoma crm : poblacion) {
			
			if (prob < prob_mutacion) {
				
				// Generamos un numero aleatorio entre 0 y numNodos
				int nodo = random.nextInt(crm.numNodos());
				
				// Muta
				crm.muta(nodo);
				
			}

		}
		
	}

}
