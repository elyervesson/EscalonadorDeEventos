package core;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Servidor implements Runnable{
	
	private boolean isLivre;
	private List<Fregues> fila1;
	private List<Fregues> fila2;
	private Random gerador;
	private Fregues nextFregues;
	
	public Servidor() {
		System.out.println("Construtor do Servidor");
		this.fila1 = new ArrayList<>();
		this.fila2 = new ArrayList<>();
		this.gerador = new Random();
		
		this.isLivre = true;
	}
	
	public boolean isLivre() {
		return isLivre;
	}

	public void setLivre(boolean isLivre) {
		this.isLivre = isLivre;
	}

	public void executaServico() throws InterruptedException {
		int duracao = gerador.nextInt(4) + 3; // Gera numeros entre 3 e 7
		
		/* execução do codigo de servico */
		System.out.println("Tempo ate o fim do atendimento: " + duracao);

		Thread.sleep(duracao*2000);
		/* fim da execução do codigo de servico */
		
		setLivre(true);
	}

	private boolean isFilaVazia() throws InterruptedException {
		return fila1.size() == 0 && fila2.size() == 0;
	}
	
	public void adicionaNaFila(Fregues newFregues) {
		if (newFregues.getTipoFila() == 1) {
			System.out.println("Adicionando cliente na fila 1");
			fila1.add(newFregues);
		} else {
			System.out.println("Adicionando cliente na fila 2");
			fila2.add(newFregues);
		}
	}
	
	private void atendeProximoDaFila() throws InterruptedException {
		if (fila1.size() != 0) {
			System.out.println("Cliente da fila 1 sera atendido");
			removeFregues(1);
			executaServico();
		} else {
			System.out.println("Cliente da fila 2 sera atendido");
			removeFregues(2);
			executaServico();
		}
	}
	
	private Fregues removeFregues(int tipoFregues) {
		Fregues freguesAtual;
		if (tipoFregues == 1) {
			freguesAtual = fila1.get(0);
			fila1 = fila1.subList(1, fila1.size());
		} else {
			freguesAtual = fila2.get(0);
			fila2 = fila2.subList(1, fila2.size());
		}
		return freguesAtual;
	}
	
	public Fregues getNextFregues() {
		if (fila1.size() != 0) {
			nextFregues = fila1.get(0);
		} else {
			nextFregues = fila2.get(0);
		}
		return nextFregues;
	}
	
	public void adicionaFregues(Fregues newFregues) throws InterruptedException {
		if (isLivre()) {
			System.out.println("Servidor livre, atendendo o cliente");
			setLivre(false);
			executaServico();
		} else {
			System.out.println("Servidor ocupado, colocando o cliente na fila");
			adicionaNaFila(newFregues);			
		}
	}

	@Override
	public void run() {
		while (true) {
			if (isLivre()) {
				//System.out.println("Servidor esta livre");
				try {
					if (!isFilaVazia()) {
						System.out.println("Atendendo o proximo cliente da fila");
						setLivre(false);
						atendeProximoDaFila();
					}
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				
				
			}
		}
	}
}
