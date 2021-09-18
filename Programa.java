public class Programa {
    private String nombre;
    private int cantidadMB;
    private int duracion;

    Programa(){
        nombre = "";
        cantidadMB = 0;
        duracion = 0;
    }

    Programa(String nombre, int cantidadMB, int duracion){
        this.nombre = nombre;
        this.cantidadMB = cantidadMB;
        this.duracion = duracion;
    }

    public String getNombre() {
        return nombre;
    }
    
    public int getCantidadMB() {
        return cantidadMB;
    }

    public int getDuracion() {
        return duracion;
    }

    public void setDuracion(int duracion) {
        this.duracion = duracion;
    }
}
