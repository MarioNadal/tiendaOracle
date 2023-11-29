package libs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Leer {
    static public void mostrarEnPantalla(String mensaje) {
        System.out.println(mensaje);
    }
    public static int introduceEntero(String texto) {
        Scanner sc = new Scanner(System.in);
        boolean continuar = false;
        System.out.println(texto);
        int num = 0;
        do {
            try {
                num = sc.nextInt();
                continuar = true;
            } catch (InputMismatchException ex) {
                System.out.println("Debes introducir números enteros");
                sc.next();
            }
        } while (!continuar);
        return num;
    }
    public static double introduceDouble() {
        Scanner sc = new Scanner(System.in);
        boolean continuar = false;
        System.out.println("Introduce un número: ");
        Double num = null;
        do{
            try{
                num= sc.nextDouble();
                continuar=true;
            }catch(InputMismatchException ex){
                System.out.println("Debes introducir números");
                sc.next();
            }
        }while(!continuar);
        return num;
    }
    public static String introduceLetra(String texto) {
        Scanner sc= new Scanner(System.in);
        boolean continuar = false;
        System.out.println(texto);
        String letra = null;
        do{
            try{
                letra = sc.next("[a-zA-Z]");
                continuar = true;
            }catch(InputMismatchException ex){
                System.out.println("Debes introducir una única letra");
                sc.next();
            }
        }while(!continuar);
        return letra;
    }
    public static String introducePalabra() {
        Scanner sc= new Scanner(System.in);
        boolean continuar = false;
        String palabra = null;
        do{
            try{
                palabra = sc.next("[a-zA-Z]+");
                continuar = true;
            }catch(InputMismatchException ex){
                System.out.println("Debes introducir solo letras");
                sc.next();
            }
        }while(!continuar);
        return palabra;
    }
    public static String introduceString(String texto) {
        Scanner sc = new Scanner(System.in);
        System.out.println(texto);
        return sc.nextLine();
    }
    public static boolean introduceBoolean(String texto) {
        Scanner sc = new Scanner(System.in);
        System.out.println(texto);
        String valorEntrada = sc.nextLine().toLowerCase();
        while (!valorEntrada.equals("true") && !valorEntrada.equals("false")) {
            System.out.println("Debes introducir 'true' o 'false'");
            System.out.println(texto);
            valorEntrada = sc.nextLine().toLowerCase();
        }
        return Boolean.parseBoolean(valorEntrada);
    }
    //COGER DATE SQL
    static public Date introduceDateSQL(final String texto){
        Date dateSql = null;
        Scanner sc = new Scanner(System.in);
        boolean error = true;
        while(error) {
            try {
                mostrarEnPantalla(texto);
                dateSql = Date.valueOf(sc.next());
                String formatoEsperado = "yyyy-MM-dd";
                SimpleDateFormat sdf = new SimpleDateFormat(formatoEsperado);
                String fechaFormateada = sdf.format(dateSql);
                if (fechaFormateada.equals(dateSql.toString())) {
                    System.out.println("La fecha tiene el formato correcto: " + fechaFormateada);
                    error = false;
                } else {
                    System.out.println("La fecha no tiene el formato correcto.");
                }
            } catch (Exception e) {
                System.out.println("Formato Incorrecto");
            }
        }
        return dateSql;
    }

    //COGER DATE UTIL
    static public Date introduceDateUtil(final String texto) {
        BufferedReader dataIn = new BufferedReader(new InputStreamReader(System.in));
        Date dato = null;
        boolean error = true;
        String datoT = "";
        while (error) {
            try {
                mostrarEnPantalla(texto);
                datoT = dataIn.readLine();
                dato = (Date) new SimpleDateFormat("dd-MM-yyyy").parse(datoT);
                error = false;
            } catch (IOException e) {
                mostrarEnPantalla("Vuelve a introducir el dato, por favor. ");
                error = true;
            } catch (NumberFormatException e) {
                mostrarEnPantalla("El dato introducido no es una fecha válida: dd-MM-yyyy");
                error = true;
            } catch (ParseException e) {
                mostrarEnPantalla("El dato introducido no es una fecha válida: dd-MM-yyyy");
                error = true;
            }
        }
        return dato;
    }
}
