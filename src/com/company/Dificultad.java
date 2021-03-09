package com.company;


public enum Dificultad {
    BAJA(3), MEDIA(5), ALTA(8);

    private int nivel;

    private Dificultad(int n){
        nivel=n;
    }

    public int getNivel(){
        return nivel;
    }
}
