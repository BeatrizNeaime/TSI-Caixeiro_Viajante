package caixeiroviajante;

public class Vertice {
    private int id;
    
    public Vertice(int id) {
        this.id = id;
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }


    @Override
    public String toString(){
        return "" + this.id;
    }

}