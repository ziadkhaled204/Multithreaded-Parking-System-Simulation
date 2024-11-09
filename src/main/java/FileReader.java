import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;

public class FileReader {
    private static String filePath;

    private static void setFilePath(String _filePath) {
        filePath = _filePath;
    }

    private static ArrayList<CarData> parseAll() {
        ArrayList<CarData> carDataList = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new java.io.FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(", ");
                int gate = Integer.parseInt(parts[0].split(" ")[1]);
                int carId = Integer.parseInt(parts[1].split(" ")[1]);
                int arrivalTime = Integer.parseInt(parts[2].split(" ")[1]);
                int parkingDuration = Integer.parseInt(parts[3].split(" ")[1]);

                CarData carData = new CarData(gate, carId, arrivalTime, parkingDuration);
                carDataList.add(carData);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return carDataList;
    }

    public static ArrayList<CarData> parseAll(String filePath) {
        setFilePath(filePath);
        return parseAll();
    }

}
