package async;

import elevator.Application;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.management.MemoryMXBean;
import java.lang.management.MemoryUsage;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.util.Properties;
import java.util.concurrent.*;

public class AsyncProgramming {

    private static Logger logger = LoggerFactory.getLogger(AsyncProgramming.class);

    public void simpleCompletableFuture(){
        logger.info("A simple completable future with a manual completion of the future");
        CompletableFuture<String> completableFuture = new CompletableFuture<>();
        try {
            logger.info("Await a get from completableFuture for 10 seconds");
            logger.info(completableFuture.get(10, TimeUnit.SECONDS));
            logger.info("Waited 10 seconds for the future to complete, now completing it manually");
            completableFuture.complete("Hello, I am complete!");
        } catch (Exception ex){
            logger.error("CompletableFuture failed - {}", ex);
        }
    }

    public void methodToPrintData(){
        for (int i = 0; i < 10; i++) {
            logger.info("Printing - {}", i);
        }
    }

    public Properties methodToProvideData(){
        return System.getProperties();
    }
    public void usingRunAsync() {
        try {
            CompletableFuture<Void> runTaskWithoutOutput = CompletableFuture.runAsync(() -> {
                methodToPrintData();
            });
            // To execute the method you need to call .get() which would block the execution till the future is finished
            runTaskWithoutOutput.get();
        } catch (Exception ex){
            logger.error("Exception on usingRunAsync  ", ex);
        }
    }

    /**
     * Completable Future with Executor rather than using the default Forkjoin Pool.
     */
    public void usingRunAsyncWithExecutor() {
        Executor executor = Executors.newSingleThreadExecutor(Executors.defaultThreadFactory());
        try {
            CompletableFuture<Void> runTaskWithoutOutput = CompletableFuture.runAsync(() -> {
                methodToPrintData();
            },executor);
            // To execute the method you need to call .get() which would block the execution till the future is finished
            runTaskWithoutOutput.get();
        } catch (Exception ex){
            logger.error("Exception on usingRunAsync  ", ex);
        }
    }

    /**
     * Using a Supplier instead of Runnbale means we expect there should be an output from the task we are submitting to the completable future.
     * This means the Supplier would take no input but provide an output.
     */
    public void usingSupplyAsync() {
        try {
            CompletableFuture<Properties> runTaskWithOutput = CompletableFuture.supplyAsync(() -> methodToProvideData());
            // To execute the method you need to call .get() which would block the execution till the future is finished
            logger.info(runTaskWithOutput.get().toString());
        } catch (Exception ex) {
            logger.error("Exception on usingRunAsync  ", ex);
        }
    }
    public void usingSupplyAsyncWithExceutor() {
        Executor executor = Executors.newSingleThreadExecutor(Executors.defaultThreadFactory());
        try {
            CompletableFuture<Properties> runTaskWithOutput = CompletableFuture.supplyAsync(() -> methodToProvideData(), executor);
            // To execute the method you need to call .get() which would block the execution till the future is finished
            logger.info(runTaskWithOutput.get().toString());
        } catch (Exception ex) {
            logger.error("Exception on usingRunAsync  ", ex);
        }
    }

    /**
     *
     * To take full advantage of the completable future we dont want to actually block and wait for the task to be finished , instead we want the task to proceed on its own
     * and we should progess to do other things or tasks. This would help us chain methods some of which can provide an output or complete on their own time and we can work through
     * all the tasks without any issues. This can be done by having a callback function once the first task is completed.
     */

    public void chainingSupplyAsync(){
        try {
        CompletableFuture<Properties> taskWithouput = CompletableFuture.supplyAsync(() -> methodToProvideData());
        taskWithouput.thenApply((properties)-> {
          return  properties.setProperty("name", "test");
        });
        logger.info(taskWithouput.get().get("name").toString());
        } catch (Exception ex){
            logger.error("Exception on chainingSupplyAsync  ", ex);
        }

    }
    public static void main(String[] args) {
        AsyncProgramming asyncProgramming = new AsyncProgramming();
//        asyncProgramming.simpleCompletableFuture();
//        asyncProgramming.usingRunAsync();
//        asyncProgramming.usingRunAsyncWithExecutor();
//

//        asyncProgramming.usingSupplyAsync();
//        asyncProgramming.usingSupplyAsyncWithExceutor();

        asyncProgramming.chainingSupplyAsync();
    }

}
