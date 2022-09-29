package caixeiroviajante;

import java.util.*;

public class Auxiliar {
    int k, max;
    ArrayList<int[]> individuos = new ArrayList<>();
    ArrayList<int[]> pais = new ArrayList<>();

    public Auxiliar(int k, int max, ArrayList<int[]> individuos) {
        this.k = k;
        this.max = max;
        this.individuos = individuos;
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
        int[] cromossomos = new int[max];
        int[] ns = listaPossivel();
        embaralhar(ns);
        for (int i = 0; i < max; i++) {
            cromossomos[i] = ns[i];
        }
        individuos.add(cromossomos);
        // imprimir();
    }

    public void imprimir() {
        for (int i = 0; i < individuos.size(); i++) {
            for (int j = 0; j < max; j++) {
                System.out.print(individuos.get(i)[j]);
            }
            System.out.println();
        }

    }

    public void selecionaPais() {
        Random random = new Random();
        int nPai = random.nextInt(max);
        int nMae = random.nextInt(max);
        if (nPai == nMae) {
            do {
                nMae = random.nextInt(max);
            } while (nMae == nPai);
        }

        int[] pai = individuos.get(nPai);
        int[] mae = individuos.get(nMae);

        /* pai[0] - mãe [1] */
        pais.add(pai);
        pais.add(mae);
        exibePais();
    }

    public void exibePais() {
        for (int i = 0; i < pais.size(); i++) {
            for (int j = 0; j < max; j++) {
                System.out.print(pais.get(i)[j]);
            }
            System.out.println();
        }
    }

    public void crossover(int x) {
        int[] filho = new int[max];
        int metade = max / 2;

        /* Cruzamento: primeira metade do pai, segunda metade da mãe */
        selecionaPais();

        for (int i = 0; i < max; i++) {
            if (i < metade) {
                filho[i] = pais.get(0)[i];
            } else
                filho[i] = pais.get(1)[i];
        }

        System.out.println("----- novo individuo: -----");
        for (int i = 0; i < max; i++) {
            System.out.print(filho[i]);
        }

        System.out.println();
        individuos.add(filho);
    }

}
