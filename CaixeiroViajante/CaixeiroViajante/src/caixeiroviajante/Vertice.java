package caixeiroviajante;

import java.util.ArrayList;

public class Vertice<categoria> {
    private categoria dado;
    private ArrayList<Aresta<categoria>> origem;
    private ArrayList<Aresta<categoria>> destino;

    public Vertice(categoria valor) {
        this.dado = valor;
        this.origem = new ArrayList<Aresta<categoria>>();
        this.destino = new ArrayList<Aresta<categoria>>();
    }

    public categoria getDado() {
        return dado;
    }

    public void setDado(categoria dado) {
        this.dado = dado;
    }

    public void adicionarArestaEntrada(Aresta<categoria> aresta) {
        this.origem.add(aresta);
    }

    public void adicionarArestaSaida(Aresta<categoria> aresta) {
        this.destino.add(aresta);
    }

    public ArrayList<Aresta<categoria>> getOrigem() {
        return origem;
    }

    public ArrayList<Aresta<categoria>> getDestino() {
        return destino;
    }
}
