package Automata;

public class Estado {

    private Boolean start; // Si el estado es estado inicial

    private Boolean end; //Si el estado es estado final o de aceptacion

    private int id; //numero del estado

    /*
     * Constructor con parametros
     * @param start Estado inicial
     * @param end Estado de aceptacion
     */
    public Estado(Boolean start, Boolean end, int id) {
        this.start = start;
        this.end = end;
        this.id = id;
    }
    

    /*
     * Set del estado inicial
     * @param start Estado inicial
     */
    public void setStart(Boolean start) {
        this.start = start;
    }
    
    /*
     * Get del estado inicial
     * @return start Estado inicial
     */
    public Boolean getStart() {
        return start;
    }

    /*
     * Set del estado de aceptacion
     * @param end Estado de aceptacion
     */
    public void setEnd(Boolean end) {
        this.end = end;
    }

    /*
     * Get del estado de aceptacion
     * @return end Estado de aceptacion
     */
    public Boolean getEnd() {
        return end;
    }

    /*
     * Set del numero del estado
     * @param id Numero del estado
     */
    public void setId(int id) {
        this.id = id;
    }

    /*
     * Get del numero del estado
     * @return id Numero del estado
     */
    public int getId() {
        return id;
    }


}
