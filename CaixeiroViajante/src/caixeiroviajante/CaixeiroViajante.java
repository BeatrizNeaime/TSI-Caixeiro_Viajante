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
    public static void main(String[] args) {
        // TODO code application logic here
        
        FileManager fileManager = new FileManager();
        ArrayList<String> text = fileManager.stringReader("./data/Teste.txt");
        
        Graph graph = null;

        int nVertex = 0;
        
        for (int i = 0; i < text.size(); i++) {
            String line = text.get(i);
            if (i == 0){
                nVertex = Integer.parseInt(line.trim());
                graph = new AdjMatrix(nVertex);
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
                    graph.setEdge(oriVertex, targetVertex, weight);
                    graph.setEdge(targetVertex, oriVertex, weight);
                }
            }
        }
        
        /*
            print no GRAPH
        */
        graph.printGraph();
    }
    
}
