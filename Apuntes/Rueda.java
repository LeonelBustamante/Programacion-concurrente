package Apuntes;

public class Rueda {
    public synchronized void rodar(String nombre) {
        System.out.println(nombre + " empieza a rodar");
        try {
            Thread.sleep((long) Math.random() * 1500);
        } catch (InterruptedException ex) {
        }
    }
}