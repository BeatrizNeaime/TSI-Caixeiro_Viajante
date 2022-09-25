package caixeiroviajante;

import java.util.ArrayList;

public class CaixeiroViajante {

    public static void show_matrix(int[][] matrix) {
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix.length; j++) {
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println("");
        }
    }

    public static void pega_Adjs(int graph[][], ArrayList<Vertice> vs) {
        for (int i = 0; i < graph.length; i++) {
            //System.out.println("\nLista de Adjacência do Nó " + i + ":");

            for (int j = 0; j < graph.length; j++) {
                if (graph[i][j] != 0) {
                    // System.out.println("Origem: " + i + " | Destino: " + j + " | peso: " +
                    //         graph[i][j]);
                    // vs.get(i).getAdjs().add(vs.get(j));
                }
            }
        }
    }

    public static void clear_matrix(int[][] matrix) {
        for (int i = 0; i < matrix.length; i++) {
            matrix[i][i] = 0;
        }
    }

    public static void main(String[] args) {

        FileManager fileManager = new FileManager();
        ArrayList<String> text = fileManager.stringReader("./data/Teste_3.txt");

        int nVertex = 0;   
        int graph[][] = null;

        for (int i = 0; i < text.size(); i++) {
            String line = text.get(i);
            if (i == 0) {
                nVertex = Integer.parseInt(line.trim());
                graph = new int[nVertex][nVertex];
            } else {
                int oriVertex = Integer.parseInt(line.split(" ")[0]);
                String splits[] = line.substring(line.indexOf(" "), line.length()).split(";");
                for (String part : splits) {
                    String edgeData[] = part.split("-");
                    int targetVertex = Integer.parseInt(edgeData[0].trim());
                    int weight = Integer.parseInt(edgeData[1]);

                    /*
                     * ADICIONAR A ARESTA À REPRESENTAÇÃO
                     */
                    graph[oriVertex][targetVertex] = weight;
                    graph[targetVertex][oriVertex] = weight;
                }
            }
        }

        clear_matrix(graph);
        show_matrix(graph);

        // ArrayList<Vertice> vertices = new ArrayList<Vertice>();

        // for (int i = 0; i < graph.length; i++) {
        //     vertices.add(new Vertice(i));
        // }

        // /* ---- PEGAR VÉRTICES ADJACENTES ---- */
        // pega_Adjs(graph, vertices);

        // for(int i=0; i<graph.length; i++){
        // System.out.println(vertices.get(0).getAdjs());
        // }

        /* ---- BUSCAS ---- */

        //vertices.get(0).zeraVisitados(vertices.get(0));
        //vertices.get(0).buscaLArgura(vertices.get(0));
        
        Teste t = new Teste(graph);
        t.help(0);
    }

}
