package modelo.algoritmo;

import java.util.ArrayList;

public class Datos {

	private static final ArrayList<Double> entrada = new ArrayList<Double>(); 
	private static final ArrayList<Double> salida  = new ArrayList<Double>();
	
	public Datos() {
		entrada.add(0.61);
		entrada.add(1.00);
		entrada.add(1.84);
		entrada.add(11.9);
		entrada.add(29.4);
		entrada.add(83.5);
		
		salida.add(0.72);
		salida.add(1.00);
		salida.add(1.52);
		salida.add(5.20);
		salida.add(9.53);
		salida.add(19.1);
	}
	
	public static ArrayList<Double> getEntrada(){
		return entrada;
	}
	
	public static ArrayList<Double> getSalida(){
		return salida;
	}
	
}
