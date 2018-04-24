package pev.main;

import pev.controlador.Controlador;
import pev.vista.Vista;

/**
 * 
 * @author Elianni Aguero
 * @author Manuel Martin
 */
public class Main{
	
    public static void main(String[] args){
   	
    	Vista vista = new Vista();
    	Controlador controlador = new Controlador(vista);
    	vista.conectaControlador(controlador);
    	
    }
    
}
