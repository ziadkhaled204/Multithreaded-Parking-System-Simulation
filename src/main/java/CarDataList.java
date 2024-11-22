import java.util.ArrayList;

public class CarDataList {
    private ArrayList<CarData> cars = new ArrayList<>();

    public void assignList(ArrayList<CarData> carDataList){
        cars = carDataList;
    }

    public void addCar(CarData car) {
        cars.add(car);
    }

    public void removeCar(int carIndex) {
        if (carIndex >= 0 && carIndex < cars.size()) {
            cars.remove(carIndex);
        }
    }

    public ArrayList<CarData> getCars() {
        return cars;
    }

    public CarData getCar(int carIndex) {
        if (carIndex >= 0 && carIndex < cars.size()) {
            return cars.get(carIndex);
        }
        return null;
    }

    public int getNumOfCars() {
        return cars.size();
    }

    public int getNumOfCarsFromGate(int index){
        int num = 0;
        for(int i=0 ; i<cars.size() ; i++){
            if(getCar(i).getGate() == index){
                num++;
            }
        }
        return num;
    }

    public void print() {
        if (cars.isEmpty()) {
            System.out.println("No cars in the list.");
        } else {
            for (CarData car : cars) {
                System.out.println("Car ID: " + car.getId());
                System.out.println("Gate: " + car.getGate());
                System.out.println("Arrival Time: " + car.getArrivalTime());
                System.out.println("Parking Duration: " + car.getParkingDuration());
                System.out.println("---------------------------");
            }
        }
    }

}
