
public class Main {
    public static void main(String[] args) {
        CarDataList carDataList = new CarDataList();
        carDataList.assignList( FileReader.parseAll("/home/mohab/Desktop/file.txt") );
        carDataList.print();
    }
}