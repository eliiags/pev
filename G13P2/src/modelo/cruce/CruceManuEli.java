package modelo.cruce;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;

import modelo.Cromosoma;

public class CruceManuEli implements Cruce{

	private static final int NUM = 10;
	
	private int N;
	
	@Override
	public void reproduccion(ArrayList<Cromosoma> poblacion, double prob_cruce) {
		
		/***/
		this.N = poblacion.get(0).getGenes().get(0).getLongitud();
		/***/

		// Seleccionados para reproducir
		int[] seleccionado_cruce = new int[poblacion.size()];
	
		// Contador de seleccionados
		int cont_seleccionado = 0;
		
		double prob;

		
		// Se eligen los individuos a cruzar
		for (int i = 0; i < seleccionado_cruce.length; i++) {
			// Se generan números aleatorios entre 0 y 1
			prob = Math.random();
			// Se eligen los individuos de las posiciones i si prob < prob_cruce
			if (prob < prob_cruce) {
				seleccionado_cruce[cont_seleccionado] = i;
				cont_seleccionado++;
			}
		}
		
		// Hacemos el numero de seleccionados par
		if ((cont_seleccionado % 2) == 1) {
			cont_seleccionado--;
		}
		
		
		ArrayList<Integer> pos;
		HashSet<Integer> posibles;
		
		// Se cruzan
		for (int i = 0; i < cont_seleccionado; i+=2) {
			
			// Borramos los posibles para este individuo
			pos = new ArrayList<Integer>();
			posibles = new HashSet<Integer>();
			
			// Escoger N elementos al azar 
	        while (posibles.size() < NUM) {
	        	posibles.add(((int) (Math.random() * (this.N))));
	        }
	        
	        // Metemos en un arraylist las posiciones posibles
	        pos = new ArrayList<Integer>(posibles);
	        
	        Collections.sort(pos);
	        
			ArrayList<Character> cod_padre1 = getCromosoma(poblacion.get(seleccionado_cruce[i]), pos, true);
			ArrayList<Character> cod_padre2 = getCromosoma(poblacion.get(seleccionado_cruce[i+1]), pos, true);

//			System.out.println("Padre1: " + cod_padre1.toString());
//			System.out.println("Padre2: " + cod_padre2.toString());
//			System.out.println("**************************************************");
				
			ArrayList<Character> intervalo_padre1 = getIntervalo(poblacion.get(seleccionado_cruce[i]), pos);
			ArrayList<Character> intervalo_padre2 = getIntervalo(poblacion.get(seleccionado_cruce[i+1]), pos);
				
//			System.out.println("Intervalo Padre1: " + intervalo_padre1.toString());
//			System.out.println("Intervalo Padre2: " + intervalo_padre2.toString());
//			System.out.println("**************************************************");
				
			ArrayList<Character> cod_hijo1 = getCromosoma(poblacion.get(seleccionado_cruce[i+1]), pos, false);
			ArrayList<Character> cod_hijo2 = getCromosoma(poblacion.get(seleccionado_cruce[i]), pos, false);
				
//			System.out.println("hijo1: " + cod_hijo1.toString());
//			System.out.println("hijo2: " + cod_hijo2.toString());
//			System.out.println("**************************************************");
				

			// Para hijo1
			for (int j = 0; j < cod_hijo1.size(); j++) {
				if (cod_hijo1.get(j).charValue() == 'X') {
					if(!cod_hijo1.contains(cod_padre1.get(j))) {
						cod_hijo1.set(j, cod_padre1.get(j));
					}
					else {
						boolean ok = false;
						while (!ok && !intervalo_padre1.isEmpty()) {
							if(!cod_hijo1.contains(intervalo_padre1.get(0))) {
								cod_hijo1.set(j, intervalo_padre1.get(0));
								intervalo_padre1.remove(0);
								ok = true;
							}else {
								intervalo_padre1.remove(0);
							}
							
						}
					}
				}
			}
			
			
			// Para hijo2
			for (int j = 0; j < cod_hijo2.size(); j++) {
				if (cod_hijo2.get(j).charValue() == 'X') {
					if(!cod_hijo2.contains(cod_padre2.get(j))) {
						cod_hijo2.set(j, cod_padre2.get(j));
					}
					else {
						boolean ok = false;
						while (!ok && !intervalo_padre2.isEmpty()) {
							if(!cod_hijo2.contains(intervalo_padre2.get(0))) {
								cod_hijo2.set(j, intervalo_padre2.get(0));
								intervalo_padre2.remove(0);
								ok = true;
							}else {
								intervalo_padre2.remove(0);
							}
							
						}
					}
				}
			}
			
//			System.out.println("**************************************************");
//			System.out.println("Hijo1: " + cod_hijo1.toString());
//			System.out.println("Hijo1: " + cod_hijo2.toString());
			
			// Descodificamos los cromosomas
			Cromosoma hijo1 = this.integrarCromosoma(cod_hijo1);
			Cromosoma hijo2 = this.integrarCromosoma(cod_hijo2);
			
			
			// Los nuevos individuos sustituyen a sus progenitores
			poblacion.set(seleccionado_cruce[i], hijo1);
			poblacion.set(seleccionado_cruce[i+1], hijo2);
			
			poblacion.get(seleccionado_cruce[i]).setModificado(true);
			poblacion.get(seleccionado_cruce[i+1]).setModificado(true);
			

		}
		
	}
	
	
	public ArrayList<Character> getCromosoma(Cromosoma hijo, ArrayList<Integer> pos, boolean igual) {
		
		ArrayList<Character> genes = new ArrayList<Character>();
		ArrayList<Integer> posiciones = new ArrayList<Integer>(pos);
		
		for (int i = 0; i < this.N; i++) {
			if (!igual) {
				if (!posiciones.isEmpty() && i == posiciones.get(0)) {
					genes.add((char) hijo.getGenes().get(0).getAlelo(i));
					posiciones.remove(0);
				}
				else {
					genes.add((char) 'X');
				}
			}else {
				genes.add((char) hijo.getGenes().get(0).getAlelo(i));
			}
		}
		
		return genes;
	}
	
	
	public ArrayList<Character> getIntervalo(Cromosoma hijo, ArrayList<Integer> pos) {
		
		ArrayList<Character> genes = new ArrayList<Character>();
		
		for (int i = 0; i < pos.size(); i++) {
			genes.add((char) hijo.getGenes().get(0).getAlelo(pos.get(i)));
		}
		
		return genes;
		
	}
	
	
	public Cromosoma integrarCromosoma(ArrayList<Character> hijo) {
		
		// Creamos una copia de un individuo de la poblacion
		// Al final del algoritmo este será el cromosoma decodificado,
		Cromosoma crm = new Cromosoma();
				
		for (int i = 0; i < this.N; i++){
			// Actualizamos el cromosoma
			crm.getGenes().get(0).getAlelos().add(new Character(hijo.get(i)));
			
		}
		
		return crm;
	}
	
	
	

}
