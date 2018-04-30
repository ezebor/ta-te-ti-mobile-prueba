package com.example.ezequielborenstein.a10tateti;

import java.util.Random;

public class Partida {

    public final Integer dificultad;
    public Integer jugador;

    public Partida(Integer dificultad){
        this.dificultad = dificultad;
        this.jugador = 1;
    }

    public Integer imagenDeJugada() {
        if(this.jugador == 1){
            this.jugador = 2;
            return R.drawable.circulo;
        }

        this.jugador = 1;
        return R.drawable.cruz;
    }

    public String casillaRandom(){
        Integer fila = (new Random()).nextInt(3);
        String letra;
        Integer columna = (new Random()).nextInt(3);

        switch (fila){
            case 0:
                letra = "a";
                break;
            case 1:
                letra = "b";
                break;
            default:
                letra = "c";
                break;
        }

        switch (columna){
            case 0:
                columna = 1;
                break;
            case 1:
                columna = 2;
                break;
            default:
                columna = 3;
                break;
        }
        return letra + columna;
    }
}
