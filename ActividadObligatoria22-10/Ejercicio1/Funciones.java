package Ejercicio1;

public class Funciones implements Runnable {
    private int operacion; // Variable
    private Matematica matematica; // Recurso compartido

    public Funciones(Matematica m, int operacion) {
        this.operacion = operacion;
        this.matematica = m;
    }

    @Override
    public void run() {
        try {
            if (operacion == 1) {
                matematica.proceso1();
            } else {
                matematica.proceso2();
            }
            matematica.proceso3();
            matematica.proceso4();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
