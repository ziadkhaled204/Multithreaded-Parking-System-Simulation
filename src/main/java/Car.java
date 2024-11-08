
public class Car implements Runnable{
    private Gate gatenum;
    private String CarId;
    private int Arrival;
    private int duration;

    public Gate getGatenum() {
        return gatenum;
    }

    public int getArrival() {
        return Arrival;
    }

    public int getDuration() {
        return duration;
    }

    public String getCarId() {
        return CarId;
    }
    @Override
    public void run()
    {

    }
}
