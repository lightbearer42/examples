import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

class ThreadExceptionExample {
    public static void main(String[] args) throws Exception {
        try {
            Future<Object> future = Executors.newFixedThreadPool(3).submit(() -> {
                System.out.println("T1");
                throw new RuntimeException();
            });
            future.get();
        } catch (ExecutionException e) {
            System.out.println("Catched T1 exp " + e);
        }

        // Not working
        try {
            Thread t2 = new Thread(() -> {
                System.out.println("T2");
                throw new RuntimeException();
            });
            t2.start();
            t2.join();
        } catch (Exception e) {
            System.out.println("Catched T2 exp " + e);
        }
        //

        Thread t3 = new Thread(() -> {
            System.out.println("T3");
            throw new RuntimeException();
        });
        t3.setUncaughtExceptionHandler(ThreadExceptionExample::handle);
        t3.start();
    }

    public static void handle(Thread t, Throwable e) {
        System.out.println("Catched T3 exp " + e);
    }
}
