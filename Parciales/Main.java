import java.util.Random;

class Main {
    public static void main(String[] args) {
        int cantAlumnos = 100;
        Acrobacia a = new Acrobacia();
        new Thread(new Control(a), "CONTROL").start();
        for (int i = 0; i < cantAlumnos; i++) {
            new Thread(new Alumno(a, new Random().nextInt(3), new Random().nextInt(3)), "Alumno - " + i);
        }
    }
}