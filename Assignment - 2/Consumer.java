/** The Consumer Thread program.
 * Takes processes buffer as input and consume item .
 * @author Ashutoshanand Sinha
 */
import java.util.Queue;
import java.util.concurrent.TimeUnit;

public class Consumer extends Thread {

    private Queue<Integer> queue;


    Consumer(Queue<Integer> queue){

        this.queue=queue;
    }

    public void run() {
        while (true){

            synchronized (queue) {

                if (queue.isEmpty()) {

                    System.out.println("No items on buffer, waiting for producer to produce items.");

                    try {
                        queue.wait();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                System.out.println("Consuming....." + queue.peek());
                try {
                    TimeUnit.SECONDS.sleep(1);
                }
                catch (Exception e){
                    e.printStackTrace();
                }
                queue.poll();
                queue.notify();
            }
        }
    }
}
