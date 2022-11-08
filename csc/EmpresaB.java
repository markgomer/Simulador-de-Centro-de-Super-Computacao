import java.util.concurrent.Semaphore;
import java.util.Random;


public class EmpresaB implements Runnable {
    String id;
    Semaphore multiplexB, bUsando, liberado;
    Random rand = new Random();
    int max = 10_000;
    int min = 5_000;
    public static int contadorB = 0;

    EmpresaB(String id, Semaphore bUsando, Semaphore multiplexB, Semaphore liberado) {
        this.id = id;
        this.bUsando = bUsando;
        this.multiplexB = multiplexB;
        this.liberado = liberado;
    }


    /*
     * Release access from the computer
     */
    private void up() throws InterruptedException {
        multiplexB.release();
    
        unlock();
    }


    /*
     * Ask access to the computer
     */
    private void down() throws InterruptedException {
        Thread.sleep(rand.nextInt((4000) + 1) + 1000); //tempo aleatorio entre 1 e 4s
        System.out.println(id + " tentando acesso");
        
        lock();
        
        multiplexB.acquire();
    }


    /*
     * Check if this thread is able to enter the computer.
     * It locks for EmpresaB if the computer is empty
     */
    private void lock() throws InterruptedException {
        bUsando.acquire();
            contadorB++;
            if(contadorB==1)
                liberado.acquire();
        bUsando.release();
    }
    

    /*
     * Unlocks the computer for EmpresaB if this thread is 
     * the last to exit.
     */
    private void unlock() throws InterruptedException {
        bUsando.acquire();
            contadorB--;
            if(contadorB==0)
                liberado.release();
        bUsando.release();
    }


    /*
     * Simulate this thread is using the computer
     * by putting it to sleep for a random time.
     */
    private void simulateUse() throws InterruptedException {
        System.out.println("+ " + id + " acessou");
        Thread.sleep(rand.nextInt((max - min) + 1) + min); //tempo aleatorio entre min e max
        System.out.println("- " + id + " terminou o acesso");
    }


    @Override
    public void run() {
        try {
            down();
            simulateUse();
            up();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
