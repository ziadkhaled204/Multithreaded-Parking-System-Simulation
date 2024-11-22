import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.atomic.AtomicInteger;

public class Semaphore {
    Queue<IProcess> q;
    final int initialSize;
    AtomicInteger size;

    public Semaphore(int size) {
        initialSize = size;
        this.size = new AtomicInteger(size);  // استخدام AtomicInteger
        q = new LinkedList<IProcess>();
    }

    public void waitt(IProcess p) {
        int currentSize = size.get();
        if (currentSize > 0) {
            size.decrementAndGet();  // تقليص العدد بطريقة ذرة
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
            size.incrementAndGet();  // زيادة العدد بطريقة ذرة
        }
    }

    public int getOccupied() {
        return Math.max(0, initialSize - size.get());  // الحصول على العدد باستخدام get()
    }
}
