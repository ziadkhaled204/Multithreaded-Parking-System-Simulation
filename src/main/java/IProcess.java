public interface IProcess {
    void block() throws InterruptedException;
    void wakeup();
}