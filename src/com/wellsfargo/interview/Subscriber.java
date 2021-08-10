package com.wellsfargo.interview;

import com.sun.corba.se.spi.orbutil.threadpool.Work;
import sun.text.resources.cldr.chr.FormatData_chr;

import java.util.Queue;
import java.util.concurrent.*;

public class Subscriber implements Runnable {

    ThreadPoolExecutor  executorService = null;

    Orchestrator orchestrator = null;
    WorkerThread worker = new WorkerThread();

    public Subscriber(Orchestrator orchestrator){
        this.orchestrator = orchestrator;
        executorService = (ThreadPoolExecutor) Executors.newCachedThreadPool();

    }

    public void subscribe() throws InterruptedException {
        System.out.println("pool size "+executorService.getActiveCount());
        WorkerThread worker = new WorkerThread();

        Queue<String> queue = orchestrator.getQueue();

        while(queue.size() > 0) {

            worker.setMessage(queue.poll());
            executorService.submit(worker);
            Thread.sleep(100);
        }

        executorService.shutdown();
    }


    @Override
    public void run() {
        try {
            subscribe();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

