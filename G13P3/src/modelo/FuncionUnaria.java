package modelo;

import java.util.ArrayList;

//import java.util.Random;

import modelo.algoritmo.Datos;

public class FuncionUnaria implements Nodo {

//	private static final String[] operadores = { "sqrt", "log" };
	private ArrayList<ArrayList<String>> operadores = new ArrayList<>();

	private int[] datos;
	
	private Nodo hijo;
	
	private String operacion;


	@Override
	public Nodo getNodo(int num_nodo) {
		
		if (num_nodo == 0) {
			return this;
		}

		return this.hijo.getNodo(num_nodo - 1);
	
	}


	@Override
	public void setNodo(int num_nodo, Nodo nodo) {
		
		if (num_nodo == 0)
			return;
		
		if (num_nodo == 1){
			this.hijo = nodo.hacerCopia();
			return;
		}
		
		this.hijo.setNodo(num_nodo - 1, nodo);
	
	}
    
	
	@Override
	public double getValor(double valor) {
		return getResultado(valor);
	}
	
	
	private double getResultado(double valor) {
		
		Double resultado = 0.0;
		
		switch (operacion) {
		case "sqrt":
			resultado = Math.sqrt(this.hijo.getValor(valor));
			break;
		case "log":
			resultado = Math.log(this.hijo.getValor(valor));
			break;
		default:
			break;
		}
		
		return resultado;
	
	}

	
	@Override
	public void inicializar(int profundidad, int num_terminales, int tipo, ArrayList<ArrayList<String>> operadores) {
	
		datos = new int[3];
		datos[0] = profundidad;
		datos[1] = num_terminales;
		datos[2] = tipo;
		
//		Random random = new Random();
//		int i = random.nextInt(operadores.length);
//		int i = Datos.nextInt(operadores.length);
//		this.operacion = operadores[i];
		this.operadores.addAll(operadores); 
		int i = Datos.nextInt(this.operadores.get(1).size());
		this.operacion = operadores.get(1).get(i);
		
		if (profundidad == 1) {
			this.hijo = new Terminal();
			this.hijo.inicializar(profundidad - 1, num_terminales, tipo, operadores);
			return;
		}
		
		
		if (tipo == 0) { // Si la inicializacion es completa
//			i = random.nextInt(2);
			i = Datos.nextInt(2);
		}
		else { // Si la inicializacion es creciente
//			i = random.nextInt(3);
			i = Datos.nextInt(3);
		}
		
		switch (i) {
		case 0:
			this.hijo = new FuncionBinaria();
			this.hijo.inicializar(profundidad - 1, num_terminales, tipo, operadores);
			break;
		case 1:
			this.hijo = new FuncionUnaria();
			this.hijo.inicializar(profundidad - 1, num_terminales, tipo, operadores);
			break;
		case 2:
			this.hijo = new Terminal();
			this.hijo.inicializar(profundidad - 1, num_terminales, tipo, operadores);
			break;
		default:
			break;
		}
	
	}

	
	@Override
	public int numNodos() {
		return 1 + this.hijo.numNodos();
	}

	
	@Override
	public int numNodosBinarios() {
		return this.hijo.numNodosBinarios();
	}

	
	@Override
	public int numNodosUnarios() {
		return 1 + this.hijo.numNodosUnarios();
	}

	
	@Override
	public int numNodosTerminales() {
		return this.hijo.numNodosTerminales();
	}

	
	@Override
	public boolean esHoja() {
		return false;
	}
	
	
	@Override
	public void muta(int num_nodo) {
		
		if (num_nodo == -1) {
			return;
		}
		
		if (num_nodo == 0) {
//			if (this.operacion == operadores[0]) {
//				this.operacion = operadores[1];
//			}
//			else {
//				this.operacion = operadores[0];
//			}
			if (this.operadores.get(1).size() > 0) {
				if (this.operacion == operadores.get(1).get(0)) {
					this.operacion = operadores.get(1).get(1);
				}
				else {
					this.operacion = operadores.get(1).get(0);
				}
			}
			return;
		}

		this.hijo.muta(num_nodo - 1);
		
	}

	
	@Override
	public boolean muta(int num_nodo, int tipo_mutacion) {
		
		switch (tipo_mutacion) {
		case 0: // FUNCIONAL
			if (num_nodo == 0) {
//				if (this.operacion == operadores[0]) {
//					this.operacion = operadores[1];
//				}
//				else {
//					this.operacion = operadores[0];
//				}
				if (this.operadores.get(1).size() > 0) {
					if (this.operacion == operadores.get(1).get(0)) {
						this.operacion = operadores.get(1).get(1);
					}
					else {
						this.operacion = operadores.get(1).get(0);
					}
				}
				return true;
			}
			else {
				this.hijo.muta(num_nodo - 1, tipo_mutacion);
			}
			break;
		case 1: // TERMINAL
			return this.hijo.muta(num_nodo, tipo_mutacion);
		case 2: // ARBOL
			if (num_nodo == 0) {
				return true;
			}
			
			if (num_nodo == 1) {
				this.hijo.inicializar(Datos.getProfundidad(), datos[1], datos[2], this.operadores);
				return true;
			}
			
			this.hijo.muta(num_nodo - 1, tipo_mutacion);
			break;
		case 3: // PERMUTACION
			return this.hijo.muta(num_nodo, tipo_mutacion);
		default:
			break;
		}
		
		return false;
	}

	
	@Override
	public void hacerPoda(int profundidad) {
		
		if (profundidad == 1 && !this.hijo.esHoja()) {
			this.hijo = new Terminal();
			this.hijo.inicializar(profundidad, datos[1], datos[2], this.operadores);
			return;
		}
		
		this.hijo.hacerPoda(profundidad - 1);
		
	}
	
	
	@Override
	public Nodo hacerCopia() {
		FuncionUnaria nodo = new FuncionUnaria();
		nodo.hijo = this.hijo.hacerCopia();
		nodo.operacion = this.operacion;
		nodo.datos = this.datos;
		nodo.operadores = this.operadores;
		return nodo;
	}
	
	
	public String toString() {
		return this.operacion + " (" + this.hijo.toString() + ")";
	}
	
	
}
