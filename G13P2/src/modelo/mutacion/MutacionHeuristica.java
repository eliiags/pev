package modelo.mutacion;

import java.util.ArrayList;
import java.util.HashSet;

import modelo.Cromosoma;

public class MutacionHeuristica extends Mutacion {

	private ArrayList<String> permutaciones;
	
	private int N;
	
	private static final int PER = 2;

    public MutacionHeuristica() {
    	
    }
    
    @Override
   	public void muta(ArrayList<Cromosoma> poblacion, double prob_mutacion) {
   		
    	
    	this.N = poblacion.get(0).getLongitudCromosoma();
    	
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
					
					// calcula el fitness
					cromosoma_prueba.funcionFitness();
					
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
    		// System.out.println(act);
        } else {
            for (int i = 0; i < valores.length; i++) {
                if (!act.contains(valores[i] + "")) { // Controla que no haya repeticiones
                	obtenerPermutaciones(valores, act + valores[i] + "", n - 1);
                }
            }
        }
    }    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
//    private static ArrayList<ArrayList<Integer>> getPermutaciones(ArrayList<Integer> lista) {
//        ArrayList<ArrayList<Integer>> list = new ArrayList<>();
//        
//        if (lista.size() == 1) {
//            list.add(lista);
//        } else {
//            for (Integer elem : lista) {
//                ArrayList<Integer> subLista = new ArrayList<>(lista);
//                subLista.remove(elem);
//                // Llamada recursiva
//                ArrayList<ArrayList<Integer>> subListaNueva = getPermutaciones(subLista);
//                // Añado las permutaciones anteriores e itero sobre esa lista de listas
//                for(ArrayList<Integer> lista2: subListaNueva) {
//                    ArrayList<Integer> local = new ArrayList<>();
//                    local.add(elem);
//                    local.addAll(lista2);
//                    list.add(local);
//                }
//            }
//        }
//        
//        return list;
//    }
    


    
//    protected void mutar(Cromosoma[] poblacion, int i, double probabilidad) {
//        HashSet<Integer> posibles = new HashSet();
//
//        // Seleccionamos que elemenos vamos a permutar
//        while(posibles.size() < N)
//            posibles.add(this.rand.nextInt(poblacion[i].getSize()));
//
//        // Añadimos los elemntos al ArrayList
//        ArrayList<Integer> seleccionados = new ArrayList(Arrays.asList(posibles.toArray()));
//        // Generamos todas las permutaciones del ArrayList
//        // Nos da un ArrayList con ArrayList de cada una de las permutaciones
//        // 1 2 3
//        // 1 3 2
//        // 2 1 3
//        // 3 1 2
//        // 3 2 1
//        ArrayList<ArrayList<Integer>> permutaciones = getPermutaciones(seleccionados);
//        //permutaciones.remove(0);
//        double fitnessMejor = Double.MIN_VALUE;
//        Cromosoma mejor = null;
//        // Recorremos toda la lista de posibles permutaciones
//        for (int j = 0; j < permutaciones.size(); j++) {
//            Cromosoma c = poblacion[i].copy();
//
//            // Para cada posible permutacion recorro toda la permutacion
//            for (int k = 0; k < permutaciones.get(j).size(); k++) {
//
//                int posicion_anterior = seleccionados.get(k);
//                int posicion_nueva = permutaciones.get(j).get(k);
//                c.getGen(0).setImplementacion(posicion_nueva, poblacion[i].getGen(0).getImplementacion(posicion_anterior));
//            }
//
//            if (c.getFitness() > fitnessMejor) {
//                fitnessMejor = c.getFitness();
//                mejor = c;
//            }
//        }
//        if(mejor != null)
//            poblacion[i] = mejor;
//    }

	
   
}
