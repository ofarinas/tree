/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tree;

/**
 *
 * @author ENTRAR
 */
public class Trie {
    /**
     * Clase auxiliar para guardar caracteres que forman parte de una palabra
     * @author zenon
     */
    protected class Info {
        char c;    // Caracter actual
        Info alt;  // Caracter alternativo para la misma posición
        Info next; // Caracter que es la primera opción en la siguiente posición
        
        public Info(char c, Info alt, Info next) {
            this.c = c;
            this.alt = alt;
            this.next = next;
        }
    }
    
    protected Info root; // Referencia al punto de inicio de la estructura
    
    /**
     * Inserta una palabra en la estructura interna del objeto Trie
     * empleando un algoritmo recursivo
     * @param list - estructura interna del objeto Trie
     * @param word - palabra a insertar
     * @return la estructura interna del objeto Trie modificada
     */
    private Info insert(Info list, String word) {
        // Si la estructura está vacía, la palabra se inserta sin más
        if (list == null) {
            // Si se ha alcanzado el final de la palabra, se pone una marca
            if (word.length() == 0){
                list = new Info('*', null, null);
            } else {
                // Se crea un nodo con el caracter actual y una lista en la
                // que se inserta el resto de la palabra
                Character c = word.charAt(0);

                list = new Info(c, null, insert(null, word.substring(1)));
            }
        } else {
            Character c = word.charAt(0);
            
            if (list.c == c) {
                // Si el caracter actual ya está en list, se inserta el
                // resto de la palabra en la sublista correspondiente
                list.next = insert(list.next, word.substring(1));
            } else {
                if (list.c < c) {
                    // Si el caracter actual es mayor que el de list,
                    // se inserta por detrás
                    list.alt = insert(list.alt, word);
                } else {
                    // Si el caracter actual es menor que el de list, se crea
                    // un nodo con el caracter y se inserta list por detrás
                    list = new Info(c, list, insert(null, word.substring(1)));
                }
            }
        } 
        
        return list;        
    }
    
    /**
     * Inserta una palabra en el objeto Trie
     * @param word
     */
    public void insert(String word) {
        root = insert(root, word); // Método auxiliar para la inserción
    }
    
    /**
     * Obtiene una representación como String de las palabras almacenadas en el
     * Trie. En la String resultante cada palabra está separada por un salto de
     * línea
     * @param list - estructura interna del Trie
     * @param pref - prefijo de las palabras en la posisicón actual del Trie
     * @param result - resultado en construcción
     * @return el resultado construido
     */
    private String toString(Info list, String pref, String result) {
        if (list == null) {
            // el resultado entrante no cambia
            return result;
        } else {
            if (list.c == '*') {
                // Se ha alcanzado el final de una palabra
                result += pref + '\n';
                // Se examinan alternativas más largas
                result = toString(list.alt, pref, result);
                return result;
            } else {
                // Se avanza a las palabras que comparten todos los caracteres
                // hasta la posición actual
                result = toString(list.next, pref + list.c, result);
                // Se avanza a las palabras que no comparten el caractre actual,
                // pero sí los anteriores
                return toString(list.alt, pref, result);
            }
        }
    }
    
    @Override
    public String toString() {
        return toString(root, "", ""); // Método auxiliar
    }
}