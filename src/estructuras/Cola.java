package estructuras;

import java.util.LinkedList;
import java.util.Queue;

public class Cola<T> {
    private Queue<T> elementos;

    public Cola() {
        elementos = new LinkedList<>();
    }

    public void encolar(T elemento) {
        elementos.add(elemento);
    }

    public T desencolar() {
        return elementos.poll(); // Retorna y elimina el primer elemento, o null si la cola está vacía
    }

    public boolean contiene(T elemento) {
        return elementos.contains(elemento);
    }

    public boolean estaVacia() {
        return elementos.isEmpty();
    }

    public int tamano() {
        return elementos.size();
    }
}
