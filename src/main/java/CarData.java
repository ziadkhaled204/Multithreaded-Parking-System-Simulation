public class CarData {
    private int gate;
    private int carId;
    private int arrivalTime;
    private int parkingDuration;

    public CarData(int gate, int carId, int arrivalTime, int parkingDuration) {
        this.gate = gate;
        this.carId = carId;
        this.arrivalTime = arrivalTime;
        this.parkingDuration = parkingDuration;
    }

    public int getGate() {
        return gate;
    }

    public int getCarId() {
        return carId;
    }

    public int getArrivalTime() {
        return arrivalTime;
    }

    public int getParkingDuration() {
        return parkingDuration;
    }
}
