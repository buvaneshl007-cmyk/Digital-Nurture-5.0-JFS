public class ThreadExample extends Thread {
    private String threadName;
    
    public ThreadExample(String name) {
        this.threadName = name;
    }
    
    @Override
    public void run() {
        for (int i = 1; i <= 5; i++) {
            System.out.println(threadName + ": Message " + i);
            try {
                Thread.sleep(1000); // Sleep for 1 second
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
    
    public static void main(String[] args) {
        ThreadExample thread1 = new ThreadExample("Thread-1");
        ThreadExample thread2 = new ThreadExample("Thread-2");
        
        thread1.start();
        thread2.start();
    }
}
