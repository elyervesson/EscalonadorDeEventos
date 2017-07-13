package main;

import core.Escalonador;
import core.Servidor;

public class Main {
	
	public static void main(final String[] args) {
	    System.out.println("Inicio do programa");
		
        Servidor servidor = new Servidor();
        Escalonador escalonador = new Escalonador(servidor);

        new Thread(servidor).start();
        new Thread(escalonador).start();
	}

}
