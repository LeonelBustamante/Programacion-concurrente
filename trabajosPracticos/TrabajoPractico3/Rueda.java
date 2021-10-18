package TrabajoPractico3;

import java.util.Random;

public class Rueda {
    public synchronized void usarRueda() {
        System.out.println(Thread.currentThread().getName() + " usando la rueda.");
        try {
            Thread.sleep(new Random().nextInt(2) * 1000);
        } catch (InterruptedException e) {
        }
        System.out.println(Thread.currentThread().getName() + " termino de usar la rueda.");
    }
}
