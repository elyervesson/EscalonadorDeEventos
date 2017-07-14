package core;

public class Escalonador implements Runnable{
	
	private final Servidor servidor;

    public Escalonador(final Servidor servidor) {
		System.out.println("Construtor do Escalonador");
    	this.servidor = servidor;
    }
    
    

    @Override
    public void run() {
        while (true) {
			System.out.println("******************** Inicio do Loop ********************\n");
        	
			Fregues nextFregues = servidor.getNextFregues();
        	
        	// Simula o tempo em que o fregues vai chegar
        	try {
        		System.out.println("Tempo ate a chegado do cliente: " + nextFregues.getTempoDeChegada());
				Thread.sleep(nextFregues.getTempoDeChegada()*1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

			System.out.println("Cliente chegou");
        	// Simula a chegada do fregues ao servidor
        	try {
				servidor.adicionaFregues(nextFregues);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
        }
    }

}
