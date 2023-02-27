package InfixToPostfix;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

public class InfixtoPostfix {
    private static Map<Character, Integer> operators;
    private static Stack <Character> numbers;
    private static Stack <Character> stackOperators;
    private static ArrayList <String> expressions;

    /*
     * Constructor sin parametros
     */
    public InfixtoPostfix() {
        operators = new HashMap<Character, Integer>();
    }

    /*
     * Constructor con parametros
     * @param ope String con los operadores y su precedencia
     */
    public InfixtoPostfix(Map<Character, Integer> ope, ArrayList <String> exp) {
        operators = ope;
        expressions = exp;
        infixToPostfix();
    }

    /*
     * Set para el mapa de operadores
     */
    public void setoperators(String ope) {
        for (int i = 0; i < ope.length()-1; i++) {
            operators.put(ope.charAt(i), Character.getNumericValue(ope.charAt(i + 1)));
            i++;
        }
    }
    /*
     * Set para las expresiones a ser evaluadas
     */
    public void setexpression(ArrayList <String> exp) {
        expressions = exp;
    }
    /*
     * get para las expresiones a ser evaluadas
     */
    public ArrayList <String> getexpression() {
        return expressions;
    }
    /*
     * get para las expresiones a ser evaluadas
     */
    public Stack<Character> getnumbers() {
        return numbers;
    }
    /*
     * get para las expresiones a ser evaluadas
     */
    public Stack<Character> getstackOperators() {
        return stackOperators;
    }

    /*
     * Get para el mapa de operadores
     * @return Map con los operadores y su precedencia
     */
    public Map<Character, Integer> getoperators() {
        return operators;
    }

    /*  
     * Convierte una expresion de infix a postfix
     * @param expressions ArrayList con las expresiones
     * @return Stack con la expresion postfix
     */
    public void infixToPostfix(){
        numbers = new Stack<Character>();
        stackOperators = new Stack<Character>();
        // Operador para indicar el inicio de cada expresion
        stackOperators.add('@');
        // Recorre cada expresion
        for (String expression : expressions) {
            // Recorre cada caracter de la expresion
            for (int i = 0; i < expression.length(); i++) {
                // Si es un operador
                if (operators.containsKey(expression.charAt(i))){
                    // Si es un parentesis
                    if (operators.get(expression.charAt(i))>= operators.get('(')){
                        // Si es un parentesis abierto, lo agrega a la pila de operators
                        if (expression.charAt(i) == '('){
                            stackOperators.push(expression.charAt(i));
                        // Si es el parentesis cerrado, saca todos los operators hasta encontrar el primer parentesis abierto
                        } else if (expression.charAt(i) == ')'){
                            for (int j = stackOperators.size()-1; j > 0; j--){
                                if (stackOperators.get(j) == '('){
                                    stackOperators.remove(j);
                                    break;
                                }
                                numbers.push(stackOperators.pop());
                            }
                        }
                    // Si es un operador +-*/
                    } else if (operators.get(expression.charAt(i)) <= operators.get('(')){
                        // Si la precedencia del operador es menor o igual a la procedencia del operador en la cima de la pila, lo agrega a la pila de numeros
                        for (int j = stackOperators.size()-1; j > 0; j--){
                            if (operators.get(expression.charAt(i)) <= operators.get(stackOperators.get(j)) && stackOperators.get(j) != '(' && stackOperators.get(j) != ')'){
                                numbers.push(stackOperators.get(j));
                                stackOperators.remove(j);
                                break;
                            } else if (stackOperators.get(j) == '('){
                                break;
                            }
                        }
                        stackOperators.push(expression.charAt(i));
                    }
                // Si es un numero
                } else {
                    numbers.push(expression.charAt(i));
                }
            }
            while (stackOperators.size() > 1 ){
                if (stackOperators.peek() != '('&& stackOperators.peek() != '@'){
                    numbers.push(stackOperators.peek());
                }
                stackOperators.pop();
            }
            break;
        }
    }
}
