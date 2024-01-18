package SlidingWindow;

import java.util.LinkedList;

public class SlidingWindowRateLimiter {
    private final int maxRequest;
    private final long windowMillis;
    private LinkedList<Long> timeStamps;

    public SlidingWindowRateLimiter(int maxRequest, long windowMillis) {
        this.maxRequest = maxRequest;
        this.windowMillis = windowMillis;

        timeStamps = new LinkedList();
    }

    public synchronized boolean allowRequest() {
        long currentTime = System.currentTimeMillis();

        removeOutdatedTimestamp(currentTime);

        if(timeStamps.size() < maxRequest) {
            timeStamps.add(currentTime);
            return true;
        }

        return false;
    }

    private void removeOutdatedTimestamp(long currentTime) {
        while(!timeStamps.isEmpty() && timeStamps.getLast() + windowMillis <= currentTime) {
            timeStamps.removeFirst();
        }
    }
}
