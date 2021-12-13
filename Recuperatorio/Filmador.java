package Recuperatorio;

import java.util.Random;

public class Filmador implements Runnable {

    private final Filmacion f;

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

        try {

            while (true) {
                producidas++;
                System.out.println(Thread.currentThread().getName() + " grabando el capitulo " + producidas);
                produciendoNuevoCapitulo();
                System.out.println(Thread.currentThread().getName() + " termino de grabar el capitulo " + producidas);
                
                f.agregarABiblioteca(new Capitulo(producidas - 1));
            }
            
        } catch (Exception e) {
        }
    }

    private void produciendoNuevoCapitulo() throws Exception {
        Thread.sleep(new Random().nextInt(300));
    }

}
