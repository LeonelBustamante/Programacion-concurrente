package Apuntes;

public class HamsterMonitor implements Runnable {
    private Plato comida;
    private Rueda ejercicio;
    private String miNombre;

    public HamsterMonitor(Plato laComida, Rueda elEjercicio, String nombre) {
        comida = laComida;
        ejercicio = elEjercicio;
        miNombre = nombre;
    }

    public void run() {
        while (true) {
            comida.empezarAComer(miNombre);
            try {
                Thread.sleep((long) Math.random() * 1500);
            } catch (InterruptedException ex) {
            }
            comida.terminarDeComer(miNombre);
            ejercicio.rodar(miNombre);
        }
    }
}