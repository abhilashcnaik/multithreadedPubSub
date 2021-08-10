package com.wellsfargo.interview;

import com.sun.corba.se.spi.orbutil.threadpool.Work;

import java.util.Random;
import java.util.concurrent.Callable;

public class WorkerThread implements Runnable {
    String message = "";


    public void setMessage (String message) {
        this.message = message;
    }

    @Override
    public void run() {

            System.out.println("Message received: "+message);


    }
}
