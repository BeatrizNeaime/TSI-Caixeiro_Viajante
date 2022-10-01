
package caixeiroviajante;

import java.util.ArrayList;

public class CaixeiroViajante {

    public static void show_matrix(int[][] graph) {
        for (int i = 0; i < graph.length; i++) {
            for (int j = 0; j < graph.length; j++) {
                System.out.print(graph[i][j] + " ");
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        // TODO code application logic here

        FileManager fileManager = new FileManager();
        ArrayList<String> text = fileManager.stringReader("./data/Teste_4.txt");

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

                    graph[oriVertex][targetVertex] = weight;
                    graph[targetVertex][oriVertex] = weight;
                }
            }
        }

        int k = 10000; // k indivíduos
        int x = k/10; // x vezes pai e mãe
        int q = 6000; // quantidade de indivíduos para mutações (60%)
        int m = 40; // porcentagem pra próx geração
        int g = 10; // gerações

        Auxiliar aux = new Auxiliar(k, nVertex, q, x, m, graph);
        show_matrix(graph);

        long to = System.currentTimeMillis();

        for (int i = 0; i < g; i++) {
            System.out.println("----- GERAÇÃO " + g + " -----");
            aux.geraIndividuo();
            aux.crossover();
            aux.mutacao();
            aux.selecao();
            aux.calcFitness();
            aux.ordenaFitness();
            aux.geraNext();
        }
        long tf = System.currentTimeMillis();
        System.out.println(">>> FIM DE " + g + " GERAÇÕES");
        
        System.out.println("\n\nConfiguração:\nVértices: "+nVertex+"\nPopulação inicial: " + k+ "\nCrossover: " + x + "\nIndivíduos separados para mutação: " + q + "\nPassaram para a próxima geração: " + m + "%\nGerações: " + g);
        System.out.println("Tempo de execução: " + (tf-to) + "ms");
        System.out.println(">>> MELHOR RESULTADO: ");
        aux.theEnd();
    }
}