package main;

import core.Escalonador;
import core.Fregues;
import core.Servidor;

public class Main {
	
	public static void main(final String[] args) {
	    System.out.println("Inicio do programa");
		
        Servidor servidor = new Servidor();
        Escalonador escalonador = new Escalonador(servidor);

        servidor.adicionaNaFila(new Fregues());
        new Thread() {
        	@Override
			public void run() {
				servidor.adicionaNaFila(new Fregues());
			}
        }.start();
        
        new Thread(escalonador).start();
        new Thread(servidor).start();

	}

}
