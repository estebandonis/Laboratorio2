/**
 * Clase para fabricar bloques
 */

public class Bloque {
    private boolean ocupado;
    private Programa programa;

    /**
     * constructor de la clase
     */
    Bloque (){
        ocupado = false;
    }

    /**
     * Obtenemos el valor de la variable ocupado
     * @return ocupado
     */
    public boolean getOcupado() {
        return ocupado;
    }

    /**
     * Definimos el valor de la variable ocupado
     * @param ocupado
     */
    public void setOcupado(boolean ocupado) {
        this.ocupado = ocupado;
    }

    /**
     * Obtenemos el programa ocupado por el bloque
     * @return programa
     */
    public Programa getPrograma() {
        return programa;
    }

    /**
     * Definimos el programa que va a ocupar el bloque
     * @param programa programa que va a ocupar el bloque
     */
    public void setPrograma(Programa programa) {
        this.programa = programa;
    }
}
