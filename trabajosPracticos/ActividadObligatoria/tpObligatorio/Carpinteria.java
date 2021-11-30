package ActividadObligatoria.tpObligatorio;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Carpinteria {
    public static void main(String[] args) {
        int cantMaxMuebles = 8;
        int cantCarpinterosC1 = 3;
        int cantCarpinterosC2 = 3;
        int cantCarpinterosC3 = 3;
        int cantEnsambladores = 3;

        Muebleria m = new Muebleria(cantMaxMuebles);

        for (int i = 1; i <= cantEnsambladores; i++) {
            (new Thread(new Ensamblador(m), "Ensamblador_" + i)).start();
        }

        for (int i = 1; i <= cantCarpinterosC1; i++) {
            (new Thread(new Carpintero1(m), "Carpintero1_" + i)).start();
        }

        for (int i = 1; i <= cantCarpinterosC2; i++) {
            (new Thread(new Carpintero2(m), "Carpintero2_" + i)).start();
        }

        for (int i = 1; i <= cantCarpinterosC3; i++) {
            (new Thread(new Carpintero3(m), "Carpintero3_" + i)).start();
        }

    }

}

class Carpintero1 implements Runnable {
    private Muebleria muebleria;

    public Carpintero1(Muebleria m) {
        this.muebleria = m;
    }

    public void run() {
        try {
            System.out.println(Thread.currentThread().getName() + ": Comenzo a fabricar la parte 1");
            muebleria.fabricarParte1();
            System.out.println(Thread.currentThread().getName() + ": Termino de fabricar la parte 1");
        } catch (Exception e) {
        }
    }
}

class Carpintero2 implements Runnable {
    private Muebleria muebleria;

    public Carpintero2(Muebleria m) {
        this.muebleria = m;
    }

    public void run() {
        try {
            System.out.println(Thread.currentThread().getName() + ": Comenzo a fabricar la parte 2");
            muebleria.fabricarParte2();
            System.out.println(Thread.currentThread().getName() + ": Termino de fabricar la parte 2");
        } catch (InterruptedException ex) {
        }
    }
}

class Carpintero3 implements Runnable {
    private Muebleria muebleria;

    public Carpintero3(Muebleria m) {
        this.muebleria = m;
    }

    public void run() {
        try {
            System.out.println(Thread.currentThread().getName() + ": Comenzo a fabricar la parte 3");
            muebleria.fabricarParte3();
            System.out.println(Thread.currentThread().getName() + ": Termino de fabricar la parte 3");
        } catch (Exception e) {
        }
    }
}

class Ensamblador implements Runnable {
    private Muebleria muebleria;

    public Ensamblador(Muebleria m) {
        this.muebleria = m;
    }

    public void run() {
        try {
            System.out.println(Thread.currentThread().getName() + ": Intento ensamblar las partes");
            muebleria.ensamblar();
            System.out.println(Thread.currentThread().getName() + ": Terminé de ensamblar");
        } catch (InterruptedException ex) {
        }
    }
}

class Muebleria {
    private final int CANTMUEBLES;
    private int cantPiezas1, cantPiezas2, cantPiezas3, totalPiezas, mueblesArmados;
    private Lock l;
    private Condition cinta1, cinta2, cinta3, cintaEnsamble;

    public Muebleria(int CANTMUEBLES) {
        this.CANTMUEBLES = CANTMUEBLES;
        this.cantPiezas1 = 0;
        this.cantPiezas2 = 0;
        this.cantPiezas3 = 0;
        this.totalPiezas = 0;
        this.mueblesArmados = 0;
        this.l = new ReentrantLock();
        this.cinta1 = l.newCondition();
        this.cinta2 = l.newCondition();
        this.cinta3 = l.newCondition();
        this.cintaEnsamble = l.newCondition();
    }

    public void fabricarParte1() throws InterruptedException {
        while (mueblesArmados < CANTMUEBLES) {
            l.lock();
            try {
                while (cantPiezas1 > 0) {
                    System.out.println(Thread.currentThread().getName() + " ya hay una pieza en mi cinta");
                    cinta1.await(); // Si ya fabriqué una pieza1 espero
                }

                if (mueblesArmados < CANTMUEBLES) {
                    System.out.println(Thread.currentThread().getName() + ": fabrico pieza 1..");
                    cantPiezas1++;
                    totalPiezas++;

                    if (totalPiezas == 3) {
                        cintaEnsamble.signal();
                    }
                }
            } finally {
                l.unlock();
            }
        }
    }

    public void fabricarParte2() throws InterruptedException {
        while (mueblesArmados < CANTMUEBLES) {
            try {
                l.lock();

                while (cantPiezas2 > 0) {
                    System.out.println(Thread.currentThread().getName() + " ya hay una pieza en mi cinta");
                    cinta2.await(); // Si ya fabriqué una pieza2 espero
                }

                if (mueblesArmados < CANTMUEBLES) {
                    System.out.println(Thread.currentThread().getName() + ": fabrico pieza 2..");
                    cantPiezas2++;
                    totalPiezas++;

                    if (totalPiezas == 3) {
                        cintaEnsamble.signal();
                    }
                }
            } finally {
                l.unlock();
            }
        }
    }

    public void fabricarParte3() throws InterruptedException {
        while (mueblesArmados < CANTMUEBLES) {
            try {
                l.lock();

                while (cantPiezas3 > 0) {

                    System.out.println(Thread.currentThread().getName() + " ya hay una pieza en mi cinta");
                    cinta3.await(); // Si ya fabriqué una pieza3 espero
                }

                if (mueblesArmados < CANTMUEBLES) {
                    System.out.println(Thread.currentThread().getName() + ": fabrico pieza 3..");
                    cantPiezas3++;
                    totalPiezas++;

                    if (totalPiezas == 3) {
                        cintaEnsamble.signal();
                    }
                }
            } finally {
                l.unlock();
            }
        }
    }

    public void ensamblar() throws InterruptedException {
        while (mueblesArmados < CANTMUEBLES) {
            try {
                l.lock();
                while (totalPiezas < 3) {
                    System.out.println(Thread.currentThread().getName() + " faltan piezas ❗");
                    cintaEnsamble.await();
                }

                if (mueblesArmados < CANTMUEBLES) {
                    mueblesArmados++;
                    System.out.println(Thread.currentThread().getName() + " ensamblando... " + mueblesArmados);
                    cantPiezas1--;
                    cantPiezas2--;
                    cantPiezas3--;
                    totalPiezas = 0;
                    cinta1.signal();
                    cinta2.signal();
                    cinta3.signal();
                }

                if (mueblesArmados == CANTMUEBLES) {
                    totalPiezas = 3;
                    cinta1.signalAll();
                    cinta2.signalAll();
                    cinta3.signalAll();
                    cintaEnsamble.signalAll();
                }

            } finally {
                l.unlock();
            }
        }
    }
}