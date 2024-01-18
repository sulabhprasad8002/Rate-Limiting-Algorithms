package TokenBucket;

public class TokenBucketRateLimiter {
    private final int maxTokens;
    private int tokens;
    private final long refillInterval;
    private long lastRefillTimestamp;

    public TokenBucketRateLimiter(int maxTokens, long refillInterval) {
        this.maxTokens = maxTokens;
        this.tokens = this.maxTokens;
        this.refillInterval = refillInterval;
        this.lastRefillTimestamp = System.currentTimeMillis();
    }

    public synchronized boolean allowRequest() {
        refillTokens();

        if(this.tokens > 0) {
            this.tokens--;
            return true;
        }

        return  false;
    }

    private void refillTokens() {
        long currentTime = System.currentTimeMillis();
        long elapsedTime = currentTime - lastRefillTimestamp;

        if(elapsedTime > refillInterval) {
            int tokensToAdd = (int) (elapsedTime / refillInterval);
            tokens = Math.min(tokens + tokensToAdd, maxTokens);
            lastRefillTimestamp = currentTime;
        }
    }
}
