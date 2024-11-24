package estructuras;

import java.util.ArrayList;
import java.util.Iterator;

public class ListaDoble<T> implements Iterable<T> {
    private class Nodo {
        T dato;
        Nodo anterior;
        Nodo siguiente;

        Nodo(T dato) {
            this.dato = dato;
        }
    }

    private Nodo cabeza;
    private Nodo cola;
    private int tamaño;

    public ListaDoble() {
        cabeza = null;
        cola = null;
        tamaño = 0;
    }

    public void agregar(T dato) {
        Nodo nuevo = new Nodo(dato);
        if (cabeza == null) {
            cabeza = cola = nuevo;
        } else {
            cola.siguiente = nuevo;
            nuevo.anterior = cola;
            cola = nuevo;
        }
        tamaño++;
    }

    public void eliminar(int index) {
        if (index < 0 || index >= tamaño) {
            throw new IndexOutOfBoundsException("Índice fuera de rango");
        }

        Nodo actual = cabeza;
        for (int i = 0; i < index; i++) {
            actual = actual.siguiente;
        }

        if (actual.anterior != null) {
            actual.anterior.siguiente = actual.siguiente;
        } else {
            cabeza = actual.siguiente;
        }

        if (actual.siguiente != null) {
            actual.siguiente.anterior = actual.anterior;
        } else {
            cola = actual.anterior;
        }

        tamaño--;
    }

    public int size() {
        return tamaño;
    }

    @Override
    public Iterator<T> iterator() {
        ArrayList<T> elementos = new ArrayList<>();
        Nodo actual = cabeza;
        while (actual != null) {
            elementos.add(actual.dato);
            actual = actual.siguiente;
        }
        return elementos.iterator();
    }
}
