public class VirtualThreadsExample {
    public static void main(String[] args) throws InterruptedException {
        System.out.println("Starting 100,000 virtual threads...");
        long startTime = System.currentTimeMillis();
        
        for (int i = 1; i <= 100000; i++) {
            final int threadNumber = i;
            Thread.startVirtualThread(() -> {
                System.out.println("Virtual Thread " + threadNumber + " is running");
            });
        }
        
        Thread.sleep(5000);
        
        long endTime = System.currentTimeMillis();
        System.out.println("All threads completed in " + (endTime - startTime) + " ms");
    }
}
