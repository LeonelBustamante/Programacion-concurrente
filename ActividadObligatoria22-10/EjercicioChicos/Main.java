package EjercicioChicos;

import EjercicioChicos.Forma1.Instrucciones;
import EjercicioChicos.Forma1.t1;
import EjercicioChicos.Forma1.t2;

//import ProgrmacionConcurrente.TpObligatorio.Forma1.t1;

public class Main {

    public static void main(String[] args) {

        Instrucciones in = new Instrucciones(1, 2, 3);
        Thread t1 = new Thread(new t1(in), "t1");
        Thread t2 = new Thread(new t2(in), "t2");

        t1.start();
        t2.start();

        try {
            t1.join();
            t2.join();
        } catch (Exception e) {
        }

        in.s3();
        in.s4();
        System.out.println(in.getW());
    }

    // public static void main(String[] args) {
    // Instrucciones in = new Instrucciones(18, 12, 10);
    // Thread[] arr = new Thread[4];
    // for (int i = 0; i < args.length; i++) {
    // arr[i] = new Thread(new Hilo(i + 1, in), "t" + i);
    // arr[i].start();
    // }

    // try {
    // for (int i = 0; i < arr.length; i++) {
    // arr[i].join();
    // }
    // } catch (Exception e) {
    // }
    // System.out.println(in.getW());
    // }
}