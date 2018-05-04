package modelo;

public class FuncionBinaria implements Funcion {
	
	private Nodo izquierda,
				 derecha;

	private String operacion;
	
	private static final String[] operadores = { "+", "-", "*", "/" };
	
	@Override
	public double getValor(double valor) {
		return getResultado(valor);
	}
	
	private double getResultado(double valor) {
		
		double resultado = 0.0;
		
		switch (operacion) {
		case "+":
			resultado = izquierda.getValor(valor) + derecha.getValor(valor);
			break;
		case "-":
			resultado = izquierda.getValor(valor) - derecha.getValor(valor);
			break;
		case "*":
			resultado = izquierda.getValor(valor) * derecha.getValor(valor);
			break;
		case "/":
			resultado = izquierda.getValor(valor) / derecha.getValor(valor);
			break;
		default:
			break;
		}
		
		return resultado;
	}

	
	@Override
	public void inicializar(int profundidad) {
		
		int i = (int) (Math.random() * operadores.length);
		this.operacion = operadores[i];
		
		if (profundidad == 1) {
			this.izquierda = new Terminal();
			this.derecha   = new Terminal();
			return;
		}
		
		
		i = (int) (Math.random() * 3);
		
		switch (i) {
		case 0:
			this.izquierda = new Terminal();
			break;
		case 1:
			this.izquierda = new FuncionUnaria();
			izquierda.inicializar(profundidad - 1);
			break;
		case 2:
			this.izquierda = new FuncionBinaria();
			izquierda.inicializar(profundidad - 1);
			break;
		default:
			break;
		}
		
		
		i = (int) (Math.random() * 3);
		
		switch (i) {
		case 0:
			this.derecha = new Terminal();
			break;
		case 1:
			this.derecha = new FuncionUnaria();
			derecha.inicializar(profundidad - 1);
			break;
		case 2:
			this.derecha = new FuncionBinaria();
			derecha.inicializar(profundidad - 1);
			break;
		default:
			break;
		}
		
		
	}
	
	
}
