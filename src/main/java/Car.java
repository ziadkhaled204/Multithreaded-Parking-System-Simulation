public class Car implements Runnable {
    public final int CarId;
    public final int GateNum;
    public final int Arrival;
    public final int Duration;
    public int WaitedTime = 0;
    private final ParkingLot parkingLot;

    public Car(int CarId, int GateNum, int Arrival, int Duration, ParkingLot parkingLot) {
        this.CarId = CarId;
        this.GateNum = GateNum;
        this.Arrival = Arrival;
        this.Duration = Duration;
        this.parkingLot = parkingLot;
    }

    @Override
    public void run() {
        while (!parkingLot.tryAcquire()) {
            try {
                Thread.sleep(1000); // Wait for 1 second
                WaitedTime++;
            } catch (InterruptedException e) {
                System.out.println("Car " + CarId + " from Gate " + GateNum + " was interrupted while waiting.");
                Thread.currentThread().interrupt();
                return;
            }
        }
        parkingLot.carIn(CarId, GateNum, Arrival, WaitedTime);
        try {
            Thread.sleep(Duration * 1000L); // Simulate parking duration
        } catch (InterruptedException e) {
            System.out.println("Car " + CarId + " was interrupted while parked.");
            Thread.currentThread().interrupt();
        }
        parkingLot.carOut(CarId, GateNum, Duration);
    }
}