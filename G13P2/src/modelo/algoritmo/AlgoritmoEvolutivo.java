package modelo.algoritmo;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;

import modelo.Cromosoma;
import modelo.CromosomaDesencripta;
import modelo.seleccion.Seleccion;
import modelo.seleccion.SeleccionEstocastico;
import modelo.seleccion.SeleccionManuEli;
import modelo.seleccion.SeleccionRestos;
import modelo.seleccion.SeleccionRuleta;
import modelo.seleccion.SeleccionTorneoDeterministico;
import modelo.seleccion.SeleccionTorneoProbabilistico;
import modelo.seleccion.SeleccionTruncamiento;
import vista.Vista;
import modelo.cruce.Cruce;
import modelo.cruce.CruceCodificacionOrdinal;
import modelo.cruce.CruceManuEli;
import modelo.cruce.CrucePMX;
import modelo.fich_texto.Texto;
import modelo.mutacion.Mutacion;
import modelo.mutacion.MutacionHeuristica;
import modelo.mutacion.MutacionInsercion;
import modelo.mutacion.MutacionIntercambio;
import modelo.mutacion.MutacionInversion;
import modelo.mutacion.MutacionManuEli;



public class AlgoritmoEvolutivo implements ActionListener {

	
	private Vista vista;
	
	private ArrayList<Cromosoma> poblacion;
	
	private int tam_poblacion;
	
	private int num_generaciones;
	
	private int cont_generaciones;

	
	private Cromosoma[] elite;
	
	private double elitismo;
	
	private double total_fitness;
	
	private Cromosoma el_mejor;
	
	private Cromosoma el_mejor_absoluto;
	
	private String texto_plano;
	
	
	
	public static Texto texto;
	
	private double mejor;
	
	private double media;
	
	private double absoluto;
	
	private double generaciones;
	
	
	
	
	private double prob_seleccion;
	
	public double prob_cruce;
	
	public double prob_mutacion;
	
	private boolean cambio_mejor;
	
	
	public Seleccion seleccion;
	
	public Cruce cruce;
	
	public Mutacion mutacion;
	
	
	
	
	public AlgoritmoEvolutivo() {
	
	}
	
	
	public void conectarVista(Vista vista) {
		this.vista = vista;
	}
	
	
	public void setDatos(
			int tam_poblacion,
			int num_generaciones,
			double elitismo,
			int opcion_seleccion,
			int opcion_cruce,
			int opcion_mutacion,
			double prob_seleccion,
			double prob_cruce,
			double prob_mutacion) {

		
		this.tam_poblacion = tam_poblacion;
		
		this.num_generaciones  = num_generaciones;
		this.cont_generaciones = 0;
		
		this.elitismo = elitismo;
		
		this.prob_seleccion = prob_seleccion;
		this.prob_cruce     = prob_cruce;
		this.prob_mutacion  = prob_mutacion;
	
		
		this.poblacion = new ArrayList<Cromosoma>();
		
		this.cambio_mejor = false;
		
		this.el_mejor = null;
		this.elite = null;
		this.el_mejor_absoluto = null;
		this.total_fitness = 0.0;
		this.texto_plano = "";
		
		seleccion(opcion_seleccion);
		cruce(opcion_cruce);
		mutacion(opcion_mutacion);
		
		boolean elite = this.elitismo == 0.0 ? false : true; 
		
		this.ejecuta(elite);
	}

	
	/**************************** GET & SET ********************************/
	
	
	/***********************************************************************/

	
	
	
	
	
	
	
	
	
	
	/***************************** METODOS *********************************/
	

