package estructuras;

import java.util.ArrayList;
import java.util.Collections;

public class GrafoKruskal {
    public static class Arista implements Comparable<Arista> {
        public int origen;
        public int destino;
        public int peso;

        public Arista(int origen, int destino, int peso) {
            this.origen = origen;
            this.destino = destino;
            this.peso = peso;
        }

        @Override
        public int compareTo(Arista otra) {
            return Integer.compare(this.peso, otra.peso);
        }
    }

    private ArrayList<Arista> aristas;
    private int numNodos;

    public GrafoKruskal(int numNodos) {
        this.numNodos = numNodos;
        this.aristas = new ArrayList<>();
    }

    public void agregarArista(int origen, int destino, int peso) {
        aristas.add(new Arista(origen, destino, peso));
    }

    public ArrayList<Arista> obtenerAristas() {
        return new ArrayList<>(aristas);
    }

    public int getNumNodos() {
        return numNodos;
    }

    public ArrayList<Arista> calcularKruskal() {
        Collections.sort(aristas);

        int[] padres = new int[numNodos];
        for (int i = 0; i < numNodos; i++) {
            padres[i] = i;
        }

        ArrayList<Arista> resultado = new ArrayList<>();

        for (Arista arista : aristas) {
            int origenRaiz = encontrarRaiz(padres, arista.origen);
            int destinoRaiz = encontrarRaiz(padres, arista.destino);

            if (origenRaiz != destinoRaiz) {
                resultado.add(arista);
                padres[origenRaiz] = destinoRaiz;
            }
        }

        return resultado;
    }

    private int encontrarRaiz(int[] padres, int nodo) {
        while (padres[nodo] != nodo) {
            nodo = padres[nodo];
        }
        return nodo;
    }
}
