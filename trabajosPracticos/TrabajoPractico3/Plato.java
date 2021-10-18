package TrabajoPractico3;

import java.util.Random;

public class Plato {
    public synchronized void usarPlato() {
        System.out.println(Thread.currentThread().getName() + " usando el plato.");
        try {
            Thread.sleep(new Random().nextInt(2) * 1000);
        } catch (InterruptedException e) {
        }
        System.out.println(Thread.currentThread().getName() + " termino de usar el plato.");

    }

}