	public void ejecuta(boolean elite) {
    	
		vista.borrarDatos();
		
		inicializaPoblacion();
		evaluarPoblacion();
		actualizarValoresGrafica();
		this.vista.actualiza(this.generaciones, this.media, 
				 this.mejor, this.absoluto, this.texto_plano);
		

		for (int i = 0; i < this.num_generaciones; i++) {
		
			if (elite) {
				int tam_elite = calcularTamElite();
				this.elite = new Cromosoma[tam_elite];
				// Extraemos los mejores individuos de la poblacion (hacemos una copia)
				separarMejores(tam_elite);
			}
			
			
			// Aplicamos el proceso de seleccion/reproduccion/mutacion		
			// SELECCION
			this.seleccion.seleccionar(this.poblacion);
			this.poblacion = this.seleccion.getNuevaPoblacion();
			// CRUCE
			this.cruce.reproduccion(this.poblacion, this.prob_cruce);
			// MUTACION
			this.mutacion.muta(this.poblacion, this.prob_mutacion);
			
			
			if (elite) {
				//Volvemos a integrar a la elite
				incluirMejores();
			}
			
			
			evaluarPoblacion();
			actualizarValoresGrafica();
			this.vista.actualiza(this.generaciones, this.media, 
					 this.mejor, this.absoluto, this.texto_plano);
		}
		
		this.vista.borrarDatos();
		
	}
	
	
	
	public void separarMejores(int tam_elite) {
		
		// Array donde se almacenaras las aptitudes para ordenarlas posteriormente
		double[] aptitudes = new double[this.tam_poblacion];

		// Actualizamos el array de aptitudes con el fitness de cada individuo;
		for (int i = 0; i < aptitudes.length; i++) {
			aptitudes[i] = this.poblacion.get(i).getAptitud();
		}
		
		// Ordenador de mayor a menor
		Arrays.sort(aptitudes);


		for (int i = 0; i < tam_elite; i++) {
			for (int j = 0; j < this.poblacion.size(); j++) {
				if (aptitudes[i] == this.poblacion.get(j).getAptitud()) {
					this.elite[i] = this.poblacion.get(j).hacerCopia();
				}
			}
		}

	}
	
	
	public void incluirMejores() {
		
		// Buscamos los peores
		double[] aptitudes = new double[this.tam_poblacion];
		
		// Actualizamos el array de aptitudes con el fitness de cada individuo;
		for (int i = 0; i < aptitudes.length; i++) {
			aptitudes[i] = this.poblacion.get(i).getAptitud();
		}
		
		// Ordenador de mayor a menor
		Arrays.sort(aptitudes);

		
		for (int i = 0; i < this.elite.length; i++) {
			for (int j = 0; j < this.poblacion.size(); j++) {
				if (aptitudes[aptitudes.length - 1 - i] == this.poblacion.get(j).getAptitud()) {
					this.poblacion.set(j, this.elite[i]);
				}
			}
		}
		
	}

	
	public int calcularTamElite() {
		return (int) Math.ceil(this.tam_poblacion * this.elitismo / 100);
	}
	
	
	
	/**
	 * Inicializa la problacion
	 */
	public void inicializaPoblacion() {
		
		for (int i = 0; i < this.tam_poblacion; i++) {
			this.poblacion.add(new CromosomaDesencripta());
		}
		
	}
	
	
	/**
	 * Devuelve la suma total de fitness
	 * @return
	 */
	private void calcularTotalFitness() {
		
		this.total_fitness = 0.0;
		
		for (int i = 0; i < this.poblacion.size(); i++) {
			if (this.poblacion.get(i) == null)
				System.out.println("fer");
			this.total_fitness += this.poblacion.get(i).getAptitud();
		}
		
	}
	
	
	public void evaluarPoblacion() {
		
		this.cambio_mejor = false;
		
		// Primeramente calculamos el fitness total
		calcularTotalFitness();
		
		// Inicializamos los valores del primer cromosoma
		this.poblacion.get(0).evaluarCromosoma(this.total_fitness, 0.0);
		this.el_mejor  = this.poblacion.get(0);
		
		if(this.cont_generaciones == 0) {
			this.el_mejor_absoluto = this.el_mejor;
		}
		
		// Inicializamos los valores del resto de cromosomas
		for (int i = 1; i < this.poblacion.size(); i++) {
			this.poblacion.get(i).evaluarCromosoma(this.total_fitness, 
					this.poblacion.get(i - 1).getPuntuacionAcumulada());
			
			if (this.poblacion.get(i).getAptitud() < this.el_mejor.getAptitud()) {
				this.el_mejor  = this.poblacion.get(i);
			}

			if (this.el_mejor.getAptitud() < this.el_mejor_absoluto.getAptitud()) {
				this.el_mejor_absoluto = this.el_mejor;
				this.cambio_mejor = true;
			}
		}	
	}
	
	
	
	
	public void seleccion(int opcion_seleccion) {
		
		switch (opcion_seleccion) {
		case 0:
			seleccion = new SeleccionEstocastico();
			break;
		case 1:
			seleccion = new SeleccionManuEli();
			break;
		case 2:
			seleccion = new SeleccionRestos();
			break;
		case 3:
			seleccion = new SeleccionRuleta();
			break;
		case 4:
			seleccion = new SeleccionTorneoDeterministico();
			break;
		case 5:
			seleccion = new SeleccionTorneoProbabilistico();
			break;
		case 6:
			seleccion = new SeleccionTruncamiento(this.prob_seleccion);
			break;
		default:
			System.out.println("Introduzca tipo de seleccion");
			break;
		}
	}
	
	
	
