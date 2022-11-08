import java.util.concurrent.Semaphore;
import java.util.Random;

public class EmpresaA implements Runnable {
    private static int contadorA = 0;
    
    private String id;
    private Semaphore multiplexA, aUsando, liberado;
    private Random rand = new Random();
    private final int max = 10_000;
    private final int min = 5_000;
    

    EmpresaA(String id, Semaphore aUsando, Semaphore multiplexA, Semaphore liberado){
        this.id = id;
        this.aUsando = aUsando;
        this.multiplexA = multiplexA;
        this.liberado = liberado;
    }


    /*
     * Release access from the computer
     */
    private void up() throws InterruptedException {
        multiplexA.release();
        unlock();
    }


    /*
     * Ask access to the computer
     */
    private void down() throws InterruptedException {
        System.out.println(id + " tentando acesso");
        lock();
        multiplexA.acquire();
    }


    /*
     * Check if this thread is able to enter the computer.
     * It locks for EmpresaB if the computer is empty
     */
    private void lock() throws InterruptedException {
        aUsando.acquire(); // make sure no other thread will try to lock at the same time
            contadorA++;
            if(contadorA==1)
                liberado.acquire();
        aUsando.release();
    }


    /*
     * Unlocks the computer for EmpresaB if this thread is 
     * the last to exit.
     */
    private void unlock() throws InterruptedException {
        aUsando.acquire(); // make sure no other thread will try to unlock at the same time
            contadorA--;
            if(contadorA==0)
                liberado.release();
        aUsando.release();
    }


    /*
     * Simulate this thread is using the computer
     * by putting it to sleep for a random time.
     */
    private void simulateUse() throws InterruptedException {
        System.out.println("+ " + id + " acessou");
        Thread.sleep(rand.nextInt((max - min) + 1) + min); // random time between min and max
        System.out.println("- " + id + " terminou o acesso");
    }


    @Override
    public void run() {
        try {
            Thread.sleep(rand.nextInt((4000) + 1) + 1000); //tempo aleatorio entre 1 e 4s
            down();
            simulateUse();
            up();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}