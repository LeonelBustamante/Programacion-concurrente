package TrabajoPractico3;

public class sumaMatriz2 {

    public synchronized int sumMatriz(int nums[], int posInicial, int posFinal) {
        int sum = 0;
        int pos = posInicial;
        while (pos <= posFinal) {
            // System.out.println(pos);
            sum += nums[pos];
            pos++;
        }

        return sum;
    }
}