package LeakyBucket;

public class LeakyBucketRateLimiter {
    private long refillInterval;
    private final int capacity;
    private int tokens;
    private long lastRefillTime;
    private int refillRate;

    public LeakyBucketRateLimiter(long refillInterval, int capacity, int refillRate) {
        this.refillInterval = refillInterval;
        this.capacity = capacity;
        this.refillRate = refillRate;
        this.tokens = 0;
        this.lastRefillTime = System.currentTimeMillis();
    }

    public synchronized boolean allowRequest() {
        refillTokens();

        if(tokens > 0) {
            tokens--;
            return true;
        }

        return false;
    }

    public void refillTokens() {
        long currentTime = System.currentTimeMillis();
        long elapsedTime = currentTime - lastRefillTime;
        System.out.println(elapsedTime);

        if(elapsedTime >= refillInterval) {
            int tokenToAdd = (int) ((elapsedTime / refillInterval) * refillRate);

            tokens = Math.min(tokens + tokenToAdd, capacity);
            lastRefillTime = currentTime;
        }
    }
}
