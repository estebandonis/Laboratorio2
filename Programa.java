/**
 * Clase para fabricar programas
 */

public class Programa {
    private String nombre;
    private int cantidadMB;
    private int duracion;

    /**
     * Constructor de la clase
     */
    Programa(){
        nombre = "";
        cantidadMB = 0;
        duracion = 0;
    }

    /**
     * Constructor (overloading) de la clase
     * @param nombre
     * @param cantidadMB
     * @param duracion
     */
    Programa(String nombre, int cantidadMB, int duracion){
        this.nombre = nombre;
        this.cantidadMB = cantidadMB;
        this.duracion = duracion;
    }

    /**
     * obtenemos el valor de nombre
     * @return nombre
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Obtenemos el valor de cantidadMB
     * @return cantidadMB
     */
    public int getCantidadMB() {
        return cantidadMB;
    }

    /**
     * Obtenemos el valor de duracion
     * @return duracion
     */
    public int getDuracion() {
        return duracion;
    }

    /**
     * Definimos el valor de duracion
     * @param duracion nuevo valor de duracion
     */
    public void setDuracion(int duracion) {
        this.duracion = duracion;
    }
}
