package core;

import java.util.Random;

public class Fregues{
	
	private int tempoDeChegada;
	private int tipoFila;
	private int idFregues;
	private Random gerador;

	public Fregues() {

		gerador = new Random();
		tipoFila = gerador.nextInt(2) + 1; // do tipo 1 ou 2
		
		if (tipoFila == 1) {
			tempoDeChegada = gerador.nextInt(10) + 1; // de 1 a 10 segundos
		} else if (tipoFila == 2) {
			tempoDeChegada = gerador.nextInt(5) + 1; // de 1 a 5 segundos
		}
		
		idFregues = gerador.nextInt(500);
	}

	public int getTempoDeChegada() {
		return tempoDeChegada;
	}
	
	public int getTipoFila() {
		return tipoFila;
	}

	public int getIdFregues() {
		return idFregues;
	}

	public void setTipoFila(int tipoFila) {
		this.tipoFila = tipoFila;
	}
	
}
