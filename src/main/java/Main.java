import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        // Create lists to hold cars for each gate
        List<Car> gate1Cars = new ArrayList<>();
        List<Car> gate2Cars = new ArrayList<>();
        List<Car> gate3Cars = new ArrayList<>();

        // Input file name
        String filename = "parking_simulation_data.txt";

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
                Car car = new Car(carId, gateNum, arriveTime, parkTime);

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

        // Create a list of ParkingLot instances, each with a capacity of 1 slot
        List<ParkingLot> slots = new ArrayList<>(4);
        for (int i = 0; i < 4; i++) {
            slots.add(new ParkingLot(1)); // Each parking lot has a capacity of 1
        }

        // Create Gate instances, each using the list of parking lots
        Gate gate1 = new Gate(gate1Cars, new ParkingLot(1));
        Gate gate2 = new Gate(gate2Cars, new ParkingLot(1));
        Gate gate3 = new Gate(gate3Cars, new ParkingLot(1));

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

        // Print final parking statistics
        System.out.println("\nTotal Cars Served: " + (gate1Cars.size() + gate2Cars.size() + gate3Cars.size()));
        System.out.println("Current Cars in Parking: " + ParkingLot.getCurrentOccupiedSlots(slots));
        System.out.println("Details:");
        System.out.println("- Gate 1 served " + gate1Cars.size() + " cars.");
        System.out.println("- Gate 2 served " + gate2Cars.size() + " cars.");
        System.out.println("- Gate 3 served " + gate3Cars.size() + " cars.");
    }
}
