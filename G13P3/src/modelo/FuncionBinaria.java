package modelo;

import java.util.ArrayList;
import java.util.Random;


public class FuncionBinaria implements Nodo {
	
	private static final String[] operadores = { "+", "-", "*", "/" };

	private int[] datos;
	
	private String operacion;

	private Nodo izquierda,
				 derecha;

	@Override
	public Nodo getNodo(int num_nodo) {
	
		if (num_nodo == 0){
			return this;
		}
		
		Nodo izq = this.izquierda.getNodo(num_nodo - 1);
		if (izq != null){
			return izq;
		}
		
		return this.derecha.getNodo(num_nodo - this.izquierda.numNodos() - 1);
	
	}
	
	
	@Override
	public void setNodo(int num_nodo, Nodo nodo) {
		
		if (num_nodo == 1) {
			this.izquierda = nodo;
			return;
		}
		
		if ((num_nodo - this.izquierda.numNodos() - 1) > 0){
			this.derecha.setNodo(num_nodo - this.izquierda.numNodos() - 1, nodo);
		}
		else {
			this.izquierda.setNodo(num_nodo - 1, nodo);
		}
		
	}

	
	@Override
	public double getValor(double valor) {
		return getResultado(valor);
	}

	
	private double getResultado(double valor) {
		
		double resultado = 0.0;
		
		switch (operacion) {
		case "+":
			resultado = (this.izquierda.getValor(valor) + this.derecha.getValor(valor));
			break;
		case "-":
			resultado = (this.izquierda.getValor(valor) - this.derecha.getValor(valor));
			break;
		case "*":
			resultado = (this.izquierda.getValor(valor) * this.derecha.getValor(valor));
			break;
		case "/":
			resultado = (this.izquierda.getValor(valor) / this.derecha.getValor(valor));
			break;
		default:
			break;
		}
		
		return resultado;
	
	}
	
	
	@Override
	public void inicializar(int profundidad, int num_terminales, int tipo) {
		
		datos = new int[3];
		datos[0] = profundidad;
		datos[1] = num_terminales;
		datos[2] = tipo;
		
		Random random = new Random();
		int i = random.nextInt(operadores.length);
		this.operacion = operadores[i];
		
		if (profundidad == 1) {
			this.izquierda = new Terminal();
			this.izquierda.inicializar(profundidad - 1, num_terminales, tipo);
			this.derecha   = new Terminal();
			this.derecha.inicializar(profundidad - 1, num_terminales, tipo);
			return;
		}
		
		
		if (tipo == 0) { // Si la inicializacion es completa
			i = random.nextInt(2);
		}
		else { // Si la inicializacion es creciente
			i = random.nextInt(3);
		}
		
		
		switch (i) {
		case 0:
			this.izquierda = new FuncionBinaria();
			this.izquierda.inicializar(profundidad - 1, num_terminales, tipo);
			break;
		case 1:
			this.izquierda = new FuncionUnaria();
			this.izquierda.inicializar(profundidad - 1, num_terminales, tipo);
			break;
		case 2:
			this.izquierda = new Terminal();
			this.izquierda.inicializar(profundidad - 1, num_terminales, tipo);
			break;
		default:
			break;
		}
		
		
		if (tipo == 0) { // Si la inicializacion es creciente
			i = random.nextInt(3);
		}
		else { // Si la inicializacion es completa
			i = random.nextInt(2);
		}
		
		
		switch (i) {
		case 0:
			this.derecha = new FuncionBinaria();
			this.derecha.inicializar(profundidad - 1, num_terminales, tipo);
			break;
		case 1:
			this.derecha = new FuncionUnaria();
			this.derecha.inicializar(profundidad - 1, num_terminales, tipo);
			break;
		case 2:
			this.derecha = new Terminal();
			this.derecha.inicializar(profundidad - 1, num_terminales, tipo);
			break;
		default:
			break;
		}
	
	}

	
	@Override
	public int numNodos() {
		return 1 + this.izquierda.numNodos() + this.derecha.numNodos();
	}

	
	@Override
	public int numNodosBinarios() {
		return 1 + this.izquierda.numNodosBinarios() + this.derecha.numNodosBinarios();
	}

	
	@Override
	public int numNodosUnarios() {
		return this.izquierda.numNodosUnarios() + this.derecha.numNodosUnarios();
	}

	
	@Override
	public int numNodosTerminales() {
		return this.izquierda.numNodosTerminales() + this.derecha.numNodosTerminales();
	}

	
	@Override
	public void muta(int num_nodo) {
		
		Random random = new Random();		
		
		if (num_nodo == -1) {
			return;
		}

		if (num_nodo == 0) {
			
			ArrayList<String> aleatorios = new ArrayList<String>();
			
			for (int j = 0; j < operadores.length; j++) {
				aleatorios.add(operadores[j]);
			}
			
			aleatorios.remove(this.operacion);
			this.operacion = aleatorios.get(random.nextInt(aleatorios.size()));
			
			return;
		}
		
		this.izquierda.muta(num_nodo - 1);
		this.derecha.muta(num_nodo - this.izquierda.numNodos() - 1);
		
	}

	
	@Override
	public boolean muta(int num_nodo, int tipo_mutacion) {
		
		Random random = new Random();
		boolean izq = false;
		
		switch (tipo_mutacion) {
		case 0: // FUNCIONAL
			if (num_nodo == 0) {
				
				ArrayList<String> aleatorios = new ArrayList<String>();
				
				for (int j = 0; j < operadores.length; j++) {
					aleatorios.add(operadores[j]);
				}
				
				aleatorios.remove(this.operacion);
				this.operacion = aleatorios.get(random.nextInt(aleatorios.size()));
				
				return true;
			
			}
			else {
				if (!this.izquierda.muta(num_nodo - 1, tipo_mutacion)) {
					return this.derecha.muta(num_nodo - (this.izquierda.numNodosBinarios() + this.izquierda.numNodosUnarios()) - 1, tipo_mutacion);
				}
			}
			break;
		case 1: // TERMINAL
			izq = this.izquierda.muta(num_nodo, tipo_mutacion);
			
			if (!izq) {
				return this.derecha.muta(num_nodo - this.izquierda.numNodosTerminales(), tipo_mutacion);
			}
			break;
		case 2: // ARBOL
			if (num_nodo == 0) {
				return true;
			}
			
			if (num_nodo == 1) {
				this.izquierda.inicializar(datos[0], datos[1], datos[2]);
				return true;
			}
			
			if (!this.izquierda.muta(num_nodo - 1, tipo_mutacion)) {
				return this.derecha.muta(num_nodo - (this.izquierda.numNodosBinarios() + this.izquierda.numNodosUnarios()) - 1, tipo_mutacion);
			}
			break;
		case 3:
			if (num_nodo == 0) {
				Nodo aux = this.izquierda.hacerCopia();
				this.izquierda = this.derecha.hacerCopia();
				this.derecha   = aux.hacerCopia();
				return true;
			}
			
			if (!this.izquierda.muta(num_nodo - 1, tipo_mutacion)) {
				return this.derecha.muta(num_nodo - this.izquierda.numNodosBinarios() - 1, tipo_mutacion);
			}
			break;
		default:
			break;
		}
		
		return izq;

	}

	
	@Override
	public Nodo hacerCopia() {
		FuncionBinaria nodo = new FuncionBinaria();
		nodo.izquierda = this.izquierda.hacerCopia();
		nodo.derecha   = this.derecha.hacerCopia();
		nodo.operacion = this.operacion;
		nodo.datos = this.datos;
		return nodo;
	}
	
	
	public String toString() {
		return this.operacion + " (" + this.izquierda.toString() + " " + this.derecha.toString() + ")";
	}

}
