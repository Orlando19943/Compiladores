package Expression;

import java.util.ArrayList;
import java.util.Map;

public class Error {
    
    private String descripcion;
    private int position;
    private Boolean isCorrect;

    public static final String ANSI_RESET = "\u001B[0m";
    // Declaring the background color
    public static final String ANSI_RED_BACKGROUND = "\u001B[41m";

    public Error() {
        this.descripcion = "";
        this.position = 0;
        this.isCorrect = true;
    }

    public Error(String descripcion, int position, Boolean isCorrect) {
        this.descripcion = descripcion;
        this.position = position;
        this.isCorrect = isCorrect;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public Boolean getIsCorrect() {
        return isCorrect;
    }

    public void setIsCorrect(Boolean isCorrect) {
        this.isCorrect = isCorrect;
    }

    public String toString() {
        return "Error [" + descripcion + ", position=" + position + "]";
    }

    public void isEmpthy (String expression){
        if (expression.length() == 0) {
            this.descripcion = "Expresion vacia";
            this.isCorrect = false;
        }
    }

    public void checkParentesis (String expression){
        int p = 0;
        String showError = "";
        ArrayList<Integer> parentesisCierre = new ArrayList<Integer>();
        ArrayList<Integer> parentesisApertura = new ArrayList<Integer>();
        for (int i = 0; i < expression.length(); i++) {
            if (expression.charAt(i) == '(') {
                parentesisApertura.add(i);
                p++;
            }
            else if (expression.charAt(i) == ')') {
                parentesisCierre.add(i);
                p--;
            }
            if (p < 0) {
                this.position = parentesisCierre.get(parentesisCierre.size()-1);
                showError =expression.substring(0, this.position)+ ANSI_RED_BACKGROUND+expression.substring(this.position, this.position+1)+ANSI_RESET+expression.substring(this.position+1, expression.length());
                this.descripcion = showError+" <- Parentesis de cierre sin apertura";
                this.isCorrect = false;
                break;
            }
            else if (p > 0 && i == expression.length()-1) {
                this.position = parentesisApertura.get(0);
                showError =expression.substring(0, this.position)+ ANSI_RED_BACKGROUND+expression.substring(this.position, this.position+1)+ANSI_RESET+expression.substring(this.position+1, expression.length());
                this.descripcion = showError+" <- Parentesis de apertura sin cierre";
                this.position = parentesisApertura.get(parentesisApertura.size()-1);
                this.isCorrect = false;
                break;
            }
        }
    }

    public Boolean subOperation(int start, int end, String expression, Map<Character, Integer> operators, String originalExpression){
        String subExpression = expression.substring(start, end);
        ArrayList<Integer> positions = new ArrayList<Integer>();
        int newStart = 0;
        int newEnd = 0;
        for (int i = 0; i < subExpression.length(); i++) {
            if (subExpression.charAt(i) == '(') {
                newStart = i;
                positions.add(newStart);
            } else if (subExpression.charAt(i) == ')') {
                newEnd = i;
                if (!subOperation(newStart+1, newEnd, subExpression, operators, originalExpression)){
                    return false;
                } 
                positions.remove(positions.size()-1);
                if (positions.size() > 0) {
                    newStart = positions.get(positions.size()-1);
                }
            } 
        }
        String showError = "";
        Boolean token = false;
        for (int i = 0; i < subExpression.length(); i++) {
            if (subExpression.charAt(i) != '+' && subExpression.charAt(i) != '*'&& subExpression.charAt(i) != '|') {
                token = true;
            } else if(operators.containsKey(subExpression.charAt(i)) && !token) {
                this.position = i+start;
                showError =originalExpression.substring(0, this.position)+ ANSI_RED_BACKGROUND+originalExpression.substring(this.position, this.position+1)+ANSI_RESET+originalExpression.substring(this.position+1, originalExpression.length());
                this.descripcion = showError+" <- Error de sintaxis";
                this.isCorrect = false;
                return false;
            }
            if (subExpression.charAt(i) == '|' ) {
                if (i != subExpression.length()-1) {
                    if (subExpression.charAt(i+1) == '|'  || subExpression.charAt(i+1) == '*'|| subExpression.charAt(i+1) == '+'|| subExpression.charAt(i+1) == '?' ) {
                        this.position = i+start;
                        showError =originalExpression.substring(0, this.position)+ ANSI_RED_BACKGROUND+originalExpression.substring(this.position, this.position+1)+ANSI_RESET+originalExpression.substring(this.position+1, subExpression.length());
                        this.descripcion = showError+" <- Error de sintaxis";
                        this.isCorrect = false;
                        return false;
                    } 
                } else {
                    this.position = i+start;
                    showError =originalExpression.substring(0, this.position)+ ANSI_RED_BACKGROUND+originalExpression.substring(this.position, this.position+1)+ANSI_RESET;
                    this.descripcion = showError+" <- Error de sintaxis";
                    this.isCorrect = false;
                    return false;
                }
                if (subExpression.charAt(i-1) == ')') {
                    if (subExpression.charAt(i-2) == '(') {
                        this.position = i+start;
                        showError =originalExpression.substring(0, this.position)+ ANSI_RED_BACKGROUND+originalExpression.substring(this.position, this.position+1)+ANSI_RESET+originalExpression.substring(this.position+1, subExpression.length());
                        this.descripcion = showError+" <- Error de sintaxis";
                        this.isCorrect = false;
                        return false;
                    }
                }
            }
        }

        return true;
    }

}
