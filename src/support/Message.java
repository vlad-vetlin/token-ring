package support;

import java.util.Timer;

public class Message {
    private long sumLatency;

    private long processedCount;

    private long lastTime;

    private String text;

    public Message(String text) {
        this.text = text;
        lastTime = System.nanoTime();
    }

    public void process() {
        long time = getTime();
        sumLatency += time;
        ++processedCount;
    }

    public long getSumLatency() {
        long result = sumLatency;
        sumLatency = 0;
        return result;
    }

    public long getProcessedCount() {
        long result = processedCount;
        processedCount = 0;

        return result;
    }

    private long getTime() {
        long curTime = System.nanoTime() - lastTime;
        lastTime = System.nanoTime();
        return curTime;
    }

    public String getText() {
        return text;
    }
}
