package modelo.fich_texto;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;

public class Fichero {

	
	private static HashMap<String, Double> MONOGRAMAS_ESPERADO = new HashMap<String, Double>();
	private static HashMap<String, Double> BIGRAMAS_ESPERADO   = new HashMap<String, Double>();
	private static HashMap<String, Double> TRIGRAMAS_ESPERADO  = new HashMap<String, Double>();
	
	
	public Fichero() {
		String[] fich = {"monogramas.txt", "bigramas.txt", "trigramas.txt"};
		cargarFicherosFrecuencia(fich);
	}
	
	
	public static HashMap<String, Double> getMonogramasEsperado(){
		return MONOGRAMAS_ESPERADO;
	}
	
	public static HashMap<String, Double> getBigramasEsperado(){
		return BIGRAMAS_ESPERADO;
	}
	
	public static HashMap<String, Double> getTrigramasEsperado(){
		return TRIGRAMAS_ESPERADO;
	}
	
	
	
	/**
	 * Carga los ficheros de monogramas, bigramas y trigramas
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	public static void cargarFicherosFrecuencia(String[] ruta) {
		
		try {
			
			for (int i = 0; i < ruta.length; i++) {
				
				File archivo = new File ("fichero/"+ruta[i]);
				
				FileReader fr;
				
				fr = new FileReader(archivo);
				
				BufferedReader br = new BufferedReader(fr);
				
				String cadena;
				
				while((cadena = br.readLine()) != null) {
					String linea[] = cadena.split(" ");
					
					if (i == 0) {
						MONOGRAMAS_ESPERADO.put(linea[0], Double.parseDouble(linea[1]));
					}
					else if (i == 1){
						BIGRAMAS_ESPERADO.put(linea[0], Double.parseDouble(linea[1]));
					}
					else {
						TRIGRAMAS_ESPERADO.put(linea[0], Double.parseDouble(linea[1]));
					}
					
//					System.out.println(cadena);
				}
				
				br.close();
			}
		} 
		catch (FileNotFoundException e) {
			// TODO: handle exception
		}
		catch (IOException | NumberFormatException e) {
			// TODO: handle exception
		}
		
	}
	
	
	
}

//		MONOGRAMAS.put("a", 0.0510196);
//		MONOGRAMAS.put("b", 0.00957424);
//		MONOGRAMAS.put("c", 0.0188791);
//		MONOGRAMAS.put("d", 0.0230956);
//		MONOGRAMAS.put("e", 0.0721681);
//		MONOGRAMAS.put("f", 0.0130149);
//		MONOGRAMAS.put("g", 0.0124471);
//		MONOGRAMAS.put("h", 0.0295658);
//		MONOGRAMAS.put("i", 0.0437018);
//		MONOGRAMAS.put("j", 0.00131121);
//		MONOGRAMAS.put("k", 0.00482471);
//		MONOGRAMAS.put("l", 0.0250958);
//		MONOGRAMAS.put("m", 0.0150721);
//		MONOGRAMAS.put("n", 0.0427894);
//		MONOGRAMAS.put("o", 0.0445499);
//		MONOGRAMAS.put("p", 0.0123268);
//		MONOGRAMAS.put("q", 0.000620612);
//		MONOGRAMAS.put("r", 0.0377811);
//		MONOGRAMAS.put("s", 0.0401406);
//		MONOGRAMAS.put("t", 0.053325);
//		MONOGRAMAS.put("u", 0.0159984);
//		MONOGRAMAS.put("v", 0.00632008);
//		MONOGRAMAS.put("w", 0.0108901);
//		MONOGRAMAS.put("x", 0.0011416);
//		MONOGRAMAS.put("y", 0.0102697);
//		MONOGRAMAS.put("z", 0.000678672);