package core;

import java.util.Random;

public class Fregues{
	
	private int tempoDeChegada;
	private int tipoFila;
	private Random gerador;
	
	public Fregues() {
		gerador = new Random();
		tempoDeChegada = gerador.nextInt(10) + 1; // de 1 a 10 segundos
		tipoFila = gerador.nextInt(1) + 1; // do tipo 1 ou 2
	}

	public int getTempoDeChegada() {
		return tempoDeChegada;
	}
	
	public int getTipoFila() {
		return tipoFila;
	}
	
}
