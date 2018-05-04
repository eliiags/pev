package modelo;

public class FuncionUnaria implements Funcion {

	private Nodo hijo;
	
	private String operacion;
	
	private static final String[] operadores = { "sqrt", "log" };
	
	
	
	@Override
	public double getValor(double valor) {
		return getResultado(valor);
	}
	
	private double getResultado(double valor) {
		
		double resultado = 0.0;
		
		switch (operacion) {
		case "sqrt":
			resultado = Math.sqrt(hijo.getValor(valor));
			break;
		case "log":
			resultado = Math.log(hijo.getValor(valor));
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
			this.hijo = new Terminal();
			return;
		}
		
		
		i = (int) (Math.random() * 3);
		
		switch (i) {
		case 0:
			this.hijo = new Terminal();
			break;
		case 1:
			this.hijo = new FuncionUnaria();
			hijo.inicializar(profundidad - 1);
			break;
		case 2:
			this.hijo = new FuncionBinaria();
			hijo.inicializar(profundidad - 1);
			break;
		default:
			break;
		}
		
	}
	
	
	
}
