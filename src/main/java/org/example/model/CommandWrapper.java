package org.example.model;

public class CommandWrapper {

    private Operation operation;
    private User user;

    public CommandWrapper(Operation operation, User user) {
        this.operation = operation;
        this.user = user;
    }

    public Operation getOperation() {
        return operation;
    }

    public void setOperation(Operation operation) {
        this.operation = operation;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
