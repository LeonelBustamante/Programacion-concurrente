package Recuperatorio;

import java.util.Random;

public class Filmador implements Runnable {

    private Filmacion f;

    public Filmador(Filmacion f) {
        this.f = f;
    }

    public void run() {
        /**
         * Metodo que simula la accion de producir un nuevo capitulo de la serie
         * donde el Filmador realiza un capitulo y este a su vez lo envia al
         * estudio de filmacion para que sea agregado a la biblioteca
         *
         * producidas es la cantidad de capitulos producidos por el filmador
         * capitulo es el que genera en cada iteración
         */
        int producidas = 0;
        Capitulo capitulo;
        try {
            while (true) {
                producidas++;
                System.out.println(Thread.currentThread().getName() + " Me encuentro grabando el capitulo " + producidas);
                produciendoNuevoCapitulo();
                capitulo = new Capitulo(producidas);
                System.out.println(Thread.currentThread().getName() + " termine de grabar el capitulo " + producidas);
                f.agregarABiblioteca(capitulo);
            }
        } catch (Exception e) {
            System.out.println("ERROR EN FILMADOR");
        }
    }

    private void produciendoNuevoCapitulo() throws Exception {
        Thread.sleep(new Random().nextInt(3000, 5000));
    }

}
