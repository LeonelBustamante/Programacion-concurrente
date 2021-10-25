package ActividadObligatoria.EjercicioChicos.Forma1;

public class t2 implements Runnable {

    private Instrucciones aux;

    public t2(Instrucciones i) {
        aux = i;
    }

    @Override
    public void run() {
        aux.s2();
    }
}