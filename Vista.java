import java.util.ArrayList;
import java.util.Scanner;

public class Vista {
    private Scanner scan = new Scanner(System.in);

    public void bienvenida(){
        System.out.println("Bienvenid@ a nuestro programa de asistencia a caninos perdidos");
    }

    public int menu(){
        boolean paso = false;
        int option = 0;
        while (paso != true){
            try {
                System.out.println("Elija una opción:");
		        System.out.println("1. Ingresar nuevos programas");//Le damos todas las opciones disponibles
		        System.out.println("2. Ver cantidad de memoria RAM total");
		        System.out.println("3. Ver cantidad de memoria RAM disponible");
		        System.out.println("4. Ver cantidad de memoria RAM en uso");
		        System.out.println("5. Ver programas en ejecucion");
                System.out.println("6. Ver programas en cola");
                System.out.println("7. Ver los espacios (por bloques de RAM) que ocupa un programa particular");
                System.out.println("8. Ver estado de memoria");
                System.out.println("9. Realizar un ciclo de reloj");
                System.out.println("10. Salir/n/n");

                String optionString = scan.nextLine();//Recibimos el valor como String para evitar un bug con el metodo nextLine()
                option = Integer.parseInt(optionString);//Lo cambiamos a Integer
                paso = true;
            } catch (Exception e) {
                System.out.println("El valor que ingreso no es el adecuado, ingrese un valor adecuado");
            }
        }
        return option;//regresamos el valor convertido
    }

    public int tipoRAM(){
        boolean paso = false;
        int datoInt = 0;
        while (paso != true){
            try {
                System.out.println("Elige un tipo de RAM con un numero ['1' para SDR] o ['2' para DDR]");
                String dato = scan.nextLine();
                datoInt = Integer.parseInt(dato);
                if (datoInt == 1 || datoInt == 2){
                    paso = true;
                }
                else{
                    System.out.println("Ingreso un valor mayor a 1 y 2, vuelva a elegir");
                }
            } catch (Exception e) {
                System.out.println("Ingreso un valor no numerico");
            }
        }
        return datoInt;
    }

    public int obtenerCantidadSDR(){
        boolean paso = false;
        int cantidadInt = 0;
        while (paso != true){
            try {
                System.out.println("¿Cual es la cantidad de RAM que necesita? (opciones de 4, 8, 16 y 32)");
                String cantidad = scan.nextLine();
                cantidadInt = Integer.parseInt(cantidad);
                if (cantidadInt == 4 || cantidadInt == 8 || cantidadInt == 16 || cantidadInt == 32){
                    paso = true;
                }
                else{
                    System.out.println("Ingreso un valor diferente a las opciones dadas, vuelva a elegir");
                }
            } catch (Exception e) {
                System.out.println("Ingreso un valor no numerico");
            }
        }
        return cantidadInt;
    }

    public String deseaAgregarProgramas(){
        boolean paso = false;
        String respuesta = "";
        while (paso != true){
            try {
                System.out.println("¿Desea agregar programas? (responda cualquier otro o otros caracteres diferentes de 'si' para responder 'no')");
                respuesta = scan.nextLine().toLowerCase();
                paso = true;
            } catch (Exception e) {
                System.out.println("Ingreso un valor no autorizado");
            }
        }
        return respuesta;
    }

    public int cantidadProgramas(){
        boolean paso = false;
        int cantidadInt = 0;
        while (paso != true){
            try {
                System.out.println("¿Cuantos programas piensa agregar?");
                String cantidad = scan.nextLine();
                cantidadInt = Integer.parseInt(cantidad);
                paso = true;
            } catch (Exception e) {
                System.out.println("Eligio un valor no numerico, vuelva a intentarlo");
            }
        }
        return cantidadInt;
    }

    public Programa agregarPrograma(ArrayList<Programa> programas){
        Programa program = new Programa();
        boolean paso = false;
        while(paso != true){
            try {
                System.out.println("¿Cual es el programa a elegir?");
                String nombre = scan.nextLine().toUpperCase();
                for (int i = 0; i < programas.size(); i++){
                    Programa programa = programas.get(i);
                    String nombrePrograma = programa.getNombre();
                    if (nombre.equals(nombrePrograma)){
                        program = programa;
                        paso = true;
                        break;
                    }
                }
                if (program.getNombre().equals("")){
                    System.out.println("El programa que ingreso es no se encontro");
                }
            } catch (Exception e) {
                System.out.println("Ocurrio un error, vuelva a escribirlo");
            }
        }
        return program;
    }

