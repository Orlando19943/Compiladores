package Automata;

public class Transicion {
    private Estado estadoInicial;
    private Estado estadoFinal;
    private Character simbolo;

    public Transicion(Estado estadoInicial, Estado estadoFinal, Character simbolo) {
        this.estadoInicial = estadoInicial;
        this.estadoFinal = estadoFinal;
        this.simbolo = simbolo;
    }

    public void setEstadoInicial(Estado estadoInicial) {
        this.estadoInicial = estadoInicial;
    }

    public Estado getEstadoInicial() {
        return estadoInicial;
    }

    public void setEstadoFinal(Estado estadoFinal) {
        this.estadoFinal = estadoFinal;
    }

    public Estado getEstadoFinal() {
        return estadoFinal;
    }

    public void setSimbolo(Character simbolo) {
        this.simbolo = simbolo;
    }

    public Character getSimbolo() {
        return simbolo;
    }

    public String toString() {
        return estadoInicial.getId() + "--" + simbolo + "-->" + estadoFinal.getId();
    }
}
