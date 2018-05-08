package modelo;

import java.util.ArrayList;
import java.util.Random;

public class FuncionBinaria implements Funcion {
	
	private static final String[] operadores = { "+", "-", "*", "/" };

	private String operacion;

	private Nodo izquierda,
				 derecha;

	

	
	@Override
	public void inicializar(int profundidad) {
		
		Random random = new Random();
		int i = random.nextInt(operadores.length);
		this.operacion = operadores[i];
		
		if (profundidad == 1) {
			this.izquierda = new Terminal();
			this.derecha   = new Terminal();
			return;
		}
		
		
		i = random.nextInt(3);
		
		switch (i) {
		case 0:
			this.izquierda = new Terminal();
			break;
		case 1:
			this.izquierda = new FuncionUnaria();
			this.izquierda.inicializar(profundidad - 1);
			break;
		case 2:
			this.izquierda = new FuncionBinaria();
			this.izquierda.inicializar(profundidad - 1);
			break;
		default:
			break;
		}
		
		
		i = random.nextInt(3);
		
		switch (i) {
		case 0:
			this.derecha = new Terminal();
			break;
		case 1:
			this.derecha = new FuncionUnaria();
			this.derecha.inicializar(profundidad - 1);
			break;
		case 2:
			this.derecha = new FuncionBinaria();
			this.derecha.inicializar(profundidad - 1);
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

	
	
	@Override
	public Nodo encuentraNodo(int aleatorio) {
		
		if (aleatorio == 0) {
			return this;
		}
		else {
			Nodo izq = this.izquierda.encuentraNodo(aleatorio - 1);
			if (izq != null) {
				return izq;
			}

			Nodo der = this.derecha.encuentraNodo(aleatorio - this.izquierda.numNodos() - 1);
			if (der != null) {
				return der;
			}
		}
		
		return null;
	}

	
	@Override
	public int numNodos() {
		return 1 + this.izquierda.numNodos() + this.derecha.numNodos();
	}
	
	
	
	
	@Override
	public void muta(int numNodo) {
		
		Random random = new Random();
		
		if (numNodo == -1) {
			return;
		}

		if (numNodo == 0) {
			
			ArrayList<String> aleatorios = new ArrayList<String>();
			
			for (int j = 0; j < operadores.length; j++) {
				aleatorios.add(operadores[j]);
			}
			
			aleatorios.remove(this.operacion);
			this.operacion = aleatorios.get(random.nextInt(aleatorios.size()));
			
			return;
		}
		
		this.izquierda.muta(numNodo - 1);
		this.derecha.muta(numNodo - this.izquierda.numNodos() - 1);
		
	}
	
	public String toString() {
		return this.operacion + " (" + this.izquierda.toString() + " " + this.derecha.toString() + ")";
	}

	
}
