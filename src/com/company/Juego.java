package com.company;

import java.util.Random;
import java.util.Scanner;

public class Juego {
    private Dificultad dificultad;
    private Integer palitos;
    private Boolean ganadorUsuario;
    private Boolean turnoUsuario;
    private Integer topeDificil;
    Scanner scanner = new Scanner(System.in);
    Random random= new Random();

    public Juego() {
        palitos=21;
        ganadorUsuario=false;
        turnoUsuario=false;
        dificultad=Dificultad.BAJA;
        topeDificil=8;
    }


    public void jugar(){
        darBienvenida();
        preguntarDificultad();
        preguntarQuienEmpieza();
        System.out.println("------------------------------------------------------------------------------------------");
        System.out.println("Empecemos.....");
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
        System.out.println((palitos==1)?"Queda " + palitos + " palito.":"Quedan " + palitos + " palitos.");
        System.out.println();
    }

    private void jugarTurnoPC() {
        Integer cantidadPalitos = elegirCantidadPalitosASacar();
        System.out.println("Mi turno: quito " + cantidadPalitos + (cantidadPalitos==1?" palito...":" palitos..."));
        restarPalitos(cantidadPalitos);
        turnoUsuario=true;
    }

    private Integer elegirCantidadPalitosASacar() {
        if (tocaJugadaDificil()) {
            //System.out.println("#### dificil");
            if (convieneSacar(palitos - 2)) return 2;
            else return 1;
        } else {
            //System.out.println("#### facil");
            return random.nextInt(1)+1;
        }
    }

    private Boolean tocaJugadaDificil() {
        Integer numeroAzar= random.nextInt(10);
        //System.out.println("#### " + numeroAzar);
        return numeroAzar>topeDificil;
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
        if (palitos<2)
            ganadorUsuario=(palitos==1)?turnoUsuario:!turnoUsuario;
    }

    private void preguntarQuienEmpieza() {
        System.out.print("¿Querés jugar primero? (S/N): ");
        String respuesta = scanner.nextLine();
        turnoUsuario =  (respuesta.toUpperCase().contains("S"));
        if (!turnoUsuario) System.out.println("Empiezo YO.");
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

        switch (opcion){
            case 1: dificultad=Dificultad.BAJA;
                    topeDificil=8;
                    break;
            case 2: dificultad=Dificultad.MEDIA;
                    topeDificil=5;
                    break;
            case 3: dificultad=Dificultad.ALTA;
                    topeDificil=3;
        }
        System.out.println();
    }

    private void darBienvenida() {
        System.out.println("*******************************************");
        System.out.println("**     Bienvenido al juego 21 Palitos    **");
        System.out.println("*******************************************");
        System.out.println();
    }

}
