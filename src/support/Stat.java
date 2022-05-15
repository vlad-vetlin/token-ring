package support;

public class Stat {
    private final long throughput;

    private final double latency;

    public Stat(double latency, long throughput) {
        this.latency = latency;
        this.throughput = throughput;
    }

    public long getThroughput() {
        return throughput;
    }

    public double getLatency() {
        return latency;
    }
}
