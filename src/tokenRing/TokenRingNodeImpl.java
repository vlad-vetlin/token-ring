package tokenRing;

import queue.Queue;
import queue.SimpleQueue;
import support.Message;

import java.util.*;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class TokenRingNodeImpl implements Runnable, TokenRingNode {
    private final int MAX_SIZE = 10000000;

    private TokenRingNode next;

    private final int index;

    private final Queue<Message> messages = new queue.SimpleQueue<>();

    public TokenRingNodeImpl(int index) {
        this.index = index;
    }

    public void setNext(TokenRingNodeImpl next) {
        this.next = next;
    }

    public void addMessage(Message message) {
        messages.add(message);
    }

    @Override
    public void run() {
        while (true) {
            try {
                processMessage();
            } catch (InterruptedException exception) {
                System.out.println("Interrupted exception in node: " + index);
            }
        }
    }

    private void processMessage() throws InterruptedException {
        Message message;

        if (messages.isEmpty()) {
            return;
        }

        message = messages.poll();

        message.process();
        next.addMessage(message);
    }
}
