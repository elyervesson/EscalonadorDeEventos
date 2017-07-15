package core;

import utils.Utils;

public class Escalonador implements Runnable{
	
	private final Servidor servidor;

    public Escalonador(final Servidor servidor) {
    	this.servidor = servidor;
    }
    
    

    @Override
    public void run() {
        while (true) {
			System.out.println("\n******************** Inicio do Loop ********************\n");
        	
			Fregues nextFregues = new Fregues();
        	
        	// Simula o tempo em que o fregues vai chegar
        	try {
        		Utils.print(nextFregues.getTempoDeChegada() +" segundos ate a chegada de um cliente",
						servidor.getFila1().size(), servidor.getFila2().size(), nextFregues.getIdFregues());

				Thread.sleep(nextFregues.getTempoDeChegada()*1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

			Utils.print("Novo cliente chegou ao servidor",
					servidor.getFila1().size(), servidor.getFila2().size(), nextFregues.getIdFregues());

			servidor.adicionaFregues(nextFregues);
        }
    }

}
