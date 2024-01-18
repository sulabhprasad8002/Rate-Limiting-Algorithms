package FixedWindow;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class FixedWindowRateLimiter {
    private final int maxRequests;
    private final long windowMillis;
    private final BlockingQueue<Long> timeStamps;

    public FixedWindowRateLimiter(int maxRequests, long windowMillis) {
        this.maxRequests = maxRequests;
        this.windowMillis = windowMillis;
        this.timeStamps = new LinkedBlockingQueue<>();
    }

    public boolean allowRequests() {
        long currentTime = System.currentTimeMillis();

        removeOutdatedTimestamps(currentTime);

        if(timeStamps.size() < maxRequests) {
            timeStamps.offer(currentTime);
            return true;
        }

        return false;
    }

    public void removeOutdatedTimestamps(long currentTime) {
        while(!timeStamps.isEmpty() && timeStamps.peek() + windowMillis <= currentTime) {
            timeStamps.poll();
        }
    }
}
