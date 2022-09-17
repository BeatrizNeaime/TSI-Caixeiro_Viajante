package caixeiroviajante;

import java.util.ArrayList;
import java.util.Arrays;

public class Permuta {
    private static int cont = 0, flag = 0;;
    private static int[] p;
    private static ArrayList<int[]> caminhos = new ArrayList<int[]>();

    public void permuta(ArrayList<Vertice> vs) {
        caminhos = new ArrayList<int[]>();
        p = new int[vs.size()];
        permuta(vs, 0);
    }

    public static void permuta(ArrayList<Vertice> vs, int n) {
        if (n == vs.size() && p[0] == 0) {
            cont++;
            imprime();
            caminhos.add(flag, p);
            flag++;
            System.out.println("Flag: " + flag);
        } else {
            for (int i = 0; i < vs.size(); i++) {
                boolean found = false;
                for (int j = 0; j < n; j++) {
                    if (p[j] == vs.get(i).getId()) {
                        found = true;
                    }
                }

                if (!found) {
                    p[n] = vs.get(i).getId();
                    permuta(vs, n + 1);

                }
            }
        }
    }

    public static void imprime() {
        System.out.println();
        System.out.print("(" + cont + "): ");
        for (int i = 0; i < p.length; i++) {

            System.out.print(p[i] + " ");
        }
    }

    public void colar() {
            System.out.println("Posição: " + flag + " " + caminhos.get(0)[flag-1]);

    }
}
