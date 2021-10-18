package TrabajoPractico3;

import java.util.Random;

public class Hamaca {
    public synchronized void usarHamaca() {
        System.out.println(Thread.currentThread().getName() + " usando la hamaca.");
        try {
            Thread.sleep(new Random().nextInt(2) * 1000);
        } catch (InterruptedException e) {
        }
        System.out.println(Thread.currentThread().getName() + " termino de usar la hamaca.");
    }
}
