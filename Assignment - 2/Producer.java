/** The Producer Thread program.
 * Takes queue and maxSize of buffer.
 * @author Ashutoshanand Sinha
 */
import java.util.Queue;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public class Producer extends Thread {

    private Queue<Integer> queue ;
    private int bufferSize;

    //Initializing producer 
    Producer(Queue queue, int maxSize){
        this.queue = queue;
        this.bufferSize = maxSize;
    }

    //Producer running
    public void run() {

        while (true){

            synchronized (queue) {
                if (queue.size() == bufferSize) {
                    System.out.println("Buffer is full! Waiting for Consumer to consume. ");
                    try {
                        queue.wait();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                }
                //Producing random numbers and adding it to the buffer
                Random r = new Random();
                int num = r.nextInt();
                System.out.println("Producing.." + num);
                try {
                    TimeUnit.SECONDS.sleep(1);
                }
                catch (Exception e){
                    e.printStackTrace();
                }
                queue.add(num);
                queue.notify();
            }
        }
    }
}
