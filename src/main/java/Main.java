import java.io.File;
import java.io.FileNotFoundException;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        List<Car> cars = new ArrayList<>();
        String filename = "parking_simulation_data.txt";
        try{
            Scanner scanner = new Scanner(new File(filename));
            while(scanner.hasNextLine()) {
                String line;
                try{ line = scanner.nextLine();}catch(Exception e){System.err.println("File is empty");return;}
                String[] parts = line.split(", ");
                int Car_name = Integer.parseInt(parts[0].split(" ")[1]);
                int Car_gate = Integer.parseInt(parts[1].split(" ")[1]);
                int Car_arr_Time = Integer.parseInt(parts[2].split(" ")[1]);
                int Car_Par_Time = Integer.parseInt(parts[3].split(" ")[1]);
                cars.add(new Car(Car_name,Car_gate,Car_arr_Time,Car_Par_Time));
            }
        } catch (FileNotFoundException e) {

            System.err.println("File : "+filename+" not exist");
        }
    }

}