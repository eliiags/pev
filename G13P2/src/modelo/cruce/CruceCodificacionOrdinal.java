package modelo.cruce;

import java.util.ArrayList;

import modelo.Cromosoma;

public class CruceCodificacionOrdinal implements Cruce {

	private int N;
	
	@Override
	public void reproduccion(ArrayList<Cromosoma> poblacion, double prob_cruce) {
		
		N = poblacion.get(0).getGenes().get(0).getLongitud();
		
		// Seleccionados para reproducir
		int[] seleccionado_cruce = new int[poblacion.size()];
	
		// Contador de seleccionados
		int cont_seleccionado = 0,
			punto_cruce = 0;
		
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
			punto_cruce = ((int) (Math.random() * (N)));
			
			// Codificamos los cromosomas
			ArrayList<Integer> cod_padre1 = this.codificarfCromosoma(poblacion.get(seleccionado_cruce[i]));
			ArrayList<Integer> cod_padre2 = this.codificarfCromosoma(poblacion.get(seleccionado_cruce[i+1]));
			
			ArrayList<Integer> cod_hijo1 = new ArrayList<Integer>();
			ArrayList<Integer> cod_hijo2 = new ArrayList<Integer>();
			
			
			cod_hijo1.addAll(cod_padre1);
			cod_hijo2.addAll(cod_padre2);
			
			
			// Cruzamos los arrays
			for (int j = punto_cruce; j < cod_hijo1.size(); j++) {
				cod_hijo1.set(j, cod_padre2.get(j));
				cod_hijo2.set(j, cod_padre1.get(j));
			}
			
			
			// Descodificamos los cromosomas
			Cromosoma hijo1 = this.decodificarCromosoma(cod_hijo1);
			Cromosoma hijo2 = this.decodificarCromosoma(cod_hijo2);
			
//			System.out.println("------------");
//			
//			System.out.println(this.nueva_poblacion.get(seleccionado_cruce[i]).toString());
//			System.out.println(this.nueva_poblacion.get(seleccionado_cruce[i+1]).toString());
//			System.out.println(hijo1.toString());
//			System.out.println(hijo2.toString());
//			
//			System.out.println("------------");
			
			
			// Los nuevos individuos sustituyen a sus progenitores
			poblacion.set(seleccionado_cruce[i], hijo1.hacerCopia());
			poblacion.set(seleccionado_cruce[i+1], hijo2.hacerCopia());
			
			poblacion.get(seleccionado_cruce[i]).setModificado(true);
			poblacion.get(seleccionado_cruce[i+1]).setModificado(true);

		}
		
	}
	
	
	
	public ArrayList<Integer> codificarfCromosoma (Cromosoma crm) {
		
		int pos;
		
		// Creamos abc
		ArrayList<Character> abc = new ArrayList<Character>();

		// Inicializamos la lista
		for (int i = 0; i < this.N; i++) {
			abc.add((char) (i + 97));
		}
		
		// ArrayList que tendra el cromosoma codificado
		ArrayList<Integer> cromosoma_codificado = new ArrayList<Integer>();

		
		for (int j = 0; j < this.N; j++){
			// Buscamos la posicion que ocupa cada alelo del cromosoma en abc
			char c = (char) crm.getGenes().get(0).getAlelo(j);
			pos = abc.indexOf((char) c);
			// Annadimos la posicion al array
			cromosoma_codificado.add(pos);
			// Eliminamos la letra de abc
			abc.remove(pos);
		}
		
		return cromosoma_codificado;
		
	}
	

	public Cromosoma decodificarCromosoma(ArrayList<Integer> cromosoma_codificado){
		
		char elemento;
		
		// Creamos una copia de un individuo de la poblacion
		// Al final del algoritmo este será el cromosoma decodificado,
		Cromosoma crm = new Cromosoma();
		
		// Creamos la lista dinamica abc
		ArrayList<Character> abc = new ArrayList<Character>();

		// Inicializamos la lista
		for (int i = 0; i < this.N; i++) {
			abc.add((char) (i + 97));
		}
		
		for (int i = 0; i < this.N; i++){
			// Buscamos cual es el elemento que ocupa la pos i del arraylist
			elemento = abc.get(cromosoma_codificado.get(i));
			// Actualizamos el cromosoma
			crm.getGenes().get(0).getAlelos().add(elemento);
			// Vamos borrando los elementos de abc
			abc.remove((int) cromosoma_codificado.get(i));
		}

		return crm;

	}
	
	
}