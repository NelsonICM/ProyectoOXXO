package estructuras;

import java.util.ArrayList;

public class Pila<T> {
    private ArrayList<T> elementos;

    public Pila() {
        elementos = new ArrayList<>();
    }

    public void apilar(T elemento) {
        elementos.add(elemento);
    }

    public T desapilar() {
        if (!elementos.isEmpty()) {
            return elementos.remove(elementos.size() - 1);
        }
        return null; // Retorna null si la pila está vacía
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
