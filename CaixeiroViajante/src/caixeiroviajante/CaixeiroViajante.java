package caixeiroviajante;

import java.util.ArrayList;

public class CaixeiroViajante {

    public static void show_graph(int[][] graph){
        for (int[] graph1 : graph) {
            for (int j = 0; j < graph1.length; j++) {
                System.out.print(graph1[j] + " ");
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {        
        FileManager fileManager = new FileManager();
        ArrayList<String> text = fileManager.stringReader("./data/Teste.txt");
        
        int nVertex = 0;
        int graph[][] = null;
        
        for (int i = 0; i < text.size(); i++) {
            String line = text.get(i);
            if (i == 0){
                nVertex = Integer.parseInt(line.trim());
                graph = new int[nVertex][nVertex];
            }
            else {
                int oriVertex = Integer.parseInt(line.split(" ")[0]);
                String splits[] = line.substring(line.indexOf(" "), line.length()).split(";");
                for (String part : splits){
                    String edgeData[] = part.split("-");
                    int targetVertex = Integer.parseInt(edgeData[0].trim());
                    int weight = Integer.parseInt(edgeData[1]);
                    
                    graph[oriVertex][targetVertex] = weight;
                    graph[targetVertex][oriVertex] = weight;
                }
            }
        }
        
        Grafo<Integer> grafo = new Grafo<>();
        grafo.adicionarVertice(0);
        grafo.adicionarVertice(1);
        grafo.adicionarVertice(2);
        grafo.adicionarVertice(3);
        grafo.adicionarVertice(4);
        
        /*
            A PRIMEIRA PODA A SER REALIZADA É ZERAR A DIAGONAL PRINCIPAL
            PARA RETIRAR OS CAMINHOS QUE APENAS RETORNAM AO MESMO LUGAR
        */
        
        for (int[] graph1 : graph) {
            for (int j = 0; j < graph1.length; j++) {
                graph[j][j] = 0;
            }
        }
        
        show_graph(graph);
        
        for (int i = 0; i<graph.length; i++) {
            for (int j = 0; j < graph.length; j++) {
                if(graph[i][j]==0){
                    continue;
                } else{
                    grafo.adicionarAresta(graph[i][j], i, j);
                }
            }
        }
        
        
        /*
        REALIZAR A BUSCA!
         */
        
    }
    
}
