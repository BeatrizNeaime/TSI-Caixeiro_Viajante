package caixeiroviajante;

import java.util.ArrayList;

public class Grafo<categoria> {
    private ArrayList<Vertice<categoria>> vertices;
    private ArrayList<Aresta<categoria>> arestas;
    ArrayList<Grafo> visited;

    public Grafo() {
        this.vertices = new ArrayList<Vertice<categoria>>();
        this.arestas = new ArrayList<Aresta<categoria>>();
    }

    public void adicionarVertice(categoria dado) {
        Vertice<categoria> novoVertice = new Vertice<categoria>(dado);
        this.vertices.add(novoVertice);
    }

    public void adicionarAresta(int peso, categoria dadoInicio, categoria dadoFim) {
        Vertice<categoria> inicio = this.getVertice(dadoInicio);
        Vertice<categoria> fim = this.getVertice(dadoFim);
        Aresta<categoria> aresta = new Aresta<categoria>(peso, inicio, fim);
        inicio.adicionarArestaSaida(aresta);
        fim.adicionarArestaEntrada(aresta);
        this.arestas.add(aresta);
    }

    public Vertice<categoria> getVertice(categoria dado) {
        Vertice<categoria> vertice = null;
        for (int i = 0; i < this.vertices.size(); i++) {
            if (this.vertices.get(i).getDado().equals(dado)) {
                vertice = this.vertices.get(i);
                break;
            }
        }
        return vertice;
    }

    public void buscaEmLargura() {
        ArrayList<Vertice<categoria>> marcados = new ArrayList<Vertice<categoria>>();
        ArrayList<Vertice<categoria>> fila = new ArrayList<Vertice<categoria>>();
        Vertice<categoria> atual = this.vertices.get(0);
        marcados.add(atual);
        System.out.println(atual.getDado());
        fila.add(atual);
        while (fila.size() > 0) {
            Vertice<categoria> visitado = fila.get(0);
            for (int i = 0; i < visitado.getOrigem().size(); i++) {
                Vertice<categoria> proximo = visitado.getOrigem().get(i).getFim();
                if (!marcados.contains(proximo)) { // se o vértice ainda não foi marcado
                    marcados.add(proximo);
                    System.out.println(proximo.getDado());
                    fila.add(proximo);
                }
            }
            fila.remove(0);
        }
    }

    public void buscaProfunda(Grafo g) {
        visited = new ArrayList<Grafo>();
        deepSearch(g);
    }

    public void deepSearch(Grafo g) {
        if (!visited.contains(g)) {
            visited.add(g);
            for (Grafo g1 : g.getVertice()) {
                deepSearch(g1);
            }
        }
    }
}
