package caixeiroviajante;

import java.util.ArrayDeque;
import java.util.ArrayList;

public class Vertice {
    private int id;
    private ArrayList<Vertice> adjs = new ArrayList<Vertice>();
    private ArrayList<Vertice> visitados;
    
    public Vertice(int id) {
        this.id = id;
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public ArrayList<Vertice> getAdjs() {
        return this.adjs;
    }

    public void setAdjs(ArrayList<Vertice> adjs) {
        this.adjs = adjs;
    }

    public void zeraVisitados(Vertice v){
        System.out.println("> Busca em Profundidade");
        visitados = new ArrayList<Vertice>();
        buscaProfundidade(v);
    }


    public void buscaProfundidade(Vertice v){
        System.out.println("\nVértice atual: " + v.getId());
        System.out.println("Adjs: " + v.getAdjs()+"\n");
        System.out.print("Visitados: ");
        for(int i=0; i<visitados.size(); i++){
            System.out.print(visitados.get(i)+ " ");
        }
        if(!visitados.contains(v)){
            //System.out.println(v); 
            visitados.add(v);
            for(Vertice vertice : v.getAdjs()){
                buscaProfundidade(vertice);
            }
        }
    }

    public void buscaLArgura(Vertice v){
        System.out.println("\n>> Busca em Largura");
        System.out.println("Vértice atual:: "+ v.getId());
        ArrayDeque<Vertice> fila = new ArrayDeque<Vertice>();
        visitados = new ArrayList<Vertice>();
        visitados.add(v);
        fila.addFirst(v);

        while (!fila.isEmpty()) {
            Vertice vertice = fila.removeLast();
            for(Vertice vertex : vertice.getAdjs()){
                if(!visitados.contains(vertex)){ 
                    //System.out.println(vertex.toString());
                    fila.push(vertex);
                    visitados.add(vertex);
                }
            }
        }

        for(int i=0; i<visitados.size();i++){
            System.out.println("Largura visitados: " + visitados.get(i));
        }
    }

    @Override
    public String toString(){
        return "" + this.id;
    }

}
