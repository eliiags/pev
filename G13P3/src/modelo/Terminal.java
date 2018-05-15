package modelo;

import java.util.ArrayList;
import java.util.Random;

public class Terminal implements Nodo {

	private String terminal;
	
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
			
		this.terminal = terminales.get(i) + "";
		
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
			
			terminales.remove(new Character(this.terminal.charAt(0)));
			this.terminal = terminales.get(random.nextInt(terminales.size())) + "";
			
		}
		
	}

	
	public String toString() {
		return this.terminal;
	}


	@Override
	public Nodo hacerCopia() {
		Terminal nodo = new Terminal();
		nodo.terminal = this.terminal;
		nodo.num_terminales = this.num_terminales;
		return nodo; 
	}

	
}
