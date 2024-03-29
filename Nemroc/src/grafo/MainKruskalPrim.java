package grafo;


import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;
import java.util.Stack;
import java.util.TreeSet;

import agm.Kruskal;
import agm.Prim;


public class MainKruskalPrim {

	private static final String caminhoArquivo = System.getProperty("user.dir") + "/src/testes/kruskalprim/";
	private static List<String> arquivos = new ArrayList<String>();
	static BufferedReader in;
	
	protected GrafoListaAdjacenciaPesada grafo;
	
	public MainKruskalPrim(GrafoListaAdjacenciaPesada grafo) throws FileNotFoundException {
		
		this.grafo = grafo;
		
	}
	
	public void leArquivo() throws IOException{
		String linha = in.readLine();
		while (linha != null) {
			String palavras[] = linha.split(" ");
			
			switch(palavras[0]){
			case "edge":
				grafo.addEdge(Integer.parseInt(palavras[1]), Integer.parseInt(palavras[2]), Double.parseDouble(palavras[3]));
				break;
				
			case "prim":
				GrafoListaAdjacenciaPesada primMST = (new Prim()).computeMST(grafo);
				
				Iterator vertexIter = primMST.vertexIterator(); //iterador de vertices
				double pesoTotal=0; //contador peso total
				while (vertexIter.hasNext()){ //enquanto houver vertices
					Vertice u = (Vertice) vertexIter.next(); //pega um vertice
					System.out.print(u + ":\n");
					
					IteradorArestasPesadas edgeIter = primMST.weightedEdgeIterator(u); //iterador de arestas do vertice acima
					while (edgeIter.hasNext()) { //para todas as arestas do vertice
				    	Vertice v = (Vertice) edgeIter.next(); //le um vertice adjacente
						if (u.getIndex() < v.getIndex()){ //verificador anti-duplicidade by pacotinho
							pesoTotal += edgeIter.getWeight(); //conta peso
							System.out.print( "    " + v + "\n");
						}
				    }
				}
				System.out.println("Peso AGM:" + pesoTotal);
				break;
				
			case "kruskal":
				GrafoListaAdjacenciaPesada kruskalMST = (new Kruskal()).computeMST(grafo);
				
				Iterator vertexItera = kruskalMST.vertexIterator(); //iterador de vertices
				double pesoTota=0; //contador peso total
				while (vertexItera.hasNext()){ //enquanto houver vertices
					Vertice u = (Vertice) vertexItera.next(); //pega um vertice
					System.out.print(u + ":\n");
					
					IteradorArestasPesadas edgeIter = kruskalMST.weightedEdgeIterator(u); //iterador de arestas do vertice acima
					while (edgeIter.hasNext()) { //para todas as arestas do vertice
				    	Vertice v = (Vertice) edgeIter.next(); //le um vertice adjacente
						if (u.getIndex() < v.getIndex()){ //verificador anti-duplicidade by pacotinho
							pesoTota += edgeIter.getWeight(); //conta peso
							System.out.print( "    " + v + "\n");
						}
				    }
				}
				System.out.println("Peso AGM:" + pesoTota);
				break;
			}
			linha = in.readLine();
		}
	}
	
	public static void main(String[] args) throws IOException {
		//TODO fazer nome do arquivo ser entrada de teclado
		
		String nome_arquivo="";
		Scanner scanner = new Scanner(System.in); 
		
		System.out.println("Informe o nome do arquivo");
		nome_arquivo = scanner.nextLine();
		
		
		arquivos.add(caminhoArquivo + nome_arquivo);

		in = new BufferedReader(new FileReader(arquivos.get(0)));
		String card = in.readLine();
		GrafoListaAdjacenciaPesada grafo = new GrafoListaAdjacenciaPesada(Integer.parseInt(card), false);
		for (int i = 0; i < Integer.parseInt(card); i++){
			grafo.addVertex(i, Integer.toString(i));
		}
		
		MainKruskalPrim a = new MainKruskalPrim(grafo);
		a.leArquivo();
		scanner.close();
	}
}
