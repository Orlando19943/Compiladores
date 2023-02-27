package com.example;

import java.io.PrintStream;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

import Automata.AFN;
import Automata.Estado;
import Automata.Transicion;
import Expression.Correction;
import Ilustrations.Mermaid;
import InfixToPostfix.InfixtoPostfix;
import ReadFiles.ReadFile;
import ReadFiles.WriteFile;
import Tree.Tree;

//ε
public class App 
{
    static Map<Character, Integer> operadores = new HashMap<Character, Integer>(){{
        put('|', 1);
        put('.', 2);
        put('*', 3);
        put('+', 3);
        put('?', 3);
        put(')', 5);
        put('(', 5);
    }};

    public static void main(String[] args) {
        ArrayList<String> expresiones = new ArrayList<String>();
        ReadFile file = new ReadFile("./src/main/java/utils/expression.txt");
        for (String line : file.getLines()) {
            Correction expression = new Correction(line, operadores);
            if (expression.getErrores().getIsCorrect()){
                expresiones.add(expression.getExpression());
            } else {
                System.out.println(expression.getErrores().toString());
            }
        }
        if (expresiones.size() > 0) {
            InfixtoPostfix intopost = new InfixtoPostfix(operadores, expresiones);
            Tree tree = new Tree();
            tree.createTree(intopost.getnumbers(), operadores);
            AFN automata = new AFN(tree.showTree(tree.getRoot()), operadores, tree);
            automata.defineAlphabet();
            automata.createAutomata();
            // TODO mover esto a otro lugar
            String s = "";
            String a = "";
            for(Transicion transicion : automata.getTransiciones()) {
                s += transicion.toString() + "\n";
            }
            for (Estado e: automata.getEstados()) {
                if (e.getEnd()) {
                    s += "style "+ e.getId() +" stroke:#84a98c,stroke-width:4px\n";
                } else if (e.getStart()) {
                    s += "style "+ e.getId() +" fill:#84a98c\n";
                }
            }
            for (String line : expresiones) {
                a += line;
            }
            Mermaid mermaid = new Mermaid(s, "./src/main/java/utils/automata.html", a);
            System.out.println("Automata generado con exito");
        } else {
            System.out.println("No se pudo generar el automata");
        }
    }
}
