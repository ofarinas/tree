/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tree;

/**
 *
 * @author ENTRAR
 */
public class Aplication {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
      ExtendedTrie extendedTrie=new ExtendedTrie();
      extendedTrie.insert("casa");
      extendedTrie.insert("capa");
      extendedTrie.insert("cara");
      extendedTrie.insert("hola");
      extendedTrie.insert("que ");
        //System.out.println(extendedTrie.contain("cara"));
        System.out.println(extendedTrie.count());
    }
}
