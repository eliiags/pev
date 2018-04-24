/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pev.vista;

import java.awt.Color;

import pev.controlador.Controlador;
import pev.modelo.Cromosoma;


public class Vista extends javax.swing.JFrame {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;




	/**
     * Creates new form Vista
     */
    public Vista() {
        initComponents();
        setVisible(true);
    }

    private void initComponents() {

        PanelPrincipal = new javax.swing.JPanel();
        PanelOpciones = new javax.swing.JPanel();
        LabelFuncion = new javax.swing.JLabel();
        ComboBoxFuncion = new javax.swing.JComboBox<>();
        ComboBoxNumFun = new javax.swing.JComboBox<>();
        Separador1 = new javax.swing.JSeparator();
        LabelPoblacion = new javax.swing.JLabel();
        TextFieldPoblacion = new javax.swing.JTextField();
        LabelGeneraciones = new javax.swing.JLabel();
        TextFieldGeneraciones = new javax.swing.JTextField();
        LabelCruces = new javax.swing.JLabel();
        TextFieldCruces = new javax.swing.JTextField();
        LabelMutacion = new javax.swing.JLabel();
        TextFieldMutacion = new javax.swing.JTextField();
        LabelTolerancia = new javax.swing.JLabel();
        TextFieldTolerancia = new javax.swing.JTextField();
        LabelElitismo = new javax.swing.JLabel();
        TextFieldElitismo = new javax.swing.JTextField();
        Separador2 = new javax.swing.JSeparator();
        LabelOpcionSeleccion = new javax.swing.JLabel();
        ComboBoxOpcionSeleccion = new javax.swing.JComboBox<>();
        LabelOpcionCruce = new javax.swing.JLabel();
        ComboBoxOpcionCruce = new javax.swing.JComboBox<>();
        LabelOpcionMutacion = new javax.swing.JLabel();
        ComboBoxOpcionMutacion = new javax.swing.JComboBox<>();
        Separador3 = new javax.swing.JSeparator();
        ButtonIniciar = new javax.swing.JButton();
        PanelDiagrama = new javax.swing.JPanel();
        TabbedPaneDiagrama = new javax.swing.JTabbedPane();
        plot = new org.math.plot.Plot2DPanel();
        PanelInformacion = new javax.swing.JPanel();
        ScrollPane = new javax.swing.JScrollPane();
        TextAreaInfo = new javax.swing.JTextArea();
        jMenuBar1 = new javax.swing.JMenuBar();
        Menu = new javax.swing.JMenu();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        LabelFuncion.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        LabelFuncion.setText("Funcion");

        ComboBoxFuncion.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Funcion 1", "Funcion 2", "Funcion 3", "Funcion 4", "Funcion 5" }));

        ComboBoxNumFun.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "1", "2", "3", "4", "5", "6", "7" }));

        LabelPoblacion.setText("Poblacion");
        
        TextFieldPoblacion.setText("100");
        
        LabelGeneraciones.setText("Generaciones");

        TextFieldGeneraciones.setText("100");
        
        LabelCruces.setText("Cruces");
        
        TextFieldCruces.setText("0.6");

        LabelMutacion.setText("Mutacion");
        
        TextFieldMutacion.setText("0.05");

        LabelTolerancia.setText("Tolerancia");

        TextFieldTolerancia.setText("0.001");
        
        LabelElitismo.setText("Elitismo");
        
        TextFieldElitismo.setText("0.0");

        LabelOpcionSeleccion.setText("Seleccion");

        ComboBoxOpcionSeleccion.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Estocastico", "Ruleta", "Torneo Deterministico", "Trucamiento" }));

        LabelOpcionCruce.setText("Cruce");

        ComboBoxOpcionCruce.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "MonoPunto" }));

        LabelOpcionMutacion.setText("Mutacion");

        ComboBoxOpcionMutacion.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Basica" }));

        ButtonIniciar.setText("Iniciar");

        javax.swing.GroupLayout PanelOpcionesLayout = new javax.swing.GroupLayout(PanelOpciones);
        PanelOpciones.setLayout(PanelOpcionesLayout);
        PanelOpcionesLayout.setHorizontalGroup(
            PanelOpcionesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(Separador1)
            .addComponent(Separador2, javax.swing.GroupLayout.Alignment.TRAILING)
            .addComponent(Separador3, javax.swing.GroupLayout.Alignment.TRAILING)
            .addGroup(PanelOpcionesLayout.createSequentialGroup()
                .addGroup(PanelOpcionesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(PanelOpcionesLayout.createSequentialGroup()
                        .addGap(21, 21, 21)
                        .addGroup(PanelOpcionesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(LabelOpcionSeleccion)
                            .addComponent(LabelOpcionCruce)
                            .addComponent(LabelOpcionMutacion))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(PanelOpcionesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(ComboBoxOpcionMutacion, 0, 106, Short.MAX_VALUE)
                            .addComponent(ComboBoxOpcionCruce, 0, 106, Short.MAX_VALUE)
                            .addComponent(ComboBoxOpcionSeleccion, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(PanelOpcionesLayout.createSequentialGroup()
                        .addGap(22, 22, 22)
                        .addGroup(PanelOpcionesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(PanelOpcionesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(LabelMutacion, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 65, Short.MAX_VALUE)
                                .addComponent(LabelCruces, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(LabelElitismo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(LabelTolerancia, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addComponent(LabelGeneraciones, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(LabelPoblacion, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(PanelOpcionesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(TextFieldPoblacion, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(TextFieldGeneraciones, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(TextFieldCruces, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(TextFieldMutacion, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(TextFieldTolerancia, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(TextFieldElitismo, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(PanelOpcionesLayout.createSequentialGroup()
                        .addGap(23, 23, 23)
                        .addComponent(LabelFuncion)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 29, Short.MAX_VALUE)
                        .addComponent(ComboBoxFuncion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(ComboBoxNumFun, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
            .addGroup(PanelOpcionesLayout.createSequentialGroup()
                .addGap(85, 85, 85)
                .addComponent(ButtonIniciar)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        PanelOpcionesLayout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {TextFieldCruces, TextFieldElitismo, TextFieldGeneraciones, TextFieldMutacion, TextFieldPoblacion, TextFieldTolerancia});

        PanelOpcionesLayout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {LabelCruces, LabelElitismo, LabelGeneraciones, LabelMutacion, LabelTolerancia});

        PanelOpcionesLayout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {LabelOpcionCruce, LabelOpcionMutacion, LabelOpcionSeleccion});

        PanelOpcionesLayout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {ComboBoxOpcionCruce, ComboBoxOpcionMutacion, ComboBoxOpcionSeleccion});

        PanelOpcionesLayout.setVerticalGroup(
            PanelOpcionesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelOpcionesLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(PanelOpcionesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(LabelFuncion)
                    .addComponent(ComboBoxFuncion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ComboBoxNumFun, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(Separador1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(PanelOpcionesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(LabelPoblacion)
                    .addComponent(TextFieldPoblacion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(PanelOpcionesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(LabelGeneraciones)
                    .addComponent(TextFieldGeneraciones, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(PanelOpcionesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(LabelCruces)
                    .addComponent(TextFieldCruces, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(PanelOpcionesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(LabelMutacion)
                    .addComponent(TextFieldMutacion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(PanelOpcionesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(LabelTolerancia)
                    .addComponent(TextFieldTolerancia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(PanelOpcionesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(LabelElitismo)
                    .addComponent(TextFieldElitismo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(Separador2, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(PanelOpcionesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(LabelOpcionSeleccion)
                    .addComponent(ComboBoxOpcionSeleccion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(PanelOpcionesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(LabelOpcionCruce)
                    .addComponent(ComboBoxOpcionCruce, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(PanelOpcionesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(LabelOpcionMutacion)
                    .addComponent(ComboBoxOpcionMutacion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(Separador3, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(36, 36, 36)
                .addComponent(ButtonIniciar)
                .addContainerGap(42, Short.MAX_VALUE))
        );

        TabbedPaneDiagrama.addTab("Estudio", plot);

        javax.swing.GroupLayout PanelDiagramaLayout = new javax.swing.GroupLayout(PanelDiagrama);
        PanelDiagrama.setLayout(PanelDiagramaLayout);
        PanelDiagramaLayout.setHorizontalGroup(
            PanelDiagramaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelDiagramaLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(TabbedPaneDiagrama)
                .addContainerGap())
        );
        PanelDiagramaLayout.setVerticalGroup(
            PanelDiagramaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, PanelDiagramaLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(TabbedPaneDiagrama)
                .addContainerGap())
        );

        TextAreaInfo.setColumns(20);
        TextAreaInfo.setRows(5);
        ScrollPane.setViewportView(TextAreaInfo);

        javax.swing.GroupLayout PanelInformacionLayout = new javax.swing.GroupLayout(PanelInformacion);
        PanelInformacion.setLayout(PanelInformacionLayout);
        PanelInformacionLayout.setHorizontalGroup(
            PanelInformacionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelInformacionLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(ScrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, 557, Short.MAX_VALUE)
                .addContainerGap())
        );
        PanelInformacionLayout.setVerticalGroup(
            PanelInformacionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, PanelInformacionLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(ScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout PanelPrincipalLayout = new javax.swing.GroupLayout(PanelPrincipal);
        PanelPrincipal.setLayout(PanelPrincipalLayout);
        PanelPrincipalLayout.setHorizontalGroup(
            PanelPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelPrincipalLayout.createSequentialGroup()
                .addComponent(PanelOpciones, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(PanelPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(PanelDiagrama, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(PanelInformacion, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );
        PanelPrincipalLayout.setVerticalGroup(
            PanelPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(PanelOpciones, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(PanelPrincipalLayout.createSequentialGroup()
                .addComponent(PanelDiagrama, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(PanelInformacion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        Menu.setText("PROGRAMACION EVOLUTIVA");
        jMenuBar1.add(Menu);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(PanelPrincipal, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(PanelPrincipal, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>                        

    
    // Variables declaration - do not modify                     
    private javax.swing.JButton ButtonIniciar;
    private javax.swing.JComboBox<String> ComboBoxFuncion;
    private javax.swing.JComboBox<String> ComboBoxNumFun;
    private javax.swing.JComboBox<String> ComboBoxOpcionCruce;
    private javax.swing.JComboBox<String> ComboBoxOpcionMutacion;
    private javax.swing.JComboBox<String> ComboBoxOpcionSeleccion;
    private javax.swing.JLabel LabelCruces;
    private javax.swing.JLabel LabelElitismo;
    private javax.swing.JLabel LabelFuncion;
    private javax.swing.JLabel LabelGeneraciones;
    private javax.swing.JLabel LabelMutacion;
    private javax.swing.JLabel LabelOpcionCruce;
    private javax.swing.JLabel LabelOpcionMutacion;
    private javax.swing.JLabel LabelOpcionSeleccion;
    private javax.swing.JLabel LabelPoblacion;
    private javax.swing.JLabel LabelTolerancia;
    private javax.swing.JMenu Menu;
    private javax.swing.JPanel PanelDiagrama;
    private javax.swing.JPanel PanelInformacion;
    private javax.swing.JPanel PanelOpciones;
    private javax.swing.JPanel PanelPrincipal;
    private javax.swing.JScrollPane ScrollPane;
    private javax.swing.JSeparator Separador1;
    private javax.swing.JSeparator Separador2;
    private javax.swing.JSeparator Separador3;
    private javax.swing.JTabbedPane TabbedPaneDiagrama;
    private javax.swing.JTextArea TextAreaInfo;
    private javax.swing.JTextField TextFieldCruces;
    private javax.swing.JTextField TextFieldElitismo;
    private javax.swing.JTextField TextFieldGeneraciones;
    private javax.swing.JTextField TextFieldMutacion;
    private javax.swing.JTextField TextFieldPoblacion;
    private javax.swing.JTextField TextFieldTolerancia;
    private javax.swing.JMenuBar jMenuBar1;
    private org.math.plot.Plot2DPanel plot;
    // End of variables declaration                   

                


	public void conectaControlador(Controlador c){
		ButtonIniciar.addActionListener(c);
        ButtonIniciar.setActionCommand("INICIAR");
	}
	
	
	
	
	public void actualizarGrafica(double[] generaciones, double[] media_fitness, double[] fitness_mejor, double[] mejor_absoluto){
		
	  // define the legend position
	  plot.addLegend("SOUTH");
	
	  // add a line plot to the PlotPanel
	  plot.addLinePlot("Mejor absoluto", Color.BLUE, generaciones, mejor_absoluto);
	  plot.addLinePlot("Media fitness", Color.RED, generaciones, media_fitness);
	  plot.addLinePlot("Mejor", Color.GREEN, generaciones, fitness_mejor);
	  
	  
	}
	
	public void actualizarInformacion(Cromosoma el_mejor) {
		
		TextAreaInfo.append("  El mejor Individuo: \n");
		
		for (int i = 0; i < el_mejor.getGenes().length; i++) {
			TextAreaInfo.append("   X: " + el_mejor.getFenotipo()[i] + "\n");
		}
		
		TextAreaInfo.append("   Fitness: " + el_mejor.getAptitud() + 
				"\n" + "   Cromosoma: " + el_mejor.toString());
		
		
		
	}
	
    
    
	public void borraDatosGrafica() {
		plot.removeAllPlots();
		TextAreaInfo.setText("");
	}    
        
        
        

    public javax.swing.JComboBox<String> getComboBoxFuncion() {
		return ComboBoxFuncion;
	}

	public void setComboBoxFuncion(javax.swing.JComboBox<String> comboBoxFuncion) {
		ComboBoxFuncion = comboBoxFuncion;
	}







	public javax.swing.JComboBox<String> getComboBoxNumFun() {
		return ComboBoxNumFun;
	}

	public void setComboBoxNumFun(javax.swing.JComboBox<String> comboBoxNumFun) {
		ComboBoxNumFun = comboBoxNumFun;
	}







	public javax.swing.JTextField getTextFieldPoblacion() {
		return TextFieldPoblacion;
	}

	public void setTextFieldPoblacion(javax.swing.JTextField textFieldPoblacion) {
		TextFieldPoblacion = textFieldPoblacion;
	}







	public javax.swing.JTextField getTextFieldGeneraciones() {
		return TextFieldGeneraciones;
	}

	public void setTextFieldGeneraciones(javax.swing.JTextField textFieldGeneraciones) {
		TextFieldGeneraciones = textFieldGeneraciones;
	}







	public javax.swing.JTextField getTextFieldCruces() {
		return TextFieldCruces;
	}

	public void setTextFieldCruces(javax.swing.JTextField textFieldCruces) {
		TextFieldCruces = textFieldCruces;
	}







	public javax.swing.JTextField getTextFieldMutacion() {
		return TextFieldMutacion;
	}

	public void setTextFieldMutacion(javax.swing.JTextField textFieldMutacion) {
		TextFieldMutacion = textFieldMutacion;
	}







	public javax.swing.JTextField getTextFieldTolerancia() {
		return TextFieldTolerancia;
	}

	public void setTextFieldTolerancia(javax.swing.JTextField textFieldTolerancia) {
		TextFieldTolerancia = textFieldTolerancia;
	}







	public javax.swing.JTextField getTextFieldElitismo() {
		return TextFieldElitismo;
	}

	public void setTextFieldElitismo(javax.swing.JTextField textFieldElitismo) {
		TextFieldElitismo = textFieldElitismo;
	}







	public javax.swing.JComboBox<String> getComboBoxOpcionSeleccion() {
		return ComboBoxOpcionSeleccion;
	}

	public void setComboBoxOpcionSeleccion(javax.swing.JComboBox<String> comboBoxOpcionSeleccion) {
		ComboBoxOpcionSeleccion = comboBoxOpcionSeleccion;
	}







	public javax.swing.JComboBox<String> getComboBoxOpcionCruce() {
		return ComboBoxOpcionCruce;
	}

	public void setComboBoxOpcionCruce(javax.swing.JComboBox<String> comboBoxOpcionCruce) {
		ComboBoxOpcionCruce = comboBoxOpcionCruce;
	}







	public javax.swing.JComboBox<String> getComboBoxOpcionMutacion() {
		return ComboBoxOpcionMutacion;
	}

	public void setComboBoxOpcionMutacion(javax.swing.JComboBox<String> comboBoxOpcionMutacion) {
		ComboBoxOpcionMutacion = comboBoxOpcionMutacion;
	}


	
	

    
}
