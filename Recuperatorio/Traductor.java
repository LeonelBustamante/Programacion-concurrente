package Recuperatorio;

public class Traductor implements Runnable {

    private Filmacion filmacion;

    public Traductor(Filmacion filmacion) {
        this.filmacion = filmacion;
    }

    public void run() {
        /**
         * Metodo que simula la accion de traducir un capitulo de la serie este
         * a su vez lo envia al estudio de filmacion para que sea agregado a la
         * biblioteca de traducidos.
         */
        Capitulo capituloTraduciendo;
        try {
            while (true) {
                //Se busca un capitulo para que sea traducido
                capituloTraduciendo = filmacion.buscarParaTraducir();

                //Se simula la accion de traducir el capitulo y se actualiza el capitulo a un nuevo estado
                System.out.println(Thread.currentThread().getName()
                        + " COMENZO a traducir el capitulo " + capituloTraduciendo.getId());
                traduciendoCapitulo();
                capituloTraduciendo.traducida();
                System.out.println(Thread.currentThread().getName()
                        + " TERMINO de traducir el capitulo " + capituloTraduciendo.getId());

                //Se agrega el capitulo traducido a la biblioteca de traducidos
                filmacion.agregarABibliotecaTraducidas(capituloTraduciendo);
            }
        } catch (Exception e) {
        }
    }

    private void traduciendoCapitulo() throws Exception {
        Thread.sleep(5000);
    }

}
