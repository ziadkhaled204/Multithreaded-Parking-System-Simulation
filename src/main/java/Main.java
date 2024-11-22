import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        ParkingLot parkingLot = new ParkingLot(4);

        // Create lists to hold cars for each gate
        List<Car> gate1Cars = new ArrayList<>();
        List<Car> gate2Cars = new ArrayList<>();
        List<Car> gate3Cars = new ArrayList<>();

        // Input file name
        String filename = "data.txt";

        try {
            // Read the input file
            Scanner scanner = new Scanner(new File(filename));
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] parts = line.split(", ");

                // Parse each part of the line
                int gateNum = Integer.parseInt(parts[0].split(" ")[1]);
                int carId = Integer.parseInt(parts[1].split(" ")[1]);
                int arriveTime = Integer.parseInt(parts[2].split(" ")[1]);
                int parkTime = Integer.parseInt(parts[3].split(" ")[1]);

                // Create a Car object based on parsed values
                Car car = new Car(carId, gateNum, arriveTime, parkTime, parkingLot);

                // Add the car to the appropriate gate list based on the gate number
                switch (gateNum) {
                    case 1 -> gate1Cars.add(car);
                    case 2 -> gate2Cars.add(car);
                    case 3 -> gate3Cars.add(car);
                    default -> System.out.println("Invalid gate number for car: " + carId);
                }
            }
        } catch (FileNotFoundException e) {
            System.err.println("File " + filename + " does not exist.");
            return;
        }

        // Create Gate instances, each with its list of cars
        Gate gate1 = new Gate(gate1Cars, parkingLot);
        Gate gate2 = new Gate(gate2Cars, parkingLot);
        Gate gate3 = new Gate(gate3Cars, parkingLot);

        // Start each gate in a separate thread
        Thread gate1Thread = new Thread(gate1);
        Thread gate2Thread = new Thread(gate2);
        Thread gate3Thread = new Thread(gate3);

        gate1Thread.start();
        gate2Thread.start();
        gate3Thread.start();

        try {
            // Wait for all gate threads to complete
            gate1Thread.join();
            gate2Thread.join();
            gate3Thread.join();
        } catch (InterruptedException e) {
            System.err.println("Main thread was interrupted.");
            Thread.currentThread().interrupt();
        }

        // Print final parking statistics after all threads finish
        synchronized (parkingLot) { // Ensure thread-safe access
            System.out.println("\n=== Final Parking Lot Details ===");
            System.out.println("Total Cars Served: " + (gate1Cars.size() + gate2Cars.size() + gate3Cars.size()));
            System.out.println("Current Cars in Parking: " + parkingLot.getCurrentOccupiedSlots());
            System.out.println("Details:");
            System.out.println("- Gate 1 served " + gate1Cars.size() + " cars.");
            System.out.println("- Gate 2 served " + gate2Cars.size() + " cars.");
            System.out.println("- Gate 3 served " + gate3Cars.size() + " cars.");
            System.out.println("Remaining parking spots: "
                    + (parkingLot.getTotalSpots() - parkingLot.getCurrentOccupiedSlots()));
        }
    }
}
