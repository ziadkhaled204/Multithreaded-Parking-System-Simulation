import java.util.ArrayList;
import java.util.List;

public class Gate implements Runnable {
    private final List<Car> cars;
    private final ParkingLot parkingLot;

    public Gate(List<Car> cars, ParkingLot parkingLot) {
        this.cars = cars;
        this.parkingLot = parkingLot;
    }

    @Override
    public void run() {
        List<Thread> carThreads = new ArrayList<>();
        for (Car car : cars) {
            // Start a new thread for each car to process it in parallel
            Thread carThread = new Thread(car);
            carThread.start();
            carThreads.add(carThread);
            try {
                // Sleep for 1 second between processing each car (this is not blocking the car threads)
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                System.out.println("Gate was interrupted while processing cars.");
                Thread.currentThread().interrupt();  // Preserve interrupt status
            }
        }
        for (Thread carThread : carThreads) { // <<==============================added to the code
            try {
                carThread.join(); // Wait for each car thread to finish
            } catch (InterruptedException e) {
                System.out.println("Gate was interrupted while waiting for car threads.");
                Thread.currentThread().interrupt();
            }
        }

        System.out.println("Gate has started processing all cars in parallel.");
    }
}