package modelo;

import java.util.ArrayList;
import java.util.Map;

import modelo.fich_texto.Fichero;
import modelo.fich_texto.Texto;

public class Cromosoma {

	
	private ArrayList<Gen> genes;
	
	private double aptitud; // Funcion de evaluacion fitness (adaptacion)
	
	private double punt_relativa; // Puntuacion relativa = (aptitud / suma_aptitud)
	
	private double punt_acumulada; // Puntuacion acumulada para seleccion 
										// (suma de todos los fitness)
	private boolean modificado;
	
	private String texto_plano;
	
	
	
	public Cromosoma() {
		this.genes = new ArrayList<Gen>();
		this.genes.add(new Gen());
	}

	
	
	
	/************************** GET & SET *****************************/
	
	public double getAptitud() {
		
		if (this.modificado) {
			funcionFitness();
		}
		
		return this.aptitud;
	}
	
	public double getPuntuacionRelativa() {
		return this.punt_relativa;
	}
	
	public double getPuntuacionAcumulada() {
		return this.punt_acumulada;
	}
	
	public ArrayList<Gen> getGenes() {
		return this.genes;
	}
	
	public void setGen(Gen gen, int pos) {
		this.genes.set(pos, gen);
	}

	public void setModificado(boolean modificado) {
		this.modificado = modificado;
	}
	
	public String getTextoPlano() {
		return this.texto_plano;
	}
	
	/***********************************************************************/
	

	
	
	
	
	
	/***************************** METODOS *********************************/

	public void inicializarCromosoma() {
		// Solo tendremos un gen que tendra 26 alelos tipo char
		this.genes.get(0).inicializaGen();
		
		this.funcionFitness();
	}
	

	private void funcionFitness() {
		
		double mono = 0.0,
			   bi   = 0.0,
			   tri  = 0.0;
		
		String monograma = "",
				bigrama  = "",
				trigrama = "";
		
		int pos;
		
		double frecuencia;

		this.aptitud = 0.0;
		
		// MONOGRAMAS
		for (Map.Entry<String, Integer> n_grams : Texto.getMonogramasReal().entrySet()) {
			monograma = "";
			
			int valor = n_grams.getValue();
			
			// Traducimos
			for (int i = 0; i < n_grams.getKey().length(); i++) {
				pos = Texto.getABC().indexOf(n_grams.getKey().charAt(i));
				monograma += this.genes.get(0).getAlelo(pos);
			}
			
			if (Fichero.getMonogramasEsperado().containsKey(monograma)) {
				// Buscamos la traduccion en el HashMap
				frecuencia = ((double) valor) / Texto.getTotalFrecuencias(1);
			}
			else {
				frecuencia = 0.0;
			}
				
			// mono += Math.pow(frecuencia - Fichero.getMonogramasEsperado().get(monograma) , 2);				
			mono += Math.abs(frecuencia * Math.log(Fichero.getMonogramasEsperado().get(monograma) / Math.log(2)));
		}
		

		// BIGRAMAS
		for (Map.Entry<String, Integer> n_grams : Texto.getBigramasReal().entrySet()) {
			bigrama = "";
			
			int valor = n_grams.getValue();
			
			// Traducimos
			for (int i = 0; i < n_grams.getKey().length(); i++) {
				pos = Texto.getABC().indexOf(n_grams.getKey().charAt(i));
				bigrama += this.genes.get(0).getAlelo(pos);
			}
			
			if (Fichero.getBigramasEsperado().containsKey(bigrama)) {
				// Buscamos la traduccion en el HashMap
				frecuencia = (double) (valor) / Texto.getTotalFrecuencias(2);
			}
			else {
				frecuencia = 0.0;
			}
			
			// bi += Math.pow(frecuencia - Fichero.getBigramasEsperado().get(bigrama) , 2);				
			bi += Math.abs(frecuencia * Math.log(Fichero.getBigramasEsperado().get(bigrama) / Math.log(2)));
		}
		
		
		// TRIGRAMAS
		for (Map.Entry<String, Integer> n_grams : Texto.getTrigramasReal().entrySet()) {
			trigrama = "";
			
			int valor = n_grams.getValue();
			
			// Traducimos
			for (int i = 0; i < n_grams.getKey().length(); i++) {
				pos = Texto.getABC().indexOf(n_grams.getKey().charAt(i));
				trigrama += this.genes.get(0).getAlelo(pos);
			}
			
			if (Fichero.getTrigramasEsperado().containsKey(trigrama)) {
				// Buscamos la traduccion en el HashMap
				frecuencia = (double) (valor) / Texto.getTotalFrecuencias(3);
			}
			else {
				frecuencia = 0.0;
			}
		
			// tri += Math.pow(frecuencia - Fichero.getTrigramasEsperado().get(trigrama) , 2);				
			tri += Math.abs(frecuencia * Math.log(Fichero.getTrigramasEsperado().get(trigrama) / Math.log(2)));
		}
		
		this.aptitud = 0.05 * mono + 0.25 * bi + 0.7 * tri;
	}

	
	
