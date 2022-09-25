package caixeiroviajante;

import java.util.ArrayList;

public class Teste {
    int[][] graph;
    int prox = 0;
    ArrayList<Integer> cidades;
    int menor, soma, conta = 0;
    ArrayList<Integer> pesos = new ArrayList<>();

    public Teste(int[][] graph) { 
        this.graph = graph;
    }

    public void help(int id) {
        menor = graph[id][id + 1];
        cidades = new ArrayList<>();
        cidades.add(id);
        verificaMenor(id);
    }

    public int pegaDestino(int a, int n) {
        int destino = 0;
        for (int j = 0; j < graph.length; j++) {
            if (graph[a][j] == n) {
                destino = j;
            }
        }
        return destino;
    }

    public void verificaMenor(int id) {
        menor = 1000000000;
        if (conta < graph.length-1) {
            for (int i = 0; i < graph.length; i++) {
                if (graph[id][i] > 0 && graph[id][i] < menor && !cidades.contains(i)) {
                    menor = graph[id][i];
                    prox = pegaDestino(id, menor);
                }
            }
            cidades.add(prox);
            conta++; 
            pesos.add(menor);
            verificaMenor(prox);
        } else {
            imprimir();
            imprimePeso();
        }
    }

    public void imprimir() {
        cidades.add(0);
        System.out.println("\nMelhor rota: ");
        for (int i = 0; i < cidades.size(); i++) {
            System.out.print(cidades.get(i) + " ");
        }
    }

    public void imprimePeso() {
        System.out.println();
        pesos.add(graph[cidades.get(cidades.size()-2)][0]);
        // for(int i=0;i<pesos.size();i++){
        //     System.out.print(pesos.get(i) + " ");
        // }
        for(int i=0;i<pesos.size();i++){
            soma += pesos.get(i);
        }
        System.out.println("\nSoma dos pesos: " + soma);
    }

}
