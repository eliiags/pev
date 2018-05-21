package modelo;

import java.util.Map;

import modelo.fich_texto.Fichero;
import modelo.fich_texto.Texto;

public class CromosomaDesencripta extends Cromosoma {

	
	public CromosomaDesencripta() {
		super(1);
		
		// Inicializamos la poblacion
		this.inicializarCromosoma();
		//traducir
//		this.traducir();
		// Calculamos la frecuencia del texto plano
//		this.calcularFrecuenciaTextoPlano();
		// Calculamos el fitness del individuo
		this.funcionFitness();
		
		
		
	}

	
	
	@Override
	public void inicializarCromosoma() {
		// Solo tendremos un gen que tendra 26 alelos tipo char
		this.genes.add(new GenCaracter());
	}

	

	@Override
	public void funcionFitness() {
		
		double mono = 0.0,
			   bi   = 0.0,
			   tri  = 0.0;
		
		String monograma = "",
				bigrama  = "",
				trigrama = "";
		
		int pos;
		
		double frecuencia;
//		System.out.println(this.toString());
		
		// MONOGRAMAS
		for (Map.Entry<String, Integer> n_grams : Texto.getMonogramasReal().entrySet()) {
			monograma = "";
			
			int valor = n_grams.getValue();
//			System.out.println("Valor: " + valor);
			// Traducimos
			for (int i = 0; i < n_grams.getKey().length(); i++) {
				pos = Texto.getABC().indexOf(n_grams.getKey().charAt(i));
				monograma += this.genes.get(0).getAlelo(pos);
//				System.out.println("mono: " + monograma);
			}
			if (Fichero.getMonogramasEsperado().containsKey(monograma)) {
				// Buscamos la traduccion en el HashMap
				frecuencia = ((double) valor) / Texto.getTotalFrecuencias(1);
//				System.out.println("FREC: "+frecuencia);
			}
			else {
				System.out.println("error mono");
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
				System.out.println("error bi");
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
				System.out.println("error tri");
				frecuencia = 0.0;
			}
		
			// tri += Math.pow(frecuencia - Fichero.getTrigramasEsperado().get(trigrama) , 2);				
			tri += Math.abs(frecuencia * Math.log(Fichero.getTrigramasEsperado().get(trigrama) / Math.log(2)));
		
			
		}
		
		this.aptitud = 0.05 * mono + 0.25 * bi + 0.7 * tri;
//		this.aptitud = 0.05 * mono + 0.75 * bi + 0.2 * tri;
//		System.out.println(this.aptitud);
	}

	
	
	@Override
	public Cromosoma hacerCopia() {
		// Creamos un cromosoma
		Cromosoma cromosoma = new CromosomaDesencripta();
		// Hacemos una copia de cada uno de los genes y los
		// establecemos en el cromosoma creado. 
		cromosoma.setGen(this.genes.get(0).hacerCopia(), 0);
		// Calculamos su aptitud
		cromosoma.funcionFitness();
		// Lo devolvemos
		return cromosoma;
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