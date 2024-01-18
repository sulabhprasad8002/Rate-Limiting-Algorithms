import FixedWindow.FixedWindowRateLimiter;
import LeakyBucket.LeakyBucketRateLimiter;
import SlidingWindow.SlidingWindowRateLimiter;
import TokenBucket.TokenBucketRateLimiter;

public class RateLimiterTestDrive {
    public static void main(String[] args) {
//        runFixedWindowRateLimiter();
//        runSlidingWindowRateLimiter();
//        runTokenBucketRateLimiter();
        runLeakyBucketRateLimiter();
    }

    public static void runFixedWindowRateLimiter() {
        FixedWindowRateLimiter fixedWindowRateLimiter = new FixedWindowRateLimiter(5, 1000);

        for(int i=1; i<=20; i++) {
            if(fixedWindowRateLimiter.allowRequests()) {
                System.out.println("Request no " + i + " is allowed");
            } else {
                System.out.println("Request no " + i + " is denied");
            }

            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public static void runSlidingWindowRateLimiter() {
        SlidingWindowRateLimiter rateLimiter = new SlidingWindowRateLimiter(5, 1000);

        for(int i=1; i<=20; i++) {
            if(rateLimiter.allowRequest()) {
                System.out.println("Request no " + i + " is allowed");
            } else {
                System.out.println("Request no " + i + " is denied");
            }

            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public static void runTokenBucketRateLimiter() {
        TokenBucketRateLimiter rateLimiter = new TokenBucketRateLimiter(10, 200);

        for(int i=1; i<=10; i++) {
            if(rateLimiter.allowRequest()) {
                System.out.println("Request no " + i + " is allowed");
            } else {
                System.out.println("Request no " + i + " is denied");
            }

            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public static void runLeakyBucketRateLimiter() {
        LeakyBucketRateLimiter rateLimiter = new LeakyBucketRateLimiter(200, 10, 2);
        for(int i=1; i<=20; i++) {
            if(rateLimiter.allowRequest()) {
                System.out.println("Request no " + i + " is allowed");
            } else {
                System.out.println("Request no " + i + " is denied");
            }

            try {
                Thread.sleep(300);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
