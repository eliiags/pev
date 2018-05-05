package modelo.algoritmo;

import java.util.ArrayList;

import modelo.Cromosoma;

public class AlgoritmoEvolutivo {

	private static final ArrayList<Double> entrada = new ArrayList<Double>(); 
	private static final ArrayList<Double> salida  = new ArrayList<Double>();
	
	private ArrayList<Cromosoma> poblacion;
	
	
	public AlgoritmoEvolutivo() {
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
		
		this.poblacion = new ArrayList<Cromosoma>();
		
	}	
	
	public void crearCromosoma() {

		for (int i = 0; i < 10; i++) {
			poblacion.add(new Cromosoma(5));
			poblacion.get(i).inicializarCromosoma();
			poblacion.get(i).calcularFitness(entrada, salida);
			System.out.println(poblacion.get(i).toString());
		}
		
		
		
		
		
	}
	
	
}
