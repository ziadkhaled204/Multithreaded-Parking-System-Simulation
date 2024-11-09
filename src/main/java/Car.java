
public class Car implements Runnable{
    private final int CarId;
    private final int GateNum;
    private final int Arrival;
    private final int Duration;
    private int WaitedTime = 0;

    public Car(int CarId,int GateNum, int Arrival, int Duration)
    {
        this.CarId=CarId;
        this.GateNum=GateNum;
        this.Arrival=Arrival;
        this.Duration=Duration;
    }
    @Override
    public void run()
    {
        while(!ParkingLot.check_if_there_avPlaces())
            {
                try {
                    Thread.sleep(1000);
                    WaitedTime++;
                } catch (InterruptedException e) {
                    System.out.println("Car " + CarId + " from Gate " + GateNum + " was interrupted while waiting.");
                    Thread.currentThread().interrupt();
                    return;
                }
            }
        ParkingLot.Car_In(CarId,GateNum,Arrival,WaitedTime);
        try {
                Thread.sleep(Duration * 1000L);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            ParkingLot.Car_Out(CarId,GateNum,Duration);
    }
}

