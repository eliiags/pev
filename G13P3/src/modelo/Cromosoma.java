package modelo;

import java.util.ArrayList;
import java.util.Iterator;

public class Cromosoma {
	
	private Nodo raiz;
	
	private int profundidad;
	
	private double aptitud;
	
	
	public Cromosoma(int profundidad) {
		this.profundidad = profundidad; 
	}
	
	
	
	public void inicializarCromosoma() {
		
		int i = (int) (Math.random() * 3);
		
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
	
}
