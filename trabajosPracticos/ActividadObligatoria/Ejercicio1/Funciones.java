package ActividadObligatoria.Ejercicio1;

public class Funciones implements Runnable {
    private int operacion; // Variable
    private Matematica matematica; // Recurso compartido

    public Funciones(Matematica matematica, int operacion) {
        this.operacion = operacion;
        this.matematica = matematica;
    }

    @Override
    public void run() {
        try {
            if (operacion == 1) {
                matematica.proceso1();
                matematica.proceso3();
                matematica.proceso4();
            } else {
                matematica.proceso2();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
