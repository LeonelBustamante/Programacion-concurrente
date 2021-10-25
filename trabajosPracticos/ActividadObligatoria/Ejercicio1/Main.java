package ActividadObligatoria.Ejercicio1;

import java.util.Random;

public class Main {
    public static void main(String[] args) {
        Matematica matematica = new Matematica(new Random().nextInt(10), new Random().nextInt(10),
                new Random().nextInt(10));
        Thread[] threads = new Thread[2];
        for (int i = 0; i < threads.length; i++) {
            threads[i] = new Thread(new Funciones(matematica, i), "HILO " + i);
            threads[i].start();
        }
        try {
            for (int i = 0; i < threads.length; i++) {
                threads[i].join();
            }
        } catch (Exception e) {
        }
        System.out.println("Termino");
    }
}
