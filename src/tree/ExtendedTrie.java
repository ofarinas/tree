/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tree;

/**
 *
 * @author ENTRAR
 */
public class ExtendedTrie extends Trie {
    
    public ExtendedTrie() {
        super(); // Llamada al constructor de la clase Trie
    }
    
    public int count() {
        Info aux = root;
        return countRecursivo(aux);
    }
    
    public boolean contain(String cadena) {
        Info temp = root;
        return recursiveContain(temp, cadena);
    }
    
    private boolean recursiveContain(Info temp, String cadena) {
        if (temp == null) {
            return false;
        }
        if (temp.c == '*' && cadena.length() == 0) {
            return true;
        }
        if (cadena.length() == 0) {
            return false;
            
        } else {
            if (cadena.charAt(0) == temp.c) {
                return recursiveContain(temp.next, cadena.substring(1));
            } else {
                return recursiveContain(temp.alt, cadena);
            }
        }
    }
    
    private int countRecursivo(Info node) {
        if (node == null) {
            return 0;
        }
        if (node.c == '*') {
            return countRecursivo(node.alt) + 1;
        } else {
            return countRecursivo(node.next) + countRecursivo(node.alt);
        }
    }
}
