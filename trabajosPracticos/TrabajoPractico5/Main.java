package TrabajoPractico5;

public class Main {
    public static void main(String[] args) {
        int maximosEnElComedor = 3;
        int cantGatos = 2;
        int cantPerros = 3;
        int cantAnimales = cantGatos + cantPerros;
        String nombre;

        Comedor c = new Comedor(maximosEnElComedor);
        Thread[] threads = new Thread[cantAnimales];

        for (int i = 0; i < cantAnimales; i++) {
            nombre = i < cantPerros ? "PERRO " + i : "GATO " + i;
            threads[i] = new Thread(new Animal(nombre, (i % 2 == 0), c));
            threads[i].start();
        }

        try {
            for (int i = 0; i < threads.length; i++) {
                threads[i].join();
            }
        } catch (Exception e) {
        }
        System.out.println("TERMINO EL PROGRAMA");
    }
}
