package main;


import modelo.algoritmo.AlgoritmoEvolutivo;
import modelo.algoritmo.Datos;
import vista.Vista;

public class Main {

	private static Vista vista;
	
	public static void main(String[] args) {

		new Datos();
		AlgoritmoEvolutivo AE = new AlgoritmoEvolutivo();
		
		new Thread() {
			@Override
			public void run() {
				vista = new Vista();
				vista.conectaControlador(AE);
				AE.conectarVista(vista);
			}
		}.start();
		
		
    	
	}
	

}
