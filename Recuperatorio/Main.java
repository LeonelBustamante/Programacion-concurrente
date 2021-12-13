package Recuperatorio;

import java.util.Random;

public class Main {

    public static void main(String[] args) {
        //Objeto compartido
        Filmacion filmacion = new Filmacion();

        //Creacion de hilos
        new Thread(new Filmador(filmacion), "FILMADOR").start();

        new Thread(new Traductor(filmacion), "TRADUCTOR-1").start();
        new Thread(new Traductor(filmacion), "TRADUCTOR-2").start();

        int cantidadSocios = 100;
        for (int i = 0; i < cantidadSocios; i++) {
            new Thread(new Socio(filmacion, new Random().nextBoolean()), "Socio-" + i).start();
        }
    }

}
