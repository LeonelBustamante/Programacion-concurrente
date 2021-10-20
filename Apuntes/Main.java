package Apuntes;

public class Main {

    public static void main(String[] args) {
        Plato comida = new Plato(3);
        Rueda ejercicio = new Rueda();

        Thread t1 = new Thread(new HamsterMonitor(comida, ejercicio, "h1"));
        Thread t2 = new Thread(new HamsterMonitor(comida, ejercicio, "h2"));
        Thread t3 = new Thread(new HamsterMonitor(comida, ejercicio, "h3"));
        Thread t4 = new Thread(new HamsterMonitor(comida, ejercicio, "h4"));

        t1.start();
        t2.start();
        t3.start();
        t4.start();
    }
}
