package org.example.service;

import org.example.model.CommandWrapper;
import java.util.concurrent.BlockingQueue;

public class Consumer implements Runnable {

    private final UsersDao usersDao;
    private final BlockingQueue<CommandWrapper> queue;
    private static final String LOG_MESSAGE = "Consumer: %s pull command %s %n";

    public Consumer(BlockingQueue<CommandWrapper> queue,
                    UsersDao usersDao) {
        this.queue = queue;
        this.usersDao = usersDao;
    }

    @Override
    public void run() {
        boolean run = true;
        try {
            while (run) {
                CommandWrapper command = queue.take();
                switch (command.getOperation()) {
                    case ADD -> {
                        usersDao.add(command.getUser());
                        System.out.printf(LOG_MESSAGE,
                                Thread.currentThread().getName(),
                                command.getOperation());
                    }
                    case PRINT -> {
                        usersDao.printAll();
                        System.out.printf(LOG_MESSAGE,
                                Thread.currentThread().getName(),
                                command.getOperation());
                    }
                    case DELETE -> {
                        usersDao.deleteAll();
                        System.out.printf(LOG_MESSAGE,
                                Thread.currentThread().getName(),
                                command.getOperation());
                    }
                    case FINISH -> run = false;
                }
            }
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
