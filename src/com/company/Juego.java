package com.company;

import java.util.Random;
import java.util.Scanner;

public class Juego {
    private Dificultad dificultad;
    private Integer palitos;
    private Boolean ganadorUsuario;
    private Boolean turnoUsuario;
    Scanner scanner = new Scanner(System.in);
    Random random= new Random();

    public Juego() {
        palitos=21;
        ganadorUsuario=false;
        turnoUsuario=false;
        dificultad=Dificultad.BAJA;
    }


    public void jugar(){
        darBienvenida();
        preguntarDificultad();
        preguntarQuienEmpieza();
        while (palitos>1)
            jugarTurno();
        mostrarGanador();
    }

    private void mostrarGanador() {
        String msjGanador= ganadorUsuario?"GANASTE!!":"PERDISTE...";
        System.out.println(msjGanador);
    }

    private void jugarTurno() {
        if (turnoUsuario) jugarTurnoUsuario();
        else jugarTurnoPC();
        System.out.println("Quedan " + palitos + " palitos.");
        System.out.println();
    }

    private void jugarTurnoPC() {
        Integer cantidadPalitos = elegirCantidadPalitosASacar();
        System.out.println("Yo quito " + cantidadPalitos + " palitos...");
        restarPalitos(cantidadPalitos);
        turnoUsuario=true;
    }

    private Integer elegirCantidadPalitosASacar() {
        if (dificultad==Dificultad.ALTA) {
            if (convieneSacar(palitos - 2)) return 2;
            else return 1;
        } else {
            return random.nextInt(1)+1;
        }
    }

    private boolean convieneSacar(Integer palitosRestantes) {
        if (palitosRestantes%3==0) return false;
        if (palitosRestantes==2) return false;
        return true;
    }

    private void jugarTurnoUsuario() {
        Integer cantidadPalitos;
        do {
            System.out.print("Tu turno: ¿cuántos palitos querés sacar? (1 o 2): ");
            cantidadPalitos = Integer.parseInt(scanner.nextLine());
            if (cantidadPalitos<1 || cantidadPalitos>2) System.out.println("Cantidad inválida.");
        } while (cantidadPalitos<1 || cantidadPalitos>2);

        restarPalitos(cantidadPalitos);
        turnoUsuario=false;
    }

    private void restarPalitos(Integer cantidadPalitos) {
        palitos-= cantidadPalitos;
        //if (palitos<2) ganadorUsuario=turnoUsuario;
        if (palitos==1) ganadorUsuario=turnoUsuario;
        if (palitos==0) ganadorUsuario=!turnoUsuario;
    }

    private void preguntarQuienEmpieza() {
        System.out.print("¿Querés jugar primero? (S/N): ");
        String respuesta = scanner.nextLine();
        turnoUsuario =  (respuesta.toUpperCase().contains("S"));
        System.out.println();
    }

    private void preguntarDificultad() {
        Integer opcion=0;
        do {
            System.out.println("Elegí el nivel de dificultad: ");
            System.out.println("    1. Fácil");
            System.out.println("    2. Intermedio");
            System.out.println("    3. Imposible");
            System.out.print("Opción: ");
            opcion = Integer.parseInt(scanner.nextLine());
            if (opcion<1 || opcion>3) System.out.println("Cantidad inválida.");
        } while (opcion<1 || opcion>3);

        if (opcion==2){
            if (random.nextInt(9)<7) opcion=1;
            else opcion=3;
        }
        dificultad =  (opcion==1)?Dificultad.BAJA:Dificultad.ALTA;
        System.out.println();
    }

    private void darBienvenida() {
        System.out.println("*******************************************");
        System.out.println("**     Bienvenido al juego 21 Palitos    **");
        System.out.println("*******************************************");
        System.out.println();
    }

}
