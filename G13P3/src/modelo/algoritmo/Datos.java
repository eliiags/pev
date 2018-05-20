package modelo.algoritmo;

import java.util.ArrayList;
import java.util.Random;

public class Datos {

	private static final ArrayList<Double> entrada = new ArrayList<Double>(); 
	private static final ArrayList<Double> salida  = new ArrayList<Double>();
	
	private static Random random = new Random(System.currentTimeMillis());
	
	private static int profundidad;
	
	public Datos() {
		entrada.add(0.72);
		entrada.add(1.00);
		entrada.add(1.52);
		entrada.add(5.20);
		entrada.add(9.53);
		entrada.add(19.1);
		
		salida.add(0.610940);
		salida.add(1.000000);
		salida.add(1.873981);
		salida.add(11.85782);
		salida.add(29.41977);
		salida.add(83.47377);
	}
	
	public static ArrayList<Double> getEntrada(){
		return entrada;
	}
	
	public static ArrayList<Double> getSalida(){
		return salida;
	}
	
	public static int nextInt(int rango) {
		return random.nextInt(rango);
	}
	
	public static double nextDouble() {
		return random.nextDouble();
	}
	
	public static void setProfundidad(int prof) {
		profundidad = prof;
	}
	
	public static int getProfundidad() {
		return profundidad;
	}
	
}
