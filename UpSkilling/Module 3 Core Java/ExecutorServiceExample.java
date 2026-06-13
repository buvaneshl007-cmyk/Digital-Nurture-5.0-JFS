import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public class ExecutorServiceExample {
    
    static class CalculationTask implements Callable<Integer> {
        private int number;
        
        public CalculationTask(int number) {
            this.number = number;
        }
        
        @Override
        public Integer call() throws Exception {
            return number * number;
        }
    }
    
    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(5);
        
        List<Callable<Integer>> tasks = new ArrayList<>();
        for (int i = 1; i <= 10; i++) {
            tasks.add(new CalculationTask(i));
        }
        
        try {
            List<Future<Integer>> futures = executorService.invokeAll(tasks);
            
            System.out.println("Results:");
            for (int i = 0; i < futures.size(); i++) {
                Future<Integer> future = futures.get(i);
                Integer result = future.get();
                System.out.println("Square of " + (i + 1) + " = " + result);
            }
        } catch (InterruptedException | ExecutionException e) {
            System.out.println("Executor error: " + e.getMessage());
        } finally {
            executorService.shutdown();
        }
    }
}
