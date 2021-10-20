package Ejercicio1;

public class Main {
    public static void main(String[] args) {
        Matematica matematica = new Matematica(3.0, 2.0, 1.0);
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
    }
}
