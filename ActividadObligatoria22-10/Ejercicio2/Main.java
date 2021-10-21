package Ejercicio2;

public class Main {

    public static void main(String[] args) {
        int cantidadBotellasPorCaja = 10;
        Caja2 caja = new Caja2(cantidadBotellasPorCaja);

        int cantEmpaquetadores = 1, cantEmbotelladores = 3;
        Thread[] threadsEmpaquetadores = new Thread[cantEmpaquetadores];
        Thread[] threadsEmbotelladores = new Thread[cantEmbotelladores];

        for (int i = 0; i < cantEmpaquetadores; i++) {
            threadsEmpaquetadores[i] = new Thread(new Empaquetador(caja), "Empaquetador " + i);
            threadsEmpaquetadores[i].start();
        }

        for (int i = 0; i < cantEmbotelladores; i++) {
            threadsEmbotelladores[i] = new Thread(new Embotellador(caja), "Embotellador " + i);
            threadsEmbotelladores[i].start();
        }
        try {
            for (int i = 0; i < cantEmpaquetadores; i++) {
                threadsEmpaquetadores[i].join();
            }

            for (int i = 0; i < cantEmbotelladores; i++) {
                threadsEmbotelladores[i].join();
            }

        } catch (Exception e) {
        }
        System.out.println("TERMINO");
    }
}
