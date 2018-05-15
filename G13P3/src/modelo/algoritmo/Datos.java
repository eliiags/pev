package modelo.algoritmo;

import java.util.ArrayList;

public class Datos {

	private static final ArrayList<Double> entrada = new ArrayList<Double>(); 
	private static final ArrayList<Double> salida  = new ArrayList<Double>();
	
	public Datos() {
		entrada.add(0.72);
		entrada.add(1.00);
		entrada.add(1.52);
		entrada.add(5.20);
		entrada.add(9.53);
		entrada.add(19.1);
		
		salida.add(0.610940258);
		salida.add(1.000000000);
		salida.add(1.873981857);
		salida.add(11.85782442);
		salida.add(29.41977527);
		salida.add(83.47377432);
	}
	
	public static ArrayList<Double> getEntrada(){
		return entrada;
	}
	
	public static ArrayList<Double> getSalida(){
		return salida;
	}
	
}
