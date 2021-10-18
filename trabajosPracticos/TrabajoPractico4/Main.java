package TrabajoPractico4;

public class Main {
    public static void main(String[] args) {
        SynchronizedCounter c = new SynchronizedCounter();
        TestThread thread = new TestThread(c, "h1");
        TestThread thread1 = new TestThread(c, "h2");
        TestThread thread2 = new TestThread(c, "h3");
        TestThread thread3 = new TestThread(c, "h4");
        thread.start();
        thread1.start();
        thread2.start();
        thread3.start();
    }

}
