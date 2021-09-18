/**
 * Es el programa donde se maneja toda la logica
 */

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Modelo {
    private ArrayList<Bloque> RAM = new ArrayList<Bloque>();
    private ArrayList<Programa> cola = new ArrayList<Programa>();
    
    /**
     * Nos sirve para crear la ram al inicio
     * @param cantidadBloques es la cantidad de bloques que tendra nuestra ram
     */
    public void crearRAM(int cantidadBloques){
        for (int i = 0; i < cantidadBloques; i++){
            Bloque block = new Bloque();
            RAM.add(block);
        }
    }

    /**
     * Nos sirve para expandir la ram, en caso sea tipo ddr
     */
    public void expandDDRRAM(){
        for (int i = 0; i < RAM.size(); i++){
            Bloque block = new Bloque();
            RAM.add(block);
        }
    }

    /**
     * Nos sirve para agregar programas a la ram
     * @param program es el programa que queremos agregar
     */
    public void agregarPrograma(Programa program){
        int cantidadBloques = convertirMBBloques(program.getCantidadMB());
        int contador = 0;
        for (int i = 0; i < RAM.size(); i++){//Recorremos toda la ram para ir agregando en los espacios libres al programa
            Bloque block = RAM.get(i);
            String nombre = program.getNombre();
            int cantidadMB = program.getCantidadMB();
            int duracion = program.getDuracion();
            Programa programa = new Programa(nombre, cantidadMB, duracion);
            if (contador == cantidadBloques){//Verificamos si ya hemos llenado todos los bloques que necesita
                break;
            }
            else{//LLenamos el bloque actual
                if (block.getOcupado() == false){
                    block.setOcupado(true);
                    block.setPrograma(programa);
                    RAM.set(i, block);
                    contador += 1;
                }
            }
        }
    }

    /**
     * Nos sirve para agregar los programas a la cola
     * @param program el programa que vamos a agregar a la cola
     */
    public void agregarProgramaCola(Programa program){
        cola.add(program);
    }

    /**
     * Nos sirve para convertir de GB a bloques o espacios de ram
     * @param cantidadGB la cantidad que vamos a convertir
     * @return total
     */
    public int convertirGBBloques(int cantidadGB){
        double cantidadGBFloat = (double) cantidadGB;
        int total = (int) ((cantidadGBFloat*1024)/64);
        return total;
    }

    /**
     * Nos sirve para convertir de bloques a GB
     * @param cantidadBloques cantidad a convertir
     * @return total
     */
    public int convertirBloquesGB(int cantidadBloques){
        float cantidadBloquesFloat = Float.intBitsToFloat(cantidadBloques);
        int total = Float.floatToIntBits((cantidadBloquesFloat/1024)*64);
        return total;
    }

    /**
     * Nos sirve para convertir de MB a bloques o espacios de ram
     * @param cantidadMB Cantidad a convertir de MB
     * @return total
     */
    public int convertirMBBloques(int cantidadMB){
        float cantidadMBFloat = Float.intBitsToFloat(cantidadMB);
        int total = Float.floatToIntBits(cantidadMBFloat/64);
        return total;
    }

    /**
     * Asignamos los datos de un texto a un array list
     * @return
     */
    public ArrayList<Programa> obtenerDatos(){
        String pathActual = "programs.csv";//Establecemos la ruta
        String line = "";

        ArrayList<Programa> programas = new ArrayList<Programa>();

        try {
            BufferedReader br = new BufferedReader(new FileReader(pathActual));//Leemos el documento

            while((line = br.readLine()) != null){//Recorremos todas las lineas
                String[] values = line.split(",");
                Programa program = new Programa(values[0], Integer.parseInt(values[1]), Integer.parseInt(values[2]));
                programas.add(program);
            } 
            br.close();         
            return programas;
        } catch (FileNotFoundException e) {
            System.out.println("No funciono");
        } catch (IOException e) {
            System.out.println("No funciono");
        }
        return programas;
    }

    /**
     * Obtenemos el arraylist de ram
     * @return RAM
     */
    public ArrayList<Bloque> getRAM() {
        return RAM;
    }

    /**
     * Obtenemos el arraylist de cola
     * @return cola
     */
    public ArrayList<Programa> getCola() {
        return cola;
    }

    /**
     * Obtenemos la cantidad de ram disponible en bloques
     * @return contador
     */
    public int totalRAMDisponible(){
        int contador = 0;
        for (int i = 0; i < RAM.size(); i++){
            Bloque block = RAM.get(i);
            if (block.getOcupado() == false){
                contador += 1;
            }
        }
        return contador;
    }

    /**
     * Obtenemos la cantidad de ram en uso
     * @return contador
     */
    public int totaRAMUso(){
        int contador = 0;
        for (int i = 0; i < RAM.size(); i++){
            Bloque block = RAM.get(i);
            if (block.getOcupado() == true){
                contador += 1;
            }
        }
        return contador;
    }

    /**
     * Realizamos un ciclo de reloj para tipo de ram sdr
     */
    public void cicloRelojSDR(){
        for (int i = 0; i < RAM.size(); i++){//recorremos la ram para eliminar y avanzar la duracion
            Bloque block = RAM.get(i);
            if (block.getOcupado() == true){
                Programa program = block.getPrograma();
                int duracion = program.getDuracion();
                if (duracion == 0){
                    block.setOcupado(false);
                    block.setPrograma(null);
                }
                else{
                    program.setDuracion(duracion -= 1);
                    block.setPrograma(program);
                    RAM.set(i, block);
                }
            }
        }

        int contadorBloquesDisponibles = 0;

        for (int a = 0; a < RAM.size(); a++){//Contamos cuantos bloques disponibles hay
            Bloque block = RAM.get(a);
            if (block.getOcupado() == false){
                contadorBloquesDisponibles += 1;
            }
        }

        for (int i = 0; i < cola.size(); i++){//Verificamos si el programa cabe en la ram segun los espacios disponibles
            Programa program = cola.get(i);
            int cantidadBloques = convertirMBBloques(program.getCantidadMB());
            if (cantidadBloques < contadorBloquesDisponibles){
                agregarPrograma(program);
                cola.remove(i);
            }
        } 
    }

    /**
     * Ciclo de reloj para un tipo de ram ddr
     */
    public void cicloRelojDDR(){
        for (int i = 0; i < RAM.size(); i++){
            Bloque block = RAM.get(i);
            if (block.getOcupado() == true){
                Programa program = block.getPrograma();
                int duracion = program.getDuracion();
                if (duracion == 0){
                    block.setOcupado(false);
                    block.setPrograma(null);
                }
                else{
                    program.setDuracion(duracion -= 1);
                    block.setPrograma(program);
                    RAM.set(i, block);
                }
            }
        }

        int contadorBloquesDisponibles = 0;

        for (int a = 0; a < RAM.size(); a++){
            Bloque block = RAM.get(a);
            if (block.getOcupado() == false){
                contadorBloquesDisponibles += 1;
            }
        }

        if (contadorBloquesDisponibles < 10){
            expandDDRRAM();
        }

        for (int i = 0; i < cola.size(); i++){
            Programa program = cola.get(i);
            int cantidadBloques = convertirMBBloques(program.getCantidadMB());
            if (cantidadBloques < contadorBloquesDisponibles){
                agregarPrograma(program);
                cola.remove(i);
            }
        } 
    }
}