	public void cruce(int opcion_cruce) {
		
		switch (opcion_cruce) {
		case 0:
			this.cruce = new CruceCodificacionOrdinal();
			break;
		case 1:
			this.cruce = new CruceManuEli();
			break;
		case 2:
			this.cruce = new CrucePMX();
			break;
		default:
			System.out.println("Introduzca tipo de cruce");
			break;
		}
		
	}
	
	
	
	public void mutacion(int opcion_mutacion) {

		switch (opcion_mutacion) {
		case 0:
			this.mutacion = new MutacionHeuristica();
			break;
		case 1:
			this.mutacion = new MutacionInsercion();
			break;
		case 2:
			this.mutacion = new MutacionIntercambio();
			break;
		case 3:
			this.mutacion = new MutacionInversion();
			break;
		case 4:
			this.mutacion = new MutacionManuEli();
			break;
		default:
			System.out.println("Introduzca tipo de mutacion");
			break;
		}
		
	}
	
	

	
	

	public void actualizarValoresGrafica() {

		//			this.media_fitness.add(this.total_fitness / this.tam_poblacion);
		//			this.mejor_fitness.add(this.el_mejor.getAptitud());
		//			this.mejor_absoluto.add(this.el_mejor_absoluto.getAptitud());
		//			

		if (this.cont_generaciones < this.num_generaciones) {
			
			this.media = this.total_fitness / this.tam_poblacion;
			this.mejor = this.el_mejor.getAptitud();
			this.absoluto = this.el_mejor_absoluto.getAptitud();
			this.generaciones = this.cont_generaciones;
			
			if (this.cambio_mejor) {
				el_mejor_absoluto.traducir();
				this.texto_plano = el_mejor_absoluto.getTextoPlano();
				System.out.println(el_mejor_absoluto.getTextoPlano());
				System.out.println("Gen: [" + this.cont_generaciones + "] Fitness: " + el_mejor_absoluto.getAptitud());
				System.out.println("Gen: [" + this.cont_generaciones + "] Media  : " + media);
				System.out.println(el_mejor_absoluto.toString());
			}

			this.cont_generaciones++;
			
		}

	}

	
	
	

	
	
	
	
	
	
	
	
	
	
	
	
	


