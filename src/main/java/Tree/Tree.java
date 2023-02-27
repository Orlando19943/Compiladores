package Tree;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

public class Tree {
    private Node root;
    private Stack<Node> tree;
     /*
      * Constructor sin parametros
      */
    public Tree() {
        root = null;
        tree = new Stack<Node>();
    }

    /* 
     * Set de rot
     * @param root Nodo raiz
     */
    public void setRoot(Node root) {
        this.root = root;
    }

    /*
     * Get de root
     * @return root Nodo raiz
     */
    public Node getRoot() {
        return root;
    }

    /*
     * Set de tree
     * @param tree Stack de los nodos
     */
    public void setTree(Stack<Node> tree) {
        this.tree = tree;
    }

    /*
     * Get de tree
     * @return tree Stack de los nodos
     */
    public Stack<Node> getTree() {
        return tree;
    }

    /* 
     * Metodo para crear el arbol
     * @param expression Stack con la expresion en postfijo
     * @param operators Map con los operadores y su precedencia
     */
    public void createTree(Stack<Character> expression, Map<Character, Integer> operators) {
        for (int i = 0 ; i < expression.size(); i++) {
            if (operators.containsKey(expression.get(i))) {
                if (tree.size() == 1){
                    Node left = tree.pop();
                    tree.push(new Node(expression.get(i).toString(), left));
                } else {
                    Node left = tree.pop();
                    Node right = tree.pop();
                    tree.push(new Node(expression.get(i).toString(), left, right));
                }
            }
            else{
                tree.push(new Node(expression.get(i).toString()));
            }
        }
        root = tree.pop(); 
    }

    /*
     * Metodo para imprimir el arbol
     * @param root Nodo raiz
     */
    public String showTree(Node root) {
        if (root != null) {
            return (root.getLeafs(root));
        } else {
            return "";
        }
    }

    
}
