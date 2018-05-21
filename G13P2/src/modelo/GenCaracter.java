package modelo;

import java.util.ArrayList;

public class GenCaracter extends Gen{

	// Longitud del gen
	private final static int LONGITUD = 26;

	// Array list de alelos
	private ArrayList<Character> alelos;
	
	
	public GenCaracter() {
		super(LONGITUD);
		this.alelos = new ArrayList<Character>();
		inicializaGen();
	}


	
	@Override
	public void inicializaGen() {
		
		ArrayList<Character> abc = new ArrayList<Character>();
		int rand = 0;
		
		for (int i = 0; i < this.longitud_gen; i++) {
			abc.add((char) (i + 97));
		}
		
		for (int i = 0; i < LONGITUD; i++) {
			rand = ((int) (Math.random() * (abc.size())));
			
			this.alelos.add(abc.get(rand));
			
			abc.remove(rand);
		}
		
	}


	
	@Override
	public Gen hacerCopia() {
		
		GenCaracter gen = new GenCaracter();

		for (int i = 0; i < this.longitud_gen; i++) {
			gen.setAlelo(this.alelos.get(i), i);
		}
		
		return gen;
	}

	
	
	
	@Override
	public String toString() {
		String dev = "";
		
		for (int i = 0; i < this.alelos.size(); i++) {
			dev += this.alelos.get(i) + " "; 
		} 
		
		return dev;
	}

	

	@Override
	public Object getAlelo(int pos) {
		return this.alelos.get(pos);
	}
	
	
	
	@Override
	public void setAlelo(Object alelo, int pos) {
		this.alelos.set(pos, (char) alelo);
	}



	@Override
	public ArrayList<Character> getAlelos() {
		return this.alelos;
	}



	
}
