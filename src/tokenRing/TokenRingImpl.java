package tokenRing;

import exceptions.NotZeroSizeException;
import support.Message;
import support.Stat;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ConcurrentLinkedQueue;

public class TokenRingImpl implements TokenRing {
    private final TokenRingNodeImpl start;

    private final List<Message> messages = new ArrayList<>();

    private long startTime;

    private long lastProcessedSum = 0;

    public TokenRingImpl(int n) {
        if (n == 0) {
            throw new NotZeroSizeException();
        }

        TokenRingNodeImpl[] nodes = new TokenRingNodeImpl[n];

        for (int i = 0; i < n; ++i) {
            nodes[i] = new TokenRingNodeImpl(i);
        }

        for (int i = 0; i < n; ++i) {
            nodes[i].setNext(nodes[(i + 1) % n]);
        }

        for (int i = 0; i < n; ++i) {
            Thread nodeThread = new Thread(nodes[i]);
            nodeThread.start();
        }

        start = nodes[0];
    }

    public void addMessage(Message message) {
        messages.add(message);
        startTime = System.currentTimeMillis();
        start.addMessage(message);
    }

    public Stat getStats() {
        long processedCount = 0;
        long latencySum = 0;

        for (int i = 0; i < messages.size(); ++i) {
            processedCount += messages.get(i).getProcessedCount();
            latencySum += messages.get(i).getSumLatency();
        }

        double latency = (processedCount == 0 ? 0 : latencySum * 1.0 / processedCount);
        long throughput = processedCount;
        lastProcessedSum = processedCount;

        return new Stat(latency, throughput);
    }
}
