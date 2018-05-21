package modelo.fich_texto;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Texto {
	
	private static String texto;

	private final static ArrayList<Character> ABC = new ArrayList<Character>();

	private static HashMap<String, Integer> monogramas_real = new HashMap<String, Integer>();
	private static HashMap<String, Integer> bigramas_real   = new HashMap<String, Integer>();
	private static HashMap<String, Integer> trigramas_real  = new HashMap<String, Integer>();
	
	private static int total_monogramas;
	private static int total_bigramas;
	private static int total_trigramas;
	
	
	public Texto(String tex) {
		texto = tex;
		
		// Char de referencia
		for (int i = 0; i < 26; i++) {
			ABC.add((char) (i + 97));
		}
		
		calcularFrecuenciaTexto();
	}
	
	
	
	public static String getTexto() {
		return texto;
	}
	
	
	public static ArrayList<Character> getABC() {
		return ABC;
	}
	
	public static HashMap<String, Integer> getMonogramasReal(){
		return monogramas_real;
	}
	
	public static HashMap<String, Integer> getBigramasReal(){
		return bigramas_real;
	}
	
	public static HashMap<String, Integer> getTrigramasReal(){
		return trigramas_real;
	}
	
	public static int getTotalFrecuencias(int frec) {
				
		if (frec == 1) {
			return total_monogramas;
		}	
		else if (frec == 2) {
			return total_bigramas;
		}
		else {
			return total_trigramas;
		}
		
	}
	
	
	
	/**
	 * Una vez traducido el texto se calculan las frecuencias de sus
	 * monogramas, bigramas y trigramas
	 */
	public void calcularFrecuenciaTexto() {
		
		String monograma = "",
			   bigrama   = "",
		       trigrama  = "";
	
		for (int i = 0; i < texto.length(); i++) {
			if (((int) texto.charAt(i)) >= 97 && ((int) texto.charAt(i)) <= 122) {
				
				// MONOGRAMAS
				monograma += texto.charAt(i);
				if(monograma.length() == 1) {
					// Annadir monograma
					if (!monogramas_real.containsKey(monograma)) {
						monogramas_real.put(monograma, 1);
					}else {
						monogramas_real.put(monograma, monogramas_real.get(monograma) + 1);
					}
					monograma = "";
				}
				
				// BIGRAMAS
				bigrama += texto.charAt(i);
				if(bigrama.length() == 2) {
					// Annadir bigrama
					if (!bigramas_real.containsKey(bigrama)) {
						bigramas_real.put(bigrama, 1);
					}else {
						bigramas_real.put(bigrama, bigramas_real.get(bigrama) + 1);
					}
					bigrama = bigrama.substring(1, 2);
				}
				
				// TRIGRAMAS
				trigrama += texto.charAt(i);
				if(trigrama.length() == 3) {
					// Annadir trigrama
					if (!trigramas_real.containsKey(trigrama)) {
						trigramas_real.put(trigrama, 1);
					}else {
						trigramas_real.put(trigrama, trigramas_real.get(trigrama) + 1);
					}
					trigrama = trigrama.substring(1, 3);
				}
				
			}
			else {
				bigrama  = "";
				trigrama = "";
			}
		}
		
		calcularTotalFrecuencias();
	}
	
	public void calcularTotalFrecuencias() {
		
		total_monogramas = 0;
		total_bigramas = 0;
		total_trigramas = 0;
		
		for (Map.Entry<String, Integer> entry : monogramas_real.entrySet()) {
			total_monogramas += entry.getValue();
		}

		for (Map.Entry<String, Integer> entry : bigramas_real.entrySet()) {
			total_bigramas += entry.getValue();
		}		

		for (Map.Entry<String, Integer> entry : trigramas_real.entrySet()) {
			total_trigramas += entry.getValue();
		}
		
	}
	
	
	
}
