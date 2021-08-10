package com.wellsfargo.interview;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Publisher implements Runnable{

    List<Subscriber> subscribers = new ArrayList<>();
    Orchestrator orchestrator = null;

    Publisher (Orchestrator orchestrator){
        this.orchestrator = orchestrator;
    }

    public void publish() throws InterruptedException {
        Stream<Integer> infiniteStream = Stream.iterate(0, i -> i + 1);

        List<Integer> collect = infiniteStream.limit(100).collect(Collectors.toList());

        for (Integer i: collect) {
            String message = "Number " + i;
            System.out.println("Message Sent: "+message);
            Thread.sleep(50); //mimicking timetaken for generating message
            orchestrator.addMessage(message);
        }
    }


    @Override
    public void run() {
        try {
            publish();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
