package project;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MainFloyd {
	
	private static final String caminhoArquivo = System.getProperty("user.dir") + "/src/testes/floyd/";
	private static List<String> arquivos = new ArrayList<String>();
	static BufferedReader in;
	
	protected FloydWarshall floyd;
	protected static WeightedAdjacencyMatrixGraph grafo;
	
	public MainFloyd(WeightedAdjacencyMatrixGraph grafo) throws FileNotFoundException {
		
		floyd = new FloydWarshall();
		
	}
	
	public void leArquivo() throws IOException{
		String linha = in.readLine();
		while (linha != null) {
			String palavras[] = linha.split(" ");
			
			switch(palavras[0]){
			case "edge":
				grafo.addEdge(Integer.parseInt(palavras[1]), Integer.parseInt(palavras[2]), Double.parseDouble(palavras[3]));
				System.out.println("-");
				break;
				
			case "shortest":
				
				double[][] menorCaminho = floyd.computeShortestPaths(grafo);
				
				//System.out.println(menorCaminho[Integer.parseInt(palavras[1])][Integer.parseInt(palavras[2])]);
			
				boolean[][] existeCaminho = floyd.computeTransitiveClosure(grafo);
			
				if(!existeCaminho[Integer.parseInt(palavras[1])][Integer.parseInt(palavras[2])]){
					System.out.println("No Path");
				} else { 
					//TODO SALVAR E IMPRIMIR CAMINHO
					System.out.println("MENOR CAMINHO");
				}
				
				break;
				
			}
			linha = in.readLine();
		}
	}
	
	public static void main(String[] args) throws IOException {
		
		String nome_arquivo="";
		Scanner scanner = new Scanner(System.in); 
		
		System.out.println("Informe o nome do arquivo");
		nome_arquivo = scanner.nextLine();
		
		
		arquivos.add(caminhoArquivo + nome_arquivo);
		in = new BufferedReader(new FileReader(arquivos.get(0)));
		String card = in.readLine();
		grafo = new WeightedAdjacencyMatrixGraph(Integer.parseInt(card), true, 0);
		for (int i = 0; i < Integer.parseInt(card); i++){
			grafo.addVertex(i, Integer.toString(i));
		}
		MainFloyd a = new MainFloyd(grafo);
		a.leArquivo();
		scanner.close();
	}

}
