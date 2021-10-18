package TrabajoPractico3;

public class sumaMatriz {
    private int sum;

    public synchronized int sumMatriz(int nums[]) {
        sum = 0;
        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];
            System.out.println("Total acumulado de " + Thread.currentThread().getName() + " es " + sum);
            try {
                Thread.sleep(10);
            } catch (InterruptedException exc) {
                System.out.println("Hilo interrumpido");
            }
        }
        return sum;
    }
}