package modelo;

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

	
	
	public String toString() {
		return this.operacion + " (" + this.izquierda.toString() + " " + this.derecha.toString() + ")";
	}



	
}
