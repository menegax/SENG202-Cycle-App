package seng202.team7.Controllers;

import javafx.concurrent.Task;

/**
 * ThreadHandler to allow for cancellation of threads throughout the applications
 * @author asm142 Aidan Smith
 */
public class ThreadHandler {

    public static Thread thread;

    /**
     * Constructor for the ThreadHandler object
     * @param task Task to be performed by the thread handler
     */
    public ThreadHandler(Task task) {
        this.thread = new Thread(task);
    }

    /**
     * Starts the ThreadHandler's thread
     */
    public void start() {
        thread.start();
    }
}
