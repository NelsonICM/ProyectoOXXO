package estructuras;

public class NodoArbol {
    private String id;
    private String nombre;
    private NodoArbol izquierdo;
    private NodoArbol derecho;

    public NodoArbol(String id, String nombre) {
        this.id = id;
        this.nombre = nombre;
        this.izquierdo = null;
        this.derecho = null;
    }

    public String getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public NodoArbol getIzquierdo() {
        return izquierdo;
    }

    public void setIzquierdo(NodoArbol izquierdo) {
        this.izquierdo = izquierdo;
    }

    public NodoArbol getDerecho() {
        return derecho;
    }

    public void setDerecho(NodoArbol derecho) {
        this.derecho = derecho;
    }

    // Método para insertar un nodo en el árbol
    public static NodoArbol insertar(NodoArbol raiz, String id, String nombre) {
        if (raiz == null) {
            return new NodoArbol(id, nombre);
        }
        if (id.compareTo(raiz.id) < 0) {
            raiz.izquierdo = insertar(raiz.izquierdo, id, nombre);
        } else if (id.compareTo(raiz.id) > 0) {
            raiz.derecho = insertar(raiz.derecho, id, nombre);
        }
        return raiz;
    }

    // Método para buscar un nodo
    public static NodoArbol buscar(NodoArbol raiz, String id) {
        if (raiz == null || raiz.id.equals(id)) {
            return raiz;
        }
        if (id.compareTo(raiz.id) < 0) {
            return buscar(raiz.izquierdo, id);
        }
        return buscar(raiz.derecho, id);
    }

    // Método para eliminar un nodo
    public static NodoArbol eliminar(NodoArbol raiz, String id) {
        if (raiz == null) {
            return null;
        }
        if (id.compareTo(raiz.id) < 0) {
            raiz.izquierdo = eliminar(raiz.izquierdo, id);
        } else if (id.compareTo(raiz.id) > 0) {
            raiz.derecho = eliminar(raiz.derecho, id);
        } else {
            if (raiz.izquierdo == null) {
                return raiz.derecho;
            } else if (raiz.derecho == null) {
                return raiz.izquierdo;
            }
            NodoArbol sucesor = encontrarMinimo(raiz.derecho);
            raiz.id = sucesor.id;
            raiz.nombre = sucesor.nombre;
            raiz.derecho = eliminar(raiz.derecho, sucesor.id);
        }
        return raiz;
    }

    private static NodoArbol encontrarMinimo(NodoArbol raiz) {
        while (raiz.izquierdo != null) {
            raiz = raiz.izquierdo;
        }
        return raiz;
    }

 // Recorrido Preorden (NLR)
    public static String recorridoPreorden(NodoArbol raiz) {
        StringBuilder resultado = new StringBuilder();
        preordenAux(raiz, resultado); // Uso del método auxiliar
        return resultado.toString();
    }

    private static void preordenAux(NodoArbol nodo, StringBuilder resultado) {
        if (nodo == null) {
            return;
        }
        resultado.append(nodo.id).append(" - ").append(nodo.nombre).append(", ");
        preordenAux(nodo.izquierdo, resultado);
        preordenAux(nodo.derecho, resultado);
    }

    // Recorrido Inorden (LNR)
    public static String recorridoInorden(NodoArbol raiz) {
        StringBuilder resultado = new StringBuilder();
        inordenAux(raiz, resultado); // Uso del método auxiliar
        return resultado.toString();
    }

    private static void inordenAux(NodoArbol nodo, StringBuilder resultado) {
        if (nodo == null) {
            return;
        }
        inordenAux(nodo.izquierdo, resultado);
        resultado.append(nodo.id).append(" - ").append(nodo.nombre).append(", ");
        inordenAux(nodo.derecho, resultado);
    }

    // Recorrido Postorden (LRN)
    public static String recorridoPostorden(NodoArbol raiz) {
        StringBuilder resultado = new StringBuilder();
        postordenAux(raiz, resultado); // Uso del método auxiliar
        return resultado.toString();
    }

    private static void postordenAux(NodoArbol nodo, StringBuilder resultado) {
        if (nodo == null) {
            return;
        }
        postordenAux(nodo.izquierdo, resultado);
        postordenAux(nodo.derecho, resultado);
        resultado.append(nodo.id).append(" - ").append(nodo.nombre).append(", ");
    }

}
