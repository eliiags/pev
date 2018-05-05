package modelo;

import java.util.ArrayList;
import java.util.Random;

public class Cromosoma {
	
	private Nodo raiz;
	
	private int profundidad;
	
	private double aptitud;
	
	
	public Cromosoma(int profundidad) {
		this.profundidad = profundidad;
		this.aptitud 	 = 0.0;
	}
	

	
	
	/************************** GET & SET ***********************/
	
	public double getAptitud() {
		return this.aptitud;
	}
	
	/************************************************************/
	
	
	
	public void inicializarCromosoma() {
		
//		int i = (int) (Math.random() * 3);
		Random random = new Random();
		int i = random.nextInt(2);
		
		switch (i) {
		case 0:
			this.raiz = new FuncionUnaria();
			raiz.inicializar(profundidad - 1);
			break;
		case 1:
			this.raiz = new FuncionBinaria();
			raiz.inicializar(profundidad - 1);
			break;
		default:
			break;
		}

	}
	
	
	public void calcularFitness(ArrayList<Double> entrada, ArrayList<Double> salida) {
		
		this.aptitud = 0.0;
		double valor;
		
		for (int i = 0; i < entrada.size(); i++) {
			valor = this.raiz.getValor(entrada.get(i));
			this.aptitud += Math.abs(valor - salida.get(i));
		}
		
	}
	
	
	public String toString() {
		return raiz.toString();
	}
	
}
