package Expression;

import java.util.ArrayList;
import java.util.Map;

public class Correction {
    private String expression;
    private Error errores;
    private Map<Character, Integer> operadores;

    public Correction(String expression, Map<Character, Integer> operadores) {
        this.expression = expression;
        this.operadores = operadores;
        this.errores = new Error();
        this.errores.isEmpthy(expression);
        if (this.errores.getIsCorrect()) {
            whiteSpace();
            this.errores.checkParentesis(this.expression);
            deleteEmpthyParenthesis();
            this.errores.subOperation(0, this.expression.length(), this.expression, this.operadores, this.expression);
            if (this.errores.getIsCorrect()) {
                changeSigns();
                addConcatenation();
            }

        }
    }

    public Error getErrores() {
        return errores;
    }

    public String getExpression() {
        return expression;
    }

    public void setExpression(String expression) {
        this.expression = expression;
    }

    public void whiteSpace() {
        this.expression = this.expression.replaceAll("\\s+", "");
    }
    public String addCharacter (String text, int i, Character character){
        return text.substring(0, i+1) + character + text.substring(i+1);
    }

    public void deleteEmpthyParenthesis () {
        for (int i = 0; i < this.expression.length(); i++) {
            if (this.expression.charAt(i) == '(') {
                if (this.expression.charAt(i+1) == ')') {
                    this.expression = this.expression.substring(0, i) + this.expression.substring(i+2);
                }
            }
        }
    }

    public void changeSigns (){
        int p = 0;
        for (int i = 0; i < this.expression.length(); i++) {
            if (this.expression.charAt(i) == '+') {
                if (this.expression.charAt(i-1) == ')') {
                    for (int j = i-1; j >= 0; j--) {
                        if (this.expression.charAt(j) == ')') {
                            p++;
                        }
                        else if (this.expression.charAt(j) == '(') {
                            if (p == 1) {
                                this.expression = this.expression.substring(0, j) + this.expression.substring(j, i)+this.expression.substring(j, i)+ '*' + this.expression.substring(i+1);
                                break;
                            }
                            else {
                                p--;
                            }
                        }
                    }
                } else {
                    this.expression = this.expression.substring(0, i-1) + this.expression.substring(i-1, i)+this.expression.substring(i-1, i)+ '*' + this.expression.substring(i+1);
                }
            }
        }
    }

    public void addConcatenation() {
        for (int i = 0; i < this.expression.length()-1; i++) {
            if (!this.operadores.containsKey(this.expression.charAt(i))){
                // Agregando . entre dos caracteres
                if (!this.operadores.containsKey(this.expression.charAt(i+1))) {
                    this.expression = addCharacter(this.expression, i, '.');
                }
                // Agregando . entre caracter y parentesis (
                else if (this.expression.charAt(i+1) == '(') {
                    this.expression = addCharacter(this.expression, i, '.');
                }
            }
            else if (this.operadores.containsKey(this.expression.charAt(i))){
                // Agregando . entre caracter y parentesis (
                if (this.expression.charAt(i) == ')') {
                    if (this.expression.charAt(i+1) == '(') {
                        this.expression = addCharacter(this.expression, i, '.');
                    }
                    else if (!this.operadores.containsKey(this.expression.charAt(i+1))) {
                        this.expression = addCharacter(this.expression, i, '.');
                    }
                }
                if (this.expression.charAt(i) == '*') {
                    if (this.expression.charAt(i+1) == '(') {
                        this.expression = addCharacter(this.expression, i, '.');
                    }
                    else if (!this.operadores.containsKey(this.expression.charAt(i+1))) {
                        this.expression = addCharacter(this.expression, i, '.');
                    }
                }
                if (this.expression.charAt(i) == '?') {
                    if (this.expression.charAt(i+1) == '(') {
                        this.expression = addCharacter(this.expression, i, '.');
                    }
                    else if (!this.operadores.containsKey(this.expression.charAt(i+1))) {
                        this.expression = addCharacter(this.expression, i, '.');
                    }
                }
                if (this.expression.charAt(i) == '+') {
                    if (this.expression.charAt(i+1) == '(') {
                        this.expression = addCharacter(this.expression, i, '.');
                    }
                    else if (!this.operadores.containsKey(this.expression.charAt(i+1))) {
                        this.expression = addCharacter(this.expression, i, '.');
                    }
                }
            }
            }    
        }
}
