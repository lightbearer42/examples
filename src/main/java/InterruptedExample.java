public class InterruptedExample {
    public static void main(String[] args) {
        new Thread(() -> {
            try {
                Service.get();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        });
    }
}

class Service {
    public static String get() throws InterruptedException {
        Thread.sleep(1000);
        return "3253455";
    }
}
