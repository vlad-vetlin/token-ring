package support;

import tokenRing.TokenRing;
import tokenRing.TokenRingImpl;

public class Checker {
    int fromN = 2;
    int toN = 6;
    int stepN = 1;

    int fromM = 5;
    int toM = 30;
    int stepM = 2;

    public void check() throws InterruptedException {
        int i = 6;
        TokenRing tokenRing = new TokenRingImpl(i);
        for (int t = 0; t < fromM - stepM; ++t) {
            tokenRing.addMessage(new Message("TEST " + t));
        }

        Thread.sleep(1000);

        for (int j = fromM; j < toM; j += stepM) {
            for (int t = 0; t < stepM; ++t) {
                tokenRing.addMessage(new Message("TEST " + j));
            }

            Thread.sleep(1000);

            long throughput = 0;
            double latency = 0;
            for (int t = 0; t < 10; ++t) {
                Stat stat = tokenRing.getStats();

                throughput += stat.getThroughput();
                latency += stat.getLatency();

                Thread.sleep(1000);
            }

            System.out.println("N: " + i + ". M: " + j + ". Throughput: " + throughput * 1.0 / 10 + ". Latency: " + latency / 10);
        }
    }

}
