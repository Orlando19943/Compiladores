package Tree;

public class Node {
    private Node left;
    private Node right;
    private String value;

    /*
     * Constructor sin parametros
     */
    public Node(String value) {
        this.value = value;
    }

    /*
     * Constructor con parametros para nodos con dos hijos
     * @param value Valor del nodo
     * @param left Nodo izquierdo
     * @param right Nodo derecho
     */
    public Node(String value, Node left, Node right) {
        this.left = left;
        this.right = right;
        this.value = value;
    }
    /*
     * Constructor con parametros para nodos con un hijo (izquierdo)
     * @param value Valor del nodo
     * @param left Nodo izquierdo
     */
    public Node(String value, Node left) {
        this.left = left;
        this.value = value;
    }

    /*
     * Set del nodo izquierdo
     * @param left Nodo izquierdo
     */
    public void setLeft(Node left) {
        this.left = left;
    }
    
    /*
     * Set del nodo derecho
     * @param right Nodo derecho
     */
    public void setRight(Node right) {
        this.right = right;
    }
    
    /*
     * Get del nodo izquierdo
     * @return left Nodo izquierdo
     */
    public Node getLeft() {
        return left;
    }
    
    /*
     * Get del nodo derecho
     * @return right Nodo derecho
     */
    public Node getRight() {
        return right;
    }

    /* 
     * Get del valor del nodo
     * @return value Valor del nodo
     */
    public String getValue() {
        return value;
    }

    /*
     * Metodo para obtener un nodo y sus hijos 
     * @param node Nodo
     * @return String con el nodo y sus hijos
     */
    public String getLeafs(Node node) {
        if (node == null) {
            return "";
        }
        return getLeafs(node.getRight())+ getLeafs(node.getLeft()) +node.getValue();
    }

}
