package Automata;

import java.util.ArrayList;
import java.util.Map;
import java.util.Stack;

import Tree.Tree;

public class AFN implements Automata, Cloneable  {

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

    /*
     * Constructor 
     */
    public AFN(String expression, Map<Character, Integer> operators, Tree tree)  {
        estados = new ArrayList<Estado>();
        estadoInicial = new Estado(true, false, 0);
        estadosDeAceptacion = new ArrayList<Estado>();
        transiciones = new ArrayList<Transicion>();
        alfabeto = new ArrayList<String>();
        this.expression = expression;
        this.operators = operators;
        this.tree = tree;
        this.id = 0;
        defineAlphabet();
    }


    public AFN (AFN object) {
        this.setEstados(object.getEstados());
        this.setEstadoInicial(object.getEstadoInicial());
        this.setEstadosDeAceptacion(object.getEstadosDeAceptacion());
        this.setTransiciones(object.getTransiciones());
        this.setAlfabeto(object.getAlfabeto());
        this.setExpression(object.getExpression());
        this.setOperators(object.getOperators());
        this.setTree(object.getTree());
        this.setId(object.getId());
    }
    public AFN(String expression, Map<Character, Integer> operators, Tree tree, int id)  {
        estados = new ArrayList<Estado>();
        estadoInicial = new Estado(true, false, 0);
        estadosDeAceptacion = new ArrayList<Estado>();
        transiciones = new ArrayList<Transicion>();
        alfabeto = new ArrayList<String>();
        this.expression = expression;
        this.operators = operators;
        this.tree = tree;
        this.id = id;
        defineAlphabet();
    }

    public void setEstados(ArrayList<Estado> estados) {
        this.estados = estados;
    }


    public ArrayList<Estado> getEstados() {
        return estados;
    }


    public void setEstadoInicial(Estado estadoInicial) {
        this.estadoInicial = estadoInicial;
    }


    public Estado getEstadoInicial() {
        return estadoInicial;
    }


    public void setEstadosDeAceptacion(ArrayList<Estado> estadosDeAceptacion) {
        this.estadosDeAceptacion = estadosDeAceptacion;
    }


    public ArrayList<Estado> getEstadosDeAceptacion() {
        return estadosDeAceptacion;
    }


    public void setTransiciones(ArrayList<Transicion> transiciones) {
        this.transiciones = transiciones;
    }


    public ArrayList<Transicion> getTransiciones() {
        return transiciones;
    }

    public void setExpression(String expression) {
        this.expression = expression;
    }

    public String getExpression() {
        return expression;
    }

    public void setOperators(Map<Character, Integer> operators) {
        this.operators = operators;
    }

    public Map<Character, Integer> getOperators() {
        return operators;
    }

    public void setTree(Tree tree) {
        this.tree = tree;
    }

    public Tree getTree() {
        return tree;
    }


    public void setAlfabeto(ArrayList<String> alfabeto) {
        this.alfabeto = alfabeto;
    }


    public ArrayList<String> getAlfabeto() {
        return alfabeto;
    }

    public void setId(int id) {
        this.id = id;
    }
    public int getId() {
        return id;
    }

    public Transicion searchEstadoInicial() {
        for (Transicion t: transiciones){
            if (t.getEstadoInicial().getStart()) {
                return t;
            }
        }
        return null;
    }
    public Transicion searchEstadofinal() {
        for (Transicion t: transiciones){
            if (t.getEstadoInicial().getEnd()) {
                return t;
            }
        }
        return null;
    }

