package caixeiroviajante;

public class Aresta<categoria> {
    private Integer peso;
    private Vertice<categoria> inicio;
    private Vertice<categoria> fim;
    
    public Aresta(int peso, Vertice<categoria> inicio, Vertice<categoria> fim){
        this.peso = peso;
        this.inicio = inicio;
        this.fim = fim;
    }

    public int getPeso() {
        return peso;
    }

    public void setPeso(int peso) {
        this.peso = peso;
    }

    public Vertice<categoria> getInicio() {
        return inicio;
    }

    public void setInicio(Vertice<categoria> inicio) {
        this.inicio = inicio;
    }

    public Vertice<categoria> getFim() {
        return fim;
    }

    public void setFim(Vertice<categoria> fim) {
        this.fim = fim;
    }
}
