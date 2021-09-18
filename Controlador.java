import java.util.ArrayList;

public class Controlador {
    public static void main(String[] args) {
        Modelo model = new Modelo();
        Vista vista = new Vista();
        ArrayList<Programa> programas = model.obtenerDatos();
        int opcion;
        String tipoRAM; 
        int cantidad;
        
        vista.bienvenida();
        int numRAM = vista.tipoRAM();
        if (numRAM == 1){
            tipoRAM = "SDR";
            cantidad = vista.obtenerCantidadSDR();
            int cantidadBloques = model.convertirGBBloques(cantidad);
            model.crearRAM(cantidadBloques);
        }
        else{
            tipoRAM = "DDR";
            cantidad = 4;
            int cantidadBloques = model.convertirGBBloques(4);
            model.crearRAM(cantidadBloques);
        }

        String respuesta = vista.deseaAgregarProgramas();
        if (respuesta.equals("si")){
            int cantidadProgramas = vista.cantidadProgramas();
            vista.imprimirProgramas(programas);
            for (int i = 0; i < cantidadProgramas; i++){
                Programa program = vista.agregarPrograma(programas);
                model.agregarPrograma(program);
            }
        }

        opcion = vista.menu();

        while (opcion != 10){
            opcion = vista.menu();

            if (opcion == 1){
                int cantidadProgramas = vista.cantidadProgramas();
                vista.imprimirProgramas(programas);
                for (int i = 0; i < cantidadProgramas; i++){
                    Programa program = vista.agregarPrograma(programas);
                    model.agregarProgramaCola(program);
                }
            }
            else if (opcion == 2){
                vista.imprimirCantidadRAMTotal(cantidad);
            }
            else if (opcion == 3){
                vista.imprimirCantidadRAMDisponible(model.convertirBloquesGB(model.totalRAMDisponible()));
            }
            else if (opcion == 4){
                vista.imprimirCantidadRAMUso(model.convertirBloquesGB(model.totaRAMUso()));
            }
            else if (opcion == 5){
                vista.programasEnEjecucion(model.getRAM());
            }
            else if (opcion == 6){
                vista.programasEnCola(model.getCola());
            }
            else if (opcion == 7){
                Programa programaABuscar = vista.programaABuscar(programas);
                vista.espaciosDePrograma(model.getRAM(), programaABuscar);
            }
            else if (opcion == 8){
                vista.imprimirRAM(model.getRAM());
            }
            else if (opcion == 9){
                if (tipoRAM.equals("SDR")){
                    model.cicloRelojSDR();
                }
                else {
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
