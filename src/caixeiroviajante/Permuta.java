package caixeiroviajante;

import java.util.*;

public class Permuta {

    private int cont = 0;
    private int[] p;
    private int[] melhor;
    private int melhorCaminho;
    private ArrayList<int[]> caminhos = new ArrayList<int[]>();
    int graph[][];

    public Permuta(int graph[][]) {
        this.graph = graph;
    }

    public void permuta(ArrayList<Vertice> vs) {
        caminhos = new ArrayList<int[]>();
        p = new int[vs.size()];
        permuta(vs, 1, 0);
    }

    public void permuta(ArrayList<Vertice> vs, int n, int vertice) {
        if (n == vs.size()) {
            cont++;
            imprime();
        } else { 
            for (int i = 0; i < vs.size(); i++) { // ao inves de enviar pra todos, enviar para os adjacentes
                boolean found = false;
                for (int j = 0; j < n; j++) {
                    if (p[j] == vs.get(i).getId()) {
                        found = true;
                    }
                }

                if (!found) {
                    p[n] = vs.get(i).getId();
                    permuta(vs, n + 1, i);

                }
            }
        }
    }

    public void imprime() {
        System.out.println();
        System.out.print("(" + cont + "): ");
        caminhos.add(p);
        for (int i = 0; i < p.length; i++) {
            System.out.print(p[i] + " ");
        }
    }

    public void colar() {
        for (int i = 0; i < caminhos.size(); i++) {
            for (int j = 0; j < graph.length; j++) {
                System.out.print(caminhos.get(i)[j]);
            }
            System.out.println();
        }

    }
}