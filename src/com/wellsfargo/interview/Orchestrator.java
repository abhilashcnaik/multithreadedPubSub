package com.wellsfargo.interview;


import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.*;

public class Orchestrator   {

    private volatile Queue<String> queue = new ConcurrentLinkedQueue<>();

    private List<Subscriber> subscribers = new ArrayList<>();

    public void addMessage(String message) {
        queue.add(message);
    }

    public static void main(String... args) throws InterruptedException {
        Orchestrator orchestrator = new Orchestrator();

        ExecutorService service = Executors.newFixedThreadPool(2);
        System.out.println("Starting Publisher");
        service.submit(new Publisher(orchestrator));
        Thread.sleep(100); //adding delay to start the publisher

        System.out.println("Starting subscriber");
        service.submit(new Subscriber(orchestrator));

        service.shutdown();

    }

    public Queue<String> getQueue(){
        return queue;
    }
}
