package caixeiroviajante;

import java.util.*;

public class Auxiliar {
    int k, max, q, vezes, x;
    int[][] graph;
    ArrayList<Individuo> individuos = new ArrayList<>();

    public Auxiliar(int k, int max, int vezes, int q, int x, int[][] graph) {
        this.k = k;
        this.max = max;
        this.vezes = vezes;
        this.q = q;
        this.x = x;
        this.graph = graph;
    }

    public static void embaralhar(int[] v) {
        Random random = new Random();

        for (int i = 0; i < (v.length - 1); i++) {
            int j = random.nextInt(v.length);

            int aux = v[i];
            v[i] = v[j];
            v[j] = aux;
        }

    }

    public int[] listaPossivel() {
        int[] numeros = new int[max];
        for (int i = 0; i < max; i++) {
            numeros[i] = i;
        }
        return numeros;
    }

    public void geraIndividuos() {
        for (int b = 0; b < k; b++) {
            int[] cromossomos = new int[max];
            int[] ns = listaPossivel();
            embaralhar(ns);
            for (int i = 0; i < max; i++) {
                cromossomos[i] = ns[i];
            }
            individuos.add(new Individuo(ns));

        }
        imprimir();
    }

    public void imprimir() {
        System.out.println("---- POPULAÇÃO (" + individuos.size() + ") ----");
        for (int i = 0; i < individuos.size(); i++) {
            for (int j = 0; j < max; j++) {
                System.out.print(individuos.get(i).getGenes()[j]);
            }
            System.out.println();
        }

    }

    public ArrayList<int[]> selecionaPais() {
        ArrayList<int[]> pais = new ArrayList<>();
        Random random = new Random();
        int nPai = random.nextInt(max);
        int nMae = random.nextInt(max);
        int[] pai = new int[max];
        int[] mae = new int[max];

        if (nPai == nMae) {
            do {
                nMae = random.nextInt(max);
            } while (nMae == nPai);
        }

        pai = individuos.get(nPai).getGenes();
        mae = individuos.get(nMae).getGenes();

        /* pai[0] - mãe [1] */
        pais.add(pai);
        pais.add(mae);
        System.out.println();
        return pais;
    }

    public void exibePais(ArrayList<int[]> pais) {
        for (int i = 0; i < pais.size(); i++) {
            for (int j = 0; j < max; j++) {
                System.out.print(pais.get(i)[j]);
            }
            System.out.println();
        }
    }

    public void crossover() {
        System.out.println("---- CROSSOVER (" + x + ") ----");
        /* Cruzamento: primeira metade do pai, segunda metade da mãe */
        for (int p = 0; p < x; p++) {
            int[] filho = new int[max];
            int metade = max / 2;
            ArrayList<int[]> pais = new ArrayList<>();
            pais = selecionaPais();

            for (int i = 0; i < max; i++) {
                if (i < metade) {
                    filho[i] = pais.get(0)[i];
                } else
                    filho[i] = pais.get(1)[i];
            }

            individuos.add(new Individuo(filho));
            System.out.println("----- novo individuo: -----");
            for (int i = 0; i < max; i++) {
                System.out.print(filho[i]);
            }
            System.out.println("\n\n");
        }
        System.out.println("---- FIM CROSSOVER ----");

        imprimir();
    }

    public void mutacao() {
        System.out.println("---- MUTAÇÃO (" + q + ") ----");
        for (int a = 0; a < q; a++) {
            /* SELECIONANDO */
            Random r = new Random();
            Random y = new Random();
            int[] chosen = individuos.get(r.nextInt(individuos.size())).getGenes();
            System.out.println("Escolhido para mutação:");
            for (int i = 0; i < max; i++) {
                System.out.print(chosen[i]);
            }
            System.out.println();

            /* MUTAÇÃO */
            int aux = 0, aux2 = 0;
            int ale, ale2;
            ale = y.nextInt(max);
            ale2 = y.nextInt(max);
            aux = chosen[ale];
            aux2 = chosen[ale2];

            chosen[ale] = aux2;
            chosen[ale2] = aux;

            System.out.println("Após mutação:");
            for (int i = 0; i < max; i++) {
                System.out.print(chosen[i]);
            }
            System.out.println();

        }

        System.out.println("---- FIM MUTAÇÃO ----");
        imprimir();
    }

    public void selecao() {
        ArrayList<Individuo> eliminar = new ArrayList<>();
        System.out.println("---- SELEÇÃO ----");
        for (int i = 0; i < individuos.size(); i++) { // pega posição no arraylist
            for (int j = 0; j < max; j++) { // posição o vetor
                for (int k = j + 1; k < max; k++) { // verifica o próximo
                    if (individuos.get(i).getGenes()[j] == individuos.get(i).getGenes()[k]) {
                        eliminar.add(individuos.get(i));
                        break;
                    }
                }
            }
        }

        int tamInvalid = eliminar.size();
        eliminaInvalid(tamInvalid, eliminar);
        System.out.println("---- FIM SELEÇÃO ----");
        imprimir();
    }

    public void eliminaInvalid(int tam, ArrayList<Individuo> eli) {
        System.out.println("Eliminado(s) por invalidez (" + eli.size() + "):");

        for (int i = 0; i < tam; i++) {
            for (int j = 0; j < max; j++) {
                System.out.print(eli.get(i).getGenes()[j]);
            }
            System.out.println();
        }

        for (int i = 0; i < tam; i++) {
            for (int j = 0; j < individuos.size(); j++) {
                if (individuos.get(j) == eli.get(i)) {
                    individuos.remove(j);
                }
            }
        }

    }

    public void calcFitness() {
        System.out.println("FITNESS");
        for (int i = 0; i < individuos.size(); i++) {
            int soma = 0;
            for (int k = 1; k < max; k++) {
                soma += pegaMatriz(individuos.get(i).getGenes()[k - 1], individuos.get(i).getGenes()[k]);
            }
            
            individuos.get(i).setFitness(soma);
            System.out.print("--------\nIndividuo: ");
            for (int j = 0; j < max; j++) {
                System.out.print(individuos.get(i).getGenes()[j]);
            }
            System.out.println("\nFitness: "+ individuos.get(i).getFitness());
        }  
    }

    public int pegaMatriz(int x, int y) {
        return graph[x][y];
    }

    public void ordenaFitness(){
        individuos.sort(Comparator.comparing(Individuo::getFitness));
        System.out.println("--- ORDENANDO O FITNESS ----");
        for(int i=0;i<individuos.size();i++){
            for(int j=0;j<max;j++){
                System.out.print(individuos.get(i).getGenes()[j]);
            }
            System.out.println();
        }
    }

}
