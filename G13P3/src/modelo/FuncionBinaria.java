package modelo;

import java.util.ArrayList;
import java.util.Random;


public class FuncionBinaria implements Funcion {
	
	private static final String[] operadores = { "+", "-", "*", "/" };

	private int[] datos;
	
	private String operacion;

	private Nodo izquierda,
				 derecha;

	
	@Override
	public void inicializar(int profundidad, int num_terminales) {
		
		datos = new int[2];
		datos[0] = profundidad;
		datos[1] = num_terminales;
		
		
		Random random = new Random();
		int i = random.nextInt(operadores.length);
		this.operacion = operadores[i];
		
		if (profundidad == 1) {
			this.izquierda = new Terminal();
			this.izquierda.inicializar(profundidad - 1, num_terminales);
			this.derecha   = new Terminal();
			this.derecha.inicializar(profundidad - 1, num_terminales);
			return;
		}
		
		
		i = random.nextInt(3);
		
		switch (i) {
		case 0:
			this.izquierda = new Terminal();
			this.izquierda.inicializar(profundidad - 1, num_terminales);
			break;
		case 1:
			this.izquierda = new FuncionUnaria();
			this.izquierda.inicializar(profundidad - 1, num_terminales);
			break;
		case 2:
			this.izquierda = new FuncionBinaria();
			this.izquierda.inicializar(profundidad - 1, num_terminales);
			break;
		default:
			break;
		}
		
		
		i = random.nextInt(3);
		
		switch (i) {
		case 0:
			this.derecha = new Terminal();
			this.derecha.inicializar(profundidad - 1, num_terminales);
			break;
		case 1:
			this.derecha = new FuncionUnaria();
			this.derecha.inicializar(profundidad - 1, num_terminales);
			break;
		case 2:
			this.derecha = new FuncionBinaria();
			this.derecha.inicializar(profundidad - 1, num_terminales);
			break;
		default:
			break;
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

	
	
//	@Override
//	public Nodo encuentraNodo(int aleatorio) {
//		
//		if (aleatorio == 0) {
//			return this;
//		}
//		else {
//			Nodo izq = this.izquierda.encuentraNodo(aleatorio - 1);
//			if (izq != null) {
//				return izq;
//			}
//
//			Nodo der = this.derecha.encuentraNodo(aleatorio - this.izquierda.numNodos() - 1);
//			if (der != null) {
//				return der;
//			}
//		}
//		
//		return null;
//	}

	
	@Override
	public int numNodos() {
		return 1 + this.izquierda.numNodos() + this.derecha.numNodos();
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
	
	public String toString() {
		return this.operacion + " (" + this.izquierda.toString() + " " + this.derecha.toString() + ")";
	}


	@Override
	public Nodo hacerCopia() {
		FuncionBinaria nodo = new FuncionBinaria();
		nodo.izquierda = this.izquierda.hacerCopia();
		nodo.derecha   = this.derecha.hacerCopia();
		nodo.operacion = this.operacion;
		return nodo;
	}	

	
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
	public boolean esHoja() {
		return false;
	}


	@Override
	public boolean muta(int tipo_mutacion, double prob) {

		Random random = new Random(System.currentTimeMillis());
		double p = Math.random(); 
		boolean izq = false;
		
		switch (tipo_mutacion) {
		case 0:
			if (p < prob) {
				ArrayList<String> aleatorios = new ArrayList<String>();
				
				for (int j = 0; j < operadores.length; j++) {
					aleatorios.add(operadores[j]);
				}
				
				aleatorios.remove(this.operacion);
				this.operacion = aleatorios.get(random.nextInt(aleatorios.size()));
				
				return true;
			}
			else {
				if (!this.izquierda.muta(tipo_mutacion, prob)) {
					this.derecha.muta(tipo_mutacion, prob);
				}
			}
			break;
		case 1:
			
			izq = this.izquierda.muta(tipo_mutacion, prob);
			
			if (!izq) {
				 return this.derecha.muta(tipo_mutacion, prob);
			}
			break;
		default:
			break;
		}
		
		return izq;
		
	}


	@Override
	public void mutacionArbol(int num_nodo) {
		
		if (num_nodo == -1) {
			return;
		}

		if (num_nodo == 1) {
			this.izquierda.inicializar(datos[0], datos[1]);
			return;
		}
		
		if ((num_nodo - this.izquierda.numNodos() - 1) > 0) {
			this.derecha.muta(num_nodo - this.izquierda.numNodos() - 1);
		}
		else {
			this.izquierda.muta(num_nodo - 1);
		}
		
	}



}
