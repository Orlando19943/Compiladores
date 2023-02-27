package Automata;

import java.util.ArrayList;
import java.util.Map;

import Tree.Tree;

public class AFD implements Automata {

    private ArrayList<Estado> estados; // Conjunto de estados
    private Estado estadoInicial; // Estado inicial del automata
    private ArrayList<Estado> estadosDeAceptacion; // Conjunto de estados de aceptacion del automata
    private ArrayList<Transicion> transiciones;  //Transiciones del automata
    private ArrayList<String> alfabeto; // Alfabeto del automata

    private String expression; // Expresion regular
    private Map<Character, Integer> operators; // Operadores de la expresion regular
    private Tree tree; // Arbol de la expresion regular
    private int id; // Id de los estados

    private Character epsilon ='ε'; // Simbolo de epsilon

    public AFD() {
        this.estados = new ArrayList<Estado>();
        this.estadosDeAceptacion = new ArrayList<Estado>();
        this.transiciones = new ArrayList<Transicion>();
        this.alfabeto = new ArrayList<String>();
        this.id = 0;
    }

    public AFD(String expression, Map<Character, Integer> operators, Tree tree) {
        this.estados = new ArrayList<Estado>();
        this.estadosDeAceptacion = new ArrayList<Estado>();
        this.transiciones = new ArrayList<Transicion>();
        this.alfabeto = new ArrayList<String>();
        this.expression = expression;
        this.operators = operators;
        this.tree = tree;
        this.id = 0;
        defineAlphabet();
    }

    public AFD(String expression, Map<Character, Integer> operators, Tree tree, int id) {
        this.estados = new ArrayList<Estado>();
        this.estadosDeAceptacion = new ArrayList<Estado>();
        this.transiciones = new ArrayList<Transicion>();
        this.alfabeto = new ArrayList<String>();
        this.expression = expression;
        this.operators = operators;
        this.tree = tree;
        this.id = id;
        defineAlphabet();
    }

    public ArrayList<Estado> getEstados() {
        return estados;
    }

    public void setEstados(ArrayList<Estado> estados) {
        this.estados = estados;
    }

    public Estado getEstadoInicial() {
        return estadoInicial;
    }

    public void setEstadoInicial(Estado estadoInicial) {
        this.estadoInicial = estadoInicial;
    }

    public ArrayList<Estado> getEstadosDeAceptacion() {
        return estadosDeAceptacion;
    }

    public void setEstadosDeAceptacion(ArrayList<Estado> estadosDeAceptacion) {
        this.estadosDeAceptacion = estadosDeAceptacion;
    }

    public ArrayList<Transicion> getTransiciones() {
        return transiciones;
    }

    public void setTransiciones(ArrayList<Transicion> transiciones) {
        this.transiciones = transiciones;
    }

    public ArrayList<String> getAlfabeto() {
        return alfabeto;
    }

    public void setAlfabeto(ArrayList<String> alfabeto) {
        this.alfabeto = alfabeto;
    }

    public String getExpression() {
        return expression;
    }

    public void setExpression(String expression) {
        this.expression = expression;
    }

    public Map<Character, Integer> getOperators() {
        return operators;
    }

    public void setOperators(Map<Character, Integer> operators) {
        this.operators = operators;
    }

    public Tree getTree() {
        return tree;
    }

    public void setTree(Tree tree) {
        this.tree = tree;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }



    @Override
    public void defineAlphabet() {
        for (Character c: expression.toCharArray()) {
            if (!operators.containsKey(c)) {
                if (!alfabeto.contains(c.toString())) {
                    alfabeto.add(c.toString());
                }
            }
        }
    }

    @Override
    public void createAutomata() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'createAutomata'");
    }

    @Override
    public void operate(Character operator, String symbols, AFN automatas) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'operate'");
    }
    
}
