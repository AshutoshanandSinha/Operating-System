/** The Main program for Producer-Consumer Problem.
 * Initializing the buffer max value to 15 and using a queue to put produced items(Generating Random Numbers)
 * @author Ashutoshanand Sinha
 */
import java.util.LinkedList;
import java.util.Queue;

public class PnCMain {

   static Queue<Integer> buffer = new LinkedList<>();
   static int bufferSize = 15;

    public static void main(String[] args){

        System.out.println("Starting Producer and Consumer Program : ");

        //Creating Threads for Producer and Consumer
        Thread producer = new Producer(buffer, bufferSize);
        Thread consumer = new Consumer(buffer);

        //Starting both, Producer and Consumer threads
        producer.start();
        consumer.start();
    }
}
