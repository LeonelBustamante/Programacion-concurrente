package TrabajoPractico3;

public class CuentaBanco {
    private int balance = 50;

    public CuentaBanco() {
    }

    public synchronized int getBalance() {
        return balance;
    }

    public synchronized void retiroBancario(int retiro) {
        balance = balance - retiro;
    }
}