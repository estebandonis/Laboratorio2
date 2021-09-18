/**
 * Programa principal
 */

import java.util.ArrayList;

public class Controlador {
    /**
     * Programa principal donde se ejecutaran las acciones
     * @param args parametros
     */
    public static void main(String[] args) {
        Modelo model = new Modelo();
        Vista vista = new Vista();
        ArrayList<Programa> programas = model.obtenerDatos();//base de datos de todos los programas 
        int opcion = 0;
        String tipoRAM; 
        int cantidad;
        
        vista.bienvenida();//Damos la bienvenida
        int numRAM = vista.tipoRAM();//Obtenemos el tipo de ram en numero 
        if (numRAM == 1){//deciframos que tipo de ram quiere el usuario
            tipoRAM = "SDR";//Definimos el tipo de ram
            cantidad = vista.obtenerCantidadSDR();//Preguntamos la cantidad
            int cantidadBloques = model.convertirGBBloques(cantidad);//convertimos la cantidad
            model.crearRAM(cantidadBloques);//creamos la ram con la cantidad de bloques que tenemos
        }
        else{
            tipoRAM = "DDR";
            cantidad = 4;
            int cantidadBloques = model.convertirGBBloques(4);
            model.crearRAM(cantidadBloques);
        }

        String respuesta = vista.deseaAgregarProgramas();//preguntamos si quiere agregar algun programa a la ram
        if (respuesta.equals("si")){//si es asi...
            int cantidadProgramas = vista.cantidadProgramas();//Le pedimos la cantidad de programas a agregar
            vista.imprimirProgramas(programas);//Imprimimos los programas a agregar
            for (int i = 0; i < cantidadProgramas; i++){//Agregarmos los programas
                Programa program = vista.agregarPrograma(programas);
                model.agregarPrograma(program);
            }
        }

        while (opcion != 10){
            opcion = vista.menu();//desplegamos el menu para pedir la opcion del usuario

            if (opcion == 1){
                int cantidadProgramas = vista.cantidadProgramas();//Agregamos los programas que nos pide al usuario a la cola
                vista.imprimirProgramas(programas);
                for (int i = 0; i < cantidadProgramas; i++){
                    Programa program = vista.agregarPrograma(programas);
                    model.agregarProgramaCola(program);
                }
            }
            else if (opcion == 2){
                vista.imprimirCantidadRAMTotal(cantidad);//imprimimos la cantidad de ram total
            }
            else if (opcion == 3){
                vista.imprimirCantidadRAMDisponible(model.convertirBloquesGB(model.totalRAMDisponible()));//imprimimos la cantidad de ram disponible
            }
            else if (opcion == 4){
                vista.imprimirCantidadRAMUso(model.convertirBloquesGB(model.totaRAMUso()));//imprimimos la cantidad de ram en uso
            }
            else if (opcion == 5){
                vista.programasEnEjecucion(model.getRAM());//imprimos los programas en ejecucion
            }
            else if (opcion == 6){
                vista.programasEnCola(model.getCola());//Imprimimos los programas en cola
            }
            else if (opcion == 7){
                Programa programaABuscar = vista.programaABuscar(programas);//Obtenemos el programa que busca el usuario
                vista.espaciosDePrograma(model.getRAM(), programaABuscar);//Obtenemos y mostramos los espacios que ocupa dicho programa
            }
            else if (opcion == 8){
                vista.imprimirRAM(model.getRAM());//imprimimos los bloque sde la ram
            }
            else if (opcion == 9){
                if (tipoRAM.equals("SDR")){//realizamos un ciclo de reloj, dependiendo si era sdr
                    model.cicloRelojSDR();
                }
                else {//realizamos un ciclo de reloj, dependiendo si era ddr
                    model.cicloRelojDDR();
                }
            }
            else if (opcion == 10){
                System.exit(0);//Se sale del programa
            }
            else{
                System.out.println("Elija una opcion valida");
            }
        }
    }
}
