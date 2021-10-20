package TrabajoPractico5.Ejercicio1;

public class Main {
    public static void main(String[] args) {
        int cantPlatos = 2;
        Comedor comedero = new Comedor(cantPlatos); // cantPlatos

        int cantGatos = 10, cantPerros = 10;
        Thread[] gatos = new Thread[cantGatos]; // cantGatos
        Thread[] perros = new Thread[cantPerros]; // cantPlatos

        for (int i = 0; i < cantGatos; i++) {
            gatos[i] = new Thread(new Gato(comedero), "Gato-" + i);
            gatos[i].start();
        }

        for (int i = 0; i < cantPerros; i++) {
            perros[i] = new Thread(new Perro(comedero), "Perro-" + i);
            perros[i].start();
        }
        try {
            for (int i = 0; i < cantGatos; i++) {
                gatos[i].join();
            }

            for (int i = 0; i < cantPerros; i++) {
                perros[i].start();
            }

        } catch (Exception e) {
        }
        System.out.println("TERMINO");
    }
}
