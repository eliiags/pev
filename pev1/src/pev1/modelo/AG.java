package pev1.modelo;

public class AG {
	
		private Individuo[] ind; // Poblacion.
		private int tam_pob; // tamaño poblacion
		private int num_max_gen; // numero maximo de generaciones
		private Individuo el_mejor; // mejor individuo
		private int pos_mejor; // posicion del individuo
		private double prob_cruce; // probabilidad de cruce
		private double prob_mut; // probabilidad de mutacion
		private double tolerancia; // tolerancia de la representacion
		
		public AG() {
			
		}
		
}