	/**
	 * Dado el fitness total, calculado previamente, 
	 * y el valor de la puntuacion relativa de los individuos anteriores,
	 * calcula y actualiza el valor de los atributos this.punt_relativa y 
	 * this.punt_acumulada.
	 * @param total_fitness
	 * @param relativa
	 */
	public void evaluarCromosoma(double total_fitness, double relativa) {
		this.punt_relativa  = this.aptitud / total_fitness;
		this.punt_acumulada = this.punt_relativa + relativa;
	}




	public void traducir() {
		
		int pos;
		
		this.texto_plano = "";
			
		for (int j = 0; j < Texto.getTexto().length(); j++) {
			if (((int) Texto.getTexto().charAt(j)) < 97 || ((int) Texto.getTexto().charAt(j)) > 122 ) {
				this.texto_plano += Texto.getTexto().charAt(j);
			}
			else {
				pos = Texto.getABC().indexOf(Texto.getTexto().charAt(j));
				this.texto_plano += this.genes.get(0).getAlelo(pos);
			}
		}

	}
	

	public Cromosoma hacerCopia() {
		// Creamos un cromosoma
		Cromosoma crm = new Cromosoma();
		// Hacemos una copia de cada uno de los genes y los
		// establecemos en el cromosoma creado.
		crm.genes.set(0, this.genes.get(0).hacerCopia());
//		crm.setGen(this.genes.get(0).hacerCopia(), 0);
		// Calculamos su aptitud
		crm.aptitud = this.aptitud;
		crm.modificado = false;
		// Lo devolvemos
		return crm;
	}


	/**
	 * Sobreescribe el metodo toString. 
	 * Devuelve un String con la representacion del cromosoma.
	 */
	public String toString() {
		String dev = "";
		
		for(int i = 0; i < this.genes.size(); i++) {
			dev += this.genes.get(i).toString();
		} 
		
		return dev;
	}
	
	/***********************************************************************/

	
	
	/*********************** METODOS ABSTRACTOS ****************************/
	/***********************************************************************/
	/***********************************************************************/
	/***********************************************************************/	
	/***********************************************************************/
	/**************************** GET & SET ********************************/
	/***********************************************************************/
	/***********************************************************************/
	/***************************** METODOS *********************************/
	
	
	
	
}

