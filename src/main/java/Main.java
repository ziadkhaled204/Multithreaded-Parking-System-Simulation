public class Main {
    public static void main(String[] args) {
        CarDataList carDataList = new CarDataList();
        carDataList.assignList( FileReader.parseAll("/home/mohab/Desktop/file.txt") );
        Semaphore parkingSemaphore = new Semaphore(4);
        for(int i=0 ; i<carDataList.getNumOfCars() ; i++){
            Thread car = new Thread(new Car(carDataList.getCar(i) , parkingSemaphore));
            car.start();
        }
    }
}