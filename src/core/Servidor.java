package core;

import utils.Utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Servidor implements Runnable{
	
	private boolean isLivre;
	private List<Fregues> fila1;
	private List<Fregues> fila2;
	private Random gerador;
	
	public Servidor() {
		this.fila1 = new ArrayList<>();
		this.fila2 = new ArrayList<>();
		this.gerador = new Random();
		
		this.isLivre = true;
	}

	public List<Fregues> getFila1() {
		return fila1;
	}

	public List<Fregues> getFila2() {
		return fila2;
	}

	public void adicionaFregues(Fregues newFregues) {
		if (isLivre) {
			Utils.print("Servidor esta livre e o cliente sera atendido",
					fila1.size(), fila2.size(), newFregues.getIdFregues());
			isLivre = false;
			executaServico(newFregues.getIdFregues());
		} else {
			adicionaNaFila(newFregues);
		}
	}

	public void adicionaNaFila(Fregues newFregues) {
		Utils.print("Servidor ocupado e o cliente sera colocado na fila " + newFregues.getTipoFila(),
				fila1.size(), fila2.size(), newFregues.getIdFregues());

		if (newFregues.getTipoFila() == 1) {
			fila1.add(newFregues);
		} else if (newFregues.getTipoFila() == 2){
			fila2.add(newFregues);
		}
	}

	public void executaServico(int idFregues) {
		int duracao = gerador.nextInt(5) + 3; // Gera numeros entre 3 e 7

		Utils.print(duracao + " segundos ate o fim do servico",
				fila1.size(), fila2.size(), idFregues);
		try {
			Thread.sleep(duracao*2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		isLivre = true;
	}

	private Fregues removeFregues(int tipoFila) {
		Fregues proximoFregues = null;
		if (tipoFila == 1) {
			proximoFregues = fila1.get(0);
			fila1 = fila1.subList(1, fila1.size());
		} else if (tipoFila == 2){
			proximoFregues = fila2.get(0);
			fila2 = fila2.subList(1, fila2.size());
		}
		return proximoFregues;
	}

	private void atendeProximoDaFila() {
		if (fila1.size() != 0) {
			Fregues proximo = removeFregues(1);
			Utils.print("Pegando proximo fregues da fila 1 pra ser atendido",
					fila1.size(), fila2.size(), proximo.getIdFregues());
			executaServico(proximo.getIdFregues());
		} else if (fila2.size() != 0) {
			Fregues proximo = removeFregues(2);
			Utils.print("Pegando proximo fregues da fila 2 pra ser atendido",
					fila1.size(), fila2.size(), proximo.getIdFregues());
			executaServico(proximo.getIdFregues());
		}
	}

	private boolean isFilaVazia() throws InterruptedException {
		return fila1.size() == 0 && fila2.size() == 0;
	}

	@Override
	public void run() {
		while (true) {
			if (isLivre) {
				try {
					if (!isFilaVazia()) {
                        isLivre = false;
                        atendeProximoDaFila();
                    }
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}
}
