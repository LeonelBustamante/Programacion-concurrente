package EjercicioChicos.Forma1;

public class t1 implements Runnable {

    private Instrucciones aux;

    public t1(Instrucciones i) {
        aux = i;
    }

    @Override
    public void run() {
        aux.s1();
    }
}