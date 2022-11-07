package csc;
import java.util.Random;


public class UserB implements Runnable {
    private String empresa = "B";
    private String funcionario;
    
    private Manager csc;
    
    private Random r = new Random();


    UserB(String funcionario, Manager csc) {
        this.funcionario = funcionario;
        this.csc = csc;
    }

    private void up() {
        csc.up(this);
        System.out.println("\t+ " + toString() + "terminou acesso");
    }

    private void down() throws InterruptedException {
        System.out.println(toString() + "tentando acesso");
        csc.down(this);
        System.out.println("\t+ " + toString() + "acessou");
    }

    private void sleep(float segs) throws InterruptedException {
        csc.sleep(segs);
    }


    @Override
    public void run() {
        while(true) {
            try {
                // wait to need to use the computer
                sleep(r.nextFloat(2F, 7F));
                // asks for access
                down();
                // use the computer (wait for some time)
                sleep(r.nextFloat(3F, 10F));
                // leave computer
                up();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public String toString() {
        return funcionario + " [" + empresa + "] ";
    }
}
