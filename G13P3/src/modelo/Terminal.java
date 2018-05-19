package modelo;

import java.util.ArrayList;
import java.util.Random;

public class Terminal implements Nodo {

	private String terminal;
	
	private int num_terminales;
	
	
	@Override
	public Nodo getNodo(int num_nodo) {
		
		if (num_nodo == 0){
			return this;
		}
		
		return null;
	
	}


	@Override
	public void setNodo(int num_nodo, Nodo nodo) {
		
	}

	
	@Override
	public double getValor(double valor) {
		return valor;
	}
	
	
	@Override
	public void inicializar(int profundidad, int num_terminales, int tipo) {
		
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
	public int numNodos() {
		return 1;
	}

	
	@Override
	public int numNodosBinarios() {
		return 0;
	}

	
	@Override
	public int numNodosUnarios() {
		return 0;
	}

	
	@Override
	public int numNodosTerminales() {
		return 1;
	}

	
	@Override
	public void muta(int num_nodo) {
		
		if (num_nodo == -1) {
			return;
		}
		
		if (num_nodo == 0) {

			Random random = new Random();
			
			ArrayList<Character> terminales = new ArrayList<Character>();
			
			for (int i = 0; i < this.num_terminales; i++) {
				terminales.add((char) (i + 65));
			}
			
			terminales.remove(new Character(this.terminal.charAt(0)));
			this.terminal = terminales.get(random.nextInt(terminales.size())) + "";
			
		}
		
	}

	
	@Override
	public boolean muta(int num_nodo, int tipo_mutacion) {
		
		Random random = new Random(System.currentTimeMillis());
		
		switch (tipo_mutacion) {
		case 1: // TERMINAL
			if (num_nodo == 0) {
				ArrayList<Character> terminales = new ArrayList<Character>();
				
				for (int i = 0; i < this.num_terminales; i++) {
					terminales.add((char) (i + 65));
				}
				
				terminales.remove(new Character(this.terminal.charAt(0)));
				this.terminal = terminales.get(random.nextInt(terminales.size())) + "";
				
				return true;
			}
			break;
		default: // FUNCIONAL // ARBOL // PERMUTACION
			break;
		}
		
		return false;
		
	}

	
	@Override
	public Nodo hacerCopia() {
		Terminal nodo = new Terminal();
		nodo.terminal = this.terminal;
		nodo.num_terminales = this.num_terminales;
		return nodo; 
	}

	
	public String toString() {
		return this.terminal;
	}
}
