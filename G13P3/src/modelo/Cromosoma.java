package modelo;

import java.util.Random;

import modelo.algoritmo.Datos;

public class Cromosoma {
	
	private Nodo raiz;
	
	private int profundidad;
	
	private int num_terminales;
	
	private int num_nodos;
	
	private Double aptitud;
	
	private double relativa;
	
	private double acumulada;
	
	private boolean modificado;
	




	public Cromosoma(int profundidad, int num_terminales) {
		this.profundidad    = profundidad;
		this.num_terminales = num_terminales;
	}
	

	
	
	/************************** GET & SET ***********************/
	
	/**
	 * Si el cromosoma se ha modificado, 
	 * vuelve a calcular el fitness y lo devuelve
	 * @return fitness del individuo
	 */
	public Double getAptitud() {
		
		if (this.modificado) {
			calcularNumNodos();
			calcularFitness();
			this.modificado = false;
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
	
	
	public void setNodo(int num_nodo, Nodo nodo){
		this.raiz.setNodo(num_nodo, nodo);
		this.modificado = true;
	}
	
	
	public Nodo getNodo(int num_nodo){
		return this.raiz.getNodo(num_nodo);
	}
	
	public int getNumNodos() {
		return this.num_nodos;
	}
	/************************************************************/
	
	
	
	public void inicializarCromosoma() {
		
		Random random = new Random();
		int i = random.nextInt(2);
		
		switch (i) {
		case 0:
			this.raiz = new FuncionUnaria();
			raiz.inicializar(profundidad - 1, num_terminales);
			break;
		case 1:
			this.raiz = new FuncionBinaria();
			raiz.inicializar(profundidad - 1, num_terminales);
			break;
		default:
			break;
		}
		
		// Calculamos el numero de nodos
		calcularNumNodos();
		
		// Calculamos el fitness
		calcularFitness();
	}
	
	
	private void calcularFitness() {
		
		double valor;
		this.aptitud = 0.0;

		for (int i = 0; i < Datos.getEntrada().size(); i++) {
			valor = this.raiz.getValor(Datos.getEntrada().get(i));
			this.aptitud += Math.abs(valor - Datos.getSalida().get(i));
		}

		this.aptitud += this.num_nodos * 0.00001;
	}
	

	public void evaluarCromosoma(double total_fitness, double relativa) {
		this.relativa  = this.aptitud / total_fitness;
		this.acumulada = this.relativa + relativa;
	}

//	public Nodo encuentraNodo(int aleatorio) {
//		return raiz.encuentraNodo(aleatorio);
//	}
	
	private void calcularNumNodos() {
		this.num_nodos = this.raiz.numNodos();
	}

//	public void muta(int num_nodo) {
//		this.raiz.muta(num_nodo);
//		this.modificado = true;
//	}
	
	public void muta(int tipo_mutacion, double prob) {
		this.raiz.muta(tipo_mutacion, prob);
		this.modificado = true;
	}

	public Cromosoma hacerCopia() {
		Cromosoma crm = new Cromosoma(profundidad, num_terminales);
		crm.raiz 	   = this.raiz.hacerCopia();
		crm.aptitud    = this.aptitud;
		crm.num_nodos  = this.num_nodos;
		crm.modificado = false;
		return crm;
	}
	
	
	public String toString() {
		return raiz.toString();
	}
	
	/*************************************************************************/
	
	
}