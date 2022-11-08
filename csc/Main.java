/*
 * Trabalho 3 - Centro de Supercomputação
 * 
 * Integrantes:
 *  Eduardo Klein Nakatani
 *  Henrique Levandoski Richa
 *  Marco Aurélio Silva de Souza Júnior
 *  Rafael Bauer Sampaio
 *  Rafael Vitagliano Tannenbaum Nuñez
 */

import java.util.concurrent.Semaphore;

public class Main {
    public static final int nFuncionarios = 10;
    public static Semaphore liberado = new Semaphore(1);
    public static Semaphore aUsando = new Semaphore(1);
    public static Semaphore bUsando = new Semaphore(1);
    public static Semaphore multiplexA = new Semaphore(3);
    public static Semaphore multiplexB = new Semaphore(3);

    public static void main(String arg[]) throws InterruptedException{
        int i;
        Thread[] funcionariosA = new Thread[nFuncionarios];
        Thread[] funcionariosB = new Thread[nFuncionarios];

        //Cria os funcionario (threads)
        for(i=0; i<nFuncionarios; i++){
            funcionariosA[i] = new Thread(new EmpresaA("F" + (i+1) + "[A]", aUsando, multiplexA, liberado));
            funcionariosB[i] = new Thread(new EmpresaB("F" + (i+1) + "[B]", bUsando, multiplexB, liberado));
        }

        //Inicia os funcionarios (threads)
        for (i=0; i< nFuncionarios; ++i) {
            funcionariosA[i].start();
            funcionariosB[i].start();
        }
    }

}