	@Override
	public void actionPerformed(ActionEvent e) {
		
		String comando  = e.getActionCommand();

		if (comando == "INICIAR") {
			new Thread() {
				@Override
				public void run () {
					iniciar();
				}
			}.start();
		}
		
	}
	
	
	
	
	@SuppressWarnings("static-access")
	public void iniciar() {

		// Hacemos las conversiones
        int tam_poblacion;
        int num_generaciones;
        double prob_seleccion;
        double prob_cruce;
        double prob_mutacion;
        double porcentaje_elite; 
        int opcion_seleccion;
        int opcion_cruce;
        int opcion_mutacion;
		String texto;
		
		// Obtenemos los valores
        tam_poblacion = Integer.parseInt(this.vista.getTextFieldPoblacion().getText());
        num_generaciones = Integer.parseInt(this.vista.getTextFieldGeneraciones().getText()); 
        
        prob_seleccion = Double.parseDouble(this.vista.getTextFieldSeleccion().getText());
        prob_cruce = Double.parseDouble(this.vista.getTextFieldCruces().getText());
        prob_mutacion = Double.parseDouble(this.vista.getTextFieldMutacion().getText());
        porcentaje_elite = Double.parseDouble(this.vista.getTextFieldElitismo().getText());
        opcion_seleccion = this.vista.getComboBoxOpcionSeleccion().getSelectedIndex();
        opcion_cruce = this.vista.getComboBoxOpcionCruce().getSelectedIndex();
        opcion_mutacion = this.vista.getComboBoxOpcionMutacion().getSelectedIndex();
        texto = this.vista.getTextAreaDesencriptar().getText().trim().toLowerCase();

//        tam_poblacion = 100;
//        num_generaciones = 100; 
//        prob_seleccion = 0.0;
//        prob_cruce = 0.6;
//        prob_mutacion = 0.05;
//        porcentaje_elite = 0.0;
//        opcion_seleccion = 0;
//        opcion_cruce = 0;
//        opcion_mutacion = 0;
//        texto = "HN ZQCL UQWCJHQV HU OEQVI JXM NHKM NOUJMUJ HV TMPHBXMLHVI JXM JMYJ, ZQC DHWW IMJ O PQTM OU O LMDOLT JXOJ DHWW UMLKM JQ HEBLQKM ZQCL ILOTM. JLZ JQ NHVM-JCVM JXM NHJVMUU NCVPJHQV UQ JXOJ JXM OWIQLHJXE DQLFU NOUJ. IQQT WCPF.".toLowerCase().trim();

        
//        System.out.println("*********************Texto: " + texto);
		
		
		this.texto = null;
        
        this.texto = new Texto(texto);
        
        
        // Creamos el algoritmo genetico
		setDatos(
				tam_poblacion, 
        		num_generaciones, 
        		porcentaje_elite, 
        		
        		opcion_seleccion, 
        		opcion_cruce, 
        		opcion_mutacion, 
        		
        		prob_seleccion, 
        		prob_cruce, 
        		prob_mutacion);
	
			
		
//			boolean elite = porcentaje_elite == 0.0 ? false : true; 
//			this.ejecuta(elite);

	}
	
	
	
	
	
	
	
	
	/***********************************************************************/
	/***********************************************************************/
	/***********************************************************************/

	
}




//public AlgoritmoEvolutivo (
//		int tam_poblacion,
//		int num_generaciones,
//		double elitismo,
//		int opcion_seleccion,
//		int opcion_cruce,
//		int opcion_mutacion,
//		double prob_seleccion,
//		double prob_cruce,
//		double prob_mutacion) {
//
//	
//	this.tam_poblacion = tam_poblacion;
//	
//	this.num_generaciones  = num_generaciones;
//	this.cont_generaciones = 0;
//	
//	this.elitismo = elitismo;
//	
//	this.prob_seleccion = prob_seleccion;
//	this.prob_cruce     = prob_cruce;
//	this.prob_mutacion  = prob_mutacion;
//
//	
//	this.poblacion = new ArrayList<Cromosoma>();
//	
//	this.media_fitness  = new ArrayList<Double>();
//	this.mejor_absoluto = new ArrayList<Double>();
//	this.mejor_fitness  = new ArrayList<Double>();
//	
//	this.cambio_mejor = false;
//	
//	seleccion(opcion_seleccion);
//	cruce(opcion_cruce);
//	mutacion(opcion_mutacion);
//	
////	boolean elite = this.elitismo == 0.0 ? false : true; 
//	
////	this.ejecuta(elite);
//}

