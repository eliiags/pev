package modelo.cruce;

import java.util.ArrayList;

import modelo.Cromosoma;
import modelo.CromosomaDesencripta;

public class CrucePMX extends Cruce{

	
	
	
	public CrucePMX() {

	}
	
	@Override
	public void reproduccion(ArrayList<Cromosoma> poblacion, double prob_cruce) {
		
		/***/
		this.N = poblacion.get(0).getLongitudCromosoma();
		/***/

		// Seleccionados para reproducir
		int[] seleccionado_cruce = new int[poblacion.size()];
	
		// Contador de seleccionados
		int cont_seleccionado = 0,
			pos1, pos2, aux;
		
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
		
		
		// Se cruzan
		for (int i = 0; i < cont_seleccionado; i+=2) {

			// Se cruzan los individuos elegidos en un punto al azar (numero de genes)
			pos1 = ((int) (Math.random() * (this.N)));
			pos2 = ((int) (Math.random() * (this.N)));
			
			if (pos1 != pos2) {
				if (pos1 > pos2) {
					// Swap
					aux = pos2;
					pos2 = pos1;
					pos1 = aux;
				}
		
				ArrayList<Character> cod_padre1 = getCromosoma(poblacion.get(seleccionado_cruce[i]), pos1, pos2, true);
				ArrayList<Character> cod_padre2 = getCromosoma(poblacion.get(seleccionado_cruce[i+1]), pos1, pos2, true);

//				System.out.println("Padre1: " + cod_padre1.toString());
//				System.out.println("Padre2: " + cod_padre2.toString());
//				System.out.println("**************************************************");
				
				ArrayList<Character> intervalo_padre1 = getIntervalo(poblacion.get(seleccionado_cruce[i]), pos1, pos2);
				ArrayList<Character> intervalo_padre2 = getIntervalo(poblacion.get(seleccionado_cruce[i+1]), pos1, pos2);
				
//				System.out.println("Intervalo Padre1: " + intervalo_padre1.toString());
//				System.out.println("Intervalo Padre2: " + intervalo_padre2.toString());
//				System.out.println("**************************************************");
				
				ArrayList<Character> cod_hijo1 = getCromosoma(poblacion.get(seleccionado_cruce[i+1]), pos1, pos2, false);
				ArrayList<Character> cod_hijo2 = getCromosoma(poblacion.get(seleccionado_cruce[i]), pos1, pos2, false);
//				System.out.println("**************************************************");
				
//				System.out.println("hijo1: " + cod_hijo1.toString());
//				System.out.println("hijo2: " + cod_hijo2.toString());
				
				
				
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
//						System.out.println("entra");
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
				
//				System.out.println("**************************************************");
//				System.out.println("Hijo1: " + cod_hijo1.toString());
//				System.out.println("Hijo1: " + cod_hijo2.toString());
				
				// Descodificamos los cromosomas
				Cromosoma hijo1 = this.integrarCromosoma(cod_hijo1);
				Cromosoma hijo2 = this.integrarCromosoma(cod_hijo2);
				
			
				
				
				// Los nuevos individuos sustituyen a sus progenitores
				poblacion.set(seleccionado_cruce[i], hijo1);
				poblacion.set(seleccionado_cruce[i+1], hijo2);

				poblacion.get(seleccionado_cruce[i]).funcionFitness();

				poblacion.get(seleccionado_cruce[i+1]).funcionFitness();
				
			}
		
		}
		
		
		
	}
	
	
	public ArrayList<Character> getCromosoma(Cromosoma hijo, int pos1, int pos2, boolean igual) {
		
		ArrayList<Character> genes = new ArrayList<Character>();
		
		for (int i = 0; i < hijo.getLongitudCromosoma(); i++) {
			if (!igual) {
				if (i >= pos1 && i <= pos2) {
					genes.add((char) hijo.getGenes().get(0).getAlelo(i));
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
	
	public ArrayList<Character> getIntervalo(Cromosoma hijo, int pos1, int pos2) {
		
		ArrayList<Character> genes = new ArrayList<Character>();
		
		for (int i = pos1; i <= pos2; i++) {
			genes.add((char) hijo.getGenes().get(0).getAlelo(i));
		}
		
		return genes;
		
	}
	
	
	public Cromosoma integrarCromosoma(ArrayList<Character> hijo) {
		
		// Creamos una copia de un individuo de la poblacion
		// Al final del algoritmo este será el cromosoma decodificado,
		Cromosoma crm = new CromosomaDesencripta();
				
		for (int i = 0; i < this.N; i++){
			// Actualizamos el cromosoma
			crm.getGenes().get(0).setAlelo(hijo.get(i), i);
			
		}
		
		return crm;
	}
	

}
