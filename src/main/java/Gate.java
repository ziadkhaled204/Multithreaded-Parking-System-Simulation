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
        // Process each car sequentially in the gate
        for (Car car : cars) {
            try {
                // Run the car's logic
                car.run();

                // Sleep for 1 second between processing each car
                Thread.sleep(200);
            } catch (InterruptedException e) {
                System.out.println("Gate was interrupted while processing cars.");
                Thread.currentThread().interrupt();  // Preserve interrupt status
            }
        }
        System.out.println("Gate has finished processing all cars.");
    }
}