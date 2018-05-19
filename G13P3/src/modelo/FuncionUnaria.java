package modelo;

import java.util.Random;

public class FuncionUnaria implements Funcion {

	private static final String[] operadores = { "sqrt", "log" };

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
		
		if (num_nodo == 1){
			this.hijo = nodo;
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
	public void inicializar(int profundidad, int num_terminales) {
	
		datos = new int[2];
		datos[0] = profundidad;
		datos[1] = num_terminales;
		
		Random random = new Random();
		int i = random.nextInt(operadores.length);
		this.operacion = operadores[i];
		
		if (profundidad == 1) {
			this.hijo = new Terminal();
			this.hijo.inicializar(profundidad - 1, num_terminales);
			return;
		}
		
		
		i = random.nextInt(3);
		
		switch (i) {
		case 0:
			this.hijo = new Terminal();
			this.hijo.inicializar(profundidad - 1, num_terminales);
			break;
		case 1:
			this.hijo = new FuncionUnaria();
			this.hijo.inicializar(profundidad - 1, num_terminales);
			break;
		case 2:
			this.hijo = new FuncionBinaria();
			this.hijo.inicializar(profundidad - 1, num_terminales);
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
	public void muta(int num_nodo) {
		
		if (num_nodo == -1) {
			return;
		}
		
		if (num_nodo == 0) {
			if (this.operacion == operadores[0]) {
				this.operacion = operadores[1];
			}
			else {
				this.operacion = operadores[0];
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
				if (this.operacion == operadores[0]) {
					this.operacion = operadores[1];
				}
				else {
					this.operacion = operadores[0];
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
			if (num_nodo == 1) {
				this.hijo.inicializar(datos[0], datos[1]);
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
	public Nodo hacerCopia() {
		FuncionUnaria nodo = new FuncionUnaria();
		nodo.hijo = this.hijo.hacerCopia();
		nodo.operacion = this.operacion;
		return nodo;
	}
	
	
	public String toString() {
		return this.operacion + " (" + this.hijo.toString() + ")";
	}
	
	
}
