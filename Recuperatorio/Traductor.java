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
                System.out.println(Thread.currentThread().getName() + " Me encuentro traduciendo el capitulo " + capituloTraduciendo.getId());
                traduciendoCapitulo();
                capituloTraduciendo.traducida();
                System.out.println(Thread.currentThread().getName() + " termine de traducir el capitulo " + capituloTraduciendo.getId());
                f.agregarABibliotecaTraducidas(capituloTraduciendo);
            }
        } catch (Exception e) {
            System.out.println("ERROR EN TRADUCTOR");
        }
    }

    private void traduciendoCapitulo() throws Exception {
        Thread.sleep(new Random().nextInt(5000, 7000));
    }

}
