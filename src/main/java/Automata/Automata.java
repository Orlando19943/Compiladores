package Automata;

public interface Automata {

    public void defineAlphabet();

    public void createAutomata();

    public void operate(Character operator, String symbols, AFN automatas);
    
}
