public class CarData {
    private int gate;
    private int id;
    private int arrivalTime;
    private int parkingDuration;

    public CarData(int gate, int id, int arrivalTime, int parkingDuration) {
        this.gate = gate;
        this.id = id;
        this.arrivalTime = arrivalTime;
        this.parkingDuration = parkingDuration;
    }

    public int getGate() {
        return gate;
    }

    public int getId() {
        return id;
    }

    public int getArrivalTime() {
        return arrivalTime;
    }

    public int getParkingDuration() {
        return parkingDuration;
    }
}
