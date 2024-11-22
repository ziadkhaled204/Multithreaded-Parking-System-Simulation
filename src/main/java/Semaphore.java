import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.atomic.AtomicInteger;

public class Semaphore {
    Queue<IProcess> q;
    final int initialSize;
    AtomicInteger size;

    public Semaphore(int size) {
        initialSize = size;
        this.size = new AtomicInteger(size);
        q = new LinkedList<IProcess>();
    }

    public void waitt(IProcess p) {
        int currentSize = size.get();
        if (currentSize > 0) {
            size.decrementAndGet();
        } else {
            q.add(p);
            try {
                p.block();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public void signall() {
        if (!q.isEmpty()) {
            IProcess nextProcess = q.remove();
            nextProcess.wakeup();
        } else {
            size.incrementAndGet();
        }
    }

    public int getOccupied() {
        return Math.max(0, initialSize - size.get());
    }
}