    public void imprimirProgramas(ArrayList<Programa> programas){
        for (int i = 0; i < programas.size(); i++){
            Programa program = programas.get(i);
            System.out.println("---------------------------------");
            System.out.println("Nombre del programa: " + program.getNombre());
            System.out.println("Cantidad que consume en el RAM: " + program.getCantidadMB());
            System.out.println("Duracion del programa en ciclos de reloj: " + program.getDuracion());
            System.out.println("---------------------------------");
        }
    }

    public void imprimirRAM(ArrayList<Bloque> RAM){
        for (int i = 0; i < RAM.size(); i++){
            Bloque block =  RAM.get(i);
            if (block.getOcupado() == true){
                Programa program = block.getPrograma();
                System.out.println("---------------------------------");
                System.out.println("Nombre del programa: " + program.getNombre());
                System.out.println("Cantidad que consume en el RAM: " + program.getCantidadMB());
                System.out.println("Duracion del programa en ciclos de reloj: " + program.getDuracion());
                System.out.println("---------------------------------");
            }
            else{
                System.out.println("---------------------------------");
                System.out.println("Espacio Libre");
                System.out.println("---------------------------------");
            }
        }
    }

    public void programasEnEjecucion(ArrayList<Bloque> RAM){
        for (int i = 0; i < RAM.size(); i++){
            Bloque block =  RAM.get(i);
            if (block.getOcupado() == true){
                Programa program = block.getPrograma();
                System.out.println("---------------------------------");
                System.out.println("Nombre del programa: " + program.getNombre());
                System.out.println("Cantidad que consume en el RAM: " + program.getCantidadMB());
                System.out.println("Duracion del programa en ciclos de reloj: " + program.getDuracion());
                System.out.println("---------------------------------");
            }
        }
    }

    public void programasEnCola(ArrayList<Programa> programas){
        for (int i = 0; i < programas.size(); i++){
            Programa program =  programas.get(i);
            System.out.println("---------------------------------");
            System.out.println("Nombre del programa: " + program.getNombre());
            System.out.println("Cantidad que consume en el RAM: " + program.getCantidadMB());
            System.out.println("Duracion del programa en ciclos de reloj: " + program.getDuracion());
            System.out.println("---------------------------------");
        }
    }

    public Programa programaABuscar(ArrayList<Programa> programas){
        Programa program = new Programa();
        boolean paso = false;
        while (paso != true){
            try {
                System.out.println("¿Que programa desea buscar?");
                String nombre = scan.nextLine().toUpperCase();
                for (int i = 0; i < programas.size(); i++){
                    Programa programa = programas.get(i);
                    String nombrePrograma = programa.getNombre();
                    if (nombre.equals(nombrePrograma)){
                        program = programa;
                        paso = true;
                        break;
                    }
                }
                if (program.getNombre().equals("")){
                    System.out.println("El programa que ingreso no fue encontrado, escriba un programa incluido esta vez");
                }
            } catch (Exception e) {
                System.out.println("Ocurrio un error, vuelva a escribirlo");
            }
        }
        
        return program;
    }

    public void espaciosDePrograma(ArrayList<Bloque> RAM, Programa programaABuscar){
        for (int i = 0; i < RAM.size(); i++){
            Bloque block =  RAM.get(i);
            if (block.getOcupado() == true){
                Programa program = block.getPrograma();
                if (program.getNombre().equals(programaABuscar.getNombre())){
                    System.out.println("---------------------------------");
                    System.out.println("Nombre del programa: " + programaABuscar.getNombre());
                    System.out.println("Cantidad que consume en el RAM: " + programaABuscar.getCantidadMB());
                    System.out.println("Duracion del programa en ciclos de reloj: " + programaABuscar.getDuracion());
                    System.out.println("---------------------------------");
                }
            }
        }
    }

    public void imprimirCantidadRAMTotal(int cantidad){
        System.out.println("La cantidad de RAM total es de: " + cantidad + " GB");
    }

    public void imprimirCantidadRAMDisponible(int cantidad){
        System.out.println("La cantidad de RAM disponible es de: " + cantidad + " GB");
    }

    public void imprimirCantidadRAMUso(int cantidad){
        System.out.println("La cantidad de RAM en uso es de: " + cantidad + " GB");
    }
}