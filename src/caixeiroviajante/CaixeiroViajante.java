
package caixeiroviajante;

import java.util.ArrayList;
import java.util.Random;

public class CaixeiroViajante {

    public static void main(String[] args) {
        // TODO code application logic here

        FileManager fileManager = new FileManager();
        ArrayList<String> text = fileManager.stringReader("./data/Teste.txt");

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

        int k = 10;// k indivíduos
        int x = 10; // x vezes pai e mãe
        int q = 5; // quantidade de indivíduos para mutações
        int v = 4; // v qtd mutações por individuo

        Auxiliar aux = new Auxiliar(k, nVertex, v, q, x, graph);

        aux.geraIndividuos();
        aux.crossover();
        aux.mutacao();
        aux.selecao();
        aux.calcFitness();
        aux.ordenaFitness();
    }
}
