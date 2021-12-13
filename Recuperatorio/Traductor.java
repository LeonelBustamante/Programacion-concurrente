package Recuperatorio;

import java.util.Random;

public class Traductor implements Runnable {

    private Filmacion f;

    public Traductor(Filmacion f) {
        this.f = f;
    }

    public void run() {
        Capitulo capituloTraduciendo;
        try {
            while (true) {

                capituloTraduciendo = f.comenzarATraducir();
                traduciendoCapitulo();
                capituloTraduciendo.traducida();
                System.out.println(Thread.currentThread().getName() + " termino de traducir el capitulo " + capituloTraduciendo.getId());
                f.agregarABibliotecaTraducidas(capituloTraduciendo);
            }
        } catch (Exception e) {
        }
    }

    private void traduciendoCapitulo() throws Exception {
        Thread.sleep(new Random().nextInt(500));
    }

}