//**
// * Una vez traducido el texto se calculan las frecuencias de sus
// * monogramas, bigramas y trigramas
// */
//public void calcularFrecuenciaTextoPlano() {
//	
//	String monograma = "",
//		   bigrama   = "",
//	       trigrama  = "";
//
//	for (int i = 0; i < this.texto_plano.length(); i++) {
//		if (((int) this.texto_plano.charAt(i)) >= 97 && ((int) this.texto_plano.charAt(i)) <= 122) {
//			
//			// MONOGRAMAS
//			monograma += this.texto_plano.charAt(i);
//			if(monograma.length() == 1) {
//				// Annadir monograma
//				if (!monogramas_real.containsKey(monograma)) {
//					monogramas_real.put(monograma, 1);
//				}else {
//					monogramas_real.put(monograma, monogramas_real.get(monograma) + 1);
//				}
//				monograma = "";
//			}
//			
//			// BIGRAMAS
//			bigrama += this.texto_plano.charAt(i);
//			if(bigrama.length() == 2) {
//				// Annadir bigrama
//				if (!bigramas_real.containsKey(bigrama)) {
//					bigramas_real.put(bigrama, 1);
//				}else {
//					bigramas_real.put(bigrama, bigramas_real.get(bigrama) + 1);
//				}
//				bigrama = bigrama.substring(1, 2);
//			}
//			
//			// TRIGRAMAS
//			trigrama += this.texto_plano.charAt(i);
//			if(trigrama.length() == 3) {
//				// Annadir trigrama
//				if (!trigramas_real.containsKey(trigrama)) {
//					trigramas_real.put(trigrama, 1);
//				}else {
//					trigramas_real.put(trigrama, trigramas_real.get(trigrama) + 1);
//				}
//				trigrama = trigrama.substring(1, 3);
//			}
//			
//		}
//		else {
//			bigrama  = "";
//			trigrama = "";
//		}
//	}
//}


//@Override
//public void funcionFitness() {
//	
//	double mono = 0.0,
//		   bi   = 0.0,
//		   tri  = 0.0;
//	
//	
//	Double frecuencia;
//	
//	// MONOGRAMAS
//	for (Map.Entry<String, Integer> n_grams : monogramas_real.entrySet()) {
//		frecuencia = ((double) n_grams.getValue()) / calcularTotalFrecuencias(1);
//			
//		if (frecuencia != null) {
//			mono += Math.pow(frecuencia - Fichero.getMonogramas().get(n_grams.getKey()) , 2);				
//			// this.aptitud += Math.abs(frecuencia - Math.log(monogramas_esperado.get(n_grams.getKey())));
//		}
//		
//	}
//	
//	// BIGRAMAS
//	for (Map.Entry<String, Integer> n_grams : bigramas_real.entrySet()) {
//		frecuencia = ((double) n_grams.getValue()) / calcularTotalFrecuencias(2);
//			
//		if (frecuencia != null) {
//			bi += Math.pow(frecuencia - Fichero.getBigramas().get(n_grams.getKey()) , 2);				
//			// this.aptitud += Math.abs(frecuencia - Math.log(bigramas_esperado.get(n_grams.getKey())));
//		}
//		
//		
//		
//	}
//	
//	// TRIGRAMAS
//	for (Map.Entry<String, Integer> n_grams : trigramas_real.entrySet()) {
//		frecuencia = ((double) n_grams.getValue()) / calcularTotalFrecuencias(3);
//			
//		if (frecuencia != null) {
//			tri += Math.pow(frecuencia - Fichero.getTrigramas().get(n_grams.getKey()) , 2);				
//			// this.aptitud += Math.abs(frecuencia - Math.log(trigramas_esperado.get(n_grams.getKey())));
//		}
//		
//	}
//	
//	
//	this.aptitud = mono + bi + tri;
//	
//}

//public int calcularTotalFrecuencias(int frec) {
//	
//	int total = 0;
//	
//	switch (frec) {
//	case 1:
//		for (Map.Entry<String, Integer> entry : monogramas_real.entrySet()) {
//			total += entry.getValue();
//		}
//		break;
//	case 2:
//		for (Map.Entry<String, Integer> entry : bigramas_real.entrySet()) {
//			total += entry.getValue();
//		}		
//		break;
//	case 3:
//		for (Map.Entry<String, Integer> entry : trigramas_real.entrySet()) {
//			total += entry.getValue();
//		}
//		break;
//	default:
//		break;
//	}
//	
//	return total;
//}