    public void increaseIdState() {
        for (Estado e: estados){
            e.setId(e.getId()+1);
        }
        id = estadosDeAceptacion.get(estadosDeAceptacion.size() - 1).getId();
    }
    public void increaseIdState(int i) {
        for (Estado e: estados){
            e.setId(i+1);
            i++;
        }
        id = estadosDeAceptacion.get(estadosDeAceptacion.size() - 1).getId();
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
	protected AFN clone() throws CloneNotSupportedException {
		return (AFN) super.clone();
	}

    @Override
    public void createAutomata() {
        Stack<AFN> automatas = new Stack<AFN>();
        AFN a = new AFN(expression, operators, tree);
        int i = 0;
        for (Character c: expression.toCharArray()) {
            if (!operators.containsKey(c)){
                automatas.add(new AFN(c.toString(), operators, tree, i));
                automatas.peek().operate(c, expression, automatas.peek());
            } else {
                AFN automata = automatas.peek();
                switch (c) {
                    case '|':
                        automatas.pop();
                        automatas.peek().setId(i);
                        automatas.peek().operate(c, expression, automata);
                        break;
                    case '.':
                        automatas.pop();
                        automatas.peek().setId(i);
                        automatas.peek().operate(c, expression, automata);
                        break;
                    case '*':
                        automatas.peek().setId(i);
                        automatas.peek().operate(c, expression, automatas.peek());
                        break;
                    case '+':
                        automatas.peek().setId(i);
                        automata = new AFN(automatas.peek());
                        automatas.peek().operate(c, expression, automatas.peek());
                        //automatas.peek().operate(c, expression);
                        break;
                    case '?':
                        automatas.peek().setId(i);
                        automatas.peek().operate(c, expression, automata);
                        //automatas.peek().operate(c, expression);
                        break;
                }
            }
            i = automatas.peek().getId();
            //System.out.println("Automata: " + automatas.peek().getTransiciones());
        }
        for (AFN afn: automatas) {
            estados.addAll(afn.getEstados());
            transiciones.addAll(afn.getTransiciones());
            estadosDeAceptacion.add(afn.getEstadosDeAceptacion().get(0));
        }
        /* System.out.println("Automata final: " + transiciones);
        for (Estado e: estados){
            System.out.println("Estado: " + e.getId() + " Inicial: " + e.getStart() + " Final: " + e.getEnd());
        } */
    }

    @Override
    public void operate(Character operator, String symbols, AFN automatas) {
        ArrayList<Estado> estadosL = new ArrayList<Estado>();
        switch (operator){
            case '|':
                automatas.increaseIdState();
                id = automatas.getId();
                estadosL.add(new Estado(true, false, id+1));
                estadosL.add(new Estado(false, true, id+2));
                for (Transicion t: automatas.transiciones) {
                    transiciones.add(t);
                }
                automatas.getEstadoInicial().setStart(false);
                estadoInicial.setStart(false);
                getEstadosDeAceptacion().get(0).setEnd(false);
                automatas.getEstadosDeAceptacion().get(0).setEnd(false);
                transiciones.add(new Transicion(estadosL.get(0), automatas.getEstadoInicial(), epsilon.charValue()));
                transiciones.add(new Transicion(estadosL.get(0), getEstadoInicial(), epsilon.charValue()));
                transiciones.add(new Transicion(automatas.getEstadosDeAceptacion().get(0),estadosL.get(1), epsilon.charValue()));
                transiciones.add(new Transicion(getEstadosDeAceptacion().get(0),estadosL.get(1), epsilon.charValue()));
                estadosDeAceptacion.clear();
                estadoInicial = estadosL.get(0);
                estadosDeAceptacion.add(estadosL.get(1));
                setId(estadosDeAceptacion.get(0).getId());
                for (Estado e: automatas.getEstados()) {
                    estados.add(e);
                }
                for (Estado e: estadosL) {
                    estados.add(e);
                }
                break;
            case '.':
                //transiciones.add(new Transicion(estadosDeAceptacion.get(0), automatas.getTransiciones().get(0).getEstadoInicial(), 'e'));
                transiciones.get(transiciones.size()-1).getEstadoFinal().setId(automatas.estadoInicial.getId());
                for (Transicion t: automatas.transiciones) {
                    transiciones.add(t);
                }
                automatas.estadoInicial.setStart(false);
                estadosDeAceptacion.clear();
                estados.remove(estados.size()-1);
                for (Estado e: automatas.getEstados()) {
                    estados.add(e);
                    if (e.getStart()) {
                        estadoInicial = e;
                    } else if(e.getEnd()) {
                        estadosDeAceptacion.add(e);
                    }
                }
                id = estadosDeAceptacion.get(estadosDeAceptacion.size() - 1).getId();
                break;
            case '*':
                estadosL.add(new Estado(true, false, id+1));
                estadosL.add(estadoInicial);
                estadosDeAceptacion.get(0).setEnd(false);
                estadosL.add(estadosDeAceptacion.get(0));
                estadosL.add(new Estado(false, true, id+2));
                transiciones.add(new Transicion(estadosL.get(0),estadosL.get(1), epsilon.charValue()));
                transiciones.add(new Transicion(estadosL.get(0),estadosL.get(3), epsilon.charValue()));
                transiciones.add(new Transicion(estadosL.get(2),estadosL.get(1), epsilon.charValue()));
                transiciones.add(new Transicion(estadosL.get(2),estadosL.get(3), epsilon.charValue()));
                estadoInicial.setStart(false);
                estadoInicial = estadosL.get(0);
                estadosDeAceptacion.clear();
                estadosDeAceptacion.add(estadosL.get(3));
                estados.add(estadoInicial);         
                estados.add(estadosDeAceptacion.get(0));         
                id = estadosL.get(estadosL.size()-1).getId();
                break;
            case '+':
                break;
            case '?':
                estadosL.add(new Estado(true, false, id+1));
                estadosL.add(new Estado(false, false, id+2));
                estadosL.add(new Estado(false, false, id+3));
                estadosL.add(new Estado(false, true, id+4));
                transiciones.add(new Transicion(estadosL.get(0), estadosL.get(1), epsilon.charValue()));
                transiciones.add(new Transicion(estadosL.get(1), estadosL.get(2), epsilon.charValue()));
                transiciones.add(new Transicion(estadosL.get(2), estadosL.get(3), epsilon.charValue()));
                transiciones.add(new Transicion(estadosL.get(0), estadoInicial, epsilon.charValue()));
                transiciones.add(new Transicion(estadosDeAceptacion.get(0),estadosL.get(3), epsilon.charValue()));
                estadoInicial.setStart(false);
                estadosDeAceptacion.get(0).setEnd(false);
                estadosDeAceptacion.clear();
                for (Estado estado: estadosL) {
                    estados.add(estado);
                    if (estado.getStart()) {
                        estadoInicial = estado;
                    } else if(estado.getEnd()) {
                        estadosDeAceptacion.add(estado);
                    }
                } 
                id = estadosL.get(estadosL.size()-1).getId();
                break;
            default:
                estadosL.add(new Estado(true, false, id+1));
                estadosL.add(new Estado(false, true, id+2));
                for (Estado estado: estadosL) {
                    estados.add(estado);
                    if (estado.getStart()) {
                        estadoInicial = estado;
                    } else if(estado.getEnd()) {
                        estadosDeAceptacion.add(estado);
                    }
                } 
                transiciones.add(new Transicion(estadosL.get(0), estadosL.get(1), operator));
                id = estadosL.get(estadosL.size()-1).getId();
                break;
        }
    }

    
}
