package com.company;

import java.util.Scanner;

public class Juego {
    private Integer palitos;
    private Boolean ganadorUsuario;
    private Boolean turnoUsuario;
    Scanner scanner = new Scanner(System.in);

    public Juego() {
        palitos=21;
        ganadorUsuario=false;
        turnoUsuario=false;
    }


    public void jugar(){
        darBienvenida();
        preguntarQuienEmpieza();
        while (palitos>1)
            jugarTurno();
        mostrarGanador();
    }

    private void mostrarGanador() {
        String msjGanador= ganadorUsuario?"GANASTE!!":"PERDISTE";
        System.out.println(msjGanador);
    }

    private void jugarTurno() {
        if (turnoUsuario) jugarTurnoUsuario();
        else jugarTurnoPC();
        System.out.println("Quedan " + palitos + " palitos.");
    }

    private void jugarTurnoPC() {
        Integer cantidadPalitos = elegirCantidadPalitosASacar();
        System.out.println("Yo quito " + cantidadPalitos + " palitos...");
        restarPalitos(cantidadPalitos);
        turnoUsuario=true;
    }

    private Integer elegirCantidadPalitosASacar() {
        if (convieneSacar(palitos-2)) return 2;
        else return 1;
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
        if (palitos<2) ganadorUsuario=turnoUsuario;
    }

    private void preguntarQuienEmpieza() {
        System.out.print("¿Querés jugar primero? (S/N): ");
        String respuesta = scanner.nextLine();
        turnoUsuario =  (respuesta.toUpperCase().contains("S"));
    }

    private void darBienvenida() {
        System.out.println("Bienvenido al juego 21 palitos");
    }

}
