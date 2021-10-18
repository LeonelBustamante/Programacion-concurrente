package TrabajoPractico3;

public class Main {
    public static void main(String[] args) {
        // EJERCICIO 1
        // VerificarCuenta vc = new VerificarCuenta();
        // Thread Luis = new Thread(vc, "Luis");
        // Thread Manuel = new Thread(vc, "Manuel");
        // Luis.start();
        // Manuel.start();

        // EJERCICIO 2
        // Vida vida = new Vida();
        // Orco orco = new Orco(vida);
        // Curandero curandero = new Curandero(vida);
        // Thread h1 = new Thread(orco, "Orco");
        // Thread h2 = new Thread(curandero, "Curandero");
        // h1.start();
        // h2.start();
        // try {
        // h1.join();
        // h2.join();
        // } catch (InterruptedException ex) {
        // }
        // System.out.println("final " + vida.getVida());

        // EJERCICIO 3
        // int arr[] = { 1, 2, 3, 4, 5 }; // puede utilizar distintos valores para ver
        // los cambios con otros valores.
        // MiHilo mh1 = MiHilo.creaEInicia("#1", arr);
        // MiHilo mh2 = MiHilo.creaEInicia("#2", arr);
        // try {
        // mh1.hilo.join();
        // mh2.hilo.join();
        // } catch (InterruptedException exc) {
        // System.out.println("Hilo principal interrumpido.");
        // }

        // EJERCICIO 4
        // Hamster h = new Hamster();
        // Thread t1 = new Thread(h, "Hamster-1");
        // Thread t2 = new Thread(h, "Hamster-2");
        // Thread t3 = new Thread(h, "Hamster-3");
        // Thread t4 = new Thread(h, "Hamster-4");
        // t1.start();
        // t2.start();
        // t3.start();
        // t4.start();

        // EJERCICIO 5
        // Letra a = new Letra(0, 'A', 1);
        // Letra b = new Letra(1, 'B', 2);
        // Letra c = new Letra(2, 'C', 3);
        // Thread t1 = new Thread(a);
        // Thread t2 = new Thread(b);
        // Thread t3 = new Thread(c);
        // t1.start();
        // t2.start();
        // t3.start();

        // EJERCICIO 7
        Letra a = new Letra(0, 'A', 1);
        Letra b = new Letra(1, 'B', 2);
        Letra c = new Letra(2, 'C', 3);
        Thread t1 = new Thread(a);
        Thread t2 = new Thread(b);
        Thread t3 = new Thread(c);
        t1.start();
        t2.start();
        t3.start();
    }
}
