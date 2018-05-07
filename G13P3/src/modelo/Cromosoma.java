package modelo;

import java.util.Random;

import modelo.algoritmo.Datos;

public class Cromosoma {
	
	private Nodo raiz;
	
	private int profundidad;
	
	private Double aptitud;
	
	private double relativa;
	
	private double acumulada;
	
	private boolean modificado;
	




	public Cromosoma(int profundidad) {
		this.profundidad = profundidad;
	}
	

	
	
	/************************** GET & SET ***********************/
	
	/**
	 * Si el cromosoma se ha modificado, 
	 * vuelve a calcular el fitness y lo devuelve
	 * @return fitness del individuo
	 */
	public Double getAptitud() {
		
		if (this.modificado) {
			calcularFitness();
		}
		
		if (this.aptitud.isNaN() || this.aptitud.isInfinite()) {
			this.aptitud = 1.0E10;
		}
		
		return this.aptitud;
	}
	
	public double getRelativa() {
		return this.relativa;
	}
	
	public double getAcumulada() {
		return this.acumulada;
	}
	
	
	/**
	 * Si el individuo ha sido modificado, 
	 * se actualiza el atributo.
	 * @param modificado
	 */
	public void setModificado(boolean modificado) {
		this.modificado = modificado;
	}
	
	/************************************************************/
	
	
	
	public void inicializarCromosoma() {
		
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
		
		// Calculamos el fitness
		calcularFitness();
		this.modificado = false;
		
	}
	
	
	private void calcularFitness() {
		
		double valor;
		this.aptitud = 0.0;

		for (int i = 0; i < Datos.getEntrada().size(); i++) {
			valor = this.raiz.getValor(Datos.getEntrada().get(i));
			this.aptitud += Math.abs(valor - Datos.getSalida().get(i));
		}

		this.modificado = false;
			
	}
	

	public void evaluarCromosoma(double total_fitness, double relativa) {
		this.relativa  = this.aptitud / total_fitness;
		this.acumulada = this.relativa + relativa;
	}

	

	
	
	public String toString() {
		return raiz.toString();
	}
	
}
