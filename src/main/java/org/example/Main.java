package org.example;

import org.example.model.CommandWrapper;
import org.example.model.User;
import org.example.service.Consumer;
import org.example.service.Producer;
import org.example.service.UsersDaoImpl;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

import static org.example.model.Operation.*;
//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {

        UsersDaoImpl usersDao = new UsersDaoImpl();

        BlockingQueue<CommandWrapper> queue = new ArrayBlockingQueue<>(2);
        List<CommandWrapper> commands = new ArrayList<>();
        commands.add(new CommandWrapper(ADD, new User(1,"1a","name")));
        commands.add(new CommandWrapper(ADD, new User(2,"1a","name")));
        commands.add(new CommandWrapper(PRINT, null));
        commands.add(new CommandWrapper(DELETE, null));
        commands.add(new CommandWrapper(PRINT, null));
        commands.add(new CommandWrapper(FINISH, null));

        Thread producer = new Thread(new Producer(queue, commands));
        Thread consumer = new Thread(new Consumer(queue, usersDao));
        producer.start();
        consumer.start();
    }
}