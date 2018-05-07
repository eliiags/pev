package modelo;

import java.util.Random;

public class FuncionUnaria implements Funcion {

	private static final String[] operadores = { "sqrt", "log" };

	private Nodo hijo;
	
	private String operacion;

	
	

	@Override
	public void inicializar(int profundidad) {
		
		Random random = new Random();
		int i = random.nextInt(operadores.length);
		this.operacion = operadores[i];
		
		if (profundidad == 1) {
			this.hijo = new Terminal();
			return;
		}
		
		
		i = random.nextInt(3);
		
		switch (i) {
		case 0:
			this.hijo = new Terminal();
			break;
		case 1:
			this.hijo = new FuncionUnaria();
			this.hijo.inicializar(profundidad - 1);
			break;
		case 2:
			this.hijo = new FuncionBinaria();
			this.hijo.inicializar(profundidad - 1);
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
	
		
	public String toString() {
		return this.operacion + " (" + this.hijo.toString() + ")";
	}





	
}
