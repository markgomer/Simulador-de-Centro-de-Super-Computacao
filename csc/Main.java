package csc;


public class Main {
    public static final int N = 10;  // número de funcionarios por empresa

    public static void main(String[] args) {
        Manager csc = new Manager();
        
        // criar as threads
        Thread [] empresaA = new Thread[N];
        Thread [] empresaB = new Thread[N];

        for (int i = 0; i < N; i++) {
            empresaA[i] = new Thread(new UserA("A"+i, csc));
            empresaB[i] = new Thread(new UserB("B"+i, csc));
        }

        for (int i = 0; i < N; i++) {
            empresaA[i].start();
            empresaB[i].start();
        }

        try {
            for (int i = 0; i < empresaB.length; i++) {
                empresaA[i].join();
                empresaB[i].join();
            }
        } catch (Exception e) { e.printStackTrace();}


        System.out.println("Fim thread principal");
    }
}