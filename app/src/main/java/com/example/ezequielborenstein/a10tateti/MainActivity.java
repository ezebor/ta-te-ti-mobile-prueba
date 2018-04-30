package com.example.ezequielborenstein.a10tateti;

import android.app.Activity;
import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.Toast;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class MainActivity extends Activity {

    private Integer jugadores;
    private Map<String, Integer> casillas;
    private Partida partida;
    private Integer cantidadJugadas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.inicializarCasillas();

        this.cantidadJugadas = 0;

        this.inicializarListeners();
    }

    public void aJugar(View boton){
        this.resetarTablero();

        jugadores = 1;
        if(boton.getId() == R.id.dos_jugadores_id){
            jugadores = 2;
        }

        Integer dificultad = 1;
        RadioGroup configuracionDificultad = (RadioGroup)findViewById(R.id.dificultad_id);
        Integer dificultadSeleccionada = configuracionDificultad.getCheckedRadioButtonId();
        if(dificultadSeleccionada == R.id.normal_id){
            dificultad = 2;
        }else if(dificultadSeleccionada == R.id.imposible_id){
            dificultad = 3;
        }

        partida = new Partida(dificultad);

        // Inhabilitar botones y RadioGroup
        ((Button)findViewById(R.id.un_jugador_id)).setEnabled(false);
        ((RadioGroup)findViewById(R.id.dificultad_id)).setAlpha(0);
        ((Button)findViewById(R.id.dos_jugadores_id)).setEnabled(false);
    }

    public void aplicarJugada(View casillaSeleccionada){
        // Esto lo hago para que evite empezar a jugar hasta que no clickeo en alguno de los dos botones
        if(partida ==  null){
            return;
        }

        String casillaElegida = "";
        for(String casilleroId : casillas.keySet()){
            if(casillaSeleccionada.getId() == casillas.get(casilleroId)){
                casillaElegida = casilleroId;
                break;
            }
        }

        this.marcarCasilla(casillaElegida);

        if(jugadores == 1 && cantidadJugadas <= 8){
            String casillaRandom;
            ImageView casillaPc;

            do{
                casillaRandom = partida.casillaRandom();
                casillaPc = (ImageView)findViewById(casillas.get(casillaRandom));
            }while((Integer)casillaPc.getTag() != 0);

            this.marcarCasilla(casillaRandom);
        }
    }

    private void inicializarListeners(){
        this.inicializarBoton(R.id.un_jugador_id);
        this.inicializarBoton(R.id.dos_jugadores_id);
        this.inicializarListenersCasillas();
    }

    private void inicializarBoton(Integer id){
        ((Button)findViewById(id)).setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                aJugar(view);
            }
        });
    }

    private void inicializarListenersCasillas(){
        for(Integer casillaId : casillas.values()){
            ((ImageView)findViewById(casillaId)).setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    aplicarJugada(view);
                }
            });
        }
    }

    private void marcarCasilla(String casillaElegida){
        ImageView casilla = (ImageView)findViewById(casillas.get(casillaElegida));
        casilla.setImageResource(partida.imagenDeJugada());
        casilla.setTag(1);
        this.cantidadJugadas++;
    }

    private void resetarTablero(){
        ImageView casillero;
        for(String casilleroId : casillas.keySet()){
            casillero = (ImageView)findViewById(casillas.get(casilleroId));
            casillero.setImageResource(R.drawable.casilla);
            casillero.setTag(0);
        }
    }

    private void inicializarCasillas(){
        casillas = new HashMap<>();
        casillas.put("a1", R.id.casilla_a1);
        casillas.put("a2", R.id.casilla_a2);
        casillas.put("a3", R.id.casilla_a3);

        casillas.put("b1", R.id.casilla_b1);
        casillas.put("b2", R.id.casilla_b2);
        casillas.put("b3", R.id.casilla_b3);

        casillas.put("c1", R.id.casilla_c1);
        casillas.put("c2", R.id.casilla_c2);
        casillas.put("c3", R.id.casilla_c3);
    }
}
