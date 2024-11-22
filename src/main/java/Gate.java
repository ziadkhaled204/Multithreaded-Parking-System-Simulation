import java.util.List;

public class Gate implements Runnable {
    private final List<Car> cars;       // List of cars that will pass through this gate
    private final ParkingLot parkingLot;

    public Gate(List<Car> cars, ParkingLot parkingLot) {
        this.cars = cars;
        this.parkingLot = parkingLot;
    }

    @Override
    public void run() {
        // Create a thread for each car to allow parallel processing
        for (Car car : cars) {
            new Thread(car).start(); // Start each car thread in parallel

            try {
                // Sleep for 1 second after starting the car thread (not after its execution)
                Thread.sleep(1000); // 1000 milliseconds = 1 second

            } catch (InterruptedException e) {
                // Handle thread interruption during sleep
                System.out.println("Gate thread was interrupted while waiting between cars.");
                Thread.currentThread().interrupt();  // Preserve interrupt status
                break;  // Exit loop if the gate processing is interrupted
            }
        }

        System.out.println("Gate has finished starting all car threads.");
    }
}
