package caixeiroviajante;

import java.util.*;

public class Auxiliar {
    int k, max, q, x, m;
    int[][] graph;
    ArrayList<Individuo> populacao = new ArrayList<>();

    public Auxiliar(int k, int max, int q, int x, int m, int[][] graph) {
        this.k = k;
        this.max = max;
        this.q = q;
        this.x = x;
        this.m = m;
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

    public void geraIndividuo() {
        int atual = populacao.size();
        for (int b = 0; b < k - atual; b++) {
            int[] cromossomos = new int[max];
            int[] ns = listaPossivel();
            embaralhar(ns);
            for (int i = 0; i < max; i++) {
                cromossomos[i] = ns[i];
            }
            populacao.add(new Individuo(ns));

        }
    }

    public void imprimir() {
        System.out.println("---- POPULAÇÃO (" + populacao.size() + ") ----");
        for (int i = 0; i < populacao.size(); i++) {
            for (int j = 0; j < max; j++) {
                System.out.print(populacao.get(i).getGenes()[j]);
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

        pai = populacao.get(nPai).getGenes();
        mae = populacao.get(nMae).getGenes();

        /* pai[0] - mãe [1] */
        pais.add(pai);
        pais.add(mae);
        return pais;
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

            populacao.add(new Individuo(filho));
        }
    }

    public void mutacao() {
        System.out.println("---- MUTAÇÃO (" + q + ") ----");
        for (int a = 0; a < q; a++) {
            Random r = new Random();
            int[] chosen = populacao.get(r.nextInt(populacao.size())).getGenes();
            /* SELECIONANDO */
            for (int i = 0; i < 5; i++) {
                Random y = new Random();

                /* MUTAÇÃO */
                int aux = 0, aux2 = 0;
                int ale, ale2;
                ale = y.nextInt(max);
                ale2 = y.nextInt(max);
                aux = chosen[ale];
                aux2 = chosen[ale2];

                chosen[ale] = aux2;
                chosen[ale2] = aux;
            }

        }

    }

    public void selecao() {
        ArrayList<Individuo> eliminar = new ArrayList<>();
        System.out.println("---- SELEÇÃO ----");
        int last = max - 1;

        for (int i = 0; i < populacao.size(); i++) { // pega posição no arraylist
            if (verificaNext(populacao.get(i).getGenes()[last], populacao.get(i).getGenes()[0])) {
                eliminar.add(populacao.get(i));
            }
            for (int j = 0; j < max; j++) { // posição o vetor
                for (int k = j + 1; k < max; k++) { // verifica o próximo
                    if (populacao.get(i).getGenes()[j] == populacao.get(i).getGenes()[k]) {
                        eliminar.add(populacao.get(i));
                        break;
                    }
                }
            }

            for (int k = 1; k < max; k++) {
                if (verificaNext(populacao.get(i).getGenes()[k - 1], populacao.get(i).getGenes()[k])) {
                    eliminar.add(populacao.get(i));
                }

            }
        }

        int tamInvalid = eliminar.size();
        eliminaInvalid(tamInvalid, eliminar);
    }

    public boolean verificaNext(int x, int y) {
        if (pegaMatriz(x, y) == 0) {
            return true;
        }
        return false;
    }

    public void eliminaInvalid(int tam, ArrayList<Individuo> eli) {

        for (int i = 0; i < tam; i++) {
            for (int j = 0; j < populacao.size(); j++) {
                if (populacao.get(j) == eli.get(i)) {
                    populacao.remove(j);
                }
            }
        }

    }

    public void calcFitness() {
        for (int i = 0; i < populacao.size(); i++) {
            int soma = 0;
            for (int k = 1; k < max; k++) {
                soma += pegaMatriz(populacao.get(i).getGenes()[k - 1], populacao.get(i).getGenes()[k]);
            }

            soma += pegaMatriz(populacao.get(i).getGenes()[max - 1], populacao.get(i).getGenes()[0]);

            populacao.get(i).setFitness(soma);
        }
        ordenaFitness();
    }

    public int pegaMatriz(int x, int y) {
        return graph[x][y];
    }

    public void ordenaFitness() {
        populacao.sort(Comparator.comparing(Individuo::getFitness));
    }

    public void geraNext() {
        ArrayList<Individuo> next = new ArrayList<>();
        int por = (populacao.size() * m) / 100;
        System.out.println("PASSAM " + por + " INDIVÍDUOS PARA A PRÓXIMA GERAÇÃO");
        for (int i = 0; i < por; i++) {
            next.add(populacao.get(i));
        }
        populacao.clear();
        for (int i = 0; i < por; i++) {
            populacao.add(next.get(i));
        }
        next.clear();
    }

    public void theEnd() {
        for (int i = 0; i < max; i++) {
            System.out.print(populacao.get(0).getGenes()[i] + " ");
        }
        System.out.println("\nFitness: " + populacao.get(0).getFitness());
    }

}
