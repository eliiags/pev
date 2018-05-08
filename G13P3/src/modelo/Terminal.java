package modelo;

public class Terminal implements Nodo {

	
	@Override
	public void inicializar(int profundidad) {
		
	}
	
	
	@Override
	public double getValor(double valor) {
		return valor;
	}

	
	@Override
	public Nodo encuentraNodo(int aleatorio) {
		
		if (aleatorio == 0) {
			return this;
		}
		
		return null;
	}

	
	@Override
	public int numNodos() {
		return 1;
	}

	
	@Override
	public void muta(int numNodo) {

		if (numNodo == -1) {
			return;
		}
		
	}

	
	public String toString() {
		return "A";
	}




	
	
	
}
