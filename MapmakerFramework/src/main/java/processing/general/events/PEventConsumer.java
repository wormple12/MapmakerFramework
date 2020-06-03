package processing.general.events;

import java.util.concurrent.BlockingQueue;

/**
 *
 * @author Simon Norup
 */
public class PEventConsumer implements Runnable {

    private final BlockingQueue<Runnable> eventQueue;

    public PEventConsumer(BlockingQueue<Runnable> eventQueue) {
        this.eventQueue = eventQueue;
    }

    @Override
    public void run() {
        try {
            while (true) {
                Runnable event = eventQueue.take();
                event.run();
            }
        } catch (InterruptedException e) {
            System.out.println("Interrupted...");
        }
    }

}
