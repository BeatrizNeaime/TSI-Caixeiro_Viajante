package caixeiroviajante;

public class Individuo {
    int[] genes;
    int fitness;
    public Individuo(int[] genes) {
        this.genes = genes;
    }

    public int[] getGenes() {
        return this.genes;
    }

    public void setGenes(int[] genes) {
        this.genes = genes;
    }

    public int getFitness() {
        return this.fitness;
    }

    public void setFitness(int fitness) {
        this.fitness = fitness;
    }

    @Override
    public String toString(){
        return " "+ genes;
    }
    
}
