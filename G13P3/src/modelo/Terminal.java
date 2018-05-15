package modelo;

import java.util.ArrayList;
import java.util.Random;

public class Terminal implements Nodo {

	private String nodo;
	
	private int num_terminales;
	
	
	@Override
	public void inicializar(int profundidad, int num_terminales) {
		
		this.num_terminales = num_terminales;
		
		ArrayList<Character> terminales = new ArrayList<Character>();
		
		for (int i = 0; i < num_terminales; i++) {
			terminales.add((char) (i + 65));
		}
		
		int i;
		
		Random random = new Random();
		i = random.nextInt(terminales.size());
			
		this.nodo = terminales.get(i) + "";
		
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
		
		if (numNodo == 0) {

			Random random = new Random();
			
			ArrayList<Character> terminales = new ArrayList<Character>();
			
			for (int i = 0; i < this.num_terminales; i++) {
				terminales.add((char) (i + 65));
			}
			
			terminales.remove(new Character(this.nodo.charAt(0)));
			this.nodo = terminales.get(random.nextInt(terminales.size())) + "";
			
		}
		
	}

	
	public String toString() {
		return this.nodo;
	}


	@Override
	public Nodo hacerCopia() {
		Nodo nodo = new Terminal();
		nodo = this;
		return nodo; 
	}

	
}
