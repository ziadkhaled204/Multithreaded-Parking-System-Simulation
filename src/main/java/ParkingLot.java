import java.util.concurrent.Semaphore;

public class ParkingLot {
    private final Semaphore parkingSpots; // Semaphore to manage available spots
    static private int currentOccupied = 0; // Tracks the number of occupied spots
    private final int totalSpots; // Total number of parking spots

    public ParkingLot(int totalSpots) {
        this.totalSpots = totalSpots;
        this.parkingSpots = new Semaphore(totalSpots, true);
    }

    // Method to acquire a parking spot
    public synchronized boolean tryAcquire() {
        if (parkingSpots.tryAcquire()) {
            currentOccupied++;
            return true;
        }
        return false;
    }

    // Method to release a parking spot
    public synchronized void release() {
        if (currentOccupied > 0) {
            currentOccupied--;
            parkingSpots.release();
        }
    }

    // Car enters the parking lot
    public synchronized void carIn(int id, int gateNum, int arriveTime, int waitedTime) {
        if (waitedTime == 0) {
            System.out.println("Car " + id + " from Gate " + gateNum + " arrived at time " + arriveTime);
            System.out.println("Car " + id + " from Gate " + gateNum + " parked (Parking Status: "
                    + (currentOccupied) + " spots Occupied)");
        } else {
            System.out.println("Car " + id + " from Gate " + gateNum + " parked after waiting for "
                    + waitedTime + " seconds (Parking Status: "
                    + (currentOccupied) + " spots Occupied)");
        }
    }

    // Car leaves the parking lot
    public synchronized void carOut(int id, int gateNum, int duration) {
        System.out.println("Car " + id + " from Gate " + gateNum + " left after "
                + duration + " seconds (Parking Status: "
                + (currentOccupied) + " spots Occupied)");
        release(); // Release the parking spot
    }

    // Get the number of currently occupied spots
    public static synchronized int getCurrentOccupiedSlots() {
        return currentOccupied;
    }

    // Get the total number of parking spots
    public int getTotalSpots() {
        return totalSpots;
    }
}
