package csc;

import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.Random;


public class Manager {
    private Random r = new Random();

    /* 
    final Lock bananaMutex = new ReentrantLock();
    final Condition bananaNotFull  = bananaMutex.newCondition(); 
    final Condition bananaNotEmpty = bananaMutex.newCondition(); 
    
    final Lock macaMutex = new ReentrantLock();
    final Condition macaNotFull  = macaMutex.newCondition(); 
    final Condition macaNotEmpty = macaMutex.newCondition();
    */
    
    private static final int maxConcurrentUsers = 3;
    private static int concurrentUsers = 0;


    private final Lock A_Lock = new ReentrantLock();
    private final Condition A_NotFull  = A_Lock.newCondition(); 
    private final Condition A_NotEmpty = A_Lock.newCondition();
    
    private final Lock B_Lock = new ReentrantLock();
    private final Condition B_NotFull  = B_Lock.newCondition(); 
    private final Condition B_NotEmpty = B_Lock.newCondition();


    
    Lock switcher = new ReentrantLock ();
    Condition maleNotFull = switcher.newCondition();
    Condition femaleNotFull = switcher.newCondition();
    
    
    Semaphore maleMultiplex = new Semaphore (maxConcurrentUsers);
    Semaphore femaleMultiplex = new Semaphore (maxConcurrentUsers);


    public Manager() {
    
    }


    public void up(UserA a) {
        maleMultiplex.release();
        concurrentUsers--;
        if(concurrentUsers == 0) switcher.unlock();
    }
    
    
    public void up(UserB a) {
        femaleMultiplex.release();
        concurrentUsers--;
        if(concurrentUsers == 0) switcher.unlock();
    }


    public void down(UserA male) throws InterruptedException {
        // check if CSC is currently being used by A
        // turn switch for A
        switcher.lock();

        // check if there is space in the CSC for A (multiplex)
        maleMultiplex.acquire();
        concurrentUsers++;

    }

    
    public void down(UserB female) throws InterruptedException {
        // check if CSC is currently being used by A
        // turn switch for A
        switcher.lock();

        // check if there is space in the CSC for A (multiplex)
        femaleMultiplex.acquire();
        concurrentUsers++;

    }


    public void sleep(float segs) throws InterruptedException {
        Thread.sleep((long)(segs*1_000));
    }
    /* 
        empty = Semaphore (1);
        maleSwitch = Switch ();
        femaleSwitch = Switch ();   
        - ensure no more than 3 users are on the computer
        maleMultiplex = Semaphore (3);
        femaleMultiplex = Semaphore (3);
    */
    /*
        turnstile . wait ()
            maleSwitch . lock ( empty )
        turnstile . signal ()

            maleMultiplex . wait ()
                # bathroom code here
            maleMultiplex . signal ()

        maleSwitch . unlock ( empty )
     */

}
