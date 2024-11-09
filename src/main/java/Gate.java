import java.util.List;

public class Gate implements Runnable{
    private List<Car> Cars;
    private ParkingLot parkingLot;
    public Gate(List<Car> Cars , ParkingLot parkingLot)
    {
        this.Cars = Cars;
        this.parkingLot = parkingLot;
    }
    @Override
    public void run() {
        for (Car car : Cars)
        {
            new Thread(car).start();
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
