package org.example.service;

import org.example.model.CommandWrapper;

import java.util.List;
import java.util.concurrent.BlockingQueue;

public class Producer implements Runnable {

    private final BlockingQueue<CommandWrapper> queue;
    private final List<CommandWrapper> commands;

    public Producer(BlockingQueue<CommandWrapper> queue,
                    List<CommandWrapper> commands) {
        this.queue = queue;
        this.commands = commands;
    }

    @Override
    public void run() {
        commands.forEach(command -> {
            try {
                queue.put(command);
                System.out.printf("Producer: %s push command %s %n",
                        Thread.currentThread().getName(),
                        command.getOperation());
            } catch (InterruptedException exception) {
                System.out.println("Error message"+ exception.getMessage());
            }
        });
    }
}
