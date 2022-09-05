/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package caixeiroviajante;

import java.util.ArrayList;

/**
 *
 * @author Douglas
 */
public class CaixeiroViajante {

    /**
     * @param args the command line arguments
     */

    public static void show_matrix(int[][] matrix){
        for(int i=0;i<matrix.length;i++){
            for(int j=0; j<matrix.length;j++){
                System.out.print(matrix[i][j]+ " ");
            }
            System.out.println("");
        }
    }

    public static void clear_matrix(int[][] matrix) {
        for(int i=0;i<matrix.length;i++){
            matrix[i][i] = 0;
        }
    }

    public static void main(String[] args) {
        // TODO code application logic here
        
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
                    
                    /*
                        ADICIONAR A ARESTA À REPRESENTAÇÃO
                    */
                    graph[oriVertex][targetVertex] = weight;
                    graph[targetVertex][oriVertex] = weight;
                }
            }
        }
        clear_matrix(graph);
        show_matrix(graph);

        Grafo<Integer> g = new Grafo<Integer>();

        for(int j=0;j<graph.length; j++){
            g.adicionarVertice(j);
        }

        for(int i=0; i<graph.length;i++){
            for(int j=0;j<graph.length; j++){
                if(graph[i][j]!=0){
                    g.adicionarAresta(graph[i][j], i, j);
                }
            }    
        }
    }
    
}
