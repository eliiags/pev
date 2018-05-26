package modelo.mutacion;

import java.util.ArrayList;
import java.util.HashSet;

import modelo.Cromosoma;

public class MutacionHeuristica implements Mutacion {

	private ArrayList<String> permutaciones;
	
	private int N;
	
	private static final int PER = 2;


	@Override
   	public void muta(ArrayList<Cromosoma> poblacion, double prob_mutacion) {
   		
    	
    	this.N = poblacion.get(0).getGenes().get(0).getLongitud();
    	
    	boolean mutado = false;
    	
    	double probabilidad = 0.0,
    		   fitness = Double.MAX_VALUE;

    	HashSet<Integer> posibles;
    	ArrayList<Integer> pos;

    	char[] valores;
    	
    	Cromosoma cromosoma_prueba, 
    			  el_mejor = null;
    	
    	
    	// Para cada individuo
		for (int i = 0; i < poblacion.size(); i++) {
			
			mutado = false;
			probabilidad = Math.random();
				
			// Comprobamos la probabilidad de mutar
			if (probabilidad < prob_mutacion) {

				// Borramos los posibles para este individuo
				pos = new ArrayList<Integer>();
				posibles = new HashSet<Integer>();
				this.permutaciones = new ArrayList<String>();
				
				// Escoger N elementos al azar 
		        while (posibles.size() < PER) {
		        	posibles.add(((int) (Math.random() * (this.N))));
		        }

		        // Metemos en un arraylist las posiciones posibles
		        pos = new ArrayList<Integer>(posibles);
		        
		        // Valores (caracteres) que estan en la posicion pos
		        valores = new char[pos.size()];

		        for (int j = 0; j < pos.size(); j++) {
					// Buscamos el elementos en esa posicion.
					valores[j] = ((char) poblacion.get(i).
							getGenes().get(0).getAlelo(pos.get(j)));
				}
				
				// Hacer permutaciones
				obtenerPermutaciones(valores, "", pos.size());
				
				// Para cada permutacion comprobar el fitness
				cromosoma_prueba = poblacion.get(i);
				
				for (int j = 0; j < this.permutaciones.size(); j++) {
					// Establece los valores
					for (int k = 0; k < pos.size(); k++) {
						cromosoma_prueba.getGenes().get(0).
							setAlelo((char)this.permutaciones.get(j).charAt(k), pos.get(k));
					}
					
					if (cromosoma_prueba.getAptitud() < fitness) {
						fitness  = cromosoma_prueba.getAptitud();
						// el mas alto es el que se queda.
						el_mejor = cromosoma_prueba.hacerCopia();
					}
				}
				
				mutado = true;
			}
			
			if (mutado) {
				poblacion.set(i, el_mejor);
			}
			
		}
		
   	}
    
    
    private void obtenerPermutaciones(char[] valores, String act, int n) {
    	
    	if (n == 0) {
            this.permutaciones.add(act);
        } else {
            for (int i = 0; i < valores.length; i++) {
                if (!act.contains(valores[i] + "")) { // Controla que no haya repeticiones
                	obtenerPermutaciones(valores, act + valores[i] + "", n - 1);
                }
            }
        }
    }    
       
}
