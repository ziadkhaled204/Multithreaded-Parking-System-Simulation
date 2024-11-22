public class Car implements IProcess, Runnable {
    private CarData carData;
    private Semaphore parkingSemaphore;
    private long startWaitTime;
    private long endWaitTime;


    public Car(CarData carData, Semaphore parkingSemaphore) {
        this.carData = carData;
        this.parkingSemaphore = parkingSemaphore;
    }

    @Override
    public void run() {
        try {
            // the car arrive
            Thread.sleep(carData.getArrivalTime() * 1000);
            System.out.println("Car " + carData.getId() + " from Gate " + carData.getGate() + " arrived at time " + carData.getArrivalTime());

            // try to enter the parking
            startWaitTime = System.currentTimeMillis();
            parkingSemaphore.waitt(this);

            // the car entered
            endWaitTime = System.currentTimeMillis();
            int waitDuration = (int)((endWaitTime - startWaitTime) / 1000);
            System.out.println("Car " + carData.getId() + " from Gate " + carData.getGate() + " parked after waiting for " + waitDuration + " units of time. (Parking Status: " + parkingSemaphore.getOccupied() + " spots occupied)");
            Thread.sleep(carData.getParkingDuration() * 1000); // the car is parking

            // The car is pulling out of the parking lot.
            parkingSemaphore.signall();
            System.out.println("Car "+ carData.getId() +" from Gate " + carData.getGate() + " left after " + carData.getParkingDuration() + " units of time. (Parking Status: " + parkingSemaphore.getOccupied() + " spots occupied)");
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    @Override
    public synchronized void block() throws InterruptedException {
        System.out.println("Car " + carData.getId() + " from Gate " + carData.getGate() + " is waiting for a spot.");
        this.wait();
    }

    @Override
    public synchronized void wakeup() {
        this.notify();
    }
}
