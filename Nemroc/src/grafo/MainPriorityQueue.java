package grafo;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import excecoes.AtualizacaoPrioridadeException;
import fila.HeapInfo;
import fila.HeapMaximoFilaPrioridade;
import fila.HeapMinimoFilaPrioridade;

/**
 * 
 * @author Anderson Rodrigues
 */
public class MainPriorityQueue {
	private static final String caminhoArquivo = System.getProperty("user.dir")
			+ "/src/testes/filaprioridade/";
	private static List<String> arquivos = new ArrayList<String>();
	static BufferedReader in;
	
	private HeapMaximoFilaPrioridade maxHeap = new HeapMaximoFilaPrioridade();
	private HeapMinimoFilaPrioridade minHeap = new HeapMinimoFilaPrioridade();
	private String heapType;
	private Object handle[] = new Object[200];

	public MainPriorityQueue(String heapType) throws FileNotFoundException {

		this.heapType = heapType;
	}

	public void leArquivo() throws IOException {
		String linha = in.readLine();
		while (linha != null) {
			String palavras[] = linha.split(" ");

			switch (palavras[0]) {
			case "extract":
				if (heapType.equals("MAX")){
					if (maxHeap.heapSize<1){
						System.out.println("empty");
					}else{
						System.out.println(maxHeap.extractMax());
					}
				}else{
					if (minHeap.heapSize<1){
						System.out.println("empty");
					}else{
						System.out.println(minHeap.extractMin());
					}
				}
				break;

			case "increase":
				try{
					maxHeap.increaseKey(handle[Integer.parseInt(palavras[1])], Integer.parseInt(palavras[2]));
					System.out.println("-");
				}catch (AtualizacaoPrioridadeException e){
					System.out.println("notupdated");
				}
				break;

			case "decrease":
				try{
					minHeap.decreaseKey(handle[Integer.parseInt(palavras[1])], Integer.parseInt(palavras[2]));
					System.out.println("-");
				}catch (AtualizacaoPrioridadeException e){
					System.out.println("notupdated");
				}
				break;

			case "insert":
				if (heapType.equals("MAX")){
					handle[Integer.parseInt(palavras[1])] = maxHeap.insert(new HeapInfo(palavras[1], Integer.parseInt(palavras[2])));
					System.out.println("-");
				}else{
					handle[Integer.parseInt(palavras[1])] = minHeap.insert(new HeapInfo(palavras[1], Integer.parseInt(palavras[2])));
					System.out.println("-");
				}
				break;
			}
			linha = in.readLine();
		}
	}

	public static void main(String[] args) throws IOException {

		String nome_arquivo = "";
		Scanner scanner = new Scanner(System.in);

		System.out.println("Informe o nome do arquivo");
		nome_arquivo = scanner.nextLine();

		arquivos.add(caminhoArquivo + nome_arquivo);
		in = new BufferedReader(new FileReader(arquivos.get(0)));
		String card = in.readLine();

		System.out.println("-");
		if (card.equals("MAX") || card.equals("MIN")){
			MainPriorityQueue a = new MainPriorityQueue(card);
			a.leArquivo();
		}else
			System.out.println("Formatacao incorreta");
		scanner.close();
	}
}
