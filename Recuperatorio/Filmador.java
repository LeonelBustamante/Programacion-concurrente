package Recuperatorio;

import java.util.Random;

public class Filmador implements Runnable {

    private final Filmacion filmacion;

    public Filmador(Filmacion filmacion) {
        this.filmacion = filmacion;
    }

    public void run() {
        /**
         * Metodo que simula la accion de producir un nuevo capitulo de la serie
         * este a su vez lo envia al estudio de filmacion para que sea agregado
         * a la biblioteca.
         */
        int numeroNuevoCapitulo = 0;

        try {

            while (true) {
                //Se incrementa el numero del proximo capitulo a estrenar
                numeroNuevoCapitulo++;

                //Se simula el tiempo de grabacion
                System.out.println(Thread.currentThread().getName()
                        + " EMPIEZA a grabar el capitulo " + numeroNuevoCapitulo);
                produciendoNuevoCapitulo();
                System.out.println(Thread.currentThread().getName()
                        + " TERMINA de grabar el capitulo " + numeroNuevoCapitulo);

                //Crea y agrega a la biblioteca un nuevo capitulo
                filmacion.agregarABiblioteca(new Capitulo(numeroNuevoCapitulo));
            }

        } catch (Exception e) {
        }
    }

    private void produciendoNuevoCapitulo() throws Exception {
        Thread.sleep(1000);
    }

